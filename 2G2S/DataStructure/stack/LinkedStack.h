#include <string>
#include <stdexcept>
#include "SLinkedList.h"

using namespace std;

template <typename E>
// typedef string E;
class LinkedStack
{
public:
    LinkedStack();
    int size() const;
    bool empty() const;
    const E &top() const throw(runtime_error);
    void push(const E &e);
    void pop() throw(runtime_error);

private:
    SLinkedList<E> S;
    int n;
};

template <typename E>
LinkedStack<E>::LinkedStack()
    : S(), n(0) {}

template <typename E>
int LinkedStack<E>::size() const
{
    return n;
}

template <typename E>
bool LinkedStack<E>::empty() const
{
    return n == 0;
}

template <typename E>
const E &LinkedStack<E>::top() const throw(runtime_error)
{
    if (empty())
        throw runtime_error("Top of empty stack");
    return S.front();
}

template <typename E>
void LinkedStack<E>::push(const E &e)
{
    ++n;
    S.addFront(e);
}

template <typename E>
void LinkedStack<E>::pop() throw(runtime_error)
{
    if (empty())
        throw runtime_error("Pop from empty stack");
    --n;
    S.removeFront();
}
