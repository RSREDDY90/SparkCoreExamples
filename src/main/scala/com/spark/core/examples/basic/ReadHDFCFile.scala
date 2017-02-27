package com.spark.core.examples.basic

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object ReadHDFCFile {
  def main(args: Array[String]): Unit = {

    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    
    
    val sc = new SparkContext("local[2]", "ReadHDFCFile", System.getenv("SPARK_HOME"))

    val reg = sc.textFile("hdfs://0.0.0.0:9000/user/hadoop.txt")
    println(reg.count())

  }
}