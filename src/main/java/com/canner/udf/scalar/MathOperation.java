/*
 * Copyright 2023 Canner
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
package com.canner.udf.scalar;

import io.trino.spi.function.Description;
import io.trino.spi.function.LiteralParameters;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlType;
import io.trino.spi.type.Int128;
import io.trino.spi.type.StandardTypes;

import static io.trino.spi.type.Int128Math.add;

public class MathOperation
{
    private MathOperation() {}

    @Description("math op")
    @ScalarFunction("math_op")
    @SqlType(StandardTypes.BIGINT)
    public static long mathOpAddBigInt(@SqlType(StandardTypes.BIGINT) long left, @SqlType(StandardTypes.BIGINT) long right)
    {
        return left + right;
    }

    @ScalarFunction("math_op")
    @Description("math op")
    public static final class MathOP
    {
        private MathOP() {}

        // this is needed, otherwise mathOp(Int128, Int128) won't work
        @LiteralParameters({"p", "s"})
        @SqlType("decimal(p, s)")
        public static long mathOp(@SqlType("decimal(p, s)") long left, @SqlType("decimal(p, s)") long right)
        {
            return left + right;
        }

        @LiteralParameters({"p", "s"})
        @SqlType("decimal(p, s)")
        public static Int128 mathOp(@SqlType("decimal(p, s)") Int128 left, @SqlType("decimal(p, s)") Int128 right)
        {
            long[] resultArray = new long[2];
            add(
                    left.getHigh(), left.getLow(),
                    right.getHigh(), right.getLow(),
                    resultArray,
                    0);
            return Int128.valueOf(resultArray);
        }
    }
}
