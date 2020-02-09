# https://towardsdatascience.com/how-to-build-a-simple-neural-network-from-scratch-with-python-9f011896d2f3

# A Neural Network is actually a function of many variables. It 
# takes an input, makes computations and produces an ouput. We 
# like to visualize it as neurons in different layers, with each
# neuron in a alyer connected with all neurons in the previous 
# and next layer.

# All the computations take place inside those neurons and 
# depend on the weights that connect the neurons with each 
# other. So all we have to do is learn the right weights in 
# order to get  the desired output.

# Their structure is generally very complex including a lot of
# layers and even more than a million neurons in order to be 
# able to handle the large datasets of our era. However, in 
# order to understand how large deep networks work, one should
# start with the simplest ones.

# Thus, below we will implement a very simple network with 2 
# layers. In order to do that we need a very simple dataset
# as well, so we will use the XOR dataset. A and B are the 2
# inputs of the Neural Network (NN) and A XOR B is the output.

# We will try to make our NN learn weights such that whatever 
# pair of A and B it takes as input, it will return the correct
# corresponding result.

# First we need to define the structure of our Neural Network. 
# Because our dataset is relatively simple, a network with just 
# one hidden layer will do fine. So we will have an input layer, 
# a hidden layer and an output layer. 

# Next we need an activation function. The sigmoid function is a
# good choice for the last layer because it outputs values between
# 0 and 1 while tanh(hyperbolic tangent) works better in the hidden
# layer, but every other commonly used function will work(e.g. ReLU).

# Tanh is a good activiation function because it squashes a real-valued
# number to the range of [-1, 1]. But unlike sigmoid, its output is
# zero centered. Therefore, in practice, the tanh non-linearity is 
# always preferred to the sigmoid non-linearity. Another strength of
# tanh, is that its gradients are stronger than sigmoid (derivatives
# are steeper). One of the disadvantages of the tanh, similar to
# sigmoid, is that it has a vanishing gradient. i.e. as you approach
# the assympotes on either side of the function, the derivative heads
# to zero, and thus, adjustments head to zero. Making it harder to 
# converge on the correct weights.

# Research should be done into ReLU and LeakyReLu as these activation
# activation functions attempt to solve the vanishing gradient problem.

# Sigmoid(z) = 1 / (1 + e^(-z)), z= theta * input. Where theta is a weight matrix and input is the training data matrix 
# tanh(z) = (e^z - e^(-z)) / (e^z + e^(-z)), z= theta * input. Where theta is a weight matrix and input is the training data matrix

# Keep in mind activiation functions themselves are only used to determine
# what the prediction of the node is. It is not used during training. 
# Only the derivatives are used during training:

# Sigmoid'(z) = Sigmoid(z) * (1 - Sigmoid(z)), z= theta * input. Where theta is a weight matrix and input is the training data matrix 
# tanh'(z) = 1 - tanh(z)^2 , z= theta * input. Where theta is a weight matrix and input is the training data matrix

# Diagram of our NN:
# input  | hidden | output
# --------------------------
#   X   -   Node  \
#       \/           Node
#   Y   -   Node  /
# --------------------------
#     W1,b1|A1|W2,b2|A2

# A1 = tanh((Input * W1) + b1)
# A2 = sigmoid((Input * W2) + b2)

import numpy as np

def sigmoid(z):
  return 1/(1 + np.exp(-z))

# n_x, n_h, and n_y represent the number of nodes in each layer.
# We will initialize our weight matricies randomly based on
# the parameters.
def initialize_parameters(n_x, n_h, n_y):
  W1 = np.random.randn(n_h, n_x) # random from normal distribution
  b1 = np.zeros((n_h, 1))
  W2 = np.random.randn(n_y, n_h) # random from normal distribution
  b2 = np.zeros((n_y, 1))

  parameters = {
    "W1": W1,    # Input layer to Hidden layer weights. Its matrix shape is [nodes in input layer, nodes in hidden layer]
    "b1" : b1,   # Input layer to Hidden layer biases. Its matrix shape is [nodes in input layer, nodes in hidden layer]
    "W2": W2,    # Hidden layer to Output layer weights. Its matrix shape is [nodes in hidden layer, nodes in output layer]
    "b2" : b2    # Hidden layer to Output layer weights. Its matrix shape is [nodes in hidden layer, nodes in output layer]
  }
  return parameters

# Forward progression allows us to pass inputs all the way 
# from the input layer to the output layer. Here X is the
# raw input. parameters is the hash initialized above.
def forward_prop(X, parameters):
  W1 = parameters["W1"]
  b1 = parameters["b1"]
  W2 = parameters["W2"]
  b2 = parameters["b2"]

  Z1 = np.dot(W1, X) + b1   # Z1 = matrix of result inputs * weights between input layer and hidden layer
  A1 = np.tanh(Z1)          # A1 = matrix of activation function results in hidden layer
  Z2 = np.dot(W2, A1) + b2  # Z2 = matrix of result A1 * W2 weights between hidden and output layer 
  A2 = sigmoid(Z2)          # A2 = matrix of activation function results in output layer

  cache = {
    "A1": A1,
    "A2": A2
  }
  return A2, cache


# Now we must compute the loss function. We will use the
# "Cross Entropy" loss function.
def calculate_loss(A2, Y, m):
  # this function is Sigmoid'(z)
  cost = -np.sum(np.multiply(Y, np.log(A2)) +  np.multiply(1-Y, np.log(1-A2)))/m
  cost = np.squeeze(cost) # basicaaly transpose

  return cost

# Now the most difficult part of the NN algorithm
# This function will return the gradients of the Loss function 
# with respect to the 4 parameters of our network(W1, W2, b1, b2):
def backward_prop(X, Y, cache, parameters, m):
  A1 = cache["A1"]
  A2 = cache["A2"]

  W2 = parameters["W2"]

  # Notice the order has reversed from the forward_prop. makes sense huh
  dZ2 = A2 - Y
  dW2 = np.dot(dZ2, A1.T)/m
  db2 = np.sum(dZ2, axis=1, keepdims=True)/m
  dZ1 = np.multiply(np.dot(W2.T, dZ2), 1-np.power(A1, 2))
  dW1 = np.dot(dZ1, X.T)/m
  db1 = np.sum(dZ1, axis=1, keepdims=True)/m

  # Gradients for all weights and biases
  grads = {
    "dW1": dW1,
    "db1": db1,
    "dW2": dW2,
    "db2": db2
  }

  return grads

# Now that we have all the graidents from the loss function, we can move on
# to the actual learning. We will use gradient descent algorithm to update 
# our parameters and make our model learn with the learned rate passed in as
# a parameter. Gradient descent = descend weights according to value of gradient
def update_parameters(parameters, grads, learning_rate):
  W1 = parameters["W1"]
  b1 = parameters["b1"]
  W2 = parameters["W2"]
  b2 = parameters["b2"]

  dW1 = grads["dW1"]
  db1 = grads["db1"]
  dW2 = grads["dW2"]
  db2 = grads["db2"]

  W1 = W1 - learning_rate*dW1
  b1 = b1 - learning_rate*db1
  W2 = W2 - learning_rate*dW2
  b2 = b2 - learning_rate*db2
  
  new_parameters = {
    "W1": W1,
    "W2": W2,
    "b1" : b1,
    "b2" : b2
  }

  return new_parameters

# By now we have implemented all the functions needed for one circle of training. Now 
# all we have to do is just put them all together inside a function called model() and 
# call model() from the main program.

# Model() function takes as input the features matrix X, the labels matrix Y, the number
# of units n_x, n_h, n_y, the number of iterations we want our Gradient Descent algorithm
# to run and the learning rate of Gradient Descent and combines all the functions above '
# to return the trained parameters of our model:
def model(X, Y, n_x, n_h, n_y, num_of_iters, learning_rate):
  parameters = initialize_parameters(n_x, n_h, n_y)

  print("-----------------------------------")
  print("Starting weights and biases:")
  print("-----------------------------------")
  print("W1:")
  print(parameters["W1"])
  print("b1:")
  print(parameters["b1"])
  print("W2:")
  print(parameters["W2"])
  print("b2:")
  print(parameters["b2"])
  print("-----------------------------------")

  for i in range(0, num_of_iters+1):
    a2, cache = forward_prop(X, parameters)

    cost = calculate_loss(a2, Y, X.shape[1])

    grads = backward_prop(X, Y, cache, parameters, X.shape[1])

    parameters = update_parameters(parameters, grads, learning_rate)

    if(i%100 == 0):
      print('Cost after iteration# {:d}: {:f}'.format(i, cost))


  print("-----------------------------------")
  print("Ending weights and biases:")
  print("-----------------------------------")
  print("W1:")
  print(parameters["W1"])
  print("b1:")
  print(parameters["b1"])
  print("W2:")
  print(parameters["W2"])
  print("b2:")
  print(parameters["b2"])
  print("-----------------------------------")

  return parameters

# The training part is now over. The function above will return the trained parameters of
# our NN. Now we just have to make our prediction. The function predict(X, parameters) takes
# as input the matrix X with elements the 2 numbers for which we want to compute the XOR 
# function and the trained parameters of the model and returns the desired result y_predict
# by using a threshold of 0.5:
def predict(X, parameters):
  a2, cache = forward_prop(X, parameters)
  yhat = a2
  yhat = np.squeeze(yhat)
  if(yhat >= 0.5):
    y_predict = 1
  else:
    y_predict = 0

  return y_predict

def main():
    np.random.seed(2)

    # The 4 training examples by columns
    X = np.array([[0, 0, 1, 1], [0, 1, 0, 1]])

    # The outputs of the XOR for every example in X
    Y = np.array([[0, 1, 1, 0]])

    # No. of training examples
    m = X.shape[1]
    # Set the hyperparameters
    n_x = 2     #No. of neurons in first layer
    n_h = 5     #No. of neurons in hidden layer
    n_y = 1     #No. of neurons in output layer
    num_of_iters = 1000
    learning_rate = 0.3

    trained_parameters = model(X, Y, n_x, n_h, n_y, num_of_iters, learning_rate)

    X_test = np.array([[1], [1]])
    y_predict = predict(X_test, trained_parameters)
    # Print the result
    print('Neural Network prediction for example ({:d}, {:d}) is {:d}'.format(
        X_test[0][0], X_test[1][0], y_predict))

main()
