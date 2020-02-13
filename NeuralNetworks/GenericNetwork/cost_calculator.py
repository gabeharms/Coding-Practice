import numpy as np

class CostCalculator:
    def execute(actual, predict):
        print('---Cost Calculator---')
        print(actual)
        print(predict)
        print(np.log(predict))
        print(np.multiply(np.log(predict), np.array(actual)))
        m = actual.size
        cost__ = -np.sum(np.multiply(np.log(predict), actual) + np.multiply((1 - actual), np.log(1 - predict)))/m
        return np.squeeze(cost__)
