
# Recursion

# Loops through recursion
# Due to immutability, loops in Elixir (as in any function language) are written differently
# than in imperative languages.

# For this reason, functional languages rely on recursion, where no data is mutated. See an 
# example below:
defmodule Recursion do
  def print_multiple_times(msg, n) when n > 0 do
    IO.puts(msg)
    print_multiple_times(msg, n -1)
  end

  def print_multiple_times(_msg, 0) do
    :ok
  end
end


# Reduce and Map algorithms
defmodule Math do
  def sum_list([head | tail], accumulator) do
    sum_list(tail, head + accumulator)
  end

  def sum_list([], accumulator) do
    accumulator
  end
end

# since [] cannot have head/tail called on it, it only matches on the second clause.


# The process of taking a list and reducing it down to one value is knowna s a reduce algorithm 
# and is central to functional programming

# What if isntead we wanted to double all our values
defmodule Math do
  def double_list([head | tail]) do
    [h*2 | double_list(tail)]
  end

  def double_list([]) do
    []
  end
end

# Here we use recursion to traverse a list, doubling each element and returning a new list. This
# is known as mapping or a map algorithm.

# The Enum module already provides conveniences for working with lists:
Enum.reduce([1, 2, 3], 0, fn(x, acc) -> x + acc end) # 6
Enum.map([1, 2, 3], fn(x) -> x * 2 end) # [2,4,6

# Or using the capture syntax:
Enum.reduce([1, 2, 3], 0, &+/2)
Enum.map([1, 2, 3], &(&1 * 2))

