from sklearn.pipeline import Pipeline, FeatureUnion
from sklearn.impute import SimpleImputer
from sklearn.base import BaseEstimator, TransformerMixin
from sklearn.preprocessing import OneHotEncoder, StandardScaler, PolynomialFeatures
from sklearn.model_selection import cross_val_score, cross_val_predict, GridSearchCV
from sklearn.model_selection import train_test_split
from sklearn.model_selection import RandomizedSearchCV
from sklearn.svm import SVC, LinearSVC, SVR, LinearSVR
from sklearn import datasets

from matplotlib import pyplot as plt

from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import confusion_matrix, precision_score, recall_score, accuracy_score, f1_score
from sklearn.metrics import mean_squared_error, mean_absolute_error

import numpy as np
import pandas as pd
import os

# Load in the wine data set
wine = datasets.load_wine()
print("Data:", wine.data, sep = '\n')
print("\n\nTarget:", wine.target, sep = '\n')


# Defining Data Pre-Processing Pipelines
class DataFrameSelector(BaseEstimator, TransformerMixin):
    
    def __init__(self, attributes):
        self.attributes = attributes
    
    def fit(self, X, y = None):
        return self
    
    def transform(self, X):
        return X[self.attributes]


class MostFrequentImputer(BaseEstimator, TransformerMixin):
    
    def fit(self, X, y = None):
        self.most_frequent = pd.Series([X[c].value_counts().index[0] for c in X],
                                       index = X.columns)
        return self
    
    def transform(self, X):
        return X.fillna(self.most_frequent)

# numeric_pipe = Pipeline([
#     ("Select", DataFrameSelector(["Age", "Fare", "SibSp", "Parch"])),  # Selects Fields from dataframe
#     ("Imputer", SimpleImputer(strategy = "median")),  # Fills in NaN w/ median value for its column
# ])
#
# # Handle categorical string for sex by encoding as female true, 1 or false,0
# train_df['Female'] = train_df["Sex"].apply(lambda x: 1 if x == 'female' else 0)
#
# categories_pipe = Pipeline([
#     ("Select", DataFrameSelector(["Pclass", "Female"])),  # Selects Fields from dataframe
#     ("MostFreqImp", MostFrequentImputer()),  # Fill in NaN with most frequent
# ])
#
# preprocessing_pipe = FeatureUnion(transformer_list = [
#     ("numeric pipeline", numeric_pipe),
#     ("categories pipeline", categories_pipe)
# ])
#
# # Process Input Data Using Pipleines
# train_X_data = preprocessing_pipe.fit_transform(train_df)
#
# train_y_data = train_df["Survived"]
