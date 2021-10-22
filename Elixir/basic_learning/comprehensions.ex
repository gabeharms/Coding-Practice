
# In Elixir, it is common to loop over an Enumerable, often filtering out some results and mapping
# values into another list. Comprehensions are syntatctic sugar for such constructs:
# they group those common tasks into the 'for' special form
#
# For example, we can map a list of integers into their squared values:
for n <- [1,2,3,4], do: n * n # [1,4,9,16]

# A comprehension is made of three parts: generators, filters, and collectables.


# Generators and filters
#
# In the expression above, 'n < [1,2,3,4]' is the generator. It is literally generating
# values to be used in the comprehension. Any enumerable can be passed on the right-hand
# side of the generator expression:
for n <- 1..4, do: n*n # [1,4,9,16]

# Generator expressions also support pattern matching on their left hand side; all non-
# matching patterns are ignored. Imagine that, instead of a range, we have a keyword list where 
# the key is the atom :good or :bad and we only want to compute the square of :good values:
values = [good: 1, good: 2, bad: 3, good: 4]
for {:good, n} <- values, do: n * n # [1, 4, 16]

# Alternatively to pattern matching, filters can also be used to select particular elements:
for n <- 0..5, rem(n,3) == 0, do: n*n # [0, 9]

# Comprehensions discard all elements for which the filter returns false or nil.
#
# Comprehensions generally provide a much more concise representation than using the equiavlanet 
# functions from the Enum and Stream modules. Furthermore, comprehensions also allow multiple 
# generators and filters to be given.
#
dirs = ['/home/mikey', '/home/james']

for dir <- dirs,
    file <- File.ls!(dir),
    path = Path.join(dir, file),
    File.regular?(path) do
      File.stat!(path).size
    end

# Multiple generators can also be used to calculate the cartesian product of two lists:
for i <- [:a, :b, :c], j <- [1, 2], do:  {i, j} [a: 1, a: 2, b: 1, b: 2, c: 1, c: 2]

# Finally keep in mind that variable assignments inside the comprehension, are not reflected
# outside the comprehension.


# Bitstring generators
#
# Bitstring generators are also supported and very useful when you need to comprehend over
# bitstring streams. The example below receives a list of pixels from a binary with their 
# respective red, green and blue values and converts them into tuples of 3 elements each:
pixels = <<213, 45, 132, 64, 76, 32, 76, 0, 0, 234, 32, 15>>
for <<r::8, g::8, b::8 <- pixels>>, do: {r, g, b} # [{213, 45, 132}, {64, 76, 32}, {76, 0, 0}, {234, 32, 15}]


# The :into option
#
# In teh examples above, all the comprehensions returned listes as their result. Hoever, the result
# of a comprehension can be inserted into different data structures by passing the :into option
# to the comprehension:
for <<c <- " hello world ">>, c != ?\s, into: "", do: <<c>> # "helloworld"

# A common use caes of :into can be transforming values in a map:
for {key, val} <- %{"a" => 1, "b" => 2}, into: %{}, do: {key, val * val} # %{"a" => 1, "b" => 4}


# Lets make another example using streams:
stream = IO.stream(:stdio, :line)
for line < stream, into: stream do
  String.upcase(line) <> "\n"
end

# Now type any string into the terminal and you will see that the same value will be printed in 
# upper-case.



