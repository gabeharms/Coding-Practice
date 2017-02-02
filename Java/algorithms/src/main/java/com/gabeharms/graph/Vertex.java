package com.gabeharms.graph;

import java.util.ArrayList;

public class Vertex implements Node
{
  private String label;

  public Vertex()
  {
    this.label = "";
  }

  public Vertex(String label)
  {
    this.label = label;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getLabel()
  {
    return label;
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
