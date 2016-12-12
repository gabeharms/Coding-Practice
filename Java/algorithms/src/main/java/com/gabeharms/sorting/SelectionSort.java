package com.gabeharms.sorting;

import java.util.List;
import java.util.ArrayList;

public class SelectionSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
  public SelectionSort(List<T> list)
  {
    super(list);
  }

  public List<T> sort()
  {
    List<T> newList = new ArrayList<T>();
    while(isListNotEmpty())
    {
      T minValue = findAndRemoveMin();
      newList.add(minValue);
    }

    this.list = newList;
    return this.list;
  }

  private boolean isListNotEmpty()
  {
    return list.size() != 0;
  }

  private T findAndRemoveMin()
  {
    int minIndex = 0;
    T minValue = list.get(0);
    for (int i = 1; i < list.size(); i++)
    {
      if (isLessThan(minValue, list.get(i)))
      {
        minIndex = i;
        minValue = list.get(i);
      }
    }
    list.remove(minIndex);
    return minValue;

  }

  private boolean isLessThan(T aValue, T bValue)
  {
    return aValue.compareTo(bValue) > 0;
  }

  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
  }
}
