import numpy as np
from sklearn import datasets

# https://medium.com/@martinpella/logistic-regression-from-scratch-in-python-124c5636b8ac
# https://ml-cheatsheet.readthedocs.io/en/latest/logistic_regression.html

class LogisticRegression:
    def __init__(self, lr=0.01, num_iter=100000, fit_intercept=True, verbose=False):
        self.lr = lr
        self.num_iter = num_iter
        self.fit_intercept = fit_intercept
        self.verbose = verbose

    def __add_intercept(self, X):
        intercept = np.ones((X.shape[0], 1))
        return np.concatenate((intercept, X), axis=1)

    # Sigmoid is at the heart of logistic regressions. It takes its input (z) and makes
    # a prediction based.
    #
    # z = Theta * x
    # sigmoid = g(z) = 1 / (1 + e^(-z))
    #
    # This function helps map an input between 0 and 1, and is assymptotic at each of these values
    # As z increases, so too does the probablity # of an affirmative prediction.
    #
    # Prediction functions have parameters/weights (represented by theta in our notation) and we 
    # want to find the best values for them. To start we pick random values and we need a way 
    # to measure how well the algorithm performs using those random weights
    def __sigmoid(self, z):
        return 1 / (1 + np.exp(-z))


    # Loss function helps us measure the success of our weights in the prediction function (theta).
    # 
    # For sigmoid functions we use the "Cross Entropy" or "Log Loss" function:
    #
    # J(theta) = sum(Cost(h(x^i), y^i))/m
    # 
    # This can be broken down into the two following separate functions:
    #
    # if y=1 then Cost(h(x), y) = -log(h(x))
    # if y=0 then Cost(h(x), y) = -log(1- h(x))
    #
    # The benefits of taking the logarithm reveal themselves when you look at the cost function 
    # These smooth monotonic functions [7] (always increasing or always decreasing) make it easy to
    # calculate the gradient and minimize cost.
    #
    # The key thing to note is the cost function penalizes confident and wrong predictions more than
    # it rewards confident and right predictions! The corollary is increasing prediction accuracy 
    # (closer to 0 or 1) has diminishing returns on reducing cost due to the logistic nature of our
    # cost function.
    # 
    # The equations from the above can be combined into the following:
    #  
    #  J(theta) = sum(y^i * log(h(x^i)) + (1 - y^i) * log(1 - h(x^i)) / m
    #
    # Multiplying by 𝑦 and (1−𝑦) in the above equation is a sneaky trick that let’s us use the 
    # same equation to solve for both y=1 and y=0 cases. If y=0, the first side cancels out. If y=1,
    # the second side cancels out. In both cases we only perform the operation we need to perform.
    # We are left with the following:
    #
    # J(theta) = (-y * log(h) - (1 - y) * log(1 - h)) / m
    #
    # Again, this function is only used to communicate the error for a particular direction. When
    # running gradient descent, we only need the derivative of this function
    #
    def __loss(self, h, y):
        return (-y * np.log(h) - (1 - y) * np.log(1 - h)).mean()


    # The derivative of the loss function with respect ot theta, is what we will use for gradient
    # descent. It tells us what the slope of the error is for our prediction.  Not that this returns
    # a vecotr. That is because each weight in theta needs to be adjusted individually according
    # to how much it contributed to the error.
    def __d_theta_loss(self, X, h, y):
        return np.dot(X.T, (h - y)) / y.size

    def fit(self, X, y):
        if self.fit_intercept:
            X = self.__add_intercept(X)

        # weights initialization. Here there are many weights represented in a single theta
        # vector. The number of weights is dependent on the number of inputs
        # In this scenario, X has 3 inputs, and thus the theta vector has 3 elements in it.
        self.theta = np.zeros(X.shape[1])

        for i in range(self.num_iter):
            # calculate z
            z = np.dot(X, self.theta)

            # input z into sigmoid and get prediction
            h = self.__sigmoid(z)

            # find value of the loss derivative for this input and prediction. Where h is our 
            # prediction and y is the actual result
            gradient = self.__d_theta_loss(X, h, y)
            self.theta -= self.lr * gradient

            if(self.verbose == True and i % 10000 == 0):
                z = np.dot(X, self.theta)
                h = self.__sigmoid(z)
                print(f'loss: {self.__loss(h, y)} \t')

    # Run sigmoid off current weights based on a particular input and return its raw result
    def predict_prob(self, X):
        if self.fit_intercept:
            X = self.__add_intercept(X)

        return self.__sigmoid(np.dot(X, self.theta))

    # Run sigmoid off current weights based on a particular input and return whether or not it 
    # exceeds a certain threshold
    def predict(self, X, threshold):
        return self.predict_prob(X) >= threshold


iris = datasets.load_iris()

X = iris.data[:, :2]
y = (iris.target != 0) * 1

model = LogisticRegression(lr=0.1, num_iter=300000)

model.fit(X, y)

print("Theta: ", end = '')
print(model.theta)
