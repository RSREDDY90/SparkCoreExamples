package com.spark.core.examples.basic

import org.apache.spark.SparkContext

object AggregateByKey {
  def main(args: Array[String]): Unit = {
     val sc = new SparkContext("local[2]", "ScTest", System.getenv("SPARK_HOME"))
//    val keysWithValuesList = Array("foo=A", "foo=A", "foo=A", "foo=A", "foo=B", "bar=C", "bar=D", "bar=D")
//    val data = sc.parallelize(keysWithValuesList)
//    //Create key value pairs
//    val kv = data.map(_.split("=")).map(v => (v(0), v(1))).cache()
//    val initialSet = collection.mutable.HashSet.empty[String]
//    val addToSet = (s: collection.mutable.HashSet[String], v: String) => s += v
//    val mergePartitionSets = (p1: collection.mutable.HashSet[String], p2: collection.mutable.HashSet[String]) => p1 ++= p2
//    val uniqueByKey = kv.aggregateByKey(initialSet)(addToSet, mergePartitionSets)
    
  //  uniqueByKey.foreach(println)
    
    
    val keysWithValuesList = Array("foo=A", "foo=A", "foo=A", "foo=A", "foo=B", "bar=C", "bar=D", "bar=D")
    val data = sc.parallelize(keysWithValuesList)
    //Create key value pairs
    val kv = data.map(_.split("=")).map(v => (v(0), v(1))).cache()
    kv.foreach(println)
    val initialCount = 0;
    val addToCounts = (n: Int, v: String) => n + 1
    val sumPartitionCounts = (p1: Int, p2: Int) => p1 + p2
    val countByKey = kv.aggregateByKey(initialCount)(addToCounts, sumPartitionCounts)
    
    countByKey.foreach(println)
    
  }
}