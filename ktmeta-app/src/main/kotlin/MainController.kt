/*
 * Copyright 2021 Hochikong
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

import com.formdev.flatlaf.FlatIntelliJLaf
import io.github.hochikong.ktmeta.dao.impl.DBResourcePool
import io.github.hochikong.ktmeta.dao.impl.ESResourcePool
import io.github.hochikong.ktmeta.dao.impl.MLResourcePool
import io.github.hochikong.ktmeta.dao.impl.MPResourcePool
import io.github.hochikong.ktmeta.swingui.controller.LaunchViewC
import io.github.hochikong.ktmeta.swingui.controller.MainScene
import javax.swing.JDialog

class MainController {
    private var fcLaunchView: LaunchViewC = LaunchViewC().apply {
        this.setLocationRelativeTo(null)
    }

    private var fcMainScene: MainScene = MainScene().apply {
        this.setLocationRelativeTo(null)
    }

    private var dialogsMap: MutableMap<String, JDialog> = mutableMapOf()

    private fun initResources() {
        // check
        if (!DBResourcePool.hasTable()) {
            DBResourcePool.resetTable()
        }

        if (!ESResourcePool.hasTable()) {
            ESResourcePool.resetTable()
        }

        if (!MLResourcePool.hasTable()) {
            MLResourcePool.resetTable()
        }


        if (!MPResourcePool.hasTable()) {
            MPResourcePool.resetTable()
        }
    }

    init {
        fcLaunchView.isVisible = true
        fcLaunchView.updateLoadingTest("正在初始化环境")
        initResources()
        Thread.sleep(2000)
        fcLaunchView.updateLoadingTest("正在初始化工具")
        Thread.sleep(2000)
        fcLaunchView.updateLoadingTest("软件即将完成初始化")
        Thread.sleep(3000)
        fcLaunchView.dispose()

        val mlList = MLResourcePool.getAllRecords()
        fcMainScene.fullUpdateMetaLibsTree(mlList.map { it.lib_name }.toList())

        val dbList = DBResourcePool.getAllRecords()
        fcMainScene.fullUpdateDBTree(dbList.map { it.db_name }.toList())

        val indexList = ESResourcePool.getAllRecords()
        fcMainScene.fullUpdateIndexTree(indexList.map { it.index_name }.toList())

        fcMainScene.isVisible = true
    }
}

fun main() {
    FlatIntelliJLaf.install()
    MainController()
}