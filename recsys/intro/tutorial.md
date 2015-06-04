Hi, it's our first lesson in Apache Giraph. Enjoy it!


## Introduction to Apache Giraph

Apache Giraph is an open-source iterative graph processing system for large-scale data. I would give some reading matrials for you, which might help you get deeper understand on this cool things.

### Objective

BTW, learning this tool is not the most important, the underlying programming model/paradigm is what we really need. Like MapReduce in Hadoop, here we have BSP in Giraph.

FYI, there're several graph processing systems. Such as GraphX in Apache Spark, ODPS-GRAPH of Alibaba and Pregel of Google. As I know, Giraph is now widely used at Facebook. 

So why I choose Giraph? It's build on top of Hadoop. Many startups choose Hadoop as their first generation of large-scale data system, for its better ecosystem. But it's okay for those prefer Spark, they are BSP, just different API.

In this 'course', you would learn a super cool weapon for processing large-scale graph data, and its programming model behind. With just two simple but useful examples. 

You would also know its industrial uses in real-world, and how people make innovation on this tool, to make it better.



### Key concepts you should know in this chapter.

 * What's the storage strategy of Giraph and other graph processing system?

 * What is the intuitive difference between BSP and MapReduce. a.k.a What's the advantages of Giraph, or how can I choose this as my hammer?

 * What is super-step?


#### Storage strategy of common graph processing system.

These systems are distributed, their data are stored in different machines/computing nodes. So they must cut the graph then distribute these vertex and edges.

![partition](https://spark.apache.org/docs/0.9.0/img/edge_cut_vs_vertex_cut.png)

Vertex-cut and edge-cut are two main methods. The former might cut few vertex in the graph with large degree(have more neighbors), that means there're copies of this vertex in many machines, which might cause the problem of consistence, so programmer must figure out the way of resolving it. 

While the latter cut edge. It might has a pitfall, that the traffic between machines, because in this way, the vertex in different must communicate through network. As you know, in many cases, some are high degree, that might cause large network traffic and slow down the entire system. *The engineering team of Facebook propose a method to resolve this, I would share with you when we begin our lecture in matrix factorization.

Giraph uses edge-cut strategy.


#### What's the difference between BSP and MapReduce.

As you know, Giraph is an iterative ***graph*** processing system. [BSP](http://en.wikipedia.org/wiki/Bulk_synchronous_parallel) is the programming model behind.

Before I know Giraph/Pregel and its BSP model, I just know Hadoop and its MapReduce behind. 

Consider an intuitive question: why Google invent such a system, which is dedicated for graph data? What's the nature of graph data?

There're tons of data can be seen as a graph, relation of web pages, friendship in Facebook, etc. It's hard for human to figure out what's the 'key' of Mapper->Reducer, and how can I retain the relation/edges of the original graph, which in most model/algorithm are based on that(random walk based methods).

On the other hand, K-Means, gradient descent, these common machine learning algorithms are iteratively updated. While MapReduce read/write this intermediate result, which might be too slow to get the output. 

Although PageRank can be seen as matrix multiplication task, while it has another iterative version. See [PageRank in Wikipedia](http://en.wikipedia.org/wiki/PageRank). So we choose PageRank as our first lecture.


#### What's super-step?

As you've seen, Giraph is an *iterative* graph processing system.

Each iteration in Giraph, is call super-step. It consists two main status, compute() and aggregate()

In each iteration/super-step, machines run compute() logic in parallel, see SimplePageRank.java for details. Then if necessary, the master node run aggregate() to gather informations of slaves.(PageRank doesn't need this, but matrix factorization do)


### Reading materials
[Apache Giraph](http://giraph.apache.org)








