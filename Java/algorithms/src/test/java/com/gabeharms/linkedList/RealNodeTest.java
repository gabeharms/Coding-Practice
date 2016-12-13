package com.gabeharms.linkedList;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Node.
 */
public class RealNodeTest
  extends TestCase
{
  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public RealNodeTest( String testName )
  {
    super( testName );
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite()
  {
    return new TestSuite( RealNodeTest.class );
  }

  /**
   * @method getValue
   * @context when null value
   */
  public void testNullGetValue()
  {
    Integer value1 = new Integer(5);
    Integer value2 = new Integer(3);
    Node<Integer> node = new RealNode<Integer>(value1, null, null);
    node.setValue(value2);

    assertEquals(value2, node.getValue());
  }

  /**
   * @method getValue
   * @context when non-null value
   */
  public void testNonNullGetValue()
  {
    Integer value = new Integer(5);
    Node<Integer> node = new RealNode<Integer>(value, null, null);

    assertEquals(value, node.getValue());
  }

  /**
   * @method getPrevious
   * @context when null value
   */
  public void testNullGetPrevious()
  {
    Node<Integer> node = new RealNode<Integer>(null, null, null);

    assertTrue(node.getPrevious() instanceof NullNode);
  }

  /**
   * @method getPrevious
   * @context when non-null value
   */
  public void testNonNullGetPrevious()
  {
    Node<Integer> previous = new RealNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new RealNode<Integer>(null, previous, null);

    assertEquals(previous, node.getPrevious());
  }

  /**
   * @method getNext
   * @context when null value
   */
  public void testNullGetNext()
  {
    Node<Integer> node = new RealNode<Integer>(null, null, null);

    assertTrue(node.getNext() instanceof NullNode);
  }

  /**
   * @method getNext
   * @context when non-null value
   */
  public void testNonNullGetNext()
  {
    Node<Integer> next = new RealNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new RealNode<Integer>(null, null, next);

    assertEquals(next, node.getNext());
  }


  /**
   * @method setValue
   * @context when non-null value
   */
  public void testNonNullSetValue()
  {
    Node<Integer> previous1 = new RealNode<Integer>(new Integer(5), null, null);
    Node<Integer> previous2 = new RealNode<Integer>(new Integer(3), null, null);
    Node<Integer> node = new RealNode<Integer>(null, previous1, null);
    node.setPrevious(previous2);

    assertEquals(previous2, node.getPrevious());
  }

  /**
   * @method setPrevious
   * @context when non-null value
   */
  public void testNonNullSetPrevious()
  {
    Integer value1 = new Integer(5);
    Integer value2 = new Integer(3);
    Node<Integer> node = new RealNode<Integer>(value1, null, null);
    node.setValue(value2);

    assertEquals(value2, node.getValue());
  }

  /**
   * @method setNext
   * @context when non-null value
   */
  public void testNonNullSetNext()
  {
    Node<Integer> next1 = new RealNode<Integer>(new Integer(5), null, null);
    Node<Integer> next2 = new RealNode<Integer>(new Integer(3), null, null);
    Node<Integer> node = new RealNode<Integer>(null, null, next1);
    node.setNext(next2);

    assertEquals(next2, node.getNext());
  }

  /**
   * @method hasNext
   * @context when null value
   */
  public void testNullHasNext()
  {
    Node<Integer> node = new RealNode<Integer>(null, null, null);

    assertFalse(node.hasNext());
  }

  /**
   * @method hasNext
   * @context when non-null value
   */
  public void testNonNullHasNext()
  {
    Node<Integer> next = new RealNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new RealNode<Integer>(null, null, next);

    assertTrue(node.hasNext());
  }

  /**
   * @method hasPrevious
   * @context when null value
   */
  public void testNullHasPrevious()
  {
    Node<Integer> node = new RealNode<Integer>(null, null, null);

    assertFalse(node.hasPrevious());
  }

  /**
   * @method hasPrevious
   * @context when non-null value
   */
  public void testNonNullHasPrevious()
  {
    Node<Integer> previous = new RealNode<Integer>(new Integer(5), null, null);
    Node<Integer> node = new RealNode<Integer>(null, previous, null);

    assertTrue(node.hasPrevious());
  }

}
