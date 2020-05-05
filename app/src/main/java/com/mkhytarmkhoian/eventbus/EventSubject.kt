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

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject


class EventSubject<T> private constructor(private val wrappedSubject: Subject<T>) : Subject<T>() {

    override fun hasObservers(): Boolean {
        return wrappedSubject.hasObservers()
    }

    override fun subscribeActual(observer: Observer<in T>) {
        wrappedSubject.subscribe(observer)
    }

    override fun onSubscribe(d: Disposable) {
        wrappedSubject.onSubscribe(d)
    }

    override fun onError(t: Throwable) {
        wrappedSubject.onError(EventSubjectThrowable(t))
    }

    override fun onComplete() {
        wrappedSubject.onComplete()
    }

    override fun onNext(t: T) {
        wrappedSubject.onNext(t)
    }

    override fun hasThrowable(): Boolean {
        return wrappedSubject.hasThrowable()
    }

    override fun hasComplete(): Boolean {
        return wrappedSubject.hasComplete()
    }

    override fun getThrowable(): Throwable? {
        return wrappedSubject.throwable
    }

    class EventSubjectThrowable(cause: Throwable) : Throwable(cause)

    companion object {

        fun <T> create(): EventSubject<T> {
            return EventSubject(PublishSubject.create())
        }

        fun <T> replaying(): EventSubject<T> {
            return EventSubject(BehaviorSubject.create())
        }

        fun <T> replaying(t: T): EventSubject<T> {
            return EventSubject(BehaviorSubject.createDefault(t))
        }
    }
}
