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

package net.acargil.autocomplete;

import net.acargil.i18n.Alphabets;

/**
 * @author oguzhan acargil
 */
public class AutoCompleteIndexBuilder<T> {

  public static final char[] DEFAULT_ALPHABET = Alphabets.Turkish.ALPHABET.toCharArray();

  private char[] alphabet;
  private int minLength;

  public static <T> AutoCompleteIndexBuilder<T> newBuilder() {
    return new AutoCompleteIndexBuilder<>();
  }

  public AutoCompleteIndex<T> build() {
    GraphIndex<T> graphIndex = new GraphIndex<>();
    if (minLength == 0) {
      minLength = 2;
    }
    graphIndex.setMinLength(minLength);
    if (alphabet == null) {
      alphabet = DEFAULT_ALPHABET;
    }
    graphIndex.setAlphabet(alphabet);
    return graphIndex;
  }

  public AutoCompleteIndexBuilder<T> alphabet(String alphabet) {
    this.alphabet = alphabet.toCharArray();
    return this;
  }

  public AutoCompleteIndexBuilder<T> minLength(int minLength) {
    this.minLength = minLength;
    return this;
  }

}
