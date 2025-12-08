/*
 * Copyright (C) 2025 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.cash.redwood.lazylayout.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

public class NoOpLoadingStrategy: LoadingStrategy {
  override var firstVisibleIndex: Int by mutableIntStateOf(0)
    private set
  override var lastVisibleIndex: Int by mutableIntStateOf(0)
    private set

  override fun scrollTo(firstVisibleIndex: Int) {}

  override fun onUserScroll(firstVisibleIndex: Int, lastVisibleIndex: Int) {
    this.firstVisibleIndex = firstVisibleIndex
    this.lastVisibleIndex = lastVisibleIndex
  }

  override fun loadRange(itemCount: Int): IntRange {
    return IntRange(0,0)
  }
}
