/*
 * Copyright (c) 2020 $developer_name$.
 * All rights reserved.
 */

package $package$

import cats.implicits._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class ExampleSuite extends AnyFunSuite with Checkers {

  test("sample test") {
    val sum = Example.sumAll(List(1, 2, 3, 4))
    assert(sum == 1 + 2 + 3 + 4)
  }

  // Property-based testing via ScalaCheck
  test("sum up any list") {
    check { (l: List[Int]) =>
      Example.sumAll(l) == l.sum
    }
  }
}
