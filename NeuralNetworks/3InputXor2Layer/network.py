import numpy as np
import matplotlib.pyplot as plt

# What's a nueral network?

# Most introductory texts to Neural Networks brings up brain analogies when
# describing them. Without delving into brain analogies, I find it easier to
# simply describe Neural Networks as a mathematical function that maps a
# given input to a desired output.

# Neural Networks consist of the following components
#   - An input layer, x
#   - An arbitrary amount of hidden layers
#   - An output layer, ŷ
#   - A set of weights and biases between each layer, W and b
#   - A choice of activation function for each hidden layer, σ. In this tutorial, we’ll use a Sigmoid activation function.

class NeuralNetwork:
    def __init__(self, x, y):
        hidden_layer_size = 4
        self.input      = x
        self.weights1   = np.random.rand(hidden_layer_size, self.input.shape[0])  # means hidden layer has 4 neurons
        self.biases1    = np.zeros((hidden_layer_size,1))  # means hidden layer has 4 neurons
        self.weights2   = np.random.rand(1, 4) # map 4 nueron middle layer to single neuron output layer
        self.biases2    = np.zeros((1,4))
        self.y          = y
        self.output     = np.zeros(y.shape)

    def sigmoid(self, z):
        return 1 / (1 + np.exp(-z))

    def sigmoid_derivative(self, output):
        return output * (1 - output)

# Feedforward
#
# The output ŷ of a simple 2-layer Neural Network is:
#
#     ŷ = activation_function(W2 * (activation_function(W1*Input) + b1) + b2)
#
# You might notice that in the equation above, the weights W and the biases b are the only variables
# that affects the output ŷ.
#
# Naturally, the right values for the weights and biases determines the strength of the predictions.
# The process of fine-tuning the weights and biases from the input data is known as training the Neural
# Network.
#
# Each iteration of the training process consists of the following steps:
#   - Calculating the predicted output ŷ, known as feedforward
#   - Updating the weights and biases, known as backpropagation
#
# As we’ve seen in the sequential graph above, feedforward is just simple calculus and for a basic
# 2-layer neural network, the output of the Neural Network is:
#
#    ŷ = activation_function(W2 * (activation_function(W1*Input) + b1) + b2)

# Let’s add a feedforward function in our python code to do exactly that. Note that for simplicity,
# we have assumed the biases to be 0.
    def feedforward(self):
        self.layer1 = np.tanh(np.dot(self.weights1, self.input) + self.biases1) # Same activation functions in both layers
        self.output = self.sigmoid(np.dot(self.weights2, self.layer1) + self.biases2)


# However, we still need a way to evaluate the “goodness” of our predictions (i.e. how far off are
# our predictions)? The Loss Function allows us to do exactly that.

# Loss function
#
# There are many available loss functions, and the nature of our problem should dictate our choice
# of loss function. In this tutorial, we’ll use a simple sum-of-sqaures error as our loss function.
#
# That is, the sum-of-squares error is simply the sum of the difference between each predicted value
# and the actual value. The difference is squared so that we measure the absolute value of the
# difference.
#
# Sum of Squares = sum((y_actual - y_pred)^2)
#
# Our goal in training is to find the best set of weights and biases that minimizes the loss function.

# Backpropagation

# Now that we’ve measured the error of our prediction (loss), we need to find a way to propagate
# the error back, and to update our weights and biases.
#
# In order to know the appropriate amount to adjust the weights and biases by, we need to know the
# derivative of the loss function with respect to the weights and biases.
#
# Recall from calculus that the derivative of a function is simply the slope of the function.

# If we have the derivative, we can simply update the weights and biases by increasing/reducing with
# it(refer to the diagram above). This is known as gradient descent.

# However, we can’t directly calculate the derivative of the loss function with respect to the
# weights and biases because the equation of the loss function does not contain the weights and
# biases. Therefore, we need the chain rule to help us calculate it.

#   Loss(y_actual, y_pred) = sum((y_actual - y_pred)^2)
#   dLoss(y_actual, y_pred) / dW = dLoss(y_actual, y_pred) / dy_actual * dy_pred/dz * dz/dW    where z = Wx + b
#                                = 2(y actual - y_pred * derivative of sigmoid * x
#                                = 2(y actual - y_pred * z(1-z) * x
#
# Thi sis the chain rule for calculating derivative of the loss function with respect to the weights.
# Note that for simplicity, we have only displayed the partial derivative assuming a 1-layer Neural
# Network.

# Phew! That was ugly but it allows us to get what we needed — the derivative (slope) of the loss
# function with respect to the weights, so that we can adjust the weights accordingly.

# Now that we have that, let’s add the backpropagation function into our python code.
    def backprop(self):
        learning_rate = 1.2
        m = self.input.shape[1]
        dy = self.output - self.y

        dW2 = (1 / m) * np.dot(dy, np.transpose(self.layer1)) # sigmoid derivative
        db2 = (1 / m) * np.sum(dy, axis=1, keepdims=True)

    
        dZ1 = np.dot(np.transpose(self.weights2), dy) * (1-np.power(self.layer1, 2)) # tan derivative
        dW1 = (1 / m) * np.dot(dZ1, np.transpose(self.input))
        print(dZ1)
        print(dW1)
        db1 = (1 / m) * np.sum(dZ1, axis=1, keepdims=True)

        # update the weights with the derivative (slope) of the loss function
        self.weights1 -= dW1 * learning_rate
        self.biases1  -= db1 * learning_rate
        self.weights2 -= dW2 * learning_rate
        self.biases2  -= db2 * learning_rate


    def cost(self, predict, actual):
        m = actual.size
        cost__ = -np.sum(np.multiply(np.log(predict), actual) + np.multiply((1 - actual), np.log(1 - predict)))/m
        return np.squeeze(cost__)

    def plot(self, costs):
        plt.plot(costs)
        plt.savefig("./cost_function.png")

    def fit(self, iterations):
        costs = []

        for i in range(iterations):
            self.feedforward()
            self.backprop()
            costs.append(self.cost(self.output, self.y))

        self.plot(costs)



x = np.transpose(np.array([[0, 0, 1],[0, 1, 0],[0, 1, 0],[0, 1, 1]]))
y = np.array([0, 1, 1, 0])

model = NeuralNetwork(x, y)

model.fit(1)
