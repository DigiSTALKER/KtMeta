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

package io.github.hochikong.ktmeta.swingui.controller

/**
 * Settings configuration data class
 *
 * When [enableCustomFont] is false, it means the software use default font config on platforms.
 *
 * @param pluginMapping, The key is a plugin's unique id, the value represents a plugin is enabled or not.
 * */
data class SettingConfigs(
    val langSelected: String,
    val themeSelected: String,
    val enableCustomFont:Boolean,
    val customFont: String,
    val customFontStyle: String,
    val customFontSize: Int,
    val pluginMapping: Map<String, String>
)