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
package com.canner.udf;

import io.trino.spi.type.LongTimestamp;
import io.trino.spi.type.LongTimestampWithTimeZone;
import io.trino.spi.type.TimeZoneKey;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.canner.udf.scalar.TimestampSeqNo.fromLongTimestampToSeqNo;
import static com.canner.udf.scalar.TimestampSeqNo.fromTimestampToSeqNo;
import static com.canner.udf.scalar.TimestampWithTZSeqNo.fromLongTimestampWithTZToSeqNo;
import static com.canner.udf.scalar.TimestampWithTZSeqNo.fromTimestampWithTZToSeqNo;
import static io.trino.spi.type.DateTimeEncoding.packDateTimeWithZone;
import static io.trino.spi.type.LongTimestampWithTimeZone.fromEpochMillisAndFraction;
import static io.trino.spi.type.Timestamps.MICROSECONDS_PER_MILLISECOND;
import static java.time.ZoneOffset.UTC;
import static org.testng.Assert.assertEquals;

public class TestSeqNo
{
    @Test
    public void testFromTimestampToSeqNo()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ZonedDateTime zonedDateTime = LocalDateTime.parse("2023-03-25 16:54:00.123", formatter).atZone(UTC);
        long epochMilli = zonedDateTime.toInstant().toEpochMilli();
        assertEquals(fromTimestampToSeqNo(epochMilli * MICROSECONDS_PER_MILLISECOND).toStringUtf8(), "202303251654001");
    }

    @Test
    public void testFromLongTimestampToSeqNo()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ZonedDateTime zonedDateTime = LocalDateTime.parse("2023-03-25 16:54:00.123", formatter).atZone(UTC);
        long epochMilli = zonedDateTime.toInstant().toEpochMilli();
        LongTimestamp longTimestamp = new LongTimestamp(epochMilli * MICROSECONDS_PER_MILLISECOND, 123 * 1000);

        assertEquals(fromLongTimestampToSeqNo(longTimestamp).toStringUtf8(), "202303251654001");
    }

    @Test
    public void testFromTimestampWithTZToSeqNo()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ZonedDateTime zonedDateTime = LocalDateTime.parse("2023-03-25 16:54:00.123", formatter).atZone(UTC);
        long epochMilli = zonedDateTime.toInstant().toEpochMilli();
        long packedEpochMillis = packDateTimeWithZone(epochMilli, TimeZoneKey.getTimeZoneKey("Asia/Taipei"));

        assertEquals(fromTimestampWithTZToSeqNo(packedEpochMillis).toStringUtf8(), "202303251654001");
    }

    @Test
    public void testFromLongTimestampWithTZToSeqNo()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        ZonedDateTime zonedDateTime = LocalDateTime.parse("2023-03-25 16:54:00.123", formatter).atZone(UTC);
        long epochMilli = zonedDateTime.toInstant().toEpochMilli();
        LongTimestampWithTimeZone longTimestampWithTimeZone = fromEpochMillisAndFraction(epochMilli, 123 * 1000, TimeZoneKey.getTimeZoneKey("Asia/Taipei"));

        assertEquals(fromLongTimestampWithTZToSeqNo(longTimestampWithTimeZone).toStringUtf8(), "202303251654001");
    }
}
