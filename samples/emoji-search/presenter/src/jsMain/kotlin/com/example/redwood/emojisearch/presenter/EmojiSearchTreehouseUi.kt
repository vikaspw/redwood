/*
 * Copyright (C) 2023 Square, Inc.
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
package com.example.redwood.emojisearch.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import app.cash.redwood.Modifier
import app.cash.redwood.compose.ConsumeInsets
import app.cash.redwood.layout.api.Constraint.Companion.Fill
import app.cash.redwood.layout.api.MainAxisAlignment
import app.cash.redwood.layout.compose.Column
import app.cash.redwood.layout.compose.Spacer
import app.cash.redwood.lazylayout.compose.LazyColumn
import app.cash.redwood.lazylayout.compose.itemsIndexed
import app.cash.redwood.lazylayout.compose.rememberLazyListState
import app.cash.redwood.treehouse.TreehouseUi
import app.cash.redwood.ui.basic.compose.Button
import app.cash.redwood.ui.basic.compose.Text
import app.cash.redwood.ui.dp

class EmojiSearchTreehouseUi(
  private val httpClient: HttpClient,
  private val navigator: Navigator,
) : TreehouseUi {
  @Composable
  override fun Show() {
    ConsumeInsets { insets ->
      EmojiSearch(
        httpClient = httpClient,
        navigator = navigator,
        viewInsets = insets,
      )
      //TestingScreen()
    }
  }
}

object StateData {
  var count by mutableIntStateOf(100)

  val stateList: SnapshotStateList<Int> = mutableStateListOf<Int>()
}

/**
 * General purpose testing fn.
 */
@Composable
fun TestingScreen() {
  val listState = rememberLazyListState()

  val lastVisibleIndex by remember {
    derivedStateOf { listState.strategy.lastVisibleIndex }
  }

  LaunchedEffect(lastVisibleIndex) {
    println("LazyListState, firstVisibleIndex : ${listState.strategy.firstVisibleIndex}")
    println("LazyListState, lastVisibleIndex : ${listState.strategy.lastVisibleIndex}")
  }

  LaunchedEffect(StateData.stateList.size) {
    val lastIdx = StateData.stateList.lastIndex
    if (StateData.stateList.isNotEmpty()) {
      //listState.programmaticScroll(lastIdx, false)
    }
  }

  Column (
    Fill,
    Fill,
    verticalAlignment = MainAxisAlignment.End
  ){
    Spacer(height = 60.dp)
    LazyColumn(
      modifier = Modifier.flex(1.0),
      state = listState,
      width = Fill,
      height = Fill,
      reverseLayout = false,
      placeholder = {}
    ) {
      itemsIndexed(StateData.stateList) { idx, _ ->
        Text(text = "Item is ${StateData.stateList[idx]}, on index $idx")
      }
    }
    Button(
      text= "Add Message", onClick = {
      StateData.stateList.add(StateData.count++)
    })
    Spacer(height = 24.dp)
  }

}
