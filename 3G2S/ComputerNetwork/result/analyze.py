import os
import xml.etree.ElementTree as elemTree
import matplotlib.pyplot as plt



def getAvg(l: list) : # param must be list
    return sum(l) / len(l)

def getYdata(data: list,tcpType: str,valueType: str) :

    values = next(x[valueType] for x in data if x['tcpVariant'] == tcpType)
    ydata = []

    for i in errorRateList :
        ydata.append(values[i])

    return ydata

def showPlot(data: list) :
    xdata = errorRateList
    
    for valueType in valueTypeList :
        for tcpType in tcpTypeList :
            ydata = getYdata(data,tcpType,valueType)
            plt.plot(xdata,ydata,'o-',label=tcpType)
        
        plt.xlabel("Error rate")
        if valueType == "delaySumAvg":
            plt.ylabel("sec")
        else :
            plt.ylabel("(%)")
        
        plt.legend()
        plt.title(valueType)
        plt.show()

if __name__ == "__main__" : 

    path = "./"
    fileList = os.listdir(path)

    dataList = []
    tcpTypeList = []
    valueTypeList = ["dataLossAvg","packetLossAvg","delaySumAvg"]
    errorRateList = ["0.01","0.05","0.1"]

    for file in fileList:
        fileSplited = file.split('.') # split to file name and ext
        if(fileSplited[-1] == "flowmonitor"): # analyze only .flowmonitor(.xml)

            fileNameSplited = fileSplited[0].split('_') # split to variant and error rate
            tcpVariant = fileNameSplited[1] # get variant
            packetErrorRate = "0." + fileNameSplited[2] # get error rate

            print("* " + file)
            print("* TCP Varient : " + tcpVariant)
            print("* Packet error rate : " + packetErrorRate)

            tree = elemTree.parse(path + file)
            flowStats = tree.find("FlowStats") # get FlowStats tag
            flowList = flowStats.findall("Flow") # get Flow tag in FlowStats

            dataLossList = []
            packetLossList = []
            delaySumList = []


            tcpGen = (item for item in dataList if item['tcpVariant'] == tcpVariant)
            isTcp = next(tcpGen,False)
            dataParsed = None     

            if isTcp :
                dataParsed = isTcp
            
            else :
                dataParsed = {
                    "tcpVariant" : tcpVariant,
                    "dataLossAvg" : {},
                    "packetLossAvg" : {},
                    "delaySumAvg" : {}
                }
                dataList.append(dataParsed)
                tcpTypeList.append(tcpVariant)
            
            for flow in flowList:
                txBytes = int(flow.get("txBytes")) # get txBytes attribute in Flow
                rxBytes = int(flow.get("rxBytes")) # get rxBytes attribute in Flow
                dataLoss = (txBytes - rxBytes) / txBytes * 100 # calc data losses percentage
                dataLossList.append(dataLoss)

                lostPackets = int(flow.get("lostPackets")) # get lostPackets attribute in Flow
                txPackets = int(flow.get("txPackets")) # get txPackets attribute in Flow
                packetLoss = lostPackets / txPackets * 100 # calc packet losses percentage
                packetLossList.append(packetLoss)
                
                delaySum = float(flow.get("delaySum")[:-2]) # get delaySum attribute in Flow and form to float
                delaySumList.append(delaySum)

                print("  DataLoss(%) : " + str(dataLoss))
                print("  PacketLoss(%) : " + str(packetLoss))
                print("  DelaySum : " + str(delaySum))

                print("----------------------------------")

            dataLossAvg = getAvg(dataLossList) # get averages
            packetLossAvg = getAvg(packetLossList)
            delaySumAvg = getAvg(delaySumList)

            dataParsed["dataLossAvg"][packetErrorRate] = dataLossAvg
            dataParsed["packetLossAvg"][packetErrorRate] = packetLossAvg
            dataParsed["delaySumAvg"][packetErrorRate] = delaySumAvg

            print(f"Data loss average(%) : {dataLossAvg:.2f}")
            print(f"Packet loss average(%) : {packetLossAvg:.2f}")
            print(f"Delay sum average : {delaySumAvg/10**9:.2f}")

            print("*********************************\n")
    showPlot(dataList)
    # print(dataList)
        
