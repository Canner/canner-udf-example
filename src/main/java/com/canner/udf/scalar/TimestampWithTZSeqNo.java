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
import io.trino.spi.function.LiteralParameters;
import io.trino.spi.function.ScalarFunction;
import io.trino.spi.function.SqlType;
import io.trino.spi.type.LongTimestampWithTimeZone;
import io.trino.spi.type.StandardTypes;

import static com.canner.udf.scalar.utils.DateTimes.formatTimestamp;
import static io.airlift.slice.Slices.utf8Slice;
import static io.trino.spi.type.DateTimeEncoding.unpackMillisUtc;

@Description("convert timestamp with timezone to yyyyMMddHHmmssS format at UTC time zone")
@ScalarFunction("seq_no")
public class TimestampWithTZSeqNo
{
    @LiteralParameters("p")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice fromTimestampWithTZToSeqNo(@SqlType("timestamp(p) with time zone") long packedEpochMillis)
    {
        return utf8Slice(formatTimestamp(unpackMillisUtc(packedEpochMillis)));
    }

    @LiteralParameters("p")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice fromLongTimestampWithTZToSeqNo(@SqlType("timestamp(p) with time zone") LongTimestampWithTimeZone timestamp)
    {
        return utf8Slice(formatTimestamp(timestamp.getEpochMillis()));
    }
}
