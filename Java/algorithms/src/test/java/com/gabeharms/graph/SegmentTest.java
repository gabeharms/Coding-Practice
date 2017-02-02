package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Segment.
 */
public class SegmentTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public SegmentTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( SegmentTest.class );
  }

  /**
   * @method getLabelA
   * @context when nodeA is not set
   */
  public void testGetLabelAWhenNotSet()
  {
    Edge edge = new Segment();

    assertNull(edge.getA());
  }

  /**
   * @method getLabelA
   * @context when nodeA is set
   */
  public void testGetLabelAWhenSet()
  {
    Node nodeA = new Vertex("a");
    Edge edge = new Segment(nodeA, null);

    assertEquals(nodeA, edge.getA());
  }

  /**
   * @method getLabelB
   * @context when nodeB is not set
   */
  public void testGetLabelBWhenNotSet()
  {
    Edge edge = new Segment();

    assertNull(edge.getB());
  }

  /**
   * @method getLabelB
   * @context when nodeB is set
   */
  public void testGetLabelBWhenSet()
  {
    Node nodeB = new Vertex("b");
    Edge edge = new Segment(null, nodeB);

    assertEquals(nodeB, edge.getB());
  }

  /**
   * @method setVerticies
   * @context when no existing vertices are set, and null verticies are passed in
   */
  public void testSetVerticiesWhenNoneSetNoneGiven()
  {
    Edge edge = new Segment(null, null);
    edge.setVerticies(null, null);

    assertNull(edge.getA());
    assertNull(edge.getB());
  }

  /**
   * @method setVerticies
   * @context when no existing vertices are set, and verticies are passed in
   */
  public void testSetVerticiesWhenNoneSetRealVerticiesGiven()
  {
    Edge edge = new Segment(null, null);
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    edge.setVerticies(nodeA, nodeB);

    assertEquals(nodeA, edge.getA());
    assertEquals(nodeB, edge.getB());
  }

  /**
   * @method setVerticies
   * @context when existing vertices are set, and null verticies are passed in
   */
  public void testSetVerticiesWhenAlreadySetNoneGiven()
  {
    Edge edge = new Segment(new Vertex("a"), new Vertex("b"));
    edge.setVerticies(null, null);

    assertNull(edge.getA());
    assertNull(edge.getB());
  }

  /**
   * @method setVerticies
   * @context when existing vertices are set, and real verticies are passed in
   */
  public void testSetVerticiesWhenAlreadySetRealVerticiesGiven()
  {
    Edge edge = new Segment(new Vertex("c"), new Vertex("d"));
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    edge.setVerticies(nodeA, nodeB);

    assertEquals(nodeA, edge.getA());
    assertEquals(nodeB, edge.getB());
  }

  /**
   * @method includes
   * @context when inbound node is not a or b
   */
  public void testIncludesWhenNotAOrB()
  {
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    Node nodeC = new Vertex("c");
    Edge edge = new Segment(nodeA, nodeB);

    assertFalse(edge.includes(nodeC));
  }

  /**
   * @method includes
   * @context when inbound node is a
   */
  public void testIncludesWhenIsA()
  {
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    Segment edge = new Segment(nodeA, nodeB);

    assertTrue(edge.includes(nodeA));
  }

  /**
   * @method includes
   * @context when inbound node is b
   */
  public void testIncludesWhenIsB()
  {
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    Edge edge = new Segment(nodeA, nodeB);

    assertTrue(edge.includes(nodeB));
  }

  /**
   * @method getAdjacentTo
   * @context when inbound node is a
   */
  public void testGetAdjacentToWhenIsA()
  {
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    Edge edge = new Segment(nodeA, nodeB);

    assertEquals(nodeB, edge.getAdjacentTo(nodeA));
  }

  /**
   * @method getAdjacentTo
   * @context when inbound node is b
   */
  public void testGetAdjacentToWhenIsB()
  {
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    Edge edge = new Segment(nodeA, nodeB);

    assertEquals(nodeA, edge.getAdjacentTo(nodeB));
  }

  /**
   * @method getAdjacentTo
   * @context when inbound node is not a or b
   */
  public void testGetAdjacentToWhenIsNotAOrB()
  {
    Node nodeA = new Vertex("a");
    Node nodeB = new Vertex("b");
    Node nodeC = new Vertex("c");
    Edge edge = new Segment(nodeA, nodeB);

    assertNull(edge.getAdjacentTo(nodeC));
  }
}
