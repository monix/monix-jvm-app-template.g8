/*
 * Copyright (c) 2020 $developer_name$.
 * All rights reserved.
 */

package $package$

import cats.implicits._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.Checkers

class ExampleUtilsSuite extends AnyFunSuite with Checkers {

  test("sample test") {
    val sum = ExampleUtils.sumAll(List(1, 2, 3, 4))
    assert(sum == 1 + 2 + 3 + 4)
  }

  // Property-based testing via ScalaCheck
  test("sum up any list") {
    check { (l: List[Int]) =>
      ExampleUtils.sumAll(l) == l.sum
    }
  }
}
