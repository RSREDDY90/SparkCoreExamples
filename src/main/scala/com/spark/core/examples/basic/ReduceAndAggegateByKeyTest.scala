package com.spark.core.examples.basic

import org.apache.spark.SparkContext

object ReduceAndAggegateByKeyTest {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local[2]", "ScTest", System.getenv("SPARK_HOME"))

    val pairs = sc.parallelize(Array(("a", 3), ("a", 1), ("b", 7), ("a", 5)))
    val strPairs = sc.parallelize(Array(("a", "sekhar"), ("a", "reddy"), ("b", "priya"), ("a", "reddy") , ("b", "sekhar")))

    val resReduce = pairs.reduceByKey(_ + _) //the same operation for everything
    resReduce.foreach(println)

    val resAgg = pairs.aggregateByKey(0)(_ + _, _ + _)

    resAgg.foreach(println)

    import scala.collection.mutable.HashSet
    //the initial value is a void Set. Adding an element to a set is the first
    //_+_ Join two sets is the  _++_
    val sets = pairs.aggregateByKey(new HashSet[Int])(_ + _, _ ++ _)
    sets.foreach(println)
    
 
    /** 
      Function2<HashSet<String>, String, HashSet<String>> sequenceFunction = new Function2<HashSet<String>, String, HashSet<String>>() {

            public HashSet<String> call(HashSet<String> aSet, String arg1) throws Exception {
                aSet.add(arg1);
                return aSet;
            }
        };

        Function2<HashSet<String>, HashSet<String>, HashSet<String>> combineFunc = new Function2<HashSet<String>, HashSet<String>, HashSet<String>>() {

            public HashSet<String> call(HashSet<String> arg0, HashSet<String> arg1) throws Exception {
                arg0.addAll(arg1);
                return arg0;
            }
        };

        JavaPairRDD<String, HashSet<String>> byKey = productIdLocationJavaPairRDD.aggregateByKey(new HashSet<String>(), sequenceFunction, combineFunc );
      **/
 val ap = strPairs.aggregateByKey(new java.util.HashSet[String]) ((key1, key2) => 
   {key1.add(key2); key1},
   (val1, val2) => {val1.addAll(val2); val1})
ap.foreach(println)

  }
}