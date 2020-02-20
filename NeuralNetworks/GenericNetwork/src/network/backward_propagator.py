import numpy as np

class BackwardPropagator:

    def execute(self, parameters, forward_propagation_result, training_output):
        dWs = []
        dbs = []

        Y = training_output
        Y_hat = forward_propagation_result.get_last_A()

        # a hack ensuring the same shape of the prediction vector and labels vector
        Y = Y.reshape(Y_hat.shape)

        # initiation of gradient descent algorithm
        dA_prev = - (np.divide(Y, Y_hat) - np.divide(1 - Y, 1 - Y_hat));

        for layer_idx_curr in reversed(range(1, parameters.layer_count()+1)):
            # we number network layers from 1
            layer_idx_prev = layer_idx_curr - 1

            # extraction of the activation function for the current layer
            activ_function_prime_curr = parameters.get_activation_prime(layer_idx_curr) # self.__sigmoid_prime if (layer_idx_curr == parameters.layer_count()+1) else self.__tanh_prime

            dA_curr = dA_prev

            A_prev = forward_propagation_result.get_A(layer_idx_prev)
            Z_curr = forward_propagation_result.get_Z(layer_idx_curr)

            W_curr = parameters.get_weights(layer_idx_curr)
            b_curr = parameters.get_biases(layer_idx_curr)

            dA_prev, dW_curr, db_curr = self.__single_layer_backward_propagation(
                dA_curr, W_curr, b_curr, Z_curr, A_prev, activ_function_prime_curr)

            dWs = [dW_curr] + dWs
            dbs = [db_curr] + dbs

        return { "dWs": dWs, "dbs": dbs }

    def __single_layer_backward_propagation(self, dA_curr, W_curr, b_curr, Z_curr, A_prev, activation_prime):
        # number of examples
        m = A_prev.shape[1]

        # calculation of the activation function derivative
        dZ_curr = activation_prime(dA_curr, Z_curr)

        # derivative of the matrix W
        dW_curr = np.dot(dZ_curr, A_prev.T) / m
        # derivative of the vector b
        db_curr = np.sum(dZ_curr, axis=1, keepdims=True) / m
        # derivative of the matrix A_prev
        dA_prev = np.dot(W_curr.T, dZ_curr)

        return dA_prev, dW_curr, db_curr
