
# the match operator
x = 1

# In elixir, the '=' is actually called the 'match' operator. Here is why:
x = 1 # 1
1 = x # 1
2 = x # MatchError no match of right hand side

# Notice that 1 = x is a valid expression, and it matched because both the left 
# and right side are equal to 1. When the sides do not match, a MatchError is raised.

# A variable can olny be assigned on the left side of a '='
1 = unknown # CompileError) undefined function unknown/0

# Since there is no defined variable 'unknown', elixir assumes you are trying to
# call a function unknown/0


# Pattern Matching
# the match operator is not only used to match against simple values, but it is also useful for
# destructuring more complex data types. We can pattern match on tuples:
{a,b,c} = {:hello, "world", 42} # {:hello, "world", 42}
a # :hello
b # "world"

# A pattern match error will occur if the sides can't be matched, for example if they have different
# sizes
{a,b,c} = {:hello, "world"} # MatchError no match of right hand side with value: {:hello, "world"}

# This will also occur with mismatching types on left and right hand side
{a, b, c} = [:hello, "world", 42] # MatchError tuple vs list

# We can also match on specific values. Below asserts that the left side will only match the right 
# side when the right side is a tuple that starts with the atom ':ok'
{:ok, result} = {:ok, 13} # {:ok, 13}
result # 13

{:ok, result} = {:error, :oops} # MatchError


# Can also match on lists
[a, b, c] = [1,2,3]
[head | tail] = [1, 2, 3]
head # 1
tail # [2,3]

[head | tail] = [] # MatchError

list = [1,2,3]
[0 | list] # [0,1,2,3]

# Pattern matching allows developers to easily destructure data types such as tuples and lists.


# The Pin operator

# variables in elixir can be rebound:
x = 1
x = 2

# However there are times when we don't want to be rebound. Here we can use the 
# pin operator '^' when you want to pattern match against a variable's existing
# value rather than rebinding the variable
x = 1
^x = 2 # MatchError

# Because we have pinned 'x' when it was bound to the value of '1', it is equivalent to the following:
1 = 2


# We can use the pin operator inside other pattern matches, such as tuples or lists:
x = 1
[^x, 2, 3] = [1, 2, 3] # [1,2,3]
{y, ^x} = {2,1} # {2,1}
y # 2
{y, ^x } = {2, 2} # MatchError, equivalent to {y, 1} = {2, 2}


# If a variable is mentioned more than once in a pattern, all references should bind to the same
# value:
{x, x} = {1,1}
x # 1
{x, x} = {1, 2} # Match Error


# Insome cases you don't care about a particular value in a pattern. It is comomn practice to bind 
# those values to the underscore '_'.
[head | _] = [1,2,3]
head # 1

# The variable '_' is special in that it can never be read from. Trying to read from it produces
# a compile error.
