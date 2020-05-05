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

class Queue<T : BusEvent> internal constructor(
    private val eventType: Class<T>,
    internal val replayLast: Boolean,
    internal val defaultEvent: T?
) {
    internal val id: Int

    init {
        val i = runningId
        runningId = i + 1
        this.id = i
    }

    override fun hashCode(): Int {
        return this.id
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is Queue<*> && other.id == this.id
    }

    override fun toString(): String {
        return "[" + this.eventType.canonicalName + "]"
    }

    class Builder<T : BusEvent> internal constructor(private val eventType: Class<T>) {
        private var defaultEvent: T? = null
        private var replayLast: Boolean = false

        fun replay(): Builder<T> {
            this.replayLast = true
            return this
        }

        fun replay(defaultEvent: T): Builder<T> {
            this.replayLast = true
            this.defaultEvent = defaultEvent
            return this
        }

        fun defaultEvent(defaultEvent: T): Builder<T> {
            this.defaultEvent = defaultEvent
            return this
        }

        fun get(): Queue<T> {
            return Queue(this.eventType, this.replayLast, this.defaultEvent)
        }
    }

    companion object {
        private var runningId: Int = 0

        fun <T : BusEvent> of(cls: Class<T>): Builder<T> {
            return Builder(cls)
        }
    }
}
