package com.gabeharms.graph;

import java.util.ArrayList;

public class VertexCostDecorator implements Node
{
  private Vertex vertex;
  private Node predecessor;
  private int cost;
  private boolean visited;

  public VertexCostDecorator()
  {
    this.vertex = null;
    this.predecessor = null;
    this.cost = 0;
    this.visited = false;
  }

  public VertexCostDecorator(Vertex vertex, Node predecessor, int cost)
  {
    this.vertex = vertex;
    this.predecessor = predecessor;
    this.cost = cost;
    this.visited = false;
  }

  public void setLabel(String label)
  {
    vertex.setLabel(label);
  }

  public String getLabel()
  {
    return vertex.getLabel();
  }

  public int getCost()
  {
    return cost;
  }

  public void setCost(int cost)
  {
    this.cost = cost;
  }

  public Node getPredecessor()
  {
    return predecessor;
  }

  public void setPredecessor(Node predecessor)
  {
    this.predecessor = predecessor;
  }

  public void markVisited()
  {
    visited = true;
  }

  public boolean isVisited()
  {
    return visited;
  }

  @Override
  public boolean equals(Object object)
  {
    if (object instanceof Node)
    {
      Node node = (Node)object;
      return getLabel().equals(node.getLabel());
    }
    else {
      return true;
    }
  }
}
