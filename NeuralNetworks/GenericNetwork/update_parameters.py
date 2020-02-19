import numpy as np

class UpdateParameters:
    def __init__(self, learning_rate):
        self.learning_rate = learning_rate

    def execute(self, results, parameters, dW, db, training_input):
        #print("dZ1: %s" % dW[0])
        for i in range(0, parameters.layer_count()):
            new_W = parameters.get_weights(i+1)
            inputs = None
            # if i == 0:
                #print("dW1: %s" % np.dot(dW[i], np.transpose(training_input)))
                # inputs = training_input
            # else:
                # inputs = results.get_A(i)

            # for neuron in range(len(dW[i])):
                # for j in range(inputs.shape[0]):
                    # print("layer %d, neuron %d, weight %d, m: %d, diff: %s " % (i+1, neuron +1, j+1, inputs.shape[1], np.sum(dW[i][neuron] * inputs[j])/inputs.shape[1]))
                    # new_W[neuron][j] += self.learning_rate * np.sum(dW[i][neuron] * inputs[j])/inputs.shape[1]
    #            new_W[neuron][-1] += self.learning_rate * np.sum(dW[i][neuron])/dW[i][neuron].size

            new_weights = parameters.get_weights(i+1) - (dW[i] * self.learning_rate)
            new_biases = parameters.get_biases(i+1) - (db[i] * self.learning_rate)

            parameters.set_weights(i+1, new_weights)
            parameters.set_biases(i+1, new_biases)

     
