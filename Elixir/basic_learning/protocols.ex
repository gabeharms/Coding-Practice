
# Protocols are a mechanism to achieve polymorphism in Elixir when you want behavior to vary
# depending on the data type. We are already familiar with one way of solving this type problem:
# via pattern matching and guard clauses. Consider
defmodule Utility do
  def type(value) when is_binary(value), do: "string"
  def type(value) when is_integer(value), do: "integer"
  # ... other implementations ...
end

# If the new use of this module were confined to your own project, you would  be able to keep
# defining new type/1 functions for each new data type. However, this code could be problematic
# if it were shared as a depeendency by multiple apps because there would be no easy way to extend
# its functionality.
#
# This is where protocols can help us: protocols allow us to extend the original behavior for as many
# data types as we need. That's because dispatching on a protocol is available to any data type
# that has implemented the protocol and a protocol can be implemented by anyone at any time.
#
# Here's how we could write the same Utility.type/1 functionality as a protocol:
defprotocol Utility do
  @spec type(t) :: String.t()
  def type(value)
end

defimpl Utility, for: BitString do
  def type(_value), do: "string"
end

defimpl Utility, for: Integer do
  def type(_value), do: "integer"
end

# We define the protocol using defprotocol - its functions and specs may look similar to interfaces
# or abstract base classes in other languages. We can add as many implementations as we like using
# defimpl. The output is exactly the same as the single module with multiple functions.
Utility.type("foo") # "string"
Utility.type(123) # "integer"

# With prootocls however, we are no longer stuck having to continuously modify the same module to 
# support more and more datatypes. 
#
# Functions defined in a protocol may have more than one input, but the dispatching will always be 
# based on the data type of the first input.
#
# One of the most common protocols you may encounter is the String.Chars protocol: implementing its
# to_string/1 function for your custom structs will tell the Elixir kernel how to represent them as strings. 


# Example
#
# In Elixir, we have two idioms for checking how many items there are in a data structure:
# length and size. length means the information must be computed. For example, length(list)
# needs to traverse the whole list ot calculate its length. On the other hand, tuple_size(tuple)
# and byte_size(binary) do not depend on the typle and binary size as the size information is 
# pre-computed in the data-structure

# Even if we have type-specific functions for getting the size built into Elixir we could implement
# a generic 'Size' protoocl that all data structures for which size is pre-computed would implmeent:
defprotocol Size do
  @doc "Calculates the size (and not the length!) of a data structure"
  def size(data)
end

defimpl Size, for: BitString do
  def size(string), do: byte_size(string)
end

defimpl Size, for: Map do
  def size(map), do: map_size(map)
end

defimpl Size, for: Tuple do
  def size(tuple), do: tuple_size(tuple)
end

# We didn't implement the Size protocol for lists as there is no "size" inoformation pre-computed 
# for lists, and the length of a list has to be computed with length/1

Size.size("foo") # 3
Size.size({:ok, "hello"}) # 2
Size.size(%{label: "some label"}) # 1

# Passing a data type that doesn’t implement the protocol raises an error:

Size.size([1, 2, 3])
#** (Protocol.UndefinedError) protocol Size not implemented for [1, 2, 3]


# Protocols and Structs
#
# The power of Elixir's extensibility comes when protocols and structs are used together. 
#
# We have learned that although structs are maps, they do not shoare protocol implementations 
# with maps. For example, MapSet are implemented as structs. Let's try to use the Size protocol
# with a MapSet:
iex> Size.size(%{}) # 0
set = %MapSet{} = MapSet.new
Size.size(set)
#** (Protocol.UndefinedError) protocol Size not implemented for #MapSet<[]>

# Instead of sharing porotocol implmementation with maps, structs require their own protocol
# implementation.


# Implementing Any
#
# Manually implementing protocolls for all types can quickly become reptitive and tedious. In such
# cases, Elixir provides two options: we can explicitly derive the protoocl implementation for our
# types or atuaomtically implement the protocol for all types. In both cases, we need to implement 
# the protocol for Any


# Deriving
#
# Elixir allows us to derive a protocol implementaion based on the Any implementation.
#
# First lets implement Any as follows:
defimpl Size, for: Any do
  def size(_), do: 0
end

# this makes no sense. i.e. integer's size would not be zero

# However should we be fine with the implementation for Any, in order to use such an implementation 
# we would need to tell our struct to explicitly derive the Size protocol:
defmodule OtherUser do
  @derive [Size]
  defstruct [:name, :age]
end

# When deriving, Elixir will implement the Size protocol for OtherUser based on the implementatation
# provided by Any


# Fallback to Any

# Another alternative to @derive is to explicitly tell the protocol to fallback to Any when an 
# implementation cannot be found. This can be ahieved by setting @fallback_to_any to true in the
# protocol definition.
defprotocol Size do
  @fallback_to_any true
  def size(data)
end

# Most of the time this won't work for all Data types and is why its an opt-in functionality.
#
# For the majority of protocols, raising an error when a protocol is not implemented is the proper
# behavior. 
#
# Which technique is best between deriving and falling back to Any depends on teh use case but, given
# Elixir developers prefer explicit over implicit, you may see many libraries pushing toward the
# @derive approach.


# Build in prootocols
#
# Elixir ships with some built-in protocols. Previously we discussed:
Enum.map [1, 2, 3], fn(x) -> x * 2 end # [2, 4, 6]
Enum.reduce 1..3, 0, fn(x, acc) -> x + acc end # 6

# Another useful example is String.Charts
to_string :hello # "hello"

# Notice that the string interpolation in Elixir calls to_string/1
"age: #{25}" # "age: 25"

# this snippit above only works because numbers implement the String.Chars protocol. Passing
# a tuple in will lead to an error

# When there is a need to "pritn" a more complex data structure, on can use inspect/1 which is
# from the Inspect protocol:
"tuple: #{inspect tuple}" # "tuple: {1, 2, 3}"

# The Inspect protoocol is the protocol used to transofrm any data structure into a readable textual representation. This is what tools like IEx use to print results
{1, 2, 3} # {1, 2, 3}
%User{} # %User{name: "john", age: 27}

# Keep in mind that whenever the inspected value starts with a '#' it is representing a data 
# structure in non-valid Elixir syntax. This means the inspect protocol is not reversable as information
# may be lost this way.
