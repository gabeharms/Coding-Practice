import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import os

# read the Houses dataset CSV file
housing_data = pd.read_csv("./RealEstate.csv", sep=",")

# only get the Size and the Price features
Xs = housing_data[['Size']]
Ys = housing_data[['Price']]

dataset_size = Xs.shape[0]

Xs = Xs.values.squeeze()
Ys = Ys.values.squeeze()

# get some statistics
max_size = np.max(Xs)
min_size = np.min(Xs)
max_price = np.max(Ys)
min_price = np.min(Ys)

# Normalize the input features
Xs = (Xs - min_size) / (max_size - min_size)
Ys = (Ys - min_price) / (max_price - min_price)

# shuffle the dataset before separating test/train sets
p = np.random.permutation(len(Xs))
Xs = Xs[p]
Ys = Ys[p]

# separate train/test sets
# Use 80% for training and 20% for testing
train_size = int(0.8 * dataset_size)
Xs_train = Xs[0:train_size]
Xs_test = Xs[train_size:]
Ys_train = Ys[0:train_size]
Ys_test = Ys[train_size:]


min_x = np.min(Xs_train)
max_x = np.max(Xs_train)


# Define the Linear model
# Initialize the weights using a normal (Gaussian) distribution with 0 mean and unit variance
W0 = np.random.normal(loc=0, scale=0.02) # Y intercept
W1 = np.asarray(np.random.normal(loc=0, scale=0.02)) # Slop of the line
W1 = W1.reshape((1))


def next_batch(Xs, Ys, batch_size=64):
    p = np.random.permutation(len(Xs))
    Xs = Xs[p]
    Ys = Ys[p]
    for i in range(0,Xs.shape[0],batch_size):
        x_batch = Xs[i:i+batch_size]
        if x_batch.shape[0] < batch_size:
            continue
        yield np.reshape(x_batch, (batch_size,-1)), Ys[i:i+batch_size]


def eval_and_plot(W0, W1, min_x, max_x, id_):
    min_y = W0 + np.dot(W1, min_x)
    max_y = W0 + np.dot(W1, max_x)
    plt.plot(Xs_train, Ys_train, 'ro')
    plt.plot([min_x, max_x], [min_y, max_y], 'k-', lw=2)
    plt.savefig("./media/fit%(number)04d.png" % {"number": id_})
    plt.clf()
    #plt.show()


# defines the size of the step we want to perform in the direction of the gradient
lr = 0.1

# set the pocket weights
W0_hat = np.inf
W1_hat = np.inf
best_hypothesis = np.inf

validation_err_list = []
training_err_list = []
batch_size = 96
total_epochs = 150

def mean_squared_error(Ys_batch, Y_pred):
    return np.mean((Ys_batch-Y_pred)**2)

for epoch in range(total_epochs):
    erros = []
    for X_batch, y_batch in next_batch(Xs_train, Ys_train, batch_size=batch_size):

        # linearly combine input and weights
        train_pred = W0 + np.dot(X_batch, W1)

        # calculate the SSE between predicted and true values
        train_err = mean_squared_error(y_batch, train_pred)
        erros.append(train_err)

        # calculate the gradients with respect to W0 and W1
        DW0 = -(2/batch_size) * sum(y_batch.squeeze() - train_pred.squeeze())
        DW1 = -(2/batch_size) * sum(X_batch.squeeze() * (y_batch.squeeze() - train_pred.squeeze()))

        # update W0 and W1 in the opposite direction to the gradient
        W0 = W0 - lr * DW0
        W1 = W1 - lr * DW1

    eval_and_plot(W0, W1, min_x, max_x, epoch+1)
    training_err_list.append(np.mean(erros))

    # test in the end of epoch
    val_pred = W0 + np.dot(np.reshape(Xs_test, (-1,1)), W1)
    val_err = mean_squared_error(Ys_test, val_pred)
    print("Epoch:", epoch, "Generalization err:", val_err)
    validation_err_list.append(val_err)

    #epoch += 1
    if val_err < best_hypothesis:
        best_hypothesis = val_err
        W0_hat = W0
        W1_hat = W1
