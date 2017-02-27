package com.spark.core.examples.basic

import org.apache.spark.SparkContext

object GroupByKeyTest {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local[2]", "ScTest", System.getenv("SPARK_HOME"))
    // val x = sc.parallelize(List( ("a" , "b" ) , ("a" , "b") , ("c" , "b") , ("a" , "d")))
      val y =  sc.parallelize( List (("riskscore","low" ), ("riskscore", "high"), ("tpi", "high") , ("tpi", "low")))    
     val kv = y.map(e => e._1 -> e._2 )                   
     val grouped = kv.groupByKey().mapValues { _.toList }
     
    grouped.foreach(println)
    //  grouped.keyBy(e => e._1 -> e._2).foreach(println)
    
 
//      val result = grouped.filter{case(key, _) => key == "token"}
//                        .values
//                        .flatMap(i => i.toList)
//                        .collect()
//     result foreach println                       
     //grouped.foreach(println)
  }
}