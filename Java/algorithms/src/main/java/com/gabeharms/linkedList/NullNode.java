package com.gabeharms.linkedList;


public class NullNode<T> extends Node<T>
{
  private T value;

  public NullNode()
  {
    super();
  }

  public NullNode(T value, Node<T> previous, Node<T> next)
  {
    super(null, null, null);
  }

  public T getValue()
  {
    return null;
  }

  public Node<T> getPrevious()
  {
    return this;
  }

  public Node<T> getNext()
  {
    return this;
  }

  public void setValue(T value)
  {
  }

  public void setPrevious(Node<T> previous)
  {
  }

  public void setNext(Node<T> next)
  {
  }

  public boolean hasNext()
  {
    return false;
  }

  public boolean hasPrevious()
  {
    return false;
  }

  public boolean isNull()
  {
    return true;
  }
}
