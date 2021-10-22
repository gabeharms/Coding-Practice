# In elixir we group several functions into modules. We have already seen
# various modules in our previous examples
String.length("hello")

# In order to create our own modules, we use the 'defmodule' macro:
defmodule Math do
  def sum(a, b) do
    a + b
  end
end

Math.sum(1, 2)


# Compilation
# Most of the time it is convenient to write modules into files so they can be compiled and
# reused. The above module can be compiled as follows:
> elixirc math.ex

# This will generate a file named Elixir.Math.beam containing the bytecode for the defined module. If
# we start 'iex' again, our module definition will be available. (provided iex is started from 
# the same directory that the new beam file is in).

# Elixir projects are usually organized into 3 directories:
# - _build - contains compilation artifacts
# - lib - contains Elixir code (usually .ex files)
# - test - contains tests (usually .exs files

# When working on projects, the build tool mix will be responsible for compiling and setting
# up the proper paths for you. For learning, Elixir also supports a scripted mode in which no 
# compiled artifacts are generated.


# Scripted Mode
# In addition to the elixir file extentions .ex,  Elixir also supports '.exs' files for scripting. Elixir
# treats both files the same way, the only difference is intention. 'ex' is mean't to be compiled, while
# 'exs' is not

# In the example above, because we used we can simply run the file without compiling it as well:
elixir math.exs

# here the file will be run, but no .beam file will be generated.


# Named functions
# Inside a module, we can define functions with 'def/2' and private functions with 'defp/2'. A private
# function can only be invoked locally


# function declarations also support guards and multiple clauses. If a function has several clauses,
# Elixir will try each clause until it find one that matches.

defmodule Math do
  def zero?(0) do
    true
  end

  def zero?(x) when is_integer(x) do
    false
  end
end

# Similar to constructs like if, named functions support both 'do:' keyword and 'do-end' block syntax.
defmodule Math do
  def zero?(0), do: true
  def zero?(x) when is_integer(x), do: false
end



# Function Capturing
# start >i ex math.exs

Math.zero?(0) # true
fun = &Math.zero?/1
is_function(fun) # true
fun.(0) # true

# Remember that elixir requires anonymous functions to be called w/ the dot notation. The capture '&' operator
# bridges this gap by allowing named functions to be assigned to variables and passes as arguments in the
# same way we assign, invoke and pass anonymous functions.

# The capture syntax can also be used as a shortcut for creating functions
fun = &(&1 + 1)
fun.(1) # 2

fun = &"Good #{&1}"
fun.("morning") # "Good morning"

# In the abobe, '&1' represents the first argument passed into the function.


# Default Arguments
# named functions in elixir support default arguments
defmodule DefaultTest do
  def dowork(x \\ "hello") do
    x
  end
end

DefaultTest.dowork # "hello"
DefaultTest.dowork 123 # 123

# When using default values, one must be careful to avoid overlapping function definitions. Consider
# the following
defmodule Concat do
  def join(a,b) do
    IO.puts "First Join"
    a <> b
  end

  def join(a, b, sep \\ " ") do
    IO.puts "Second Join"
    a <> b
  end
end

# Elixir will emit the following warning:
#   concat.ex:7: warning: this clause cannot match because a previous clause at line 2 always matches

# The compiler is telling us that invoking the join function with two arguments will always choose the 
# first definition of join. 
