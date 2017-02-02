package com.gabeharms.graph;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Hashtable;

class DepthFirstSearch extends GraphTraversalAlgorithm
{
  private Hashtable<String, VertexCostDecorator> nodes;
  private Stack<VertexCostDecorator> stack;
  private ArrayList<Node> result;

  public DepthFirstSearch(Graph graph)
  {
    super(graph);
    this.stack = new Stack<VertexCostDecorator>();
    this.nodes = transformNodes(graph.getNodes());
    this.result = new ArrayList<Node>();
  }

  public ArrayList<Node> traverse(Node source)
  {
    int currentCost = 0;
    VertexCostDecorator currentVertex = nodes.get(source.getLabel());
    currentVertex.setCost(0);
    currentVertex.markVisited();
    push(currentVertex);
    result.add(currentVertex);
    while (!stack.empty())
    {
      currentVertex = top();
      VertexCostDecorator adjacentVertex = getAdjacentUnvisitedVertex(top());
      if (adjacentVertex == null)
      {
        pop();
      }
      else
      {
        adjacentVertex.markVisited();
        adjacentVertex.setCost(currentVertex.getCost() + 1);
        adjacentVertex.setPredecessor((Node)currentVertex);
        result.add(adjacentVertex);
        push(adjacentVertex);
      }
    }
    return result;
  }

  private VertexCostDecorator getAdjacentUnvisitedVertex(VertexCostDecorator vertex)
  {
    for (Node adjacentNode : graph.adjacenciesFor((Node)vertex)) {
      VertexCostDecorator adjacentVertex = nodes.get(adjacentNode.getLabel());
      if (!adjacentVertex.isVisited())
      {
        return adjacentVertex;
      }
    }
    return null;
  }

  private void push(VertexCostDecorator vertex)
  {
    stack.push(vertex);
  }

  private VertexCostDecorator pop()
  {
    return stack.pop();
  }

  private VertexCostDecorator top()
  {
    return stack.peek();
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
}
