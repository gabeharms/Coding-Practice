package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for SortAlgorithm subclasses to extend.
 */
public abstract class GraphTraversalAlgorithmTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public GraphTraversalAlgorithmTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( GraphTraversalAlgorithmTest.class );
  }

  protected String[] nodeListToLabelArray(ArrayList<Node> nodes)
  {
    ArrayList<String> labels = new ArrayList<String>();
    for (Node node : nodes)
    {
      labels.add(node.getLabel());
    }
    return labels.toArray(new String[0]);
  }


  protected Graph oneNodeGraph()
  {
    Graph graph = new Graph();
    Vertex a = new Vertex("a");

    graph.addNode(a);

    return graph;
  }

  protected Graph twoNodeGraph()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");

    graph.addNode(vertexA);
    graph.addNode(vertexB);

    Edge edge1 = new Segment(vertexA, vertexB);

    graph.addEdge(edge1);

    return graph;
  }

  protected Graph threeNodeSimpleGraph()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);

    Edge edge1 = new Segment(vertexA, vertexB);
    Edge edge2 = new Segment(vertexB, vertexC);

    graph.addEdge(edge1);
    graph.addEdge(edge2);

    return graph;
  }

  protected Graph threeNodeComplexGraph()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);

    Edge edge1 = new Segment(vertexA, vertexB);
    Edge edge2 = new Segment(vertexA, vertexC);
    Edge edge3 = new Segment(vertexB, vertexC);

    graph.addEdge(edge1);
    graph.addEdge(edge2);
    graph.addEdge(edge3);

    return graph;
  }



  protected Graph sixNodeGraph()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");
    Node vertexD = new Vertex("d");
    Node vertexE = new Vertex("e");
    Node vertexF = new Vertex("f");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);
    graph.addNode(vertexD);
    graph.addNode(vertexE);
    graph.addNode(vertexF);


    Edge edge1 = new Segment(vertexA, vertexB);
    Edge edge2 = new Segment(vertexA, vertexC);
    Edge edge3 = new Segment(vertexB, vertexD);
    Edge edge4 = new Segment(vertexC, vertexD);
    Edge edge5 = new Segment(vertexD, vertexE);
    Edge edge6 = new Segment(vertexE, vertexF);

    graph.addEdge(edge1);
    graph.addEdge(edge2);
    graph.addEdge(edge3);
    graph.addEdge(edge4);
    graph.addEdge(edge5);
    graph.addEdge(edge6);

    return graph;
  }
}
