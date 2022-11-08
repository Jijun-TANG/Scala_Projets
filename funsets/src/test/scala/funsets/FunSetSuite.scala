package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
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
  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton1 postive")
      assert(!contains(s2, 1), "Singleton2 negative")
      assert(contains(s3, 3), "Singleton3 positive")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3 doesn't exist")
  }

  test("test forAll") {
    new TestSets:
      val s = union(s1, s2)
      val p1 = union(union(union(union(union(s1, s2),s4),s5),s6),s9)
      val p2 = union(union(union(union(union(union(s1, s2),s4),s5),s6),s9),s11)
      val p3: FunSet = (x:Int) => if x>909 && x<1000 then true else false
      val p4: FunSet = (x:Int) => if x > -1001 && x<1001 then true else false
      
      val s22: FunSet = (x:Int) => if x > -1 && x<5 then true else false
      val p5: FunSet = (x:Int) => if x<5 then true else false

      val s33: FunSet = (x:Int) => if x == -1000 || x==0 then true else false
      val p6: FunSet = (x:Int) => if x<1000 then true else false

      assert(forall(s, p1), "Discontinous Set")
      assert(forall(s, p2), "Out of bounds")
      assert(!forall(s, p3), "Small cut")
      assert(forall(s, p4), "Open ball")
      assert(forall(s22, p5), "All elements in the set are strictly less than 5.")
      assert(forall(s33, p6), "All elements in the set are strictly less than 1000.")
  }


  test("test exists") {
    new TestSets:
      val s = union(s1, s2)
      val p1 = union(union(union(union(union(s1, s2),s4),s5),s6),s9)
      val p2: FunSet = (x:Int) => if x < -1001 && x > -1100 then true else false
      val p3: FunSet = (x:Int) => if x>909 && x<1100 then true else false
      assert(exists(s, p1), "Discontinous Set")
      assert(!exists(s, p2), "doesn't exist")
      assert(!exists(s, p3), "Intersetion")

      val s111 = union(union(union(union(union(s1, s3),s4),s5),s7),s1000)
      assert(exists(s111, p3), "exists: given {1,3,4,5,7,1000}(funsets.FunSetSuite)")
  }


  test("test map") {
    new TestSets:
      val s1111 = union(union(union(union(union(s1, s3),s4),s5),s7),s1000)
      val sm = map(s1111, x => x-1)
      //assert(toString(s1111), "{1,3,4,5,7,1000}")
      //assert(toString(sm), "{0,2,3,4,6,999}")
      assert(contains(sm, 0), "Number 0")
      assert(contains(sm, 2), "Number 2")
      assert(contains(sm, 3), "Number 3")
      assert(contains(sm, 4), "Number 4")
      assert(contains(sm, 6), "Number 6")
      assert(contains(sm, 999), "Number 999")
      assert(!contains(sm, 1000), "Doesn't contain number 1000")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
