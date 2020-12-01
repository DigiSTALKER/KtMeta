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

package io.github.hochikong.ktmeta.service.metaplugin

/**
 * All metadata plugins can implement this interface to add ElasticSearch support.
 * This interface supports the use of RDBMS or ElasticSearch to store metadata.
 *
 * Supported features:
 * 1. Search result paging
 * 2. Edit abstract
 * 3. Edit custom tags
 * 4. Edit preview
 * 5. Import/Export metadata document (as json
 * 6. Full-Text search support and search rules
 * 7. Advance search support and search rules
 * 8. Aggregate search support
 * 9. Metadata maintain: update, delete
 * 10. Add custom jMenuItem in 'Tools' and new jDialog or jFrame
 * 11. Plugin description
 * */
interface AdvMetaPluginAPI