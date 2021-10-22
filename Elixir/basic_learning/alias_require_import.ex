
# In order to facilitate software reuse, Elixir provides three directives: 'alias', 'require', 
# and 'import' plus a macro called 'use':

# Alias the module so it can be called as Bar instead of Foo.Bar
alias Foo.Bar, as :Bar

# Require the module in order to use its macros
require Foo

# Import functions from Foo so they can be called without the 'Foo.' prefix
import Foo

# Invokes the custom code defined in Foo as an extension point
use Foo

# The first three are called directives because they have lexical scope, while use is a common 
# extension point that allows the used module.


# alias
#
# allows you to setup aliases for any given module name.
#
# Imagine a module uses a specialized list implemented in Math.List. The 'alias' directive allows 
# referring to Math.List just as 'List:
defmodule Stats do
  alias Math.List, as: List

end

# The original List can still be accessed within Stats by using the fully qualified name Elixir.List.
#
# Note: all modules defined in Elixir are defined inside the main Elixir namespace (i.e. Elixir.String)
#
# Alisases are frequently used to define shortcuts. In act, calling alias without an ':as' option 
# sets the alias automatically to the last part of the module name:
alias Math.List

# Note that 'alias' is lexically scoped, which allows you to set aliases inside specific functions:
defmodule Math do
  def plus(a, b) do
    alias Math.List
  end

  def minus(a,b) do
  end
end

# here only plus will be impacted, not minus


# require
#
# Elixir provices macros as a mechanism for metaprogramming. (writing code that generates code). Macros
# are expanded at compile time.

# Public functions in modules are globally available, but in order to use macros, you need to 
# opt-in by requiring the module they are defined in
Integer.is_odd(3) # (CompileError) you must require Integer before invoking the macro Integer.is_odd/1
require Integer
Integer.is_odd(3) # true

# In Elixir, Integer.is_odd/1 is defined as a macro so that it can be used as a gurad. This means that
# in order to invoke it, we must furst require the Integer module.
#
# Note that like the alias directive, require is also lexically scoped.


# import
#
# We use import whenever we want to access functions or macros from other modules without using
# the fully qualified name. Not we can only import public functions, as private functions are never
# accessible externally.
#
# For example if we want to use List.duplicate/2 several times, we can just import it:
import List, only: [duplicate: 2]
duplicate(:ok, 3) # [:ok, :ok, :ok]

# Although :only is optional, its usage is recommended in order to avoid importing all the functions
# of a given module inside the current scope. :execpt is also an option.
#
# Note that import too is lexically scoped. So we can import specific macros or functions inside
# function definitions.
#
# Note that imports are generally discouraged in the language. When working on your own code, prefer
# alias to import


# use
#
# The use macro is frequnetly used as an extension point. This means, that when you 'use' a module
# FooBar, you allow that module to inject any code in the current module, such as importing itself
# or other modules, defining new functions, setting module state, etc.
#
# For example, in order to write tests using the ExUnit framework, a developer should use the 
# ExUnit.Case module:
defmodule AssertionTest do
  use ExUnit.Case, async: true

  test "always pass" do
    assert true
  end
end

# Behind the scenes, 'use' requires the given module and then calls the '__using__/1' callback on it
# allowing the module to inject some code into the current context. Some modules use this mechanism
# to populate yhou rmodule with some basic behavior, which your module is intended to override or complete.
#
# Generally speaking the following:
defmodule Example do
  use Feature, option: :value
end

# is compiled to:
defmodule Example do
  require Feature
  Feature.__using__(option: value)
end

# Since use allows any code to run, we can't really know the side-effects  of using a module without
# reading its documentation. Therefor use this function with care and only if strictly required. 
# Don't use 'use' where an 'import' or 'alias' would do.


# Understanding aliases
#
# An alias in Elixir is a captialized identifer (like String, Keyword, etc.) which is converted to
# an atom during compilation. For instance, the String alias translates by default to the 
# atom :"Elixir.String":
is_atom(String) # true
to_string(String) # "Elixir.String"
:"Elixir.String" == String # true

# By using the alias directive, we are changing the atom the alias expands to.
#
# Aliases expand to atoms because in the Erlang VM (and consequently Elixir) modules are always 
# represented by atoms:
List.flatten([1, [2], 3]) # [1, 2, 3]
:"Elixir.List".flatten([1, [2], 3])  # [1, 2, 3]

# Thus this is the mechanism we use to call erlang modules:
:lists.flatten(1, [2], 3]) # [1,2,3]


# Module Nesting
#
# Consider
defmodule Foo do
  defmodule Bar do
  end
end

# The example above will define two modules, Foo and Foo.Bar. The second can be accessed as Bar 
# inside Foo as long as they are in the same lexical scope.
#
# If later, the Bar module is moved outside the Foo module definition, it must be referenced by its
# full name Foo.Bar. (or us an alias)
#
# Note in Elixir you don't have to define the Foo module before definging the Foo.Bar module:
defmodule Foo.Bar do
end

defmodule Foo do
end


# Multi alias/import/require/use
#
# It is possible to alias, import or require multiple modules at once. This is partciularly useful 
# oncewe start nesting modules, which is very common in Elixir applications. For example say you have
# MyApp module with MyApp.Foo, MyApp.Bar, and MyApp.Baz:
alias MyApp.{Foo, Bar, Baz}
