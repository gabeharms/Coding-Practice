package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Graph.
 */
public class GraphTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public GraphTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( GraphTest.class );
  }

  /**
   * @method setLabel
   * @context when empty string is given
   */
  public void testSetLabelEmptyString()
  {
    Vertex vertex = new Vertex(null);
    vertex.setLabel("");

    assertEquals("", vertex.getLabel());
  }
}
