
# Unicode and code points
# In order to fascilitate meaningful communication between computers across multiple languages,
# a standard is required so that ones and zeroes on one machine mean the same thing on another.
# The unicode standard acts as an official registry of all the characters we know. 
#
# Unicode organizes all of the characters in its repertoire into code charts, and each character
# is given a unique numerical index known as a 'code point'.

# In elixir you can use '?' in front of a character to reveal its code point:
?a # 97

# Note that most charts refer to code points in hex and 97 thus translates to 0061 in hex.


# UTF-8 and encodings
# Where the code point is what we store, an encoding deals with how we store it: 
# encoding is an implementation. In other words, we need a mechanism to convert the code point
# numbers into bytes so they can be stored in memory / written to disk.
#
# Elixir uses UTF-8 to encode its strings, which means code points are encoded as a series of 8
# bit bytes. UTF-8 is a variable width character encoding that uses one to four bytes to store each
# code point.
#
# UTF-8 also supports graphemes which are multiple characters perceived as one:
string = "héllo"
String.length(string) # 5
length(String.to_charlist(string)) # 6
byte_size(string) # 7

# String.length/1 counts graphemems. String.to_charlist/1 converts a strong to a list of codepoints,
# whose lenght is 6. Finally byte_size/1 reveals the number of underlying raw bytes needed to 
# store the string. (i.e. two bytes for accute accent)

# A common trick in Elixir when you want to see the inner binary representation of a string
# is to concatenate the null byte "<<0>>' to it:
"hełło" <> <<0>> #<<104, 101, 197, 130, 197, 130, 111, 0>>

# You can also use the IO.inspect/2
IO.inspect("hełło", binaries: :as_binaries)


# Bitstrings
# A bitstring is a fundamental data type in Elixir, denoted with the '<<>>' syntax. A bitstring
# is a contiguous sequence of bits in memory.

# By default , 8 bits (i.e. a byte) is used to store each number in a bitstring, but you can 
# manually specify the number of bits via a '::n' modifier to denote the size in 'n' bits:
<<42>> === <<42::8>> # true
<<3::4>> # <<3::size(4)>>
<<0::1, 0::1, 1::1, 1::1>> == <<3::4>> # true

# Any value that exceeds what can be stored in 8 bits is truncated
<<1>> === <<<257>> # true since a byte can only go to 256



# Binaries
# A binary is a bitstring where the number of bits is divisible by 8. That means every binary is 
# a bitstring, but not every bitstring is a binary. is_bitstring/1 and is_binary/1

# can pattern match on binaries and bitstrings:
<<0, 1, x>> = <<0, 1, 2>>
x # 2

# A string is a UTF-8 encoded binary, where the code point for each character is encoded using
# 1 to 4 bytes.
is_binary("hello") # true

# Given that strings are binaries, we can also pattern match on strings:
<<head, rest::binary> = "banana"
head == ?b # true
rest # "anana"


# Charlists
# A charlist is a list of integegers where all the integers are valid code points. They are not common
# except when interfacing w/ erlang.
#
# Whereas strings (i.e. binaries) are created using double-quotes, charlists are created with
# single quoted literals:
'hello' # 'hello
[?h, ?e, ?l, ?l, ?o] # 'hello'

# You can convert charlists to a string and back using to_string/1 and to_charlist/1:
to_charlist("hello")
to_string('hello')

# String (binary) concatenation uses the '<>' operator but charlists, being lists, use the '++' operator
