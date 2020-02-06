# Goal: Predict gender from height/weight

import numpy as np
import pandas as pd
from sklearn import linear_model

# Load data
data = np.loadtxt('./data.csv', delimiter=',', skiprows=1)
X = data[:,1:3]
y = data[:,0]

# Fit (train) the Logistic Regression classifier
clf = linear_model.LogisticRegression(C=1e40, solver='newton-cg')
fitted_model = clf.fit(X, y)

# Predict
prediction_result = clf.predict([(90,180)])

print(prediction_result)
