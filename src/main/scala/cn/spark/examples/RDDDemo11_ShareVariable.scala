package cn.spark.examples

import org.apache.commons.lang3.StringUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object RDDDemo11_ShareVariable {
  def main(args: Array[String]): Unit = {
    //TODO -1.env/创建环境
    val conf: SparkConf = new SparkConf().setAppName("spark").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setLogLevel("WARN")

    //TODO -2.source/加载数据/创建RDD
    val lines: RDD[String] = sc.textFile("data/input/words.txt")

    //TODO -3.transformation
    val result: RDD[(String, Int)] = lines.filter(StringUtils.isNoneBlank(_))
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)

    //TODO ========= tips: result RDD在后续会被频繁使用到，且该RDD的计算过程比较复杂，所以为了提高使用效率，应讲该RDD放到缓存之中
//    result.cache()//底层persist
//    result.persist()//底层persist(StorageLevel.MEMORY_ONLY)
    result.persist(StorageLevel.MEMORY_AND_DISK)
    //TODO ========= tips: 上面的缓存持久化并不能保证RDD的数据的绝对安全，所以应使用Checkpoint把数据放到HDFS上
    sc.setCheckpointDir("./ckp")//实际中写HDFS目录
    result.checkpoint()

    val sortResult1: Array[(String, Int)] = result.sortBy(_._2, false) //按照数量降序排列
      .take(3) //取出前三个

    val sortResult2: RDD[(Int, String)] = result.map(_.swap).sortByKey(false)

    val sortResult3: Array[(String, Int)] = result.top(3)(Ordering.by(_._2))//topN默认降序

    result.unpersist()

    //TODO -4.sink/输出
    result.foreach(println)
    sortResult1.foreach(println)
    sortResult2.foreach(println)
    sortResult3.foreach(println)

    sc.stop()
  }
}
