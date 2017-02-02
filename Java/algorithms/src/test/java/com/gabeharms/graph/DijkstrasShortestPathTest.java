package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for DijkstrasShortestPath.
 */
public class DijkstrasShortestPathTest
  extends GraphShortestPathAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public DijkstrasShortestPathTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( DijkstrasShortestPathTest.class );
  }

  /**
   * @method shortestPath
   * @context one node graph
   */
  public void testOneNodeGraph()
  {
    Graph graph = oneNodeGraph();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context two node graph
   */
  public void testTwoNodeGraph()
  {
    Graph graph = twoNodeGraph();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /*************************************************/
  /************ Three Node Graph 1 *****************/
  /*************************************************/

  /**
   * @method shortestPath
   * @context three node graph1 from A to B
   */
  public void testThreeNodeGraph1AToB()
  {
    Graph graph = threeNodeGraph1();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph1 from B to C
   */
  public void testThreeNodeGraph1BToC()
  {
    Graph graph = threeNodeGraph1();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph1 from A to C
   */
  public void testThreeNodeGraph1AToC()
  {
    Graph graph = threeNodeGraph1();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "b", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }


  /**
   * @method shortestPath
   * @context three node graph1 from C to B
   */
  public void testThreeNodeGraph1CToB()
  {
    Graph graph = threeNodeGraph1();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }


  /**
   * @method shortestPath
   * @context three node graph1 from C to A
   */
  public void testThreeNodeGraph1CToA()
  {
    Graph graph = threeNodeGraph1();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "b", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /*************************************************/
  /************ Three Node Graph 2 *****************/
  /*************************************************/

  /**
   * @method shortestPath
   * @context three node graph2 from A to B
   */
  public void testThreeNodeGraph2AToB()
  {
    Graph graph = threeNodeGraph2();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph2 from A to C
   */
  public void testThreeNodeGraph2AToC()
  {
    Graph graph = threeNodeGraph2();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph2 from C to A
   */
  public void testThreeNodeGraph2CToA()
  {
    Graph graph = threeNodeGraph2();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph2 from B to A
   */
  public void testThreeNodeGraph2BToA()
  {
    Graph graph = threeNodeGraph2();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph2 from C to B
   */
  public void testThreeNodeGraph2CToB()
  {
    Graph graph = threeNodeGraph2();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph2 from B to C
   */
  public void testThreeNodeGraph2BToC()
  {
    Graph graph = threeNodeGraph2();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }


  /*************************************************/
  /************ Three Node Graph 3 *****************/
  /*************************************************/


  /**
   * @method shortestPath
   * @context three node graph3 from A to B
   */
  public void testThreeNodeGraph3AToB()
  {
    Graph graph = threeNodeGraph3();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph3 from A to C
   */
  public void testThreeNodeGraph3AToC()
  {
    Graph graph = threeNodeGraph3();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph3 from C to A
   */
  public void testThreeNodeGraph3CToA()
  {
    Graph graph = threeNodeGraph3();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph3 from B to A
   */
  public void testThreeNodeGraph3BToA()
  {
    Graph graph = threeNodeGraph3();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph3 from C to B
   */
  public void testThreeNodeGraph3CToB()
  {
    Graph graph = threeNodeGraph3();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "b"};
    //assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph3 from B to C
   */
  public void testThreeNodeGraph3BToC()
  {
    Graph graph = threeNodeGraph3();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "c"};
    //assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /*************************************************/
  /************ Three Node Graph 4 *****************/
  /*************************************************/

  /**
   * @method shortestPath
   * @context three node graph4 from A to B
   */
  public void testThreeNodeGraph4AToB()
  {
    Graph graph = threeNodeGraph4();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph4 from A to C
   */
  public void testThreeNodeGraph4AToC()
  {
    Graph graph = threeNodeGraph4();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("a");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph4 from C to A
   */
  public void testThreeNodeGraph4CToA()
  {
    Graph graph = threeNodeGraph4();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph4 from B to A
   */
  public void testThreeNodeGraph4BToA()
  {
    Graph graph = threeNodeGraph4();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("a");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph4 from C to B
   */
  public void testThreeNodeGraph4CToB()
  {
    Graph graph = threeNodeGraph4();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("c");
    Node endingNode = graph.getNode("b");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"c", "a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method shortestPath
   * @context three node graph4 from B to C
   */
  public void testThreeNodeGraph4BToC()
  {
    Graph graph = threeNodeGraph4();
    GraphShortestPathAlgorithm shortestPathAlgorithm = new DijkstrasShortestPath(graph);
    Node startingNode = graph.getNode("b");
    Node endingNode = graph.getNode("c");

    ArrayList<Node> actualResult = shortestPathAlgorithm.shortestPath(startingNode, endingNode);

    String[] expectedResult = {"b", "a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }
}
