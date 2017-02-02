package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for SortAlgorithm subclasses to extend.
 */
public abstract class GraphShortestPathAlgorithmTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public GraphShortestPathAlgorithmTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( GraphShortestPathAlgorithmTest.class );
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

    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), 2);

    graph.addEdge(edge1);

    return graph;
  }

  protected Graph threeNodeGraph1()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);

    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), 5);
    Edge edge2 = new SegmentCostDecorator(new Segment(vertexB, vertexC), 10);

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

    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), Integer.MAX_VALUE);
    Edge edge2 = new SegmentCostDecorator(new Segment(vertexA, vertexC), Integer.MAX_VALUE);
    Edge edge3 = new SegmentCostDecorator(new Segment(vertexB, vertexC), Integer.MAX_VALUE);

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


    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), Integer.MAX_VALUE);
    Edge edge2 = new SegmentCostDecorator(new Segment(vertexA, vertexC), Integer.MAX_VALUE);
    Edge edge3 = new SegmentCostDecorator(new Segment(vertexB, vertexD), Integer.MAX_VALUE);
    Edge edge4 = new SegmentCostDecorator(new Segment(vertexC, vertexD), Integer.MAX_VALUE);
    Edge edge5 = new SegmentCostDecorator(new Segment(vertexD, vertexE), Integer.MAX_VALUE);
    Edge edge6 = new SegmentCostDecorator(new Segment(vertexE, vertexF), Integer.MAX_VALUE);

    graph.addEdge(edge1);
    graph.addEdge(edge2);
    graph.addEdge(edge3);
    graph.addEdge(edge4);
    graph.addEdge(edge5);
    graph.addEdge(edge6);

    return graph;
  }

  protected Graph threeNodeGraph2()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);

    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), 5);
    Edge edge2 = new SegmentCostDecorator(new Segment(vertexA, vertexC), 10);

    graph.addEdge(edge1);
    graph.addEdge(edge2);

    return graph;
  }
  
  protected Graph threeNodeGraph3()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);

    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), 5);
    Edge edge2 = new SegmentCostDecorator(new Segment(vertexA, vertexC), 10);
    Edge edge3 = new SegmentCostDecorator(new Segment(vertexB, vertexC), 12);

    graph.addEdge(edge1);
    graph.addEdge(edge2);
    graph.addEdge(edge3);

    return graph;
  }

  protected Graph threeNodeGraph4()
  {
    Graph graph = new Graph();
    Node vertexA = new Vertex("a");
    Node vertexB = new Vertex("b");
    Node vertexC = new Vertex("c");

    graph.addNode(vertexA);
    graph.addNode(vertexB);
    graph.addNode(vertexC);

    Edge edge1 = new SegmentCostDecorator(new Segment(vertexA, vertexB), 5);
    Edge edge2 = new SegmentCostDecorator(new Segment(vertexA, vertexC), 10);
    Edge edge3 = new SegmentCostDecorator(new Segment(vertexB, vertexC), 16);

    graph.addEdge(edge1);
    graph.addEdge(edge2);
    graph.addEdge(edge3);

    return graph;
  }
}
