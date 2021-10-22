
# Structs are extensions built on to pof maps that provide compile-time checks and default values

# Defining Structs
defmodule User do
  defstruct name: "John", age: 27
end

# the keyword list used with defstruct defines what files the struct will have along with
# its default values.
#
# Structs take the name of the module there're defined in. i.e. in the above its User.
#
# We can now create User structs by using a similar syntax to the one used to create maps:
%User{} # %User{age: 27, name: "John"}
%User{name: "Jane"} # %User{age: 27, name: "Jane"}

# Structs provide compile-time =guarantees that only the fields defined through defstruct
# will be allowed to exist in a struct:
%User{oops: :field}
# ** (KeyError) key :oops not found in: %User{age: 27, name: "John"}


# Accessing and updating structs
# When we discussed maps, we showed how we can access and update hte map fields. The same
# techniques apply to structs:
john = %User{} # %User{age: 27, name: "John"}
john.name # "John"
jane = %{john | name: "Jane"} # %User{age: 27, name: "Jane"}
%{jane | oops: :field}
#** (KeyError) key :oops not found in: %User{age: 27, name: "Jane"}

# when using the update syntax '|' the VM is aware that no new keys will be added to the struct,
# allowing the maps underneath to share their structure in memory. In the example above,
# both john and jane share the same key structure in memory.
#
# Structs can also be used in pattern matching, both for matching on the value of specific
# keys as well as for ensuring that the matching value is a struct of the same type as the
# matched value:
%User{name: name} = john # %User{age: 27, name: "John"}
name # "John"
%User{} = %{}
#** (MatchError) no match of right hand side value: %{}


# Structs are bare maps underneath
#
# In the example above, pattern matching works because structs are really just bare maps with a 
# fixed set of fields. As maps, structs store a special field named '__struct__' that holds the
# name of the struct:
is_map(john) # true
john.__struct__ # User


# Note that we refer to structs as bare maps because none of the protocols implemented for maps are 
# available for structs. For example, you can niether enumerate nor access a struct:
john = %User{} #  %User{age: 27, name: "John"}
john[:name]
#** (UndefinedFunctionError) function User.fetch/2 is undefined (User does not implement the Access behaviour)
             #User.fetch(%User{age: 27, name: "John"}, :name)
Enum.each john, fn({field, value}) -> IO.puts(value) end
#** (Protocol.UndefinedError) protocol Enumerable not implemented for %User{age: 27, name: "John"}


# However, since structs are just maps, they work with the functions from the Map module:
Map.put(%User{}, :name, "Jane") # %User{age: 27, name: "Jane"}
Map.merge(jane, %User{name: "John"}) # %User{age: 27, name: "John"}
Map.keys(jane) # [:__struct__, :age, :name]


# Default values and required keys
#
# If you don't specify a default key value when defining a struct, nil will be assumed:
defmodule Product do
  defstruct [:name]
end
%Product{} # %Product{name: nil}

# You can define a structure combining both fields with explicit default values, and implicit nil 
# values. In this case you must first specify the fields which implicity default to nil
defmoulde User do
  defstruct [:email, name: "John", age: 27]
end
%User{} # %User{age: 27, email: nil, name: "John"}

# Doing it in reverse order will raise a syntax error:
defmodule User do
  defstruct [name: "John", age: 27, :email]
end
#** (SyntaxError) iex:107: syntax error before: email

# You can also enforce that certain keys have to be specified:
defmodule Car do
  @enforce_keys [:make]
  defstruct [:model, :make]
end
%Car{}
#** (ArgumentError) the following keys must also be given when building struct Car: [:make]
    #expanding struct: Car.__struct__/1
