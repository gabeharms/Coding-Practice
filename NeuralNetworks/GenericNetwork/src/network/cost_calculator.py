import numpy as np

class CostCalculator:
    def execute(actual, predict):
        m = actual.size
        cost__ = -np.sum(np.multiply(np.log(predict), actual) + np.multiply((1 - actual), np.log(1 - predict)))/m
        print('Cost: %f' % cost__)
        return np.squeeze(cost__)
