/*
 * Copyright 2016 M. Isuru Tharanga Chrishantha Perera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.chrishantha.microbenchmark.counter.impl;

import com.github.chrishantha.microbenchmark.counter.Counter;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter implements Counter {

    private final Semaphore semaphore;
    private long counter;

    public SemaphoreCounter(boolean fair) {
        semaphore = new Semaphore(1, fair);
    }

    public long getCount() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            return counter;
        } finally {
            semaphore.release();
        }
    }

    public void increment() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            ++counter;
        } finally {
            semaphore.release();
        }
    }
}
