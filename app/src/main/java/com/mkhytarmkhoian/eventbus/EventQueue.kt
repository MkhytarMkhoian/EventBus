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

/**
 * Queues of events based on subjects. Keep in mind that they all are hot and if you need just
 * one event use smth like .take(1) to finish stream.
 */
object EventQueue {
    val LOW_MEMORY_EVENT: Queue<NoneEvent> = Queue.of(NoneEvent::class.java).get()
    val LOW_MEMORY_EVENT_REPLAY: Queue<LowMemoryEvent> = Queue.of(LowMemoryEvent::class.java).replay().get()
}
