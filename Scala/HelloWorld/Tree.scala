
/*
  In Java, a tree would be represented using an abstract super-class for the trees
  and one concrete sub-class per node or leaf. In a functional programming language,
  one would use an algebraic data-type for the same purpose.

  Scala provides the concept of case classes which is somewhat in between the two.

  abstract class Tree
  case class Sum(l: Tree, r: Tree) extends Tree
  case class Var(l: Tree, r: Tree) extends Tree
  case class Const(l: Tree, r: Tree) extends Tree

  The fact that classes Sum, Var, and Const are declared as case classes means that
  they differ from standard classes in several respects:
    * the new keyword is not mandatory to create instances of these classes
    * getter functions are automatically defined for the constructor parameters
    * default definitions for methods equals and hashCode are defined, which
        identify the structure of the instances, not the identity
    * a default definition for toString is provided
    * instances of these classes can be decomposed through pattern matching

  Now that we defined the data-type, we can define operations to manipulate them.
  We will start with a function to evaluate an expression in some enviornment. Where
  environment is something that simply defines values for all variables.

  We therefore have to find a way to represent environments.An environment is really
  nothing more than a function which associates a value to a variable name. Before writing
  the evaluation function, let us give a name to the type of the environments. We could
  always use the type String => Int for environments, but it simplifies the program if we
  introduce a name for this type. This can be done as follows

    type Environment = String => Int

  From then on, Environment can be used as an alias fo the type of functions from String
  to Int.

  We can now give the definition of the evaluation function. Conceptually, it is very simple:
  the value of a sum of two expressions is simply the sum of the value fo these expressions;
  value of a variable is obtained directly from the environemtn; and the value of a constant
  is the constant itself. Expressing this in Scala:

    def eval(t: Tree, env: Environment): Int = t match {
      case Sum(l, r)   => eval(l, env) + eval(r, env)
      case Var(n)      => env(n)
      case Constant(v) => v
    }

  This evaluation works by pattern matching on tree t. We see that the basic idea of pattern
  matching is to attempt to match a value to a serious of patterns, and as soon as a pattern
  matches, extract and name various parts of the value.

  An Object oriented programmer may wonder why we didn't define eval as a method on Tree, and
  its subclasses. We actually could have, since Scala allows method definitions in case classes
  just liek normal classes. Here are some tradeoffs
    * when defining an instance method, it is easy to add new kind of node. Just define a new sub
        class of tree and make sure it implements eval. However, adding a new operation to tree
        now requires adding the new operation to every subclass.
    * When using pattern matching it is reversed.  Adding a new node requires modification of
        all functions which do pattern matching on the tree. But adding a new operation now requires
        simply adding one new pattern matching method.

*/

abstract class Tree
case class Sum(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree


object TreeRunner {
  type Environment = String => Int

  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r)   => eval(l, env) + eval(r, env)
      case Var(n)      => env(n)
      case Const(v) => v
  }

  def main(args: Array[String]) {
    val exp: Tree = Sum(Sum(Var("x"),Var("x")),Sum(Const(7),Var("y")))
    val env: Environment = { case "x" => 5 case "y" => 7}
    println("Expression: " + exp)
    println("Evaluation with x=5, y=7: " + eval(exp, env))
  }
}
