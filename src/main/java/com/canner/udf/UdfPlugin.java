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

import com.canner.udf.scalar.DataMasking;
import com.canner.udf.scalar.EncryptDecryptWithKey;
import com.canner.udf.scalar.ExtendedHashFunction;
import com.canner.udf.scalar.MathOperation;
import com.canner.udf.scalar.TimestampSeqNo;
import com.canner.udf.scalar.TimestampWithTZSeqNo;
import io.trino.spi.Plugin;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UdfPlugin
        implements Plugin
{
    @Override
    public Set<Class<?>> getFunctions()
    {
        Set<Class<?>> functions = new HashSet<>();
        // load udfs in DataMasking class
        functions.add(DataMasking.class);
        // load udfs in EncryptDecryptWithKey class
        functions.add(EncryptDecryptWithKey.class);
        // load udfs in ExtendedHashFunction class
        functions.add(ExtendedHashFunction.class);
        // load udfs in TimestampSeqNo class
        functions.add(TimestampSeqNo.class);
        // load udfs in TimestampWithTZSeqNo class
        functions.add(TimestampWithTZSeqNo.class);
        // load udfs in MathOperation class
        functions.add(MathOperation.class);

        return Collections.unmodifiableSet(functions);
    }
}
