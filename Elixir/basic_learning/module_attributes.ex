
# Module attributes in Elixir serve 3 purposes:
# 1. They serve to annotate the module, often with information to be used by the user or VM
# 2. They work as constants
# 3. They work as tempporary module storage to be used during compliation


# As annotations
#
# Elixir brings the concept of module attributes From Erlang:
defmodule MyServer do
  @moduledoc "My server code."
end

# In the above example, we are defining the module documentation by using the module attribute
# syntax. Elixir has a handful of reserved attributs. Here are a few of the most common:
# - @moduledoc - provides documentation for the current module
# - @doc - provides documentation for the function or macro that follows the attribute
# - @spec - provides a typespec for the function that follows the attribute
# - @behavior - used for specifying OTP or user-defined behavior
#
# @moduledoc and @doc are by far th most used attributes, and we expect you to use them a lot. 
# Elixir treats documentation as first-class and provides many functions to access documentation.
#
# Lets go back to Math module:
defmodule Math do
  @moduledoc """
  Provides math-related functions.

  ## Examples

      iex> Math.sum(1, 2)
      3

  """

  @doc """
  Calculates the sum of two numbers.
  """
  def sum(a, b), do: a + b
end

# Elixir promotes the use of Markdown with heredocs to write readable documentation. Heredocs are 
# multi-line strings, tehey start and end with triple double quotes, keeping formatting of the inner text.
#
#
# We can access the documentation of any compiled module directly from IEx
# $ elixirc math.ex
# iex
h Math # Access the docs for the module Math
h Math.sum # Access the docs for the sum function


# We also provide a tool called ExDoc which is used to generate HTML pages from the documentation.
# You can take a look at the docs for Module for a complete list of supported attributes.


# As "constants"
#
# Elixir developers often use module attributes when they wish to make a value more visible or reusable
defmodule MyServer do
  @initial_state %{host: "127.0.0.1", port: 3456}
  IO.inspect @initial_state
end

# Trying to access an attribute that was not defined will print a warning
defmodule MyServer do
  @unknown
end
# warning: undefined module attribute @unknown, please remove access to @unknown or explicitly set it before access

# Attributes can also be read inside functions:
defmodule MyServer do
  @my_data 14
  def first_data, do: @my_data
  @my_data 13
  def second_data, do: @my_data
end

MyServer.first_data #=> 14
MyServer.second_data #=> 13

# Functions may be called when dfining a module attribute:
defmodule MyApp.Status do
  @service URI.parse("https://example.com")
  def status(email) do
    SomeHttpClient.get(@service)
  end
end

# This function above will be called at compilation time and its return value, not the function call
# itself, is what will be substitued in for the attribute. Thus, it will effectively compile to this:
defmodule MyApp.Status do
  def status(email) do
    SomeHttpClient.get(%URI{
      authority: "example.com",
      host: "example.com",
      port: 443,
      scheme: "https"
    })
  end
end

# This can be useful for pre-computing constant values, but it can also cause problems if you are 
# expecting the funtion to be called at runtime. For example reading a value from a database, et.c.
#
# Every time an attribute is read inside a function, Elixir takes a snapshot of its current value. 
# Therefor if you read the same attribute mulitple times inside multiple functions, you may
# end up making multiple copies of it. That's usually not an issue, if you are using functions to 
# compute large module attributes, it can slow down compile time.
#
# The solution to this it to move the attribute to shared function. So intead of:
def some_function, do: do_something_with(@example)
def another_function, do: do_something_else_with(@example)

# Do this instead:
def some_function, do: do_something_with(example())
def another_function, do: do_something_else_with(example())
defp example, do: @example



# Accumulating Attributes
#
# Normally repeating a module attribute will cause its value to be reassigned, but there are some
# cases where you may want to configure the module attribute so that its values are 
# accummulated:
defmodule Foo do
  Module.register_attribute __MODULE__, :param, accumulate: true

  @param :foo
  @param :bar
  # here @param == [:bar, :foo]
end


# As temporary storage
#
# To see an example of using module attributs as storege, lets examine ExUnit:
defmodule MyTest do
  use ExUnit.Case, async: true

  @tag :external
  @tag os: :unix
  test "contacts external service" do
    # ...
  end
end

# Here, ExUnit stores the value of async: true in a moudle attribute to change how the module is 
# compiled. Tags are also defined as accumulate: true attributes, and they store tags that can be 
# used to setup and filter tests.
#
# In order to understand the underlying code, we'd need macros, so we will revisit this in the 
# metaprogramming guide and learn how to use module attributees as storage to allow developes to 
# create Domain Specific Languages.
