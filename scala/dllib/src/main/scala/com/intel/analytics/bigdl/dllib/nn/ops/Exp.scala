/*
 * Copyright 2016 The BigDL Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intel.analytics.bigdl.dllib.nn.ops

import com.intel.analytics.bigdl.dllib.tensor.Tensor
import com.intel.analytics.bigdl.dllib.tensor.TensorNumericMath.TensorNumeric

import scala.reflect.ClassTag

class Exp[T: ClassTag, D: ClassTag]()(implicit ev: TensorNumeric[T], ev2: TensorNumeric[D])
  extends Operation[Tensor[D], Tensor[D], T]{

  output = Tensor[D]()

  override def updateOutput(input: Tensor[D]): Tensor[D] = {
    output.resizeAs(input)
    output.map(input, (a, b) => ev2.exp(b))
  }

  override def getClassTagNumerics() : (Array[ClassTag[_]], Array[TensorNumeric[_]]) = {
    (Array[ClassTag[_]](scala.reflect.classTag[T], scala.reflect.classTag[D]),
      Array[TensorNumeric[_]](ev, ev2))
  }
}

object Exp {
  def apply[T: ClassTag, D: ClassTag]()(implicit ev: TensorNumeric[T], ev2: TensorNumeric[D])
  : Exp[T, D] = new Exp()
}
