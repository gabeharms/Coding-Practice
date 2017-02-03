package com.gabeharms.graph;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Hashtable;
import java.util.ArrayDeque;

class BreadthFirstSearch extends GraphTraversalAlgorithm
{
  private Hashtable<String, VertexCostDecorator> verticies;
  private Queue<VertexCostDecorator> queue;
  private ArrayList<Node> path;

  public BreadthFirstSearch(Graph graph)
  {
    super(graph);
    this.queue = new ArrayDeque<VertexCostDecorator>();
    this.verticies = transformNodes(graph.getNodes());
    this.path = new ArrayList<Node>();
  }

  public ArrayList<Node> traverse(Node source)
  {
    enqueue(source.getLabel());
    while (queueIsNotEmpty())
    {
      visitVertex(dequeue());
    }
    return path;
  }

  private void visitVertex(VertexCostDecorator currentVertex)
  {
    updateAdjacencies(currentVertex);
    if (notVisited(currentVertex)) {
      addToPathAndMarkVisited(currentVertex);
    }
  }

  private void addToPathAndMarkVisited(VertexCostDecorator vertex)
  {
    addToPath(vertex);
    vertex.markVisited();
  }

  private void updateAdjacencies(VertexCostDecorator currentVertex)
  {
    for (Node adjacentNode : graph.adjacenciesFor((Node)currentVertex))
    {
      updateAdjacencyCostAndParentAndEnqueue(currentVertex, getVertex(adjacentNode.getLabel()));
    }
  }

  private void updateAdjacencyCostAndParentAndEnqueue(VertexCostDecorator currentVertex, VertexCostDecorator adjacentVertex)
  {
    if (notVisited(adjacentVertex))
    {
      enqueue(adjacentVertex.getLabel());
    }
  }

  private void enqueue(String vertexLabel)
  {
    queue.add(getVertex(vertexLabel));
  }

  private VertexCostDecorator getVertex(String vertexLabel)
  {
    return verticies.get(vertexLabel);
  }

  private VertexCostDecorator dequeue()
  {
    return queue.remove();
  }

  private Hashtable<String, VertexCostDecorator> transformNodes(ArrayList<Node> originalNodes)
  {
    Hashtable<String, VertexCostDecorator> newNodes = new Hashtable<String, VertexCostDecorator>();
    for (Node node : originalNodes)
    {
      newNodes.put(node.getLabel(), new VertexCostDecorator((Vertex)node, null, 0));
    }
    return newNodes;
  }

  private boolean queueIsNotEmpty()
  { 
    return !queue.isEmpty();
  }
  
  private boolean notVisited(VertexCostDecorator vertex)
  {
    return !vertex.isVisited();
  }

  private void addToPath(VertexCostDecorator vertex)
  {
    path.add((Node)vertex);
  }
}
