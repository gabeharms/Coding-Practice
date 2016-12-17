package com.gabeharms.sorting;

import java.util.List;
import java.util.ArrayList;

public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T>
{
  public InsertionSort(List<T> list)
  {
    super(list);
  }

  public List<T> sort()
  {
    List<T> newList = new ArrayList<T>();

    for (int i = 0; i < list.size(); i++)
    {
      placeItemInList(newList, list.get(i));
    }

    this.list = newList;
    return this.list;
  }

  private void placeItemInList(List<T> sortedList, T value)
  {
    if (isListEmpty(sortedList))
    {
      sortedList.add(value);
    }
    else
    {
      int insertionIndex = findInsertionIndex(sortedList, value);
      sortedList.add(insertionIndex, value);
    }
  }

  private boolean isListEmpty(List<T> list)
  {
    return list.size() == 0;
  }

  private int findInsertionIndex(List<T> sortedList, T value)
  {
    for (int i = 0; i < sortedList.size(); i++)
    {
      if (isLast(sortedList, i))
      {
        if (isGreaterThanOrEqual(value, sortedList.get(i)))
        {
          return i+1;
        }
        else
        {
          return i;
        }
      }
      else if (isLessThanOrEqual(value, sortedList.get(i)))
      {
        return i;
      }
      else if (shouldInsertBetween(sortedList, i, value))
      {
        return i+1;
      }
    }
    return -1;
  }

  private boolean isLast(List<T> sortedList, int index)
  {
    return index == sortedList.size()-1;
  }

  private boolean shouldInsertBetween(List<T> sortedList, int insertionIndex, T value)
  {
    T aValue = sortedList.get(insertionIndex);
    T bValue = sortedList.get(insertionIndex+1);

    return isGreaterThanOrEqual(value, aValue) && isLessThanOrEqual(value, bValue);
  }

  private boolean isGreaterThanOrEqual(T aValue, T bValue)
  {
    return aValue.compareTo(bValue) >= 0;
  }

  private boolean isLessThanOrEqual(T aValue, T bValue)
  {
    return aValue.compareTo(bValue) <= 0;
  }

  public static void main( String[] args )
  {
    System.out.println( "Hello World!" );
  }
}
