package com.spark.core.examples.basic

import org.apache.spark.SparkContext

object SparkWordCount {
  def main(args: Array[String]): Unit = {
    ///user/worcount_input.txt
    val sc = new SparkContext("local[2]", "SparkWordCount", System.getenv("SPARK_HOME"))
     val input =  sc.textFile("hdfs://0.0.0.0:9000/user/worcount_input.txt")
      // Split up into words.
      val words = input.flatMap(line => line.split(" "))
      // Transform into word and count.
      val counts = words.map(word => (word, 1)).reduceByKey{case (x, y) => x + y}
      // Save the word count back out to a text file, causing evaluation.
      counts.saveAsTextFile("/Users/sekharreddy/work/scala_ide_workspace/SparkCoreExamples/output/wordcount")
      
  }
}