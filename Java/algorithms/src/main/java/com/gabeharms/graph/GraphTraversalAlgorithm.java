package com.gabeharms.graph;

import java.util.ArrayList;

abstract class GraphTraversalAlgorithm
{
  protected Graph graph;
  abstract ArrayList<Node> traverse(Node source);

  public GraphTraversalAlgorithm(Graph graph)
  {
    this.graph = graph;
  }

  public static void main(String[] args) {

  }
}
