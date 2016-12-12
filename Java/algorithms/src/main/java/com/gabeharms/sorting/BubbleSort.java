package com.gabeharms.sorting;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
  public BubbleSort(List<T> list)
  {
    super(list);
  }

  public List<T> sort()
  {
    for (int i = 0; i < list.size(); i++)
    {
      iterateAndMakeSwaps();
    }

    return list;
  }

  private void iterateAndMakeSwaps() {
    for (int i = 0; i < list.size()-1; i++)
    {
      if (shouldSwap(i, i+1))
      {
        swap(i, i+1);
      }
    }
  }

  private boolean shouldSwap(int a, int b)
  {
    return list.get(a).compareTo(list.get(b)) > 0;
  }

  private void swap(int a, int b) {
    T aValue = list.get(a);
    list.set(a, list.get(b));
    list.set(b, aValue);
  }

  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
  }
}
