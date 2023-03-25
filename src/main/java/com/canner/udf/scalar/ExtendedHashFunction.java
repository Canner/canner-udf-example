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
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import static io.airlift.slice.Slices.utf8Slice;

public class ExtendedHashFunction
{
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Description("RipeMD160")
    @ScalarFunction("ripemd_160")
    @SqlType(StandardTypes.VARCHAR)
    public static Slice ripeMD160(@SqlType(StandardTypes.VARCHAR) Slice value)
            throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = MessageDigest.getInstance("RipeMD160");
        messageDigest.update(value.getBytes());
        byte[] result = messageDigest.digest();
        return utf8Slice(new BigInteger(1, result).toString(16));
    }
}
