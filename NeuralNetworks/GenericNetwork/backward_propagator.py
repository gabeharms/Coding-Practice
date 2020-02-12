import numpy as np

class BackwardPropagator:

    def execute(self, parameters, forward_propagation_result, training_output):
        network = list(map(lambda x: [], range(parameters.layer_count())))

        for i in reversed(range(1, len(network)+1)):
            activation_prime = None
            errors = list()
            if i != len(network):
                activation_prime = self.__tanh_prime
                layer_weights = parameters.get_weights(i)
                layer_plus_one_weights = parameters.get_weights(i+1)
                print(layer_weights)
                print(layer_weights.shape[1])
                print(layer_weights.shape[0])
                print(layer_plus_one_weights)
                print(layer_plus_one_weights.shape[1])
                print(layer_plus_one_weights.shape[0])
                for j in range(4): # range(layer_plus_one_weights.shape[1]):
                    error = 0.0
                    for neuron in range(1): #range(layer_plus_one_weights.shape[0]):
                        error += (layer_plus_one_weights[neuron][j] * network[i][neuron][j])
                    print("adding to errors: %s" % error)
                    errors.append(error)
            else:
                activation_prime = self.__sigmoid_prime
                layer_output = forward_propagation_result.get_last_A()
                for j in range(layer_output.shape[0]):
                    errors.append(training_output[j] - layer_output[0][j])

            layer_output = forward_propagation_result.get_A(i)
            print("entire layer output: %s" % layer_output)
            for j in range(parameters.get_weights(i).shape[0]):
                print("------------------------------------------")
                print("------------------------------------------")
                print("layer %d, neuron %d, error: %s, layer_output: %s, activation_prime: %s gradient for neuron: %s" % (i, j, errors[j], layer_output[j], activation_prime(layer_output[j]), errors[j] * activation_prime(layer_output[j])))
                network[i-1].insert(j, errors[j] * activation_prime(layer_output[j]))

        return {"dWs": network, "dbs": []}

    def __z(self, input, weights, biases):
        return np.dot(weights, input) + biases

    def __activation(self, z):
        return np.tanh(z)

    def __sigmoid_prime(self, z):
        return z * (1 - z)

    def __tanh_prime(self, z):
        return 1.0 - np.tanh(z)**2


        # dw = []  # dC/dW
        # db = []  # dC/dB
        # deltas = [None] * (parameters.layer_count()+1)  # delta = dC/dZ  known as error for each layer

        # deltas[len(deltas)-1] = ((training_output-forward_propagation_result.get_last_A())*(self.__activation_prime(forward_propagation_result.get_last_Z())))

        # print(deltas)
        # for layer_index in reversed(range(1, len(deltas)-1)):
            # print(layer_index)
            # A_layer_index = forward_propagation_result.get_A(layer_index)
            # Z_layer_index = forward_propagation_result.get_Z(layer_index)
            # W_layer_index = parameters.get_weights(layer_index)
            # delta_layer_minus_one_index = deltas[layer_index+1]
            # print(current_layer_output)
            # print(previous_layer_weights)


            # print(deltas)
            # deltas[layer_index] = W_layer_index.T.dot(delta_layer_plus_one_index)*(self.__activation_prime(Z_layer_index))

        # batch_size = training_output.shape[0]
        # deltas = deltas[1:len(deltas)-1]
        # db = [d.dot(np.ones((batch_size,1)))/float(batch_size) for d in deltas]
        # dw = [d.dot(current_layer_output.T)/float(batch_size) for i,d in enumerate(deltas)]
