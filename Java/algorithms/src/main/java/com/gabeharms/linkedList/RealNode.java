package com.gabeharms.linkedList;


public class RealNode<T> extends Node<T>
{
  public RealNode()
  {
    super();
  }

  public RealNode(T value, Node<T> previous, Node<T> next)
  {
    super(value, previous, next);
  }

  public T getValue()
  {
    return value;
  }

  public Node<T> getPrevious()
  {
    return isNodeNull(previous) ? new NullNode<T>() : previous;
  }

  public Node<T> getNext()
  {
    return isNodeNull(next) ? new NullNode<T>() : next ;
  }

  public void setValue(T value)
  {
    this.value = value;
  }

  public void setPrevious(Node<T> previous)
  {
    this.previous = previous;
  }

  public void setNext(Node<T> next)
  {
    this.next = next;
  }

  public boolean hasNext()
  {
    return !isNodeNull(next);
  }

  public boolean hasPrevious()
  {
    return !isNodeNull(previous);
  }

  public boolean isNull()
  {
    return false;
  }

  private boolean isNodeNull(Node<T> node)
  {
    return (node == null) || node.isNull();
  }
}
