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

import net.acargil.autocomplete.AutoCompleteIndex;
import net.acargil.autocomplete.Extractor;

import java.util.List;

/**
 * @param <T>
 * @author oguzhan acargil
 */
public class GraphIndex<T> implements AutoCompleteIndex<T> {

  private char[] alphabet;
  private int minLength;


  /* (non-Javadoc)
   * @see net.acargil.autocomplete.AutoCompleteIndex#filter(java.lang.String)
   */
  @Override
  public List<T> filter(String phrase) {

    return null;
  }

  /* (non-Javadoc)
   * @see net.acargil.autocomplete.AutoCompleteIndex#index(java.util.List)
   */
  @Override
  public int index(List<T> entities, Extractor<T> extractor) {
    return 0;
  }

  public void setAlphabet(char[] alphabet) {
    this.alphabet = alphabet;
  }

  public void setMinLength(int minLength) {
    this.minLength = minLength;
  }

}
