
# case allows us to compare a value against many patterns until we find a matching
# one
case {1, 2, 3} do
  {4, 5, 6} ->
    "This clause won't match"
  {1, x, 3} ->
    "This clause will match and bind x to 2 in this clause"
  _ ->
    "This clause would match any value"
end
# "This clause will match and bind x to 2 in this case"

# If you need to pattern match against an existing variable, you need to use the pin operator:
x = 1
case 10 do
  ^x -> "Won't match"
  _ -> "Will Match"
end
# "Will Match"

# Clauses also allow extra conditions to be specified via guards
case {1,2,3} do
  {1, x, 3} -> when x > 0 -> "Will match"
  _ -> "Would match, if gaurd condition were not satisfied"
end
# "Will match"

# Keep in mind errors in guards do not leak but simply make the guard fail:
hd(1)
case 1 do
  x when hd(x) -> "Won't match"
  x when -> "Got #{x}"
end
# Got 1

# But if none of the clauses match, the error will raise


# Anonymous functions can also have multiple clauses and gaurds
f = fn
  x, y when x > 0 -> x + y
  x, y -> x * y
end

f.(1,3) # 4
f.(-1, 3) # -3

# The number of arguments in each anonymous function clause needs to be the same
# otherwise an error is raised.



# cond
# case is useful when you need to match against different values. However, in many
# we want to check different conditions and find the first one that does not evaluate 
# to nil or false. This is where we use cond

cond do
  2 + 2 == 5 -> "This will not be true"
  2 * 2 == 5 -> "Nor this"
  1 + 1 == 2 -> "But this will"
end
# "But this will

# This is equivalent to 'else if' clauses in many imperative languages. Although used less
# frequently in elixir

# in order to avoid an error being raised when no conditions match, you can add a true
# as the last check
cond do
  2 + 2 == 5 -> "This will not be true"
  2 * 2 == 5 -> "Nor this"
  true -> "This is equivalent to else"
end
# "This is equivalent to else"

# Note that cond considers any value other than nil or false to be true


# if and unless
if true do
  "this works!"
end

unless true do
  "This will never be seen"
end

if nil do
  "This won't be seen"
else
  "this will"
end

# If any variable is declared or changed inside 'if', 'case, and similar constructs, the declaration
# and change will only be visible inside the construct:
x = 1
if true do
  x = x + 1
end
x # 1

# If you want to change a value you must return it from the if:
x = if true do
  x + 1
else
  x
end

x # 2

# Note: An interesting note regarding if/2 and unless/2 is that they are implemented as macros in 
# the language; they aren’t special language constructs as they would be in many languages. You can
# check the documentation and the source of if/2 in the Kernel module docs. The Kernel module is 
# also where operators like +/2 and functions like is_function/2 are defined, all automatically 
# imported and available in your code by default.


# do - end blocks
# We have now learned four control structures: case, cond, if and unless, and they were all
# wrapped in do-end blocks. It happens we could also write 'if' as follows:
if true, do: 1 + 2  # 3

# Notice how the example has a comma, that's because it is using Elixir's regular syntax where
# each argument is separated by a comma. We say this syntax is using keyword lists. We can pass
# else using keywords too:
if false, do : :this, else: :that  # :that

# do-end blocks are syntactic convenience built on top of the keyword ones. That's why
# do-end blocks do not require a comma between the previous argument and the block. 

# keyword lists play an important role in the language and are quite common in many functions
# and macros. 
