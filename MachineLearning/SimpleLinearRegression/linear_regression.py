import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os

# https://towardsdatascience.com/machine-learning-101-an-intuitive-introduction-to-gradient-descent-366b77b52645

# read the Houses dataset CSV file
housing_data = pd.read_csv("./RealEstate.csv", sep=",")

# only get the Size and the Price features
Xs = housing_data[['Size']]
Ys = housing_data[['Price']]

dataset_size = Xs.shape[0]

# This takes our current matricies which are 1xN where N is the number of rows (i.e. houses)
# and turns them into an Nx1 matrix
Xs = Xs.values.squeeze()
Ys = Ys.values.squeeze()

# get some statistics
max_size = np.max(Xs)
min_size = np.min(Xs)
max_price = np.max(Ys)
min_price = np.min(Ys)

# Normalize the input features. I.e. get them on a scale
# from 0 to 1. Lowest value in set will become 0. Highest
# value in set will be come 1. Everything else in between
#
# We normalize the data to prevent some of the features to
# out value some of the others. Also, Gradient Descent is
# known for converging much faster with normalized data than
# otherwise.
Xs = (Xs - min_size) / (max_size - min_size)
Ys = (Ys - min_price) / (max_price - min_price)

# shuffle the dataset before separating test/train sets. p is an array of randomized
# indexes. Xs[p] rearranges Xs in an order according to the order of p
p = np.random.permutation(len(Xs))
Xs = Xs[p]
Ys = Ys[p]

# separate train/test sets
# Use 80% for training and 20% for testing
train_size = int(0.8 * dataset_size) # number of records for training set. 80% of total data
Xs_train = Xs[0:train_size]
Xs_test = Xs[train_size:]
Ys_train = Ys[0:train_size]
Ys_test = Ys[train_size:]


min_x = np.min(Xs_train)
max_x = np.max(Xs_train)


# Define the Linear model.
# Initialize the weights using a normal (Gaussian) distribution with 0 mean and unit variance. A
# gaussian normal distribution is a bell curve where the center/peak of the curve is 0 in this case
# and where there is a .683 probability of being within 1 standard deviation (here .02) of the
# mean (here =0
#
# We initialize our model weights may lead it to rest in a local minimum. To avoid that, we
# initialize the two weight vectors with values from a random normal distribution with zero mean and low variance.
#
# loc = center of the distribution
# scale is the standard deviation from the center
#
# W1 is a 1x1 matrix so that we can dot product it against training data sets which are
# matricies.
#
# It appears as though .02 was chosen as the scale because the develper knew the coefficients
# trend towards .02-.04 over time. When using large numbers for scale it generally corrects fast
# toward this
W0 = np.random.normal(loc=0, scale=0.02) # Y intercept
W1 = np.asarray(np.random.normal(loc=0, scale=0.02)) # Slop of the line
W1 = W1.reshape((1))


def next_batch(Xs, Ys, batch_size=64):
    # randomly shuffle our training data
    p = np.random.permutation(len(Xs))
    Xs = Xs[p]
    Ys = Ys[p]

    # i is index into Xs. Will go from 0 to Xs.size
    for i in range(0,Xs.shape[0],batch_size):
        # get Xs batch
        x_batch = Xs[i:i+batch_size]

        #
        if x_batch.shape[0] < batch_size:
            continue
        # return x_batch as a batch_size by 1 matrix, along with the Ys batch
        yield np.reshape(x_batch, (batch_size,-1)), Ys[i:i+batch_size]


def eval_and_plot(W0, W1, min_x, max_x, id_):
    min_y = W0 + np.dot(W1, min_x)
    max_y = W0 + np.dot(W1, max_x)
    plt.plot(Xs_train, Ys_train, 'ro')
    plt.plot([min_x, max_x], [min_y, max_y], 'k-', lw=2)
    plt.savefig("./media/fit%(number)04d.png" % {"number": id_})
    plt.clf()
    #plt.show()


def plot_error(epoch_array, validation_err_list):
    plt.plot(epoch_array, validation_err_list, 'ro')
    plt.savefig('./media/error_curve')
    plt.clf()

def plot_W0(epoch_array, w0s):
    plt.plot(epoch_array, w0s, 'ro')
    plt.savefig('./media/w0_curve')
    plt.clf()

def plot_W1(epoch_array, w1s):
    plt.plot(epoch_array, w1s, 'ro')
    plt.savefig('./media/w1_curve')
    plt.clf()


# defines the size of the step we want to perform in the direction of the gradient
lr = 0.1

# set the pocket weights
W0_hat = np.inf
W1_hat = np.inf
best_hypothesis = np.inf

validation_err_list = []
training_err_list = []
W0_over_time = []
W1_over_time = []
batch_size = 96
total_epochs = 500

# used to get error of the prediction from Xs
def mean_squared_error(Ys_batch, Y_pred):
    return np.mean((Ys_batch-Y_pred)**2)

# number of iterations/batches

print("Begin:")
print("-----------")
print("W0 = ", end = '')
print(W0)
print("W1 = ", end = '')
print(W1)
print("-----------")
print("")
for epoch in range(total_epochs):
    erros = []
    for X_batch, y_batch in next_batch(Xs_train, Ys_train, batch_size=batch_size):

        # linearly combine input and weights.
        # This is linear regression!!! prediction = y + mx
        # where W0 = y and W1 = m. We control the offset and slope. Remember its only linear.
        # Predictions from a linear regession model always come from feeding training data /features
        # into this model
        train_pred = W0 + np.dot(X_batch, W1)

        # calculate the SSE between predicted and true values. MSE is often used because it penalizes
        # bad predictions heavily.
        #
        # Here, we take the prediction from the linear regression, and the actual value (y_batch)
        # and use an error function (MSE) to determine how much error there was
        #
        # NOTE: this value only represents how off we are. our error. But this value is not used
        #       to correct the model. Only the values of its gradient are.
        train_err = mean_squared_error(y_batch, train_pred)
        erros.append(train_err)

        # calculate the gradients with respect to W0 and W1
        #
        # In order to learn about how to correct errors, we need to learn about how we can reduce
        # the error by taking the derivative of the error function. This will tell us to slope
        # of the error function. And we will then travel along this derivative and try to minimize
        # it to 0. Which would represent no error. or a minimium
        #
        # In this case, since there are multiple variables/weights (W0, W1), the derivative of the
        # error function is actually a gradient. This means, rather than being one function, its
        # a vector of functions.
        #
        # Thus dMSE = [d/dW0, d/W1]
        #
        # This shows us that there are exactly two movements/adjustments we can make in order to
        # minimize the gradient.
        #
        # Given the MSE error function: sum((Ybatch - Ypred)^2) / Ypred.count
        # The derivatives of it are:
        # dW0 = sum(Ybatch - Ypred) * -2 / Ypred.count
        # dW1 = sum(Xbatch *(Ybatch - Ypred) * -2 / Ypred.count

        # Plugging our training data and our predictions into these derivatives tells the slope of
        # our error at our current predictions. If that slope the absolute value of that slope
        # is large, then we need to reduce it. Thats what these adjustments do for each of our
        # variables
        DW0 = -(2/batch_size) * sum(y_batch.squeeze() - train_pred.squeeze())
        DW1 = -(2/batch_size) * sum(X_batch.squeeze() * (y_batch.squeeze() - train_pred.squeeze()))

        # update W0 and W1 in the opposite direction to the gradient. Meaning if gradient is positive,
        # we reduce the weight. If the gradient is negative, we adjust it positively
        W0 = W0 - lr * DW0
        W1 = W1 - lr * DW1

    eval_and_plot(W0, W1, min_x, max_x, epoch+1)
    training_err_list.append(np.mean(erros))
    W0_over_time.append(W0)
    W1_over_time.append(W1)

    # test in the end of epoch
    val_pred = W0 + np.dot(np.reshape(Xs_test, (-1,1)), W1)
    val_err = mean_squared_error(Ys_test, val_pred)
    #print("Epoch:", epoch, "Generalization err:", val_err)
    validation_err_list.append(val_err)

    #epoch += 1
    if val_err < best_hypothesis:
        best_hypothesis = val_err
        W0_hat = W0
        W1_hat = W1


print("Begin:")
print("-----------")
print("W0 = ", end = '')
print(W0)
print("W1 = ", end = '')
print(W1)
print("-----------")

plot_error(range(0, total_epochs), validation_err_list)
plot_W0(range(0, total_epochs), W0_over_time)
plot_W1(range(0, total_epochs), W1_over_time)
