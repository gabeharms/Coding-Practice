package com.gabeharms.graph;


public interface Edge
{
  public void setVerticies(Node a, Node b);
  public Node getA();
  public Node getB();
  public boolean includes(Node node);
  public Node getAdjacentTo(Node node);
}
