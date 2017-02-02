package com.gabeharms.graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for VertexCostDecorator.
 */
public class VertexCostDecoratorTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public VertexCostDecoratorTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( VertexCostDecoratorTest.class );
  }

  /**
   * @method getLabel
   * @context when empty string is given to constructor
   */
  public void testGetLabelEmptyString()
  {
    Vertex vertex = new Vertex();
    VertexCostDecorator decorator = new VertexCostDecorator(vertex, null, 0);

    assertEquals("", decorator.getLabel());
  }

  /**
   * @method getLabel
   * @context when non empty string is given to constructor
   */
  public void testGetLabelNonEmptyString()
  {
    String label = new String("not empty");
    Vertex vertex = new Vertex(label);
    VertexCostDecorator decorator = new VertexCostDecorator(vertex, null, 0);

    assertEquals(label, decorator.getLabel());
  }

  /**
   * @method setLabel
   * @context when empty string is given
   */
  public void testSetLabelEmptyString()
  {
    Vertex vertex = new Vertex(null);
    VertexCostDecorator decorator = new VertexCostDecorator(vertex, null, 0);
    vertex.setLabel("");

    assertEquals("", decorator.getLabel());
  }

  /**
   * @method setLabel
   * @context when non empty string is given
   */
  public void testSetLabelNonEmptyString()
  {
    String label = new String("not empty");

    Vertex vertex = new Vertex(null);
    VertexCostDecorator decorator = new VertexCostDecorator(vertex, null, 0);
    vertex.setLabel(label);

    assertEquals(label, decorator.getLabel());
  }
}
