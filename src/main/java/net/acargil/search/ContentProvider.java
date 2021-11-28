/*
 * Copyright 2016 oguzhan acargil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.acargil.search;

/**
 * Functional interface for indexing content. Implementing classes must decide what to be indexed
 * with providing content in response of get method.
 *
 * @author oguzhan acargil
 */
public interface ContentProvider<T> {

  String get(T item);

}
