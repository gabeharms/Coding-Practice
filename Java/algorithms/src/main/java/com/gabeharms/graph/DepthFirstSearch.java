package com.gabeharms.graph;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Hashtable;

class DepthFirstSearch extends GraphTraversalAlgorithm
{
  private Hashtable<String, VertexCostDecorator> verticies;
  private Stack<VertexCostDecorator> stack;
  private ArrayList<Node> path;

  public DepthFirstSearch(Graph graph)
  {
    super(graph);
    this.stack = new Stack<VertexCostDecorator>();
    this.verticies = transformNodes(graph.getNodes());
    this.path = new ArrayList<Node>();
  }

  public ArrayList<Node> traverse(Node source)
  {
    VertexCostDecorator currentVertex = verticies.get(source.getLabel());
    getVertex(source.getLabel()).markVisited();
    push(source.getLabel());
    addToPath(source.getLabel());
    while (stackNotEmpty())
    {
      traverseAdjacencies(top());
    }
    return path;
  }

  private void traverseAdjacencies(VertexCostDecorator currentVertex)
  {
      VertexCostDecorator adjacentVertex = getAdjacentUnvisitedVertex(currentVertex);
      if (noAdjacencyExists(adjacentVertex))
      {
        popCurrentVertex();
      }
      else
      {
        visitAdjacentVertex(currentVertex, adjacentVertex);
      }
  }

  private void visitAdjacentVertex(VertexCostDecorator currentVertex, VertexCostDecorator adjacentVertex)
  {

    adjacentVertex.markVisited();
    addToPath(adjacentVertex.getLabel());
    push(adjacentVertex.getLabel());
  }

  private VertexCostDecorator getAdjacentUnvisitedVertex(VertexCostDecorator vertex)
  {
    for (Node adjacentNode : graph.adjacenciesFor((Node)vertex)) {
      VertexCostDecorator adjacentVertex = verticies.get(adjacentNode.getLabel());
      if (!adjacentVertex.isVisited())
      {
        return adjacentVertex;
      }
    }
    return null;
  }

  private void push(String vertexLabel)
  {
    stack.push(getVertex(vertexLabel));
  }

  private VertexCostDecorator getVertex(String vertexLabel)
  {
    return verticies.get(vertexLabel);
  }

  private void popCurrentVertex()
  {
    pop();
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
  
  private void addToPath(String vertexLabel)
  {
    path.add(getVertex(vertexLabel));
  }
  
  private boolean stackNotEmpty()
  {
    return !stack.empty();
  }

  private boolean noAdjacencyExists(VertexCostDecorator vertex)
  {
    return vertex == null;
  }
}
