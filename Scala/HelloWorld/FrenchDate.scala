
/*
  One of Scala's strenghts is that it makes it very easy to interact with Java 
  code. All the classes from the java.lang package are imported by default,
  while others need to be imported explicitly.


  Java's class libraries define powerful utility classes like Date & DateFormat.
  Since Scala interoperates seemlessly with java, there is no need to implement
  equivalent classes in teh Scala class library. Just import.

*/

import java.util.{Date, Locale}
import java.text.DateFormat
import java.text.DateFormat._

object FrenchDate {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println(df format now)
  }
}

/*
  Scala's import statement looks very similar to Java's. But its more powerful. Multiple classes
  can be imported by enclosing them in curly braces. Another difference is that when importing
  all the names of a package or class, one uses the underscore character _ instead of *.
*/
