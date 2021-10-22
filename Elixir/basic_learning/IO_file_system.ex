
# The IO module
#
# The IO module is the main mecahnism in Elixir for reading and writing to standard input/output,
#  standard error, files, and other IO devices. Usage of the module is pretty straightforward:
IO.puts("hello world")
IO.gets("yes or no?")
  # yes or no?
  # yes\n"

# by default, functions in the IO module read from :stdin and write to :stdout. We can change
# that by passing :stderr as an argument:
IO.puts(:stderr, "hello world")


# The File Module
#
# The File module contains functions that allow us to open files as IO devices. By default, files
# are opened in binary mode, which requires developers to use the specific IO.binread/2 and
# IO.binwrite/2 functions from the IO module:
{:ok, file} = File.oopen("hello", [:write]) # {:ok, #PIOD<0.47.0>}
IO.binwrite(file, "world") # :ok
File.close(file) # :ok
File.read("hello") # {:ok, "world"}

# A file can also be opened with :utf8 encoding, which tells the File module to interpret the bytes
# read from the file as UTF-8 encoded bytes.

# Besides functions for opening, reading and writing files, the File module has many functions to
# work with the file system. Those functionas are named after their UNIX equivalients. For example,
# File.rm/1, File.mkdir/1, File.mkdir_p/1, File.cp_r/2, File.rm_rf/1.
#
# You will also notice that the functions in the File module have two variants: one "regular" variant
# and another variant with a trailing bang (!):
File.read("hello") # {:ok, "world"}
File.read!("hello") # "world"
File.read("unknown") # {:error, :enoent}
File.read!("unknown") # File.Error exception

# The version without the '!' is prefereed when you want to handle different outcomes using pattern
# matching:
case File.read(file) do
  {:ok, body } -> # do something
  {:error, reason} -> # handle error
end

# However, if you expect the file to be there '!' is more useful as it raises a more meaningful
# error message.


# The Path module
#
# The majority of the functions in the File module expect paths as arguments. Most commonly, those
# paths will be regular binaries. The Path module provides facilities for working with such paths:
Path.join("foo", "bar") # "foo/bar"
Path.expand("~/hello") # "/Users/jose/hello"

# Using functions from the Path module as opposed to directly manipulating string si preferred since
# the Path module takes care of different operating systems transparently.
#
# Finally keep in mind that Elixir will automatically convert slashes '/' into backslashes '\' on
# Windows

# With this, we have covered the main modules that Elixir provides for dealing with IO and interacting
# with the file system.


# Processes
#
# You man have noticed that File.open/2 returns a tuple like '{:ok, pid}'
# That happens because the IO module actually works with processes. Given a file is a process,
# when you write to a file tht has been closed, you are actually sending a message to a process
# whch has been terminated:
File.close(file)
IO.write(file, "is anybody out there") # {:error, :terminated}

# When calling IO.write(pid, binary), the IO module sends a message to the process identified by pid
# with the desired operation.
pid = spawn(fn ->
receive do: (msg -> IO.inspect msg)
 end)
#PID<0.57.0>
IO.write(pid, "hello")
#{:io_request, #PID<0.41.0>, #Reference<0.0.8.91>,
 #{:put_chars, :unicode, "hello"}}
#** (ErlangError) erlang error: :terminated

# We can see the request sent by the IO module printed out. Soon after that it fails because the IO
# module expected some kind of result that we did not suppuly.
#
# By modeling IO devices with processes, the Erlang VM allows I/O messages to be routed between
# different nodes running in Distributed Erlang or even change files to perform read/write operations
# across nodes.


# iodata and chardata
#
# In all the above examples, we used binaries when writing to files. Previously we outlined how strings
# are made of bytes while charlists are lists with Unicode codepoints.
#
# The functions in IO and File also allow lists to be given as arguments. In fact they allow
# mised lists of lists, integers, and binaries:
iex> IO.puts('hello world') # hello world
iex> IO.puts(['hello', ?\s, "world"]) # hello world

# However, using lists in IO operations requires some attention. A list may represent either a
# bunch of bytes or a bunch of characters and which one to use depends on the encoding of the IO device.
#
# If the file is opened without encoding, the file is expected to be in raw mode, and the functions
# in the IO module starting with bin* must be used. Those functions expect an 'iodata' as an argument.
# i.e. they expect a list of integers representing bytes or binaries to be given.
#
# On the other hand, :stdio' and files ope ned with :utf8 encoding work with the remaining functions
# in the IO module. Those functions expect a 'chardata' as an argument, that is, a list of charcters
# or strings.
#
# Although this is a sublte difference, you only need to worry abou these details if you intend to
# pass lists to those functions. Binaries are already represented by the underliying bytes and as
# such their representation is always raw.
