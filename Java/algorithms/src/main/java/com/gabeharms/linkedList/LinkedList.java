package com.gabeharms.linkedList;


public class LinkedList<T>
{
  private HeadNode<T> head;

  public LinkedList()
  {
    this.head = new HeadNode<T>();
  }

  public LinkedList(HeadNode<T> head)
  {
    this.head = (head == null) ? new HeadNode<T>() : head;
  }

  public int size()
  {
    int size = 0;
    Node<T> currentNode = head;
    while (currentNode.hasNext())
    {
      size++;
      currentNode = currentNode.getNext();
    }
    return size;
  }

  public Node<T> get(int index)
  {
    Node<T> currentNode = head.getNext();
    for (int i = 0; i < index; i++)
    {
      currentNode = currentNode.getNext();
    }
    return currentNode;
  }

  public void add(Node<T> node)
  {
    Node<T> lastElement = getLastNode();

    lastElement.setNext(node);
    node.setPrevious(lastElement);
  }

  private Node<T> getLastNode()
  {
    int listSize = size();
    if (listSize == 0) {
      return head;
    }
    else
    {
      return get(listSize - 1);
    }
  }

  public Node<T> remove(int index)
  {
    Node<T> nodeToRemove = get(index);
    Node<T> previousNode = nodeToRemove.getPrevious();
    Node<T> nextNode = nodeToRemove.getNext();

    previousNode.setNext(nextNode);
    nextNode.setPrevious(previousNode);

    return nodeToRemove;
  }
}
