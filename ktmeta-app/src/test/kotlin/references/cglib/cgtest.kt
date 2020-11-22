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

package references.cglib

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method

open class DAO {
    open fun update(what: String) = println("Updating $what")
    open fun select() = println("Selecting")
}

class DAOProxy : MethodInterceptor {
    override fun intercept(obj: Any, method: Method, args: Array<out Any>, proxy: MethodProxy): Any {
        println("Before Method Invoke")
        proxy.invokeSuper(obj, args)
        println("After Method Invoke")
        return obj
    }
}

fun main() {
    val proxy = DAOProxy()
    val enhancer = Enhancer()
    enhancer.setSuperclass(DAO::class.java)
    enhancer.setCallback(proxy)

    val dao = enhancer.create() as DAO
    dao.update("sb")
    dao.select()
}