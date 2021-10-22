# In Elixir all code runs inside processes. Processes are isolated from each other, 
# run concurrent to one another and communicate via message passing. Processes are not only
# the basis for concurrency in Elixir, but they also provide the means for building
# distributed and fault-tolerant progresm.
#
# Elixir's processe should not be confused with operating system processes. Processes in Elixir
# are extremely lightweight in terms of memory and CPU. Because of this, it is not uncommon to
# have tens or even hundreds of thousands of processes running simultaneously.


# spawn
# The basic mechanism for spawning new processes is the auto imported spawn/1 function:
spawn(fn -> 1 + 2 end) # PID<0.43.0

# spawn/1 takes a function which it will execute in another process.

# Notice spawn/1 returns a PID. At this point, the process you spawned is very likely dead. 
# The spawned process will execute the given function and exit after the 
# function is done:
pid = spawn(fn -> 1 + 2 end)
Process.alive?(pid) # false


# We can retrieve the PID of the current process by calling self/0:
self # #PID<0.41.0>
Process.alive?(self()) # true



# send and receive
#
# We can send messages to a process with send/2 and receive them with receive/1:

send(self(), {:hello, "world"})
receive do
  {:hello, msg} -> msg
  {:world, _msg} -> "won't match"
end
# world

# when a message is sent to a process, the message is stored in the process mailbox. The recieve/1
# block goes through the current process mailbox searching for a message that matches any of the 
# given patterns. receive/1 supports  guards and many clauses such as case/2.

# The process that sends the message does not block on 'send/2', it puts the message in the recipient's
# mailbox and continues. In particular, a process can send messages to itslef.

# if there is no message in the mailbox matching any of the patterns, the current process will wait
# until a message arrives. A timeout can also be specificed
receive do
  {:hello, msg} -> msg
after
  1_000 -> "nothing after 1s"
end
# "nothing after 1s"

# A timeout of 0 can be given when you already expect the message to be in the mailbox

# Putting it all together
parent = self()

spawn(fn -> send(parent, {:hello, self()}) end)

receive do
  {:hello, pid} => "Got hello from #{inspect pid}"
end
# "Got hello from #PID<0.48.0>"

# The inspect/1 is used to convert a data structure's internal representation into a string,
# typically for printing. Notice that when the receive block gets executed the sender process
# we have spawned may already be dead, as its only instruction was to send a message.
#
# While in the shell, you may find the helper flush/0 quite useful. It flushes and prints
# all the messages in the mailbox
send(self(), :hello)
flush()


# Links
# The majority of times we spawn prcesses in Elixir, we spawn them as linked processes. Before 
# we show an example with spawn_link/1, let's see what happens when a process started with 
# spawn/1 fails:
spawn(fn -> raise "oops" end)
# [error] Process
# (RuntimeError) oops

# It merely logged an error but the parent process is still running. That's because the processes
# are isolated. If we want the failure in one process to propagate to another one, we should
# link them. This can be with spawn_link/1:
self()
spawn_link(fn -> raise "oops" end)
#** (EXIT from #PID<0.41.0>) evaluator process exited with reason: an exception was raised:
    #** (RuntimeError) oops
        #(stdlib) erl_eval.erl:668: :erl_eval.do_apply/6

# Because processes are linked, we now see a message saying the parent process, which is the shell
# process, has received an EXIT signal from another process causing the shell to terminate. 
#
# Linking can also be done manually by calling Process.link/1. We recommend exploring the Process
# module for other functionality provided by processes
#
# Processes and links play an important role when building fault-tolerant systems. Elixir processes
# are isolated and don't share anything by default. Therefore, a failure in ap rocess will never 
# crash or corrupt the state of another proces. Links, however, allow processes to establish
# a relationship in case of failure. Often we link our processes to supervisors which will detect 
# when a process dies and start a new process in its place.
#
# While other languages would require us to catch/handle exceptions, in Elixir we are actually fine
# with letting processes fail because we expect supervisors to properly restart our systems.
# "Failing fast" is a common philosophy when writing Elixir.
#
# spawn/1 and spawn/1 are the basic primitives for creating processes in Elixir. Although we have 
# used them exclusively so far, most of time we are going to use abstractions that build on top of them.

# Tasks
# Tasks build on top of the spawn functions to provide beter reports and introspection:
Task.start(fn -> raise "oops" end)
#15:22:33.046 [error] Task #PID<0.55.0> started from #PID<0.53.0> terminating
#** (RuntimeError) oops
    #(stdlib) erl_eval.erl:668: :erl_eval.do_apply/6
    #(elixir) lib/task/supervised.ex:85: Task.Supervised.do_apply/2
    #(stdlib) proc_lib.erl:247: :proc_lib.init_p_do_apply/3
#Function: #Function<20.99386804/0 in :erl_eval.expr/5>
    #Args: []

# Instead of spawn/1 and spawn_link/1 we use Task.start/1 and Task.start_link1 which return
# {:ok, pid} rather than just the PID. This is what enables tasks to be used in supervision trees.
# Furthermore, Task provides convenience functions like Task.async/1 and Task.await/1.


# State
#
# We haven't talke dabout state so far in this guide. If you are building an application taht 
# requires state, for example to keep your application configuration, or you need to parse and
# a file and keep it in memory, where would you store it?
#
# Processes are the most common answer to this question. We can write proceses that loop infinitely,
# maintain state, and send and receive messages. As an example, lets write a module that starts new 
# proceses that work as a key-value store in a file named kv.exs
defmodule KV do
  def start_link do
    Task.start_link(fn -> loop(%{}) end)
  end

  defp loop(map) do
    receive do
      {:get, key, caller} ->
        send caller, Map.get(map, key)
        loop(map)
      {:put, key, value} ->
        loop(Map.put(map, key, value))
    end
  end
end

# Note that the start link function starts a new process that runs the loop/1 function, starting
# with an empty map. The loop/1 private function then waits for message and performs the appropriate
# action for each message. In the case of a :get message, it sends a message back to the caller and
# calls loop again, to wait for a new message. While the :put message actually invokes loop/1 with
# a new version of the map, with the given key and value stored.
KV.start_link() # {:ok, #PID<0.62.0>}
send(pid, {:get, :hello, self()}) # {:get, :hello, #PID<0.41.0>}
flush() # nil

# Notice how the process is keeping a state and we can get and update this state by sending the
# process messages. In fact, any process that knows the pid above wil be able to send it messages
# and maninpulate the state.

# It is also possible to register the pid, giving it a name, and allowing everyone that knows
# the name to send it messages:
Process.register(pid, :kv)
send(:kv, {:get, :hello, self()} )
flush() # :world

# Using processes to maintain state and name registration are very common patterns in Elixir
# applications. However, most of the time, we won't implement those patterns manually as above,
# but by using one of the many abstractions that ship with Elixir. For example, Elixir provides
# agents, which are simple abstractions around state.
{:ok, pid} = Agent.start_link(fn -> %{} end)
Agent.update(pid, fn map -> Map.put(map, :hello, :world) end)
Agent.get(pid, fn map -> Map.get(map, :hello, :world) end) # :world

# A :name option could also be given to Agent.start_link/2 and it would be automatically registered.
# Besides agents, Elixir provides an API for building generic servers (called GenServer), tasks
# and more, all powered by processes underneath. Those, along with supervision trees, will be explored
# with more detail in the Mix and OPT guide which will build a complete Elixir application from start
# to finish.
