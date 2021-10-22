
# Keyword Lists
# In many functional languages, it is common to use a list of 2-item tuples
# as the representation of a key-value data structure. In elixir we have a list
# of tuples and the first item of the tuple (i.e. the key) is an atom, we call it a keyword
# list:
list = [{:a, 1}, {:b, 2}] # [a:1, b: 2]
list == [a:1, b:2] # true

# As you can see above there are 2 syntaxes for expressing keyword lists in elixir
list ++ [c:3] # [a:1, b:2, c:3]
list[:a] # 1

# Keyword lists are important because they have 3 special characteristics:
# - keys must be atoms
# - keys are ordered
# - keys can be given more than once

# For example the ecto library makes use of these features to provide an elegant DSL for writing
# database queries:
query = 
  from w in Weather,
    where: w.prcp > 0,
    where: w.temp < 20,
    select: w

# These characteristics are what prompted keyword lists to be the default mechanism for passing
# options to functions in elixir

# Example from if
if (false, [{:do, :this}, {:else, :that}])


# Although we can pattern match on keyword lists it is rarely done because you have to 
# match on the same number of items in the list (just as normal lists).

# Remember that keyword lists are just lists and provide the same linear performace. The longer
# the list, the longer it will take to find a key. For this reason, keyword lists are used
# mainly to pass in optional values. 


# Maps

  # Whenever you need a key-value store, maps are the go to data structure in Elixir. A map is created
  # using the '%{}' syntax
map = %{:a => 1, 2 => :b}
map[:a] # 1
map[2] # :b
map[:c] # nil

# Compared to keyword lists:
# - maps allow any value as a key
# - maps' keys do not follow any ordering

# Maps can be very usesful with pattern matching as it matches on subsets as well
%{} = %{:a => 1, 2 => :b} # %{} = %{:a => 1, 2 => :b}
%{:a => a} = %{:a => 1, 2 => :b} #  %{:a => 1, 2 => :b}
a # 1

# As shown, a map matches as long as they keys in the pattern exist in the given map, therefor
# an empty map matches al lmaps

# Variables can be used when accessing, matching and adding map keys:
n = 1
map = %{n => :one}
map[n] # :one

%{^n => :one} = %{1 => :one, 2 => :two, 3 => :three} # match

# The Map module provides functions to manipulate maps
Map.get(%{:a => 1, 2 => :b}, :a) # 1
Map.put(%{:a => 1, 2 => :b}, :c, 3) 
Map.to_list(%{:a => 1, 2 => :b}) # [{2, :b}, {:a, 1}]

# Syntax for updating a key's value
map = %{:a => 1, 2 => :b}
%{map | 2 => "two"} #  %{2 => "two", :a => 1}
%{map | :c => 3}  # ** (KeyError) key :c not found in: %{2 => :b, :a => 1}

# you can see that the key must exist.

# when all keys in the map are symbols/atoms, you can use the keyword syntax in maps for convenience:
map = %{a: 1, b: 2}

# another nice piece of syntax for accessing atom keys in maps:
map = %{:a => 1, 2 => :b}
map.a # 1
map.c # KeyError



# Nested Data Structures

# Often we will have maps inside maps, or keyword lists inside maps, etc. Elixir provides conveniences for
# manipulating nested data strcutrues via the put_in/2, update_in/2. 
users = [
  john: %{name: "John", age: 27, languages: ["Erlang", "Ruby", "Elixir"]},
  mary: %{name: "Mary", age: 29, languages: ["Elixir", "F#", "Clojure"]}
]
users[:john].age # 27
users = put_in users[:john].age, 31

users[:john].age # 31

# The update_in/2 macro is similar but allows us to pass a function that controls how the value
# changes. 
users = update_in users[:marry].languages, fn languages -> List.delete(languages, "Clojure") end

# There are other macros here to explore like get_and_update_in/2, put_in/3, update_in/3, 
# and get_and_update_in/3
