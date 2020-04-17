from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import os 
import re
import operator
import pickle
import nltk 
nltk.download('punkt')
nltk.download('wordnet')
from nltk.tokenize import word_tokenize
from nltk import pos_tag
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from nltk.stem.snowball import SnowballStemmer
from collections import defaultdict
from nltk.corpus import wordnet as wn
from sklearn.feature_extraction.text import TfidfVectorizer

app = Flask("__name__")

#global lemmatizer
lemmatizer = nltk.stem.WordNetLemmatizer()
#global stemmer
stemmer = SnowballStemmer("english")

#global vocabulary
#global tfidf
#global course
vocabulary = []
tfidf = TfidfVectorizer()
tfidf_tran = []
course = pd.DataFrame()

@app.route("/initialSearchingMode", methods=['GET'])
def initialization():
    global vocabulary
    global tfidf
    global tfidf_tran
    global course

    with open("vocabulary.txt", "rb") as fp: 
        vocabulary = pickle.load(fp)

    course = pd.read_csv('coursePreprocessed.csv')
    course['course_name'] = course['course_name'].astype(str)
    course['word_tokenize'] = course['word_tokenize'].astype(str)

    tfidf = TfidfVectorizer(vocabulary=vocabulary)
    tfidf.fit(course['word_tokenize'])
    tfidf_tran = tfidf.transform(course['word_tokenize'])

    return jsonify("Initialization success")

def lemmatize_text(tokenizedWord):
    return [lemmatizer.lemmatize(w) for w in tokenizedWord]

def genQueryVector(tokens):
    query = np.zeros((len(vocabulary)))    
    x= tfidf.transform(tokens)
    for token in tokens[0].split(','):
        try:
            ind = vocabulary.index(token)
            query[ind]  = x[0, tfidf.vocabulary_[token]]
        except:
            pass
    return query

def cosineSimilarity(a, b):
    cosSim = np.dot(a, b)/(np.linalg.norm(a)*np.linalg.norm(b))
    return cosSim

@app.route("/tfidfSearching", methods=['GET'])
def cosine_similarity_T():

    k = request.args.get('k')
    query = request.args.get('query')

    print(k)
    print(query)

    global vocabulary
    global tfidf
    global tfidf_tran
    global course
    global lemmatizer
    global stemmer

    preprocessed_query = re.sub("\W+", " ", query).strip()
    
    tokens = word_tokenize(str(preprocessed_query))
    tokens = lemmatize_text(tokens)
    tokens = [stemmer.stem(word) for word in tokens]
    tokens = ', '.join([str(word) for word in tokens]) 
        
    q_df = pd.DataFrame(columns=['queryInfo'])
    q_df.loc[0,'queryInfo'] = tokens
    
    d_cosines = []
    
    query_vector = genQueryVector(q_df['queryInfo'])
        
    for d in tfidf_tran.A:
        d_cosines.append(cosineSimilarity(query_vector, d))
    
    out = np.array(d_cosines).argsort()[-int(k):][::-1]
    
    a = [] 
    for i in out:
        a.append(course.loc[i, 'course_name']);

    return jsonify(a) 
	
app.run()
