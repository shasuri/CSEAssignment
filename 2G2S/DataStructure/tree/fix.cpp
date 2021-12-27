#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <cmath>
#include <vector>
#include <map>

using namespace std;

string expr = "";
map<char, int> varTable;

bool isElem(char inputChar)
{
    int cvtChar = (int)inputChar;
    if ((cvtChar >= 48 && cvtChar <= 57) || (cvtChar >= 97 && cvtChar <= 122))
    {
        return true;
    }
    else
    {
        return false;
    }
}

bool isNum(char inputChar)
{
    int cvtChar = (int)inputChar;
    if ((cvtChar >= 48 && cvtChar <= 57))
    {
        return true;
    }
    else
    {
        return false;
    }
}

class position
{
protected:
    position *left = NULL;
    position *right = NULL;
    position *parent = NULL;
    bool thisLeft = true;

public:
    virtual bool isOper() = 0;
    virtual void makeExp() = 0;
    virtual float calc() = 0;
    /*
    position *getLeft();
    position *getRight();
    void setLeft(position *posSet);
    void setRight(position *posSet);
    */
    position *getLeft(void)
    {
        return left;
    }

    position *getRight(void)
    {
        return right;
    }

    position *getParent(void)
    {
        return parent;
    }

    void setLeft(position *posSet)
    {
        this->left = posSet;
        posSet->parent = this;
    }

    void setRight(position *posSet)
    {
        this->right = posSet;
        posSet->thisLeft = false;
        posSet->parent = this;
    }

    void add(position *posAdd)
    {
        if (left == NULL)
        {
            setLeft(posAdd);
        }
        else if (right == NULL)
        {
            setRight(posAdd);
        }
        else if (left->isOper())
        {
            left->add(posAdd);
        }
        else if (right->isOper())
        {
            right->add(posAdd);
        }
    }

    void swap(position *posSwap)
    {
        posSwap->left = left;
        posSwap->right = right;

        if (thisLeft == true)
        {
            parent->left = posSwap;
        }
        else
        {
            parent->right = posSwap;
        }
    }
};

class numPos : public position
{
private:
    int value;

public:
    numPos(int _value)
    {
        this->value = _value;
    }

    bool isOper(void)
    {
        return false;
    }

    void makeExp(void)
    {
        expr = expr + to_string(value);
    }

    float calc(void)
    {
        return value;
    }
};

class charPos : public position
{
private:
    char value;

public:
    charPos(char _value)
    {
        this->value = _value;
    }

    bool isOper(void)
    {
        return false;
    }

    void makeExp(void)
    {
        expr.push_back(value);
        numPos posiSwap = numPos(varTable[value]);
        swap(&posiSwap);
    }

    float calc(void)
    {
        return 0;
    }
};

class operPos : public position
{
private:
    char value;

public:
    operPos(char _value)
    {
        this->value = _value;
    }

    bool isOper(void)
    {
        return true;
    }

    void makeExp(void)
    {
        expr.push_back('(');
        left->makeExp();
        expr.push_back(value);
        right->makeExp();
        expr.push_back(')');
    }

    float calc(void)
    {
        if (value == '+')
        {
            return (left->calc() + right->calc());
        }
        else if (value == '-')
        {
            return (left->calc() - right->calc());
        }
        else if (value == '*')
        {
            return (left->calc() * right->calc());
        }
        else if (value == '/')
        {
            return (left->calc() / right->calc());
        }
    }
};

class tree
{
protected:
    operPos *root;

public:
    //void add(position *posAdd);

    tree(void)
    {
    }

    void setRoot(operPos *_root)
    {
        this->root = _root;
    }

    void add(position *posAdd)
    {
        root->add(posAdd);
    }

    void makeExp(void)
    {
        root->makeExp();
    }

    float calc(void)
    {
        return (round(root->calc()));
    }
};

int main(void)
{
    int testCaseNum;
    string singleLine;

    ifstream inputFile("fix.in");
    ofstream outputFile("fix.out");

    while (!inputFile.eof())
    {
        getline(inputFile, singleLine);

        testCaseNum = stoi(singleLine);

        for (int testId = 0; testId < testCaseNum; testId++)
        {
            char rootChar;
            char typeChar;
            char singleChar;

            getline(inputFile, singleLine);

            istringstream ss(singleLine);
            string stringBuffer;
            vector<string> stringSplited;
            stringSplited.clear();

            while (getline(ss, stringBuffer, ' '))
            {
                stringSplited.push_back(stringBuffer);
            }

            int inputLen = stringSplited.size();

            typeChar = (char)(stringSplited[0].c_str()[0]);
            tree singleTree = tree();

            if (typeChar == 'I')
            {

                rootChar = (char)(stringSplited[1].c_str()[0]);
                operPos rootVertex = operPos(rootChar);
                singleTree.setRoot(&rootVertex);

                for (int i = 2; i < inputLen - 1; i++)
                {
                    singleChar = (char)(stringSplited[i].c_str()[0]);

                    if (isElem(singleChar))
                    {
                        if (isNum(singleChar))
                        {
                            numPos *singlePos = new numPos(stoi(stringSplited[i]));

                            singleTree.add(singlePos);
                        }
                        else
                        {
                            charPos *singlePos = new charPos(singleChar);
                            singleTree.add(singlePos);
                        }
                    }

                    else
                    {

                        operPos *singlePos = new operPos(singleChar);
                        singleTree.add(singlePos);
                    }
                }
            }

            else if (typeChar == 'P')
            {
                rootChar = (char)(stringSplited[inputLen - 2].c_str()[0]);

                operPos rootVertex = operPos(rootChar);
                singleTree.setRoot(&rootVertex);

                for (int i = inputLen - 2; i > 1; i--)
                {
                    singleChar = (char)(stringSplited[i].c_str()[0]);

                    if (isElem(singleChar))
                    {
                        if (isNum(singleChar))
                        {
                            numPos singlePos = numPos(stoi(stringSplited[i]));
                            singleTree.add(&singlePos);
                        }
                        else
                        {
                            charPos singlePos = charPos(singleChar);
                            singleTree.add(&singlePos);
                        }
                    }

                    else
                    {
                        operPos singlePos = operPos(singleChar);
                        singleTree.add(&singlePos);
                    }
                }
            }

            getline(inputFile, singleLine);

            //istringstream ss(singleLine);
            //vector<string> stringSplited;
            stringSplited.clear();

            while (getline(ss, stringBuffer, ' '))
            {
                stringSplited.push_back(stringBuffer);
            }

            for (int j = 0; j < stringSplited.size() / 2; j++)
            {
                char varName = (char)(stringSplited[j * 2].c_str()[0]);
                int varVal = stoi(stringSplited[j * 2 + 1]);
                varTable.insert(pair<char, int>(varName, varVal));
            }

            singleTree.makeExp();
            outputFile << expr << endl;
            outputFile << singleTree.calc() << endl;
            expr.clear();
        }
    }

    inputFile.close();
    outputFile.close();
}
