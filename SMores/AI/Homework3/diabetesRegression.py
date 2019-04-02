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
from sklearn import preprocessing as prep
# problem 1 models
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures


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
    # plt.axis([0, 110, 0, 4])


# Import the wine data set
diabetes = datasets.load_diabetes()

# Output the shape of the data
data = diabetes.data
pandas.set_option('display.max_columns', None)  # display every column
pandas.set_option('display.expand_frame_repr', False)  # prevent line breaking
pd_data = pandas.DataFrame(data = np.c_[diabetes['data'], diabetes['target']],
                           columns = diabetes['feature_names'] + ['target'])
print("Random Sample of Data:", pd_data.sample(10), sep = '\n')
print("Shape of Data:", pd_data.shape)

# Describe the minimum, maximum, and average values for each of the features and the target labels
print("\nMinimum, Maximum, and Average values for each column of the dataset:")
print(pd_data.agg([min, max, avg]))
print("\n")

# Implement a scatter matrix to determine if any features strongly correlate
plt.tight_layout()  # <-- failed attempt to make the UserWarning go away
scatter_matrix(pd_data, alpha = 0.2, figsize = (10, 10), c = diabetes.target)
plt.show()


print("\n\t\tLinear Regression")
train_x, test_x, train_y, test_y = train_test_split(data, diabetes.target)
lin_reg = LinearRegression(normalize = False, n_jobs = -1)
fitness = lin_reg.fit(train_x, train_y)
prediction = lin_reg.predict(test_x)
plt.figure("Linear SVM")
plot_learning_curves(lin_reg, train_x, train_y)
plt.show()
print("Training Fitness Score:", fitness.score(train_x, train_y))
print("Training Fitness Score:", fitness.score(test_x, test_y))

print("\n\t\tPolynomial Regression")
train_x, test_x, train_y, test_y = train_test_split(data, diabetes.target)
lin_reg = LinearRegression(normalize = False, n_jobs = -1)
fitness = lin_reg.fit(train_x, train_y)
prediction = lin_reg.predict(test_x)
plt.figure("Linear SVM")
plot_learning_curves(lin_reg, train_x, train_y)
plt.show()
print("Prediction:", prediction)
print("Training Fitness Score:", fitness.score(train_x, train_y))
print("Training Fitness Score:", fitness.score(test_x, test_y))
