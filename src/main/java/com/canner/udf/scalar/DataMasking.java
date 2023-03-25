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

import io.airlift.slice.Slice;
import io.trino.spi.function.Description;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlType;
import io.trino.spi.type.StandardTypes;

import static io.airlift.slice.Slices.utf8Slice;

public class DataMasking
{
    private DataMasking() {}

    @Description("mask value in column")
    @ScalarFunction("mask_column")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice maskColumn(@SqlType(StandardTypes.VARCHAR) Slice ignoredValue)
    {
        return utf8Slice("*****");
    }

    @Description("mask email")
    @ScalarFunction("mask_email")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice maskEmail(@SqlType(StandardTypes.VARCHAR) Slice value, @SqlType(StandardTypes.BIGINT) long num)
    {
        String email = value.toStringUtf8();
        return utf8Slice(email.replaceAll("(^[^@]{" + num + "}|(?!^)\\G)[^@]", "$1*"));
    }
}
