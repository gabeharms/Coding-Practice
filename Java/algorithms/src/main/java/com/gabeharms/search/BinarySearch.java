package com.gabeharms.search;

import java.util.List;
import java.util.ArrayList;

class BinarySearch<T extends Comparable<T>> extends SearchAlgorithm<T>
{

  public BinarySearch(List<T> sortedList)
  {
    super(sortedList);
  }

  public int search(T value)
  {
    return binarySearch(value, 0, sortedList.size()-1);
  }

  private int binarySearch(T value, int start, int end)
  {
    int pivotIndex = (end-start) / 2;
    if (pivotIndex < 1)
    {
      if (isEqual(value, sortedList.get(start)))
      {
        return start;
      }
      else
      {
        return -1;
      }
    }
    else if (isEqual(value, sortedList.get(start + pivotIndex)))
    {
      return start + pivotIndex;
    }
    else if (isLessThan(value, sortedList.get(start + pivotIndex)))
    {
      return binarySearch(value, start, start + pivotIndex);
    }
    else
    {
      return binarySearch(value, start + pivotIndex+1, end);
    }
  }

  private boolean isEqual(T a, T b)
  {
    return a.compareTo(b) == 0;
  }

  private boolean isLessThan(T a, T b)
  {
    return a.compareTo(b) < 0;
  }

  public static void main(String[] args) {

  }
}
