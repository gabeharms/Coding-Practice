from numpy import exp, array, random, dot
import sys
import time
import json

class NeuralNetwork():
    def __init__(self):
        # Seed the random number generator, so it generates the same numbers
        # every time the program runs.
        random.seed(int(time.time()))

        # We model a single neuron, with 3 input connections and 1 output connection.
        # We assign random weights to a 3 x 1 matrix, with values in the range -1 to 1
        # and mean 0.
        self.synaptic_weights = 2 * random.random((3, 1)) - 1

    # The Sigmoid function, which describes an S shaped curve.
    # We pass the weighted sum of the inputs through this function to
    # normalise them between 0 and 1.
    # This accepts a 4x1 matrix where each row is the output of our network for one of the 4 inputs
    def __sigmoid(self, x):
        return 1 / (1 + exp(-x))

    # The derivative of the Sigmoid function.
    # This is the gradient of the Sigmoid curve.
    # It indicates how confident we are about the existing weight.
    def __sigmoid_derivative(self, x):
        return x * (1 - x)

    # We train the neural network through a process of trial and error.
    # Adjusting the synaptic weights each time.
    def train(self, training_set_inputs, training_set_outputs, number_of_training_iterations):
        for iteration in xrange(number_of_training_iterations):
            # Pass the training set through our neural network (a single neuron).
            output = self.think(training_set_inputs)

            # Calculate the error (The difference between the desired output
            # and the predicted output).
            error = training_set_outputs - output

            # Multiply the error by the input and again by the gradient of the Sigmoid curve.
            # This means less confident weights are adjusted more.
            # This means inputs, which are zero, do not cause changes to the weights.
            adjustment = dot(training_set_inputs.T, error * self.__sigmoid_derivative(output))

            # Adjust the weights.
            self.synaptic_weights += adjustment

    # The neural network thinks.
    def think(self, inputs):
        # Pass inputs through our neural network (our single neuron).
        # 4x3 matrix times a 3x1 matrix = 4x1 matrix where each row represents the output for all 4 inputs
        return self.__sigmoid(dot(inputs, self.synaptic_weights))


if __name__ == "__main__":
    print sys.argv
    iterations = int(sys.argv[1])
    file = json.load(open("scenarios/%s" %(sys.argv[2]), 'r'))

    #Intialise a single neuron neural network.
    neural_network = NeuralNetwork()

    print "Random starting synaptic weights: "
    print neural_network.synaptic_weights

    # The training set. We have 4 examples, each consisting of 3 input values
    # and 1 output value.
    training_set_inputs = array(file['input'].values())
    training_set_outputs = array([file['output']]).T

    # Train the neural network using a training set.
    # Do it 10,000 times and make small adjustments each time.
    neural_network.train(training_set_inputs, training_set_outputs, iterations)

    print "New synaptic weights after training: "
    print neural_network.synaptic_weights

    # Test the neural network with a new situation.
    print "Considering new situation [1, 0, 0] -> ?: "
    print neural_network.think(array([1, 0, 0]))


# My Thoughts:


# This is a single neuron network. There are 3 inputs. And one output.

# It works by randomizing a set of synapzes (3x1 matrix). It then receives a set of inputs. Since there
# are 4 sets of input data, this is a 4x3 matrix. For however many times specified (10,000):
# Rows in input matrix = input set. Columns in input matrix represent a particular input.

    # Multiply each input set by the synaptic weights 1x3 * 3x1. This results in a 1x1 matrix. Since
    # there are 4 sets of input, this is done 4 times, eventually when combining the results from each
    # input set into a single 4 (number of input sets) x 1 (number of outputs) matrix.
        # 4x1 weighted input = 4x3 input * 3x1 input weight matrix

    # This output matrix is then fed into the activiation function of our neuron which is the sigmoid.
    # The numpy library allows us to pass in the 4x1 matrix rather than 1 output at a time. This returns
    # another 4x1 matrix. The output matrix has now been transformed into a probability that the output
    # is a 1. (output is scaled from 0 to 1)
        # 4x1 output = 4x1 weighted input * sigmoid

    # Now that we have our probability that the output is 1 for each set of inputs (4x1 output matrix),
    # we calculate the error. In this network, that is done by subtracting our produced output from the
    # actual correct output.
        # 4x1 error = 4x1 training_set_outputs - 4x1 output

    # Now its time to figure out our adjustments. We multiply the 4x1 error matrix by the resulting
    # 4x1 matrix produced by passing our output through the sigmoid derivative. The last step is to
    # take this resulting 4x1 matrix, and multiply it by our 4x3 input matrix which produces a
    # 3x1 matrix, where each number represents the weight adjustment for each input.
        # 3x1 adjustment = 4x3 inputs * 4x1 error matrix * 4x1 sigmoid_derivative of output


