import numpy as np
from forward_propagation_result import ForwardPropagationResult

class ForwardPropagator:

    def __init__(self, layers):
        self.layers = layers

    def execute(self, parameters, training_input):
        result = ForwardPropagationResult()
        result.set_A(0, training_input)

        for layer_index in range(1, len(self.layers)):
            weights = parameters.get_weights(layer_index)
            biases = parameters.get_biases(layer_index)

            result.set_Z(layer_index, self.__z(result.get_A(layer_index - 1), weights, biases))
            result.set_A(layer_index, self.__activation(result.get_Z(layer_index)))

        return result

    def __z(self, input, weights, biases):
        return np.dot(weights, input) + biases

    def __activation(self, z):
        return np.tanh(z)



