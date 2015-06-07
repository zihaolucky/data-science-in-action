/**
 * Copyright 2014 Grafos.ml
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package main.okapi.graphs;

import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.log4j.Logger;

/**
 * Basic Pregel PageRank implementation.
 *
 * This version initializes the value of every vertex to 1/N, where N is the
 * total number of vertices.
 *
 * The maximum number of supersteps is configurable.
 */



/**
 * Outlines.
 *
 * 1. Understand data type of vertex and edge.
 * BasicComputation<I, V, E, M>
 * I - Vertex id
 * V - Vertex data/value
 * E - Edge data/value
 * M - Message type
 *
 * 也就是说, 一个compute节点可以有这些属性. 包括它的id, 其上的值, 临边的值, 以及节点间互相发送信息的类型
 *
 *
 * 2. Understand message and their communication based on edges.
 * See line 80
 */
public class SimplePageRank extends BasicComputation<LongWritable,
  DoubleWritable, FloatWritable, DoubleWritable> {
  /** Default number of supersteps */
  public static final int MAX_SUPERSTEPS_DEFAULT = 30;
  /** Property name for number of supersteps */
  public static final String MAX_SUPERSTEPS = "pagerank.max.supersteps";

  /** Logger */
  private static final Logger LOG =
    Logger.getLogger(SimplePageRank.class);



  /**
   * compute() is core API of Giraph, which you implement compute logic of the vertex of your graph.
   *
   * compute() 是计算的核心部分. 
   * compute的对象是图中的节点, 这应证了 think like a vertex.
   */
  @Override
  public void compute(
      Vertex<LongWritable, DoubleWritable, FloatWritable> vertex,
      Iterable<DoubleWritable> messages) {
    if (getSuperstep() == 0) {
      vertex.setValue(new DoubleWritable(1f / getTotalNumVertices()));
    }
    if (getSuperstep() >= 1) {
      double sum = 0;
      // messages are those received from its neighbors
      for (DoubleWritable message : messages) {
        sum += message.get();
      }
      DoubleWritable vertexValue =
        new DoubleWritable((0.15f / getTotalNumVertices()) + 0.85f * sum);
      vertex.setValue(vertexValue);
    }

    if (getSuperstep() < getContext().getConfiguration().getInt(
      MAX_SUPERSTEPS, MAX_SUPERSTEPS_DEFAULT)) {

      long edges = vertex.getNumEdges();
      sendMessageToAllEdges(vertex,
          new DoubleWritable(vertex.getValue().get() / edges));
    } else {
      vertex.voteToHalt();
    }
  }
}
