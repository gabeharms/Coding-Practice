package com.gabeharms.graph;

import java.util.ArrayList;
import java.util.Hashtable;

public class Graph
{
  private Hashtable<String, Node> nodes;
  private ArrayList<Edge> edges;

  public Graph()
  {
    this.nodes = new Hashtable<String, Node>();
    this.edges = new ArrayList<Edge>();
  }

  public Graph(Hashtable<String, Node> nodes, ArrayList<Edge> edges)
  {
    this.nodes = nodes;
    this.edges = edges;
  }

  public ArrayList<Node> adjacenciesFor(Node node) {
    ArrayList<Node> adjacencies = new ArrayList<Node>();
    for (Edge edge : edges)
    {
      if (edge.includes(node)) {
        adjacencies.add(edge.getAdjacentTo(node));
      }
    }
    return adjacencies;
  }

  public void addNode(Node newNode)
  {
    nodes.put(newNode.getLabel(), newNode);
  }

  public void addEdge(Edge edge)
  {
    edges.add(edge);
  }

  public Edge getEdgeFor(Node a, Node b)
  {
    for (Edge edge : edges)
    {
      if (edge.includes(a) && edge.includes(b)) {
        return edge;
      }
    }
    return edges.get(0);
  }

  public ArrayList<Node> getNodes()
  {
    return new ArrayList<Node>(nodes.values());
  }

  public Node getNode(String label)
  {
    return nodes.get(label);
  }
}
