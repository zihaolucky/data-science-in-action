The objective of representation learning, in my eye, is a machine learning technique trying to *represent* any object (like words, sentences, and even vertex in a graph) with a dense low dimensional vector. Then these vectors can be directly used to calculate similarities in those nearest-neighbor methods, or can be seen as mata-data feed into any other models. 


The character of dense and low-dimension vector have serveral advantages toward sparse and high-dimension one:

 * The similarity comparison between dense vectors are sensitive and continuous which is friendly to many use cases, under distance metric, like cosine/L1/L2. While calculating the distance of sparse vectors, we might get tedious results, especially in high-dimension case. Think about top-k nearest-neighbor search.

Although there're some discussions[1] talking about the power of word vector and traditional bag-of-words model, I think the essence is to do with the model we use, these two ways are just about how to represent an object (like words here). BTW, representation learning, like word2vec, has shown its power in many cases (similar words in either syntactic or senmatic[2], analogy and etc)


### Objectives

1.Learn what's word embedding, a.k.a representation learning of words. I would give some academic papers for you, also some open-source code.

2.Dive into the character of word vector. Serveral technical blog posts would be provided.

3.Based on word2vec, how can we calculate distance of docs.


### Reading matrials
[1][word2vec/"predictive" methods vs. count-based method, Count fights back](https://twitter.com/pitzeglide/status/607202919621001216)
[2]Efficient Estimation of Word Representations in Vector Space

