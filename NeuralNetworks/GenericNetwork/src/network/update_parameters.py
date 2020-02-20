import numpy as np

class UpdateParameters:
    def __init__(self, learning_rate):
        self.learning_rate = learning_rate

    def execute(self, results, parameters, dW, db, training_input):
        for i in range(0, parameters.layer_count()):
            new_weights = parameters.get_weights(i+1) - (dW[i] * self.learning_rate)
            new_biases = parameters.get_biases(i+1) - (db[i] * self.learning_rate)

            parameters.set_weights(i+1, new_weights)
            parameters.set_biases(i+1, new_biases)

     
