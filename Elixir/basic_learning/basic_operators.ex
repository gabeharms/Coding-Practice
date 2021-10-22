
# Lists
[1,2,3] ++ [4,5,6] # [1,2,3,4,5,6]
[1,2,3] -- [2] # [1,3]

# String concat
"foo" <> "bar" # "foobar"

# elixir also supports or, and, and not which require values to resolve to booleans
true and true # true
false or is_atom(:exmpale) # true
1 and true # error. must be booleans

# and and or short circuit and don't evaluate parts of the expression if their return value is already known


# || and &&, and ! are also provided. These don't require strict booleans like the above oeprators.
# Everything that is not false or nil will evaluate to true
1 || true # 1
false || 11 # 11
nil && 13 # nil
true && 17 # 17
!true # false
!1 # false
!nil # true

# general rule of thumb is to use or/and when expecting booleans and ||/&& when you are not.


1 == 1 # true
1 == 1.0 # true
1 === 1.0 # false. also matches on type.


# can also compare two different data types
1 < :atom # true
# the reason we can compare different data types is pragmatism. Sorting algorithms don't need to worry
# about sorting across different data types. The sorting order from lower to higher is:
#    number, atom, reference, function, port, pid, tuple, map, list, bitstring
