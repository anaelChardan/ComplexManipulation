/**
  * Created by Ananas on 17/01/2017.
  */
trait Val
case class ValInt(v:Int) extends Val
case class ValBool(v: Boolean) extends Val