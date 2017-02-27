package com.spark.core.examples.basic

import org.apache.spark.SparkContext
import scala.collection.mutable.HashMap

object ReduceByTest {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[2]", "ScTest", System.getenv("SPARK_HOME"))
    val pairs = sc.parallelize(Array(("a", 3), ("a", 1), ("b", 7), ("a", 5)))
    val resReduce = pairs.reduceByKey(_ + _)
    resReduce.foreach(println)
  }
}