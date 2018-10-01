
/*
  Apart from inheriting code from a super-class, a Scala class can also import
  code from one or several traits.

  Java programmers can understand traits by viewing them as interfaces which can
  also contain code. In Scala, when a class inherits from a trait, it implements
  that trait's interface, and inherits all the code contained in the trait.

  Lets look at a classic example: ordered objects. It is often useful to be able
  to compare objects of a given class to sort them. In Java, objects which are
  comparable implement the Comparable interface. In Scala, we can do a bit better
  than in Java by defining our equivalent as a triat, which we call Ord.

  When comparing objects, six different predicates can be useful. We will only
  cover a few:
*/

trait Ord {
  def < (that: Any): Boolean
  def <= (that: Any): Boolean = (this < that) || (this == that)
  def > (that: Any): Boolean = !(this <= that)
  def >= (that: Any): Boolean = (this > that) || (this == that)
}

/*
  The type Any is a super-type of all other types in Scala. It is equivalent to Java's Object.

  To make objects of a class comparable:
*/
class Date(y: Int, m: Int, d: Int) extends Ord {
  def year = y
  def month = m
  def day = d
  override def toString(): String = year + '-' + month + '-' + day
  override def equals(that: Any): Boolean =
    that.asInstanceOf[Date] && {
      val o = that.asInstanceOf[Date]
      o.day == day && o.month == month && o.year == year
    }
  override def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date])
      sys.error("cannot compare " + that + " and a Date")

    val o = that.asInstanceOf[Date]
    (year < o.year) ||
    (year == o.year && (month < o.month ||
                       (month == o.month && day < o.day)))
  }
}


