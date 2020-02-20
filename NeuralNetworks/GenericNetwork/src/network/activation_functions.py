import numpy as np

class ActivationFunctions:
    def sigmoid(Z):
        return 1/(1+np.exp(-Z))

    def sigmoid_prime(dA, Z):
        sig = ActivationFunctions.sigmoid(Z)
        return dA * sig * (1 - sig)

    def tanh(Z):
        return np.tanh(Z)

    def tanh_prime(dA, Z):
        return dA * (1.0 - np.tanh(Z)**2)
