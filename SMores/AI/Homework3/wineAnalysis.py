import numpy as np
import pandas
from pandas.plotting import scatter_matrix
from matplotlib import pyplot as plt
from sklearn import datasets
from sklearn.metrics import confusion_matrix, precision_score, recall_score, accuracy_score, f1_score
from sklearn.metrics import mean_squared_error, mean_absolute_error
from sklearn.model_selection import cross_val_predict
from sklearn.model_selection import train_test_split
from sklearn.utils import shuffle
# problem 1 models
from sklearn.neighbors import KNeighborsClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.svm import LinearSVC


def avg(array):
    return float(sum(array)) / max(len(array), 1)


def plot_learning_curves(model, x, y):
    """
    Plots performance on the training set and testing (validation) set.
    X-axis - number of training samples used
    Y-axis - RMSE
    """
    
    train_x, test_x, train_y, test_y = train_test_split(x, y, test_size = 0.20)
    
    training_errors, validation_errors = [], []
    
    for m in range(1, len(train_x)):
        if m in range(0, 3):
            continue
        
        model.fit(train_x[:m], train_y[:m])
        
        train_pred = model.predict(train_x)
        test_pred = model.predict(test_x)
        
        training_errors.append(np.sqrt(mean_squared_error(train_y, train_pred)))
        validation_errors.append(np.sqrt(mean_squared_error(test_y, test_pred)))
    
    plt.plot(training_errors, "r-+", label = "train")
    plt.plot(validation_errors, "b-", label = "test")
    plt.legend()
    plt.axis([0, 110, 0, 4])


# Import the wine data set
wine = datasets.load_wine()

# Output the shape of the data
data = wine.data
pandas.set_option('display.max_columns', None)  # display every column
pandas.set_option('display.expand_frame_repr', False)  # prevent line breaking
pd_data = pandas.DataFrame(data = np.c_[wine['data'], wine['target']], columns = wine['feature_names'] + ['target'])
print("Random Sample of Data:", pd_data.sample(10), sep = '\n')
print("Shape of Data:", pd_data.shape)

# Describe the minimum, maximum, and average values for each of the features and the target labels
print("\nMinimum, Maximum, and Average values for each column of the dataset:")
print(pd_data.agg([min, max, avg]))
print("\n")

# Implement a scatter matrix to determine if any features strongly correlate
plt.tight_layout()  # <-- failed attempt to make the UserWarning go away
scatter_matrix(pd_data, alpha = 0.2, figsize = (10, 10), c = wine.target)
plt.show()

print("\n\t\tK-Nearest Neighbors Classifier")
train_x, test_x, train_y, test_y = train_test_split(data, wine.target)
k_nearest = KNeighborsClassifier(n_neighbors = 1)
k_nearest.fit(train_x, train_y)
pred_y = cross_val_predict(k_nearest, train_x, train_y, cv = 10)
plt.figure("K-Nearest Neighbors")
plot_learning_curves(k_nearest, train_x, train_y)
plt.show()
print("Confusion Matrix:")
print(confusion_matrix(train_y, pred_y))
print("Accuracy Score = " + str(accuracy_score(train_y, pred_y)))
print("Precision Score = " + str(precision_score(train_y, pred_y, average = 'macro')))
print("Recall Score = " + str(recall_score(train_y, pred_y, average = 'macro')))
print("F1 Score = " + str(f1_score(train_y, pred_y, average = 'macro')))
print("MAE = " + str(mean_absolute_error(train_y, pred_y)))

### Keep running into multiple errors with logistic regression incapable of determining that there actually IS 3
### different classes
print("\n\t\tLogistic Regression Classifier")
train_x, test_x, train_y, test_y = train_test_split(data, wine.target)
log_reg = LogisticRegression(solver = 'lbfgs', max_iter = 5000, multi_class = 'auto')
log_reg.fit(train_x, train_y)
pred_y = log_reg.predict(test_x)
plt.figure("Logistic Regression")
plot_learning_curves(log_reg, train_x, train_y)
plt.show()
print("Confusion Matrix:")
print(confusion_matrix(train_y, pred_y))
print("Accuracy Score = " + str(accuracy_score(train_y, pred_y)))
print("Precision Score = " + str(precision_score(train_y, pred_y, average = 'macro')))
print("Recall Score = " + str(recall_score(train_y, pred_y, average = 'macro')))
print("F1 Score = " + str(f1_score(train_y, pred_y, average = 'macro')))
print("MAE = " + str(mean_absolute_error(train_y, pred_y)))

print("\n\t\tLinear Support Vector Machine")
train_x, test_x, train_y, test_y = train_test_split(data, wine.target)
lin_svm = LinearSVC(max_iter = 10000, dual = False, C = 1)
lin_svm.fit(train_x, train_y)
pred_y = cross_val_predict(lin_svm, train_x, train_y, cv = 5)
plt.figure("Linear SVM")
plot_learning_curves(lin_svm, train_x, train_y)
plt.show()
print("Confusion Matrix:")
print(confusion_matrix(train_y, pred_y))
print("Accuracy Score = " + str(accuracy_score(train_y, pred_y)))
print("Precision Score = " + str(precision_score(train_y, pred_y, average = 'macro')))
print("Recall Score = " + str(recall_score(train_y, pred_y, average = 'macro')))
print("F1 Score = " + str(f1_score(train_y, pred_y, average = 'macro')))
print("MAE = " + str(mean_absolute_error(train_y, pred_y)))
