/*
 * Copyright The OpenTelemetry Authors
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

package io.opentelemetry.auto.bootstrap;

/**
 * Interface to represent context storage for instrumentations.
 *
 * <p>Context instances are weakly referenced and will be garbage collected when their corresponding
 * key instance is collected.
 *
 * @param <K> key type to do context lookups
 * @param <C> context type
 */
public interface ContextStore<K, C> {

  /**
   * Factory interface to create context instances
   *
   * @param <C> context type
   */
  interface Factory<C> {

    /** @return new context instance */
    C create();
  }

  /**
   * Get context given the key
   *
   * @param key the key to looup
   * @return context object
   */
  C get(K key);

  /**
   * Put new context instance for given key
   *
   * @param key key to use
   * @param context context instance to save
   */
  void put(K key, C context);

  /**
   * Put new context instance if key is absent
   *
   * @param key key to use
   * @param context new context instance to put
   * @return old instance if it was present, or new instance
   */
  C putIfAbsent(K key, C context);

  /**
   * Put new context instance if key is absent. Uses context factory to avoid creating objects if
   * not needed.
   *
   * @param key key to use
   * @param contextFactory factory instance to produce new context object
   * @return old instance if it was present, or new instance
   */
  C putIfAbsent(K key, Factory<C> contextFactory);
}
