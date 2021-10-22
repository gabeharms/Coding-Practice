# functions are identified by both their name and their arity. Thus
# we use both the name and arity to describe functions
#
# i.e. trunc/1 vs trunc/2

# This syntzx also allows us to access documentation via the 'h' function
h trunc/1

# trunc is defined in the kernal module so we can identify it without a namespace

h Kernel.trunc/1
