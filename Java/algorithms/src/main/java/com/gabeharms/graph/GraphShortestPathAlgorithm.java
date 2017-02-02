package com.gabeharms.graph;

import java.util.ArrayList;

abstract class GraphShortestPathAlgorithm
{
  protected Graph graph;
  abstract ArrayList<Node> shortestPath(Node source, Node destination);

  public GraphShortestPathAlgorithm(Graph graph)
  {
    this.graph = graph;
  }

  public static void main(String[] args) {

  }
}
