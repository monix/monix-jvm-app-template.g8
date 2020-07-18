/*
 * Copyright (c) 2020 $developer_name$.
 * All rights reserved.
 */

package $package$

import cats._
import cats.implicits._

object Example {
  /**
    * Example function.
    *
    * Usage:
    * {{{
    *   import cats.implicits._
    *
    *   Example.sumAll(List(1, 2, 3, 4))
    * }}}
    */
  def sumAll[F[_]: Traverse, A: Monoid](list: F[A]): A =
    list.foldLeft(Monoid[A].empty)(Monoid[A].combine)
}
