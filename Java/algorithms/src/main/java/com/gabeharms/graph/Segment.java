package com.gabeharms.graph;


public class Segment implements Edge
{
  private Node a;
  private Node b;

  public Segment()
  {
  }

  public Segment(Node a, Node b)
  {
    this.a = a;
    this.b = b;
  }

  public void setVerticies(Node a, Node b)
  {
    this.a = a;
    this.b = b;
  }

  public Node getA()
  {
    return a;
  }

  public Node getB()
  {
    return b;
  }

  public boolean includes(Node node)
  {
    if (getA().equals(node) || getB().equals(node))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public Node getAdjacentTo(Node node)
  {
    if (getA().equals(node))
    {
      return getB();
    }
    else if (getB().equals(node))
    {
      return getA();
    }
    else
    {
      return null;
    }
  }
}
