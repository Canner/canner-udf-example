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

import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;

import static com.canner.udf.scalar.ExtendedHashFunction.ripeMD160;
import static io.airlift.slice.Slices.utf8Slice;
import static org.testng.Assert.assertEquals;

public class TestExtendedHashFunction
{
    @Test
    public void testRipeMD160()
            throws NoSuchAlgorithmException
    {
        assertEquals(
                new String(ripeMD160(utf8Slice("canner-dev")).getBytes()),
                "2a9dc86c2b13606ffeef8b257f619edfadf7e1d6");
    }
}
