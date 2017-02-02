package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for VertexList.
 */
public class VertexTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public VertexTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( VertexTest.class );
  }

  /**
   * @method getLabel
   * @context when empty string is given to constructor
   */
  public void testGetLabelEmptyString()
  {
    Vertex vertex = new Vertex();

    assertEquals("", vertex.getLabel());
  }

  /**
   * @method getLabel
   * @context when non empty string is given to constructor
   */
  public void testGetLabelNonEmptyString()
  {
    String label = new String("not empty");
    Vertex vertex = new Vertex(label);

    assertEquals(label, vertex.getLabel());
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

  /**
   * @method setLabel
   * @context when non empty string is given
   */
  public void testSetLabelNonEmptyString()
  {
    String label = new String("not empty");
    Vertex vertex = new Vertex(null);
    vertex.setLabel(label);

    assertEquals(label, vertex.getLabel());
  }
}
