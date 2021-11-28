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

/**
 * @author oguzhan acargil
 */
public class VertexChar {

  private static final int DEFAULT_NEXT_SIZE = 16;

  private char c;
  private int[] places;
  private int placeIndex;
  private char[] nextChars;
  private int nextIndex;

  /**
   * @param c
   */
  public VertexChar(char c) {
    this.c = c;
    this.nextChars = new char[DEFAULT_NEXT_SIZE];
    this.nextIndex = -1;
    this.placeIndex = -1;
  }

  /**
   * @return the c
   */
  public char getCharacter() {
    return this.c;
  }

  public boolean hasNext() {
    return this.nextIndex > -1;
  }

  public void addPlace(int newPlace) {
    placeIndex++;
    if (placeIndex == places.length) {
      int[] newPlaces = new int[places.length * 2];
      System.arraycopy(places, 0, newPlaces, 0, places.length);
      places = newPlaces;
    }
    places[placeIndex] = newPlace;
  }

  public void addNext(char next) {
    nextIndex++;
    if (nextIndex == nextChars.length) {
      char[] newNextChars = new char[nextChars.length * 2];
      System.arraycopy(nextChars, 0, newNextChars, 0, nextChars.length);
      nextChars = newNextChars;
    }
    nextChars[nextIndex] = next;
  }
}
