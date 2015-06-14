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


let's learn from the code from [okapi](https://github.com/grafos-ml/okapi)

public final void compute(Vertex<CfLongId, FloatMatrixWritable, FloatWritable> vertex, final Iterable<FloatMatrixMessage> messages) {
    
```
    double rmsePartialSum = 0d;
    float l2norm = 0f;

    if (tolerance>0) {
        // create new object because we're going to operate the old one.
        oldValue = new FloatMatrixWritable(vertex.getValue().getRows(),
            vertex.getValue().getColumns(), vertex.getValue().data);
    }


    for (FloatMatrixMessage msg : messages) {
        // get rating
        float rating = vertex.getEdgeValue(msg.getSenderId()).get();

        // update latent factor
        // this process do exactly what stochastic gradient descent do.
        // iterate samples and update parameters one at a time.
        updateValue(vertex.getValue(), msg.getFactors(), rating,
            minRating, maxRating, lamda, gamma);
    }

    // calculate new error for rmse, for aggregator
    for (FloatMatrixMessage msg : messages) {
        float predicted = vertex.getValue().dot(msg.getFactors());
        float rating = vertex.getEdgeValue(msg.getSenderId()).get();
        predicted = Math.min(predicted, maxRating);
        predicted = Math.max(predicted, minRating);
        float err = predicted - rating;
        rmsePartialSum += (err*err);
    }

    aggregate(RMSE_AGGREGATOR, new DoubleWritable(rmsePartialSum));

    vertex.voteToHalt();
```


### what's pratical issues you might have?

As you see in the code, the vertex we are working on must receive messages from its neighbor. If its neighbor locate in another machine, this message must be sent by network traffic, which can be quite slow if the data are high skew. Few vertex with high degree would have a longer time to deal with messages from its neighbors. 

When the rating matrix are huge, it becomes infeasible due to the issue of heavy traffic workload.

The engineer from Facebook also have this problem, but they come up with a novel way to train this model. Just go to reading materials!



## Further discussion, reading materials

[Scalable collaborative filtering on top of Apache Giraph](https://www.youtube.com/watch?v=bHVFOvNHH0I)

 




