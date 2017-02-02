package com.gabeharms.graph;

import java.util.ArrayList;

public class SegmentCostDecorator implements Edge
{
  private Segment segment;
  private int cost;

  public SegmentCostDecorator()
  {
    this.segment = null;
    this.cost = 0;
  }

  public SegmentCostDecorator(Segment segment, int cost)
  {
    this.segment = segment;
    this.cost = cost;
  }

  public void setVerticies(Node a, Node b) { segment.setVerticies(a, b); }
  public Node getA() { return segment.getA(); }
  public Node getB() { return segment.getB(); }
  public boolean includes(Node node) { return segment.includes(node); }
  public Node getAdjacentTo(Node node) { return segment.getAdjacentTo(node); }

  public void setCode(int cost)
  {
    this.cost = cost;
  }

  public int getCost()
  {
    return cost;
  }

}
