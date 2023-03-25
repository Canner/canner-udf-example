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
import io.trino.spi.type.LongTimestamp;
import io.trino.spi.type.StandardTypes;

import static com.canner.udf.scalar.utils.DateTimes.formatTimestamp;
import static com.canner.udf.scalar.utils.DateTimes.scaleEpochMicrosToMillis;
import static io.airlift.slice.Slices.utf8Slice;

@Description("format timestamp in yyyyMMddHHmmssS format")
@ScalarFunction("seq_no")
public class TimestampSeqNo
{
    private TimestampSeqNo() {}

    @Description("Converts a timestamp to a sequence number of string")
    @LiteralParameters("p")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice fromTimestampToSeqNo(@SqlType("timestamp(p)") long epochMicros)
    {
        return utf8Slice(formatTimestamp(scaleEpochMicrosToMillis(epochMicros)));
    }

    @LiteralParameters("p")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice fromLongTimestampToSeqNo(@SqlType("timestamp(p)") LongTimestamp timestamp)
    {
        return utf8Slice(formatTimestamp(scaleEpochMicrosToMillis(timestamp.getEpochMicros())));
    }
}
