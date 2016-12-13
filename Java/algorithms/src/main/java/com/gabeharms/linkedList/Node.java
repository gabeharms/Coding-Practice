package com.gabeharms.linkedList;


public abstract class Node<T>
{
  protected T value;
  protected Node<T> previous;
  protected Node<T> next;

  public Node()
  {

  }

  public Node(T value, Node<T> previous, Node<T> next)
  {
    this.value = value;
    this.previous = previous;
    this.next = next;
  }

  abstract T getValue();
  abstract Node<T> getPrevious();
  abstract Node<T> getNext();
  abstract void setValue(T value);
  abstract void setPrevious(Node<T> previous);
  abstract void setNext(Node<T> next);
  abstract boolean hasNext();
  abstract boolean hasPrevious();
  abstract boolean isNull();
}
