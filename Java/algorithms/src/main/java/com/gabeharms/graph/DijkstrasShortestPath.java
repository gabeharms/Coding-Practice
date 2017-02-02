package com.gabeharms.graph;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Hashtable;
import java.util.ArrayDeque;

class DijkstrasShortestPath extends GraphShortestPathAlgorithm
{
  private Hashtable<String, VertexCostDecorator> costVerticies;
  private Hashtable<VertexCostDecorator, VertexCostDecorator> predeccessors;
  private ArrayList<VertexCostDecorator> settled;
  private ArrayList<VertexCostDecorator> unsettled;

  public DijkstrasShortestPath(Graph graph)
  {
    super(graph);
    this.settled = new ArrayList<VertexCostDecorator>();
    this.unsettled = new ArrayList<VertexCostDecorator>();
    this.predeccessors = new Hashtable<VertexCostDecorator, VertexCostDecorator>();
    this.costVerticies = transformNodesToVerticies(graph.getNodes());
  }

  public ArrayList<Node> shortestPath(Node source, Node destination)
  {
    setCost(source.getLabel(), 0);
    addToUnsettled(source.getLabel());
    while(unsettledVerticesExist())
    {
      updateAdjancentVertexDistancesAndSettle(getMinimumCostUnsettledVertex());
    }
    return getPathTo(getVertex(destination.getLabel()));
  }

  private void updateAdjancentVertexDistancesAndSettle(VertexCostDecorator currentVertex)
  {
    updateMinimumDistancesOfAdjacencies(currentVertex);
    removeFromUnsettled(currentVertex);
    addToSettled(currentVertex);
  }

  private VertexCostDecorator getMinimumCostUnsettledVertex()
  {
    VertexCostDecorator minimum = null;
    for (VertexCostDecorator unsettledVertex : unsettled)
    {
      minimum = lowerCostBetween(minimum, unsettledVertex);
    }
    return minimum;
  }

  private void updateMinimumDistancesOfAdjacencies(VertexCostDecorator currentVertex)
  {
    for(Node adjacentNode : graph.adjacenciesFor((Node)currentVertex))
    {
      updateMinimumDistancesOfAdjacentVertex(currentVertex, getVertex(adjacentNode.getLabel()));
    }
  }

  private void updateMinimumDistancesOfAdjacentVertex(VertexCostDecorator currentVertex, VertexCostDecorator adjacentVertex)
  {
    int costThroughCurrentVertex =  costThroughVertex(currentVertex, adjacentVertex);
    if (currentCost(adjacentVertex) > costThroughCurrentVertex)
    {
      updateAdjacencyCostAndParentAndUnsettle(currentVertex, adjacentVertex, costThroughCurrentVertex);
    }
  }

  private void updateAdjacencyCostAndParentAndUnsettle(VertexCostDecorator currentVertex, VertexCostDecorator adjacentVertex, int newCost)
  {
    adjacentVertex.setCost(newCost);
    setParent(adjacentVertex, currentVertex);
    addToUnsettled(adjacentVertex.getLabel());
  }

  private int costThroughVertex(VertexCostDecorator currentVertex, VertexCostDecorator adjacentVertex)
  {
    return currentVertex.getCost() + directCostBetween(currentVertex, adjacentVertex);
  }

  private int directCostBetween(VertexCostDecorator a, VertexCostDecorator b)
  {
    return ((SegmentCostDecorator)(graph.getEdgeFor((Node)a, (Node)b))).getCost();
  }

  private int edgeCostBetween(VertexCostDecorator a, VertexCostDecorator b)
  {
    return ((SegmentCostDecorator)(graph.getEdgeFor((Node)a, (Node)b))).getCost();
  }

  private Hashtable<String, VertexCostDecorator> transformNodesToVerticies(ArrayList<Node> originalNodes)
  {
    Hashtable<String, VertexCostDecorator> newVerticies = new Hashtable<String, VertexCostDecorator>();
    for (Node node : originalNodes)
    {
      newVerticies.put(node.getLabel(), new VertexCostDecorator((Vertex)node, null, Integer.MAX_VALUE));
    }
    return newVerticies;
  }

  private ArrayList<Node> getPathTo(VertexCostDecorator destination)
  {
    ArrayList<Node> result = new ArrayList<Node>();
    VertexCostDecorator currentVertex = destination;
    while (currentVertex != null)
    {
      result.add((Node)currentVertex);
      currentVertex = getParent(currentVertex);
    }
    Collections.reverse(result);
    return result;
  }

  private void addToUnsettled(String vertexLabel)
  {
    unsettled.add(getVertex(vertexLabel));
  }

  private VertexCostDecorator getVertex(String vertexLabel)
  {
    return costVerticies.get(vertexLabel);
  }

  private void setCost(String vertexLabel, int cost)
  {
    getVertex(vertexLabel).setCost(0);
  }

  private boolean unsettledVerticesExist()
  {
    return unsettled.size() != 0;
  }

  private void removeFromUnsettled(VertexCostDecorator vertex)
  {
    unsettled.remove(vertex);
  }

  private void addToSettled(VertexCostDecorator vertex)
  {
    settled.add(vertex);
  }

  private boolean notSet(VertexCostDecorator vertex)
  {
    return vertex == null;
  }

  private boolean costsLessThan(VertexCostDecorator a, VertexCostDecorator b)
  {
    return a.getCost() < b.getCost();
  }

  private void setParent(VertexCostDecorator a, VertexCostDecorator b)
  {
    predeccessors.put(a, b);
  }
  
  private VertexCostDecorator getParent(VertexCostDecorator vertex)
  {
    return predeccessors.get(vertex);
  }

  private VertexCostDecorator lowerCostBetween(VertexCostDecorator a, VertexCostDecorator b)
  {
    if (notSet(a) || costsLessThan(b, a))
    {
      return b;
    }
    else 
    {
      return a;
    }
  }

  private int currentCost(VertexCostDecorator vertex)
  {
    return vertex.getCost();
  }
}
