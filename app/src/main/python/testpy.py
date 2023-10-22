from os.path import dirname, join
import pickle
import numpy as np




def predictDT( N,	P,	K,	temperature,	humidity,	ph,	rainfally):
    DT_pkl_filename = 'DecisionTree.pkl'
    filename_DT = join(dirname(__file__), DT_pkl_filename)
    DT_Model_pkl = open(  filename_DT, 'rb')
    loaded_model = pickle.load(DT_Model_pkl)
    DT_Model_pkl.close()
    data = np.array([[ 80, 25, 70, 18, 0.3, 5.0, 0.9]])
    prediction = loaded_model.predict(data)
    return  prediction

def predictRF( N,	P,	K,	temperature,	humidity,	ph,	rainfally):
    RF_pkl_filename = 'RandomForest.pkl'
    filename_RF = join(dirname(__file__), RF_pkl_filename)
    RF_Model_pkl = open(  filename_RF, 'rb')
    loaded_model = pickle.load(RF_Model_pkl)
    RF_Model_pkl.close()
    data = np.array([[  N,	P,	K,	temperature,	humidity,	ph,	rainfally]])
    prediction = loaded_model.predict(data)
    return  prediction
#print(predictDT(80, 25, 70, 18, 0.3, 5.0, 0.9))
#print(predictRF(80, 25, 70, 18, 0.3, 5.0, 0.9))