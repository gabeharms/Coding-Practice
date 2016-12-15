package com.gabeharms.linkedList;


public class HeadNode<T> extends Node<T>
{
  private T value;

  public HeadNode()
  {
    super(null, new NullNode<T>(), new NullNode<T>());
  }

  public HeadNode(Node<T> previous, Node<T> next)
  {
    super(null, new NullNode<T>(), next);
  }

  public T getValue()
  {
    return null;
  }

  public Node<T> getPrevious()
  {
    return new NullNode<T>();
  }

  public Node<T> getNext()
  {
    return (next == null) ? new NullNode<T>() : next;
  }

  public void setValue(T value)
  {
  }

  public void setPrevious(Node<T> previous)
  {
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
    return false;
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
