package timeusage

import org.apache.spark.sql.{ColumnName, DataFrame, Row}
import scala.util.Random
import scala.util.Properties.isWin

class TimeUsageSuite extends munit.FunSuite:
  import TimeUsage.*
  println("You can add tests here")
  lazy val (columns, initDf) = read("src/main/resources/timeusage/atussum.csv")
  
  
  test("Isolate the columns") {
    val instantiatable = try {
      val (primaryNeedsColumns, workColumns, otherColumns) = classifiedColumns(columns)
      true
    } catch {
      case _: Throwable => false
    }
    assert(instantiatable, "Can't instantiate a StackOverflow object")
  }

  // val (columns, initDf) = read("src/main/resources/timeusage/atussum.csv")
  // val (primaryNeedsColumns, workColumns, otherColumns) = classifiedColumns(columns)
  // val summaryDf = timeUsageSummary(primaryNeedsColumns, workColumns, otherColumns, initDf)
  // val finalDf = timeUsageGrouped(summaryDf)
  // finalDf.show()

// object StackOverflowSuite:
//   val conf: SparkConf = new SparkConf().setMaster("local[2]").setAppName("StackOverflow")
//   val sc: SparkContext = new SparkContext(conf)

// class StackOverflowSuite extends munit.FunSuite:
//   import StackOverflowSuite.*


//   lazy val testObject = new StackOverflow {
//     override val langs =
//       List(
//         "JavaScript", "Java", "PHP", "Python", "C#", "C++", "Ruby", "CSS",
//         "Objective-C", "Perl", "Scala", "Haskell", "MATLAB", "Clojure", "Groovy")
//     override def langSpread = 50000
//     override def kmeansKernels = 45
//     override def kmeansEta: Double = 20.0D
//     override def kmeansMaxIterations = 120
//   }

//   test("testObject can be instantiated") {
//     val instantiatable = try {
//       testObject
//       true
//     } catch {
//       case _: Throwable => false
//     }
//     assert(instantiatable, "Can't instantiate a StackOverflow object")
//   }


//   import scala.concurrent.duration.given
//   override val munitTimeout = 300.seconds
