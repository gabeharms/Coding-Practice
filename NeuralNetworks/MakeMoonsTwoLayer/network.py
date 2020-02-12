# https://medium.com/better-programming/how-to-build-2-layer-neural-network-from-scratch-in-python-4dd44a13ebba
# https://machinelearningmastery.com/generate-test-datasets-python-scikit-learn/

# Build a nueral network with one hidden layer

import numpy as np
import sklearn.datasets
import matplotlib.pyplot as plt

# Activiation functions
#
# The hidden layer will use a tanh activation function. Tanh is a
# good activiation function because it squashes a real-valued
# number to the range of [-1, 1]. But unlike sigmoid, its output is
# zero centered. Therefore, in practice, the tanh non-linearity is
# always preferred to the sigmoid non-linearity. Another strength of
# tanh, is that its gradients are stronger than sigmoid (derivatives
# are steeper). One of the disadvantages of the tanh, similar to
# sigmoid, is that it has a vanishing gradient. i.e. as you approach
# the assympotes on either side of the function, the derivative heads
# to zero, and thus, adjustments head to zero. Making it harder to
# converge on the correct weights.
#
# Tanh is already defined in the numpy libary
#
# The output layer will use sigmoid activiation function because it can product
# a value from 0 to 1 that will predict whether or not it should output on.
def sigmoid(x):
    return (1 / (1 + np.exp(-x)))


# Setting Parameters
#
# What are parameters and hyperparameters? Parameters are weights and
# biases. Hyperparameters effect parameters and are before the learning
# begins. Setting hyperparameters perfectly correctly at first is not a
# piece of cake, you’ll need to tinker and tweak your values. The learning
# rate, number of iterations, and regularization rate, among others, can
# all be considered as hyperparameters.
#
# We define W1, b1, W2, and b2. It doesn’t hurt if you set your biases to
# zero at first. However, be very careful when initializing weights. Never
# set the weights to zero at first. Why exactly? Well, if you do, then
# in Z = Wx + b, Z will always be zero. If you are building a multi-layer
# neural network, neurons in every layer will behave like there is one neuron.
# So how do we initialize weights at first? I use he initialization which
# you multiple by square root of 2/input size
#
def setParameters(X, Y, hidden_size):
  np.random.seed(3)
  input_size = X.shape[0] # number of neurons in input layer
  output_size = Y.shape[0] # number of neurons in output layer.
  print(input_size)
  print(X)
  W1 = np.random.randn(hidden_size, input_size)*np.sqrt(2/input_size)
  b1 = np.zeros((hidden_size, 1))
  W2 = np.random.randn(output_size, hidden_size)*np.sqrt(2/hidden_size)
  b2 = np.zeros((output_size, 1))
  return {'W1': W1, 'W2': W2, 'b1': b1, 'b2': b2}


def forwardPropagation(X, params):
  Z1 = np.dot(params['W1'], X)+params['b1'] # Z1 = matrix of hidden layer weights times input plus bias
  A1 = np.tanh(Z1) # A1 = activation_function(Z1)
  Z2 = np.dot(params['W2'], A1)+params['b2'] # Z2 = matrix of output layer weights times result of hidden layer activation function
  y = sigmoid(Z2) # y = activation_function(Z2)
  return y, {'Z1': Z1, 'Z2': Z2, 'A1': A1, 'y': y}


def cost(predict, actual):
  m = actual.shape[1]
  cost__ = -np.sum(np.multiply(np.log(predict), actual) + np.multiply((1 - actual), np.log(1 - predict)))/m
  return np.squeeze(cost__)

def backPropagation(X, Y, params, cache):
  m = X.shape[1]
  dy = cache['y'] - Y

  dW2 = (1 / m) * np.dot(dy, np.transpose(cache['A1'])) # sigmoid derivative
  db2 = (1 / m) * np.sum(dy, axis=1, keepdims=True)

  dZ1 = np.dot(np.transpose(params['W2']), dy) * (1-np.power(cache['A1'], 2)) # tan derivative
  dW1 = (1 / m) * np.dot(dZ1, np.transpose(X))
  db1 = (1 / m) * np.sum(dZ1, axis=1, keepdims=True)
  return {"dW1": dW1, "db1": db1, "dW2": dW2, "db2": db2}


def updateParameters(gradients, params, learning_rate = 1.2):
    W1 = params['W1'] - learning_rate * gradients['dW1']
    b1 = params['b1'] - learning_rate * gradients['db1']
    W2 = params['W2'] - learning_rate * gradients['dW2']
    b2 = params['b2'] - learning_rate * gradients['db2']
    return {'W1': W1, 'W2': W2, 'b1': b1, 'b2': b2}


def fit(X, Y, learning_rate, hidden_size, number_of_iterations = 5000):
  params = setParameters(X, Y, hidden_size)
  cost_ = []
  for j in range(number_of_iterations):
    y, cache = forwardPropagation(X, params)
    costit = cost(y, Y)
    gradients = backPropagation(X, Y, params, cache)
    params = updateParameters(gradients, params, learning_rate)
    cost_.append(costit)
  return params, cost_


X, Y = sklearn.datasets.make_moons(n_samples=500, noise=.2)
X, Y = X.T, Y.reshape(1, Y.shape[0])

params, cost_ = fit(X, Y, 0.3, 5, 5000)
plt.plot(cost_)
plt.savefig("./cost_function.png")
