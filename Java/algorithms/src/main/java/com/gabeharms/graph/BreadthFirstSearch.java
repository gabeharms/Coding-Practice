package com.gabeharms.graph;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Hashtable;
import java.util.ArrayDeque;

class BreadthFirstSearch extends GraphTraversalAlgorithm
{
  private Hashtable<String, VertexCostDecorator> verticies;
  private Queue<VertexCostDecorator> queue;
  private ArrayList<Node> result;
  private int cost;

  public BreadthFirstSearch(Graph graph)
  {
    super(graph);
    this.queue = new ArrayDeque<VertexCostDecorator>();
    this.verticies = transformNodes(graph.getNodes());
    this.result = new ArrayList<Node>();
    this.cost = 0;
  }

  public ArrayList<Node> traverse(Node source)
  {
    int currentCost = 0;
    VertexCostDecorator currentNode;
    enqueue(source.getLabel());
    while (queueIsNotEmpty())
    {
      currentNode = dequeue();
      for (Node adjacentNode : graph.adjacenciesFor(currentNode))
      {
        VertexCostDecorator adjacentVertex = verticies.get(adjacentNode.getLabel());
        if (!adjacentVertex.isVisited())
        {
          adjacentVertex.setCost(currentNode.getCost() + 1);
          adjacentVertex.setPredecessor((Node)currentNode);
          enqueue(adjacentVertex.getLabel());
        }
      }
      if (!currentNode.isVisited()) {
        result.add((Node)currentNode);
      }
      currentNode.markVisited();
    }
    return result;
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
}
