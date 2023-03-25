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

import io.airlift.slice.Slices;
import org.testng.annotations.Test;

import static com.canner.udf.scalar.DataMasking.maskColumn;
import static com.canner.udf.scalar.DataMasking.maskEmail;
import static io.airlift.slice.Slices.utf8Slice;
import static org.testng.Assert.assertEquals;

public class TestDataMasking
{
    @Test
    public void testMaskColumn()
    {
        assertEquals(
                maskColumn(Slices.utf8Slice("test")),
                utf8Slice("*****"));
    }

    @Test
    public void testEmailMask()
    {
        assertEquals(
                maskEmail(utf8Slice("canner-dev@cannerdata.com"), 5),
                utf8Slice("canne*****@cannerdata.com"));
    }
}
