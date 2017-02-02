package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit test for BreadthFirstSearchList.
 */
public class BreadthFirstSearchTest
  extends GraphTraversalAlgorithmTest
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public BreadthFirstSearchTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( BreadthFirstSearchTest.class );
  }

  /**
   * @method traversal
   * @context one node graph
   */
  public void testOneNodeGraph()
  {
    Graph graph = oneNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("a");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context two node graph starting at node 'a'
   */
  public void testTwoNodeGraphStartingAtA()
  {
    Graph graph = twoNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("a");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context two node graph starting at node 'b'
   */
  public void testTwoNodeGraphStartingAtB()
  {
    Graph graph = twoNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("b");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"b", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context three node simple graph starting at node 'a'
   */
  public void testThreeNodeSimpleGraphStartingAtA()
  {
    Graph graph = threeNodeSimpleGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("a");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"a", "b", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context three node simple graph starting at node 'b'
   */
  public void testThreeNodeSimpleGraphStartingAtB()
  {
    Graph graph = threeNodeSimpleGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("b");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"b", "a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context three node simple graph starting at node 'c'
   */
  public void testThreeNodeSimpleGraphStartingAtC()
  {
    Graph graph = threeNodeSimpleGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("c");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"c", "b", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }


  /**
   * @method traversal
   * @context three node complex graph starting at node 'a'
   */
  public void testThreeNodeComplexGraphStartingAtA()
  {
    Graph graph = threeNodeComplexGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("a");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"a", "b", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context three node complex graph starting at node 'b'
   */
  public void testThreeNodeComplexGraphStartingAtB()
  {
    Graph graph = threeNodeComplexGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("b");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"b", "a", "c"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context three node complex graph starting at node 'c'
   */
  public void testThreeNodeComplexGraphStartingAtC()
  {
    Graph graph = threeNodeComplexGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("c");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"c", "a", "b"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context six node graph starting at node 'a'
   */
  public void testSixNodeGraphStartingAtA()
  {
    Graph graph = sixNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("a");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"a", "b", "c", "d", "e", "f"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context six node graph starting at node 'b'
   */
  public void testSixNodeGraphStartingAtB()
  {
    Graph graph = sixNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("b");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"b", "a", "d", "c", "e", "f"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context six node graph starting at node 'c'
   */
  public void testSixNodeGraphStartingAtC()
  {
    Graph graph = sixNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("c");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"c", "a", "d", "b", "e", "f"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context six node graph starting at node 'd'
   */
  public void testSixNodeGraphStartingAtD()
  {
    Graph graph = sixNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("d");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"d", "b", "c", "e", "a", "f"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context six node graph starting at node 'e'
   */
  public void testSixNodeGraphStartingAtE()
  {
    Graph graph = sixNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("e");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"e", "d", "f", "b", "c", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }

  /**
   * @method traversal
   * @context six node graph starting at node 'f'
   */
  public void testSixNodeGraphStartingAtF()
  {
    Graph graph = sixNodeGraph();
    GraphTraversalAlgorithm traversal = new BreadthFirstSearch(graph);
    Node startingNode = graph.getNode("f");

    ArrayList<Node> actualResult = traversal.traverse(startingNode);

    String[] expectedResult = {"f", "e", "d", "b", "c", "a"};
    assertTrue(Arrays.equals(expectedResult, nodeListToLabelArray(actualResult)));
  }
}
