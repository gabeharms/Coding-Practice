
1     # integer
0x1F  # integer
1.0   # float
true  # boolean
:atom # string / atom
"elixir" # string
[1,2,3] # list
{1,2,3} # tuple


1+2 # integer
5*5 # integer
10/2 # float

div(10, 2) #integer
rem(10,3) #integer

# Parenthesis optional
div(10, 2)
div 10, 2

# floats require a dot followed by at least one digit followed by  and support e for scientific notiation
1.0
1.0e-10


round(3.58)
trunc(3.58)


# functions are identified by both their name and their arity. Thus
# we use both the name and arity to describe functions
#
# i.e. trunc/1 vs trunc/2

# This syntzx also allows us to access documentation via the 'h' function
h trunc/1

# trunc is defined in the kernal module so we can identify it without a namespace

h Kernel.trunc/1


# Booleans
true == false

# Elixir provides predicate functions to check the type of a value
is_boolean(true)
is_integer(1)
is_float(1)
is_number(1)


# Atoms
# An atom is a constant whose value is its own name. Some other languages call these symbols. They are often used in enums
:orange
:apple

:apple == :apple # true
:apple == :orange # false

# in elixir atoms are heavily used to express the state of an operations, i.e. :ok or :error


# booleans are also atoms
true == :true #true

# Strings
# strings in elixir are deliminated by double quotes
"hello"

# elixir supports string interpolation
string == :world
"hello #{string}"

# Strings can be printed via IO.puts/1
IO.puts("hello\nworld")

# Strings in elixir are internally represented by contiguous sequences of bytes known as binaries:
is_binary("hello") # true

# can also get the number of bytes in each string
byte_size("hello")


# Anonymous functions
# anonymous functions allow us to store and pass executable code around as if it were a string or integer. 
# they are deliminated by keywords 'fn' and 'end'
add = fn a, b -> a+b end

# note the dot, which is required to invoke an anonymous function. The dot ensures there is no ambiguity
# between calling the anonymous function matched to a variable add and a named function add/2. Later
# we will write our own named functions
add.(1,2)

is_function(add) # true
is_function(add, 2) # true
is_function(add, 1) # false

# anonymous functions can access variables that are in scope when hte function is defined. This is 
# commonly referred to as a closure, as they close over their scope.

# Here we define a closure because double has access to the add variable
double = fn a => add.(a,a) end

# A variable assigned inside a function does not affect its surrounding enviornment
x = 42
(fn -> x = 0 end).()
x # still 42


# Lists
# elixir uses square brackets to specifiy a list of values. Lists can be heterogenous
[1,2,true,3.0]

length [1,2,3]

# lists can be concatenated or subtracted using ++/2 and --/2
[1,2,3] ++ [4,5,6]
[1,true, 2] -- [true]

hd([1,2,3])
tl([1,2,3])

hd([]) # error

# list operators never modify the existing lists, but always return a new list. In elixir all data 
# structures are immutable.

# sometimes you will create a list and it will return a value in single quotes
[11,12,13] # '\v\f\r'

#When elixir sees a list of printable ascII numbers, it will print them as a charlist. 
# charlists are common when interfacing with existing erlang code. 

# Whenever you see a value in IEx and you are not quite sure what it is, you can use i/1 to retrieve information
# about it
i 'hello'

# Keep in mind that single quotes are charlists, and double quotes are strings



# Tuples
# elixir uses curly brackets to define tuples.
{:ok, "hello"}
tuple_size {:ok, "hello"} # 2

# Tuples store elements contiguously in memory. This means accessing a tuple element
# by index or getting the tuple size is a fast operation. Indexes start from zero
tuple = {:ok, "hello"}
elem(tuple}, 1) # "hello
put_elem(tuple, 1, "world")

# notice that put_elem returns a new tuple. 


# Lists or tuples

# Lists are stored in memory as linked lists, which makes accessing  elements a linear operation requiring
# traversal of the list.
#
# Tuples on the other hand are contiguously stored in memory, making random access very fast. However, adding
# elements to a tuple is expensive because it requires creating a new tuple.
#
# Note this only applies to the tuple itself, not its contents. contents will still be the same references. Thus
# tuples can share their contents.

# The elixir API will guide you to use the right one. I.e. there is elem on tuple, but not on list
