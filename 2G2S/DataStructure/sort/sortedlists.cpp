#include <iostream>
#include <fstream>
#include <vector>

using namespace std;

class node;

void insertNode(node *_nodeInsert);
node *popNode(void);
void printVec(vector<node *> vectorPrint);

vector<node *> nodeHeap;

ifstream fileInput("lists.in");
ofstream fileOutput("lists.out");

class node
{
private:
    int value;

public:
    node(int _value)
    {
        this->value = _value;
    }

    int getVal()
    {
        return value;
    }
};

void insertNode(node *_nodeInsert)
{
    int heapIndex;

    heapIndex = nodeHeap.size();
    nodeHeap.push_back(NULL);

    while ((heapIndex != 1) && (_nodeInsert->getVal() < nodeHeap[heapIndex / 2]->getVal()))
    {
        nodeHeap[heapIndex] = nodeHeap[heapIndex / 2];
        heapIndex /= 2;
    }

    nodeHeap[heapIndex] = _nodeInsert;
}

node *popNode(void)
{
    node *nodePop, *tempNode;
    int parent, child;

    nodePop = *(nodeHeap.begin() + 1);
    tempNode = *(nodeHeap.end() - 1);

    parent = 1;
    child = 2;

    while (child <= nodeHeap.size() - 1)
    {
        if ((child < nodeHeap.size() - 1) && (nodeHeap[child]->getVal() > nodeHeap[child + 1]->getVal()))
        {
            child += 1;
        }

        if (tempNode->getVal() <= nodeHeap[child]->getVal())
        {
            break;
        }

        nodeHeap[parent] = nodeHeap[child];

        parent = child;
        child *= 2;
    }

    nodeHeap[parent] = tempNode;

    nodeHeap.pop_back();

    return nodePop;
}

void printVec(vector<node *> vectorPrint)
{
    int vecSize = vectorPrint.size();
    for (int i = 1; i < vecSize; i++)
    {
        fileOutput << popNode()->getVal();
        if (i < vecSize - 1)
        {
            fileOutput << " ";
        }
    }
}

int main(void)
{
    int data;

    fileInput >> data;

    node *rootNode = new node(data);

    nodeHeap.push_back(NULL);
    nodeHeap.push_back(rootNode);

    while (!fileInput.eof())
    {
        data = NULL;
        fileInput >> data;

        if (data == NULL)
        {
            break;
        }

        node *nodeInsert = new node(data);
        insertNode(nodeInsert);
    }

    fileInput.close();

    printVec(nodeHeap);

    fileOutput.close();
}