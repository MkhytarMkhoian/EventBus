/*
 * Copyright 2020 Lalafo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mkhytarmkhoian.eventbus

import io.reactivex.Observable

interface EventBus {
    fun <E : BusEvent> publish(queue: Queue<E>, e: E)

    fun <E : BusEvent> queue(queue: Queue<E>): EventSubject<E>

    fun <E : BusEvent> consumeReplayQueue(queue: Queue<E>): Observable<E>

    fun <E : BusEvent> clearReplayQueue(queue: Queue<E>)

    fun <E : BusEvent> consumeReplayQueue(queue: Queue<E>, actionBeforeClearingQueue: (t: E) -> Boolean): Observable<E>
}
