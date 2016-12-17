package com.gabeharms.sorting;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * MSD Radix Sort
 * Best Case:
 * Worst Case:
 * Theta:
 * Natural: false
 *
*/
public class RadixSort
{
  private List<Integer> list;

  public RadixSort(List<Integer> list)
  {
    this.list = list;
  }

  public List<Integer> sort()
  {
    Integer max = findMaxInList();

    for(int divisor = 1; divisor < max; divisor *= 10)
    {
      countSort(divisor);
    }

    return this.list;
  }

  private Integer findMaxInList()
  {
    Integer max = list.get(0);
    for (Integer element : list)
    {
      if(isGreaterThan(element, max))
      {
        max = element;
      }
    }
    return max;
  }

  private void countSort(int orderOfMagnitude)
  {
    int count[] = getCountArrayFor(orderOfMagnitude);
    transformCountArrayIntoPositionArray(count);
    ArrayList<Integer> output = mapPositionArrayToList(count, orderOfMagnitude);
    this.list = output;
  }

  private int[] getCountArrayFor(int orderOfMagnitude)
  {
    int count[] = new int[10];
    Arrays.fill(count, 0);
    for (Integer element : list)
    {
      count[toDigit(element, orderOfMagnitude)]++;
    }
    return count;
  }

  private int toDigit(Integer element, int orderOfMagnitude)
  {
    return (element.intValue() / orderOfMagnitude) % 10;
  }

  private void transformCountArrayIntoPositionArray(int[] count)
  {
    for (int i = 1; i < count.length; i++)
    {
      count[i] += count[i-1];
    }
  }

  private ArrayList<Integer> mapPositionArrayToList(int[] count, int orderOfMagnitude)
  {
    ArrayList<Integer> output = new ArrayList<Integer>(list.size());
    fillArrayList(output, list.size(), 0);
    for (int i = list.size()-1; i >= 0; i--) 
    {
      int digitOfElement = toDigit(list.get(i), orderOfMagnitude);
      output.set(--count[digitOfElement], list.get(i));
    }
    return output;
  }

  private void fillArrayList(ArrayList<Integer> listToFill, int size, int defaultValue)
  {
    for (int i = 0; i < size; i++)
    {
      listToFill.add(new Integer(0));
    }
  }

  private boolean isGreaterThan(Integer a, Integer b)
  {
    return a.compareTo(b) > 0;
  }

  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
  }
}
