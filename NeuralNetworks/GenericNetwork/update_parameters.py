import numpy as np

class UpdateParameters:
    def __init__(self, learning_rate):
        self.learning_rate = learning_rate

    def execute(self, results, parameters, dW, db, training_input):
        for i in range(0, parameters.layer_count()):
            print("Starting Layer")
            print(i)
            print(self.learning_rate)
            print(dW[i])
            # print(dW[i+1])
            new_W = parameters.get_weights(i+1)
            inputs = None
            if i == 0:
                inputs = training_input
            else:
                inputs = results.get_A(i)

            for neuron in range(len(dW[i])):
                print("Starting Neuron")
                for j in range(inputs.shape[0]):
                    print(dW[i][neuron])
                    print(inputs[j])
                    print(self.learning_rate * dW[i][neuron] * inputs[j])
                    print(new_W)
                    new_W[neuron][j] += self.learning_rate * np.sum(dW[i][neuron] * inputs[j])/inputs.shape[1]
                print("Ending Neuron")
                new_W[neuron][-1] += self.learning_rate * np.sum(dW[i][neuron])/dW[i][neuron].size

            print("Ending Layer")
            parameters.set_weights(i+1, new_W)

     
