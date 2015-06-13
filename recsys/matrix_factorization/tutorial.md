Hey guys, in the last lecture, we know some features of Giraph. Such as compute() logic and understand what's 'think like a vertex'.

In this time, we would learn another key API - aggregate() by a very basic but powerful model in recommender systems, matrix factorization.

![infra](https://github.com/zihaolucky/data-science-in-action/blob/master/recsys/matrix_factorization/infra.png)


## Objective

Though this lecture, you would learn:

 * why solving matrix factorization with stochastic gradient descent can be think like a graph?

 * Some pratical issues you might have interest to conquer.

### why think like a vertex in solving this problem.

In the context of collaborative filtering(CF), we have matrix factorization(MF) in simplest case:

![hypothesis](https://github.com/zihaolucky/data-science-in-action/blob/master/recsys/matrix_factorization/hypothesis.png)

where each rating `r_ui`:

![hypothesis2](https://github.com/zihaolucky/data-science-in-action/blob/master/recsys/matrix_factorization/hypothesis2.png)

we solve this objective function with stochastic gradient descent:

![cos_function](https://github.com/zihaolucky/data-science-in-action/blob/master/recsys/matrix_factorization/cos_function.png)


note that each latent vector get update information from its neighbors, see subscript of the sum over symbol. That means think each latent vector as a vertex, the user-item relation as a edge and edge's value is rating R(u,i) given by that user.



### what's pratical issues you might have?

[TODO]

My college 

Scalable collaborative filtering on top of Apache Giraph, best pratice from Facebook.



## Further discussion, reading materials

[Scalable collaborative filtering on top of Apache Giraph](https://www.youtube.com/watch?v=bHVFOvNHH0I)

 




