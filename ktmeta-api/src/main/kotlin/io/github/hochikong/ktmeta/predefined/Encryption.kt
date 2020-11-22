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
import org.jasypt.util.text.AES256TextEncryptor
import java.security.Security

object Encryption {
    private val hashEncryption = ConfigurablePasswordEncryptor().apply {
        setProvider(Security.getProvider("SUN"))
        setAlgorithm("SHA-512")
        setPlainDigest(false)
        setStringOutputType("base64")
    }

    fun encrypt(input: String): String {
        return hashEncryption.encryptPassword(input)
    }

    fun verify(raw: String, encrypted: String): Boolean {
        return hashEncryption.checkPassword(raw, encrypted)
    }

    fun encryptByPassword(input: String, password: String): String{
        val aesEncryption = AES256TextEncryptor()
        aesEncryption.setPassword(password)
        return aesEncryption.encrypt(input)
    }

    fun decryptByPassword(input: String, password: String): String{
        val aesEncryption = AES256TextEncryptor()
        aesEncryption.setPassword(password)
        return aesEncryption.decrypt(input)
    }

}