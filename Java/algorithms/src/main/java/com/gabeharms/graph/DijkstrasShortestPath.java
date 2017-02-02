package com.gabeharms.graph;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Hashtable;
import java.util.ArrayDeque;

class DijkstrasShortestPath extends GraphShortestPathAlgorithm
{
  private Hashtable<String, VertexCostDecorator> nodes;
  private Hashtable<VertexCostDecorator, VertexCostDecorator> predeccessors;
  private ArrayList<VertexCostDecorator> settled;
  private ArrayList<VertexCostDecorator> unsettled;

  public DijkstrasShortestPath(Graph graph)
  {
    super(graph);
    this.settled = new ArrayList<VertexCostDecorator>();
    this.unsettled = new ArrayList<VertexCostDecorator>();
    this.predeccessors = new Hashtable<VertexCostDecorator, VertexCostDecorator>();
    this.nodes = transformNodes(graph.getNodes());
  }

  public ArrayList<Node> shortestPath(Node source, Node destination)
  {
    unsettled.add(nodes.get(source.getLabel()));
    nodes.get(source.getLabel()).setCost(0);
    while(unsettled.size() != 0)
    {
      VertexCostDecorator vertex = getMinimumCostVertex(unsettled);
      System.out.print("Current Vertex: "); System.out.println(vertex.getLabel());
      unsettled.remove(vertex);
      settled.add(vertex);
      findMinimumDistancesOfAdjacencies(vertex);
    }
   return getPath(nodes.get(destination.getLabel()));
  }


  private VertexCostDecorator getMinimumCostVertex(ArrayList<VertexCostDecorator> verticies)
  {
    VertexCostDecorator minimum = null;
    for (VertexCostDecorator vertex : verticies)
    {
      if (minimum == null || vertex.getCost() > minimum.getCost())
      {
        minimum = vertex;
      }
    }
    return minimum;
  }

  private void findMinimumDistancesOfAdjacencies(VertexCostDecorator vertex)
  {
    for(Node adjacentNode : graph.adjacenciesFor((Node)vertex))
    {
      VertexCostDecorator adjacentVertex = nodes.get(adjacentNode.getLabel());
      System.out.print("Adjacent Vertex: "); System.out.print(adjacentVertex.getLabel()); System.out.print(". Current Cost: "); System.out.print(adjacentVertex.getCost()); System.out.print(". Cost to vertex: "); System.out.println(vertex.getCost() + edgeCostBetween(vertex, adjacentVertex));
      if (adjacentVertex.getCost() > (vertex.getCost() + edgeCostBetween(vertex, adjacentVertex)))
      {
        adjacentVertex.setCost(vertex.getCost() + edgeCostBetween(vertex, adjacentVertex));
        predeccessors.put(adjacentVertex, vertex);
        unsettled.add(adjacentVertex);
      }
    }
  }

  private int edgeCostBetween(VertexCostDecorator a, VertexCostDecorator b)
  {
    return ((SegmentCostDecorator)(graph.getEdgeFor((Node)a, (Node)b))).getCost();
  }

  private Hashtable<String, VertexCostDecorator> transformNodes(ArrayList<Node> originalNodes)
  {
    int count = 0;
    Hashtable<String, VertexCostDecorator> newNodes = new Hashtable<String, VertexCostDecorator>();
    for (Node node : originalNodes)
    {
      newNodes.put(node.getLabel(), new VertexCostDecorator((Vertex)node, null, Integer.MAX_VALUE));
    }
    return newNodes;
  }

  private ArrayList<Node> getPath(VertexCostDecorator destination)
  {
    ArrayList<Node> result = new ArrayList<Node>();
    VertexCostDecorator currentVertex = destination;
    System.out.print("Predecessors: "); System.out.println(predeccessors);
    while (currentVertex != null)
    {
      result.add((Node)currentVertex);
      currentVertex = predeccessors.get(currentVertex);
    }
    Collections.reverse(result);
    return result;
  }
}
