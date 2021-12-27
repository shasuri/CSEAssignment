#include <iostream>
#include "LinkedStack.h"

using namespace std;

int main()
{
	cout << "StudentID Name" << endl
		 << endl;

	LinkedStack<char> stack1;
	stack1.push('A');
	stack1.push('C');
	stack1.push('D');
	stack1.push('E');
	stack1.push('F');
	stack1.push('G');

	cout << "Stack1 Test" << endl;
	cout << "size: " << stack1.size() << endl;
	cout << "top entry: " << stack1.top() << endl;
	stack1.pop();
	cout << "top entry: " << stack1.top() << endl;

	LinkedStack<int> stack2;
	stack2.push(1);
	stack2.push(2);
	stack2.push(3);
	stack2.push(4);
	stack2.push(5);

	cout << endl
		 << "Stack2 Test" << endl;
	while (!stack2.empty())
	{
		cout << "top entry: " << stack2.top() << endl;
		stack2.pop();
	}
}
