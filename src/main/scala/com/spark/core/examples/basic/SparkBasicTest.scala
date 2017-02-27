package com.spark.core.examples.basic

import org.apache.spark.SparkContext

object SparkBasicTest {
  def main(args: Array[String]) {
    val sc = new SparkContext("local[2]", "ScTest", System.getenv("SPARK_HOME"))
    val input = sc.parallelize(List(1, 2, 3, 4))
    val result = input.map(x => x * x)
    println(result.collect().mkString(","))
  }
}