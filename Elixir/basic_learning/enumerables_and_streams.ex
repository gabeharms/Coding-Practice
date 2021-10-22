
# Enumerables
# Elixir provides the concept of enumerables and the 'Enum' module to work with them.
#
# This module provides a huge range of functions to transform, sort, group, filter, and retrieve
# items from enumerables. It is one of the modules developers use frequently in their elixir code.

# Elixir also proivdes ranges
Enum.map(1..3, fn x -> x * 2 end) # [2, 4, 6]
Enum.reduce(1..3, 0, &+/2) # 6

# For specific operations like inserting/updating particular elements, you man need to reach 
# for modules related to those specific data types. (i.e List, Map, etc.)

# We say the functions in the Enum module are polymorphic because they can work with diverse data
# types. In particular, the functions in the Enum module can work iwwth any data type that implements
# Enum protocal. We will discuss protocols in later chapters.


# Eager vs Lazy
# All the functions in the Enum module are eager. This means that when performing multiple operations
# with Enum, each operation is going to generate an intermediate list until we reach the result.
1..100_000 |> Enum.map(&(&1 * 3)) |> Enum.filter(odd?) |> Enum.sum()

# The example above has a pipeline of operations. Each one completes before moving onto the next.


# Pipe operator

# the '|>' symbol is the pipe operator: it takes the output from the expression on the left side
# and passes it as the first argument to the function call on its right side.

# To see how it can make code cleaner see how the above would work without the pipe operator:
Enum.sum(Enum.filter(Enum.map(1..100_000, &(&1 * 3)), odd?))



# Streams
# As an alternative to Enum, Elixir provides the Stream module which supports lazy operations:
1..100_000 |> Stream.map(&(&1 * 3)) |> Stream.filter(odd?) |> Enum.sum

# While this produces the same result, streams are lazy. Lets see how they work.
1..100_000 |> Stream.map(&(&1 * 3))
#Stream<[enum: 1..100000, funs: [#Function<34.16982430/1 in Stream.map/2>]]>

# Notice how a stream is returned back. Furthermore, they are composable because we can pipe many
# stream operations:
1..100_000 |> Stream.map(&(&1 * 3)) |> Stream.filter(odd?)
#Stream<[enum: 1..100000, funs: [...]]>

# Instead of generating intermediate lists, steams build a series of computations that are invoked
# only when we pass the underlying stream to the Enum module. Streams are useful when working
# with large, possibly infinite, collections.
#
# Many functions in the Stream module accept any enumerable as an argument and return a Stream
# as a reseult. It also proivides functions for creating streams.
#
# For example Stream.cycle/1 can be used to create a stream that cycles a given enumerable
# infinetly. Be careful not to call Enum.map/2 on such streams as they would cycle forever.
stream = Stream.cycle([1, 2, 3])
Enum.take(stream, 10) # [1, 2, 3, 1, 2, 3, 1, 2, 3, 1]

# On the other hand, Stream.unfold/2 can be used to generate values from a given initial
# value:
stream = Stream.unfold("hełło", &String.next_codepoint/1)
Enum.take(stream, 3) # ["h", "e", "ł"]


# Anothe rinteresting function is Stream.resource/3 which can be used to wrap around resources, 
# guaranteeing they are opened right before enumeration and closed afterwards, even in the case 
# of failures.
#
# For example File.stream!/1 build on top of Stream.resource/3
stream = File.stream!("path/to/file")
Enum.take(stream, 10)

# The example above will fetch the first 10 lines of the file you have selected. This means streams can
# be very useful for handling large files or even slow resources like network resources.
#
# The amount of functionality in the Enum and Stream modules can be daunting at first, but you 
# will get familiar with them case by case.
#
# In particular, focus on the Enum module first and only move to Stream for the particular scenarios 
# where laziness is required.


