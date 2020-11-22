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

package predefined

import io.github.hochikong.ktmeta.predefined.Encryption
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestEncrytion {
    @Test
    fun testEncryption() {
        val raw = "jasu87#^@5e24uwu25ee"
        val enc = Encryption.encrypt(raw)
        assertEquals(true, Encryption.verify(raw, enc))
        println("raw password is $raw")
        println("encryption result: $enc")

        val enc1 = Encryption.encrypt(raw)
        println(enc1)
        assertEquals(true, Encryption.verify(raw, enc1))
    }

    @Test
    fun testPasswordEncryption(){
        val input = """
            When using a random salt generator, two encryption results for the same message will always be different (except in the case of random salt coincidence). 
        This enforces security by difficulting brute force attacks on sets of data at a time and forcing 
        attackers to perform a brute force attack on each separate piece of encrypted data.
        """.trimIndent()
        val enc = Encryption.encryptByPassword(input, "nmsl")
        println("encrypted text: $enc")
        assertEquals(input, Encryption.decryptByPassword(enc, "nmsl"))
    }
}