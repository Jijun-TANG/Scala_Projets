package recfun
import scala.collection.mutable.HashMap

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c==0 || c==r || r<=1) return 1;
    return (pascal(c-1, r-1) + pascal(c, r-1));
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance_rec(chars: List[Char], left: Int) : Boolean={
      if (chars.isEmpty) {return (left==0)}
      if (left<0) {return false}
      //NO += operator in SCALA
      //val variables cant be modified
      var new_left = left
      if (chars(0)=='(') {return balance_rec(chars.slice(1,chars.length),left+1)}
      else if (chars(0)==')') {return balance_rec(chars.slice(1,chars.length),left-1)}
      return balance_rec(chars.slice(1,chars.length),left)
    }
    return balance_rec(chars, 0)
  }

  /**
   * Exercise 3
   */

  def countChange(amount: Int, coins: List[Int]): Int = {
    val n = coins.length
    var dp: Array[Array[Int]] = Array.fill(n+1,amount+1){-1}
    for(i<- 0 to amount){
      dp(0)(i) = 0
    }
    for(i<- 0 to n){
      dp(i)(0) = 1
    }
    for(i <- 1 to n){
      for(j <- 1 to amount){
        if(j>=coins(i-1)){
          dp(i)(j) = dp(i)(j-coins(i-1))+dp(i-1)(j)
        }
        else{
          dp(i)(j) = dp(i-1)(j)
        }
      }
    }
    return dp(n)(amount)
  }
