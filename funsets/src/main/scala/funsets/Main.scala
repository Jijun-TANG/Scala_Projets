package funsets

object Main extends App:
  import FunSets.*
      val s0 = singletonSet(0)
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s6 = singletonSet(6)
    val s7 = singletonSet(7)
    val s8 = singletonSet(8)
    val s9 = singletonSet(9)
    val s10 = singletonSet(10)
    val s11 = singletonSet(10000)
    val s1000 = singletonSet(1000)
  println(contains(singletonSet(1), 1))
  val s1111 = union(union(union(union(union(s1, s3),s4),s5),s7),s1000)
      // val sm = map(s1111, x => x-1)
      // printSet(sm)
      // assert(toString(sm), "{0,2,3,4,6,999}")
      // println(sm.getClass)
