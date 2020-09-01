/*
 * Copyright 2020 Hochikong
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.hochikong.ktmeta.predefined

import org.jasypt.util.password.ConfigurablePasswordEncryptor
import java.security.Security

object Encryption {
    private val encryptor = ConfigurablePasswordEncryptor().apply {
        setProvider(Security.getProvider("SUN"))
        setAlgorithm("SHA-512")
        setPlainDigest(false)
//        setStringOutputType("hexadecimal")
        setStringOutputType("base64")
    }

    fun encrypt(input: String): String {
        return encryptor.encryptPassword(input)
    }

    fun verify(raw: String, encrypted: String): Boolean {
        return encryptor.checkPassword(raw, encrypted)
    }

}