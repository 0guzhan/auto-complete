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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author oguzhan acargil
 */
public class WordSampler {

  private static final int MIN = 2;

  public static void main(String[] args) {
    System.out.println(getVariationsFromStart("oguzhan"));
    System.out.println(getAllVariations("oguzhan"));
  }

  /**
   * Generating variations of given word.
   *
   * @param word non empty word
   * @return set of words is contained by given word
   */
  public static Set<String> getAllVariations(String word) {
    Set<String> variations = new HashSet<>();
    int length = 0;
    if (word == null || (length = word.length()) == 0) {
      return variations;
    }

    int index = 0;
    int beginIndex = 0;
    int endIndex = 2;
    int maxWordLength = length;
    while ((endIndex - beginIndex) <= maxWordLength) {

      // move range to right to select substrings
      while (endIndex <= maxWordLength) {
        String variation = word.substring(beginIndex, endIndex);
        variations.add(variation);

        beginIndex++;
        endIndex++;
      }

      index++;
      beginIndex = 0;
      endIndex = 2 + index;
    }
    return variations;
  }

  /**
   * Generate variations of given word. Each of them starts with same character of given word and
   * follow with others. e.g. given word is "kebab", and it results {"k","ke","keb","keba","kebab"}
   *
   * @param word non empty word
   * @return set of words is contained by given word
   */
  public static Set<String> getVariationsFromStart(String word) {
    Set<String> variations = new HashSet<>();
    int length = 0;
    if (word == null || (length = word.length()) == 0) {
      return variations;
    }

    int endIndex = 2;
    int maxWordLength = length;
    while (endIndex <= maxWordLength) {
      String variation = word.substring(0, endIndex);
      variations.add(variation);
      endIndex++;
    }

    return variations;
  }
}
