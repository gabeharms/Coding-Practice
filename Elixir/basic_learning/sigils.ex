
# We have already learned that Elixir provides double quoted strings and single
# quoted char lists. However, this only covers the surface of structures that have textual 
# representation in elixir. 
#
# One of Elixir's goals is extensibility: developers should be able to extned the language to
# fit any particular domain. Computer science has become such a wide field that it is impossible
# for a language to tackle all aspects as a part of its core. Instead elixir aims to make itself
# extensible so developers, companies, and communiities can extend the language.
#
# here we will explore sigils, which are one of the mechanisms provided by the language for working 
# with textual representations. Sigils start with a tilde '~' character which is followed by a 
# letter and thena  delimiter;


# Regular expressions

# The most common sigil in Elixir is ~r, which is used to create regular expressions:
regex = ~r/foo|bar/ # ~r/foo|bar/
"foo" =~ regex # true
"bat" =~ regex # false

# Checkout the Regex module for more information and features.


# Strings, char lists, and word list sigils
# 
# Besides regular expressions, Elixir ships with 3 other sigils.
#
# Strings
#
# The ~s sigil is used to generate strings, like double quotes are. It is useful when a string contains
# double quotes:
~s(this is a string with "double" quotes, not 'single' ones) # "this is a string with \"double\" quotes, not 'single' ones"

# Char Lists
# 
# The ~c sigil is useful for generating char lists that contain single quotes:
~c(this is a char list containing 'single quotes') #  'this is a char list containing \'single quotes\''

# Word lists
#
# The ~w sigil is used to generate lists of words
~w(foo bar bat) # ["foo", "bar", "bat"]

# It also accepts c,s and a modifiers:
~w(foo bar bat)a # [:foo, :bar, :bat]


# Interpolation and escaping in string sigils
#
# Elixir supporst some sigil variants to deal with escaping characters and interpolation. For example
# the ~s and ~S will return strings; the former allows escape codes and interpolation while the latter
# does not
~s(String with escape codes \x26 #{"inter" <> "polation"}) # "String with escape codes & interpolation"
~S(String without escape codes \x26 without #{interpolation}) # "String without escape codes \\x26 without \#{interpolation}"


# Calendar sigils
#
# Date
#
# A '%Date{}' struct contains the fields 'year', 'month', 'day', and calendar. You can create one
# using the ~D sigil
d = ~D[2019-10-31]
d.day # 31

# Time
#
# The '%Time{}' struct contains the fields 'hour', 'minute', 'second', 'microsecond' and 'calendar.
# You can create one using the ~T sigil
t = ~T[23:00:07.0]
t.second # 7

# NaiveDateTime
#
# The %NaiveDateTime{} struct contains fields from both Date and Time. You can create one using
# the ~N sigil
ndt = ~N[2019-10-31 23:00:07]

# It is called naive because it does not contain timezone information.

# UTC DateTime
#
# A '%DateTime{}' struct contains the same fields as a NaiveDateTime with the addition of fields
# to track timezones. It can be created using the ~U sigil:
dt = ~U[2019-10-31 19:59:03Z]
%DateTime{minute: minute, time_zone: time_zone} = dt # ~U[2019-10-31 19:59:03Z]
minute # 59
time_zone # "Etc/UTC"



# Custom Sigils
#
# Sigils are extensible and in fact using the sigil ~r/foo/i is equivalent to calling the 
# sigil_r with a binary can dchar list as the argument:
sigil_r(<<"foo">>, 'i') # ~r"foo"i

# We can acces the documentation for the ~r sigil via sigil_r:
h sigil_r

# We can also provide our own sigils by impolementing functions that follow the sigil_{character} 
# pattern. For example, lets implement the ~i sigil that returns an integer:
#
defmodule MySigils do
  def sigil_i(string, []), do: String.to_integer(string)
  def sigil_i(string, [?n]), do: -String.to_integer(string)
end
import MySigils
~i(13) # 13
~i(42)n # -42

# Sigils can also be used to do compile-time work with the help of macros
#
# For example regular expressions in Elixir are compiled into an efficient represeentation
# during the compliation of the source code, therefore skipping this step at runtime. 
#
# If you are interested you can learn more about macros and check out how sigils are implemented
# in the Kernel module.
