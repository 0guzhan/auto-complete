/*
 * Copyright 2016 oguzhan acargil
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package net.acargil.i18n;

public class Alphabets {

  public static final class Numbers {

    public static final String ALL = "0123456789";
    public static final String COUNTABLE_ASC = "123456789";
    public static final String COUNTABLE_DESC = "987654321";
  }

  public static final class English {

    public static final class Lower {

      public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    }

    public static final class Upper {

      public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    }
  }

  public static final class Turkish {

    public static final String ALPHABET = Upper.ALPHABET + Lower.ALPHABET;

    /**
     * change given word into upper case.
     *
     * @param word word
     * @return word in upper case with respect to TR language equivalent
     */
    public static String toUpperCase(String word) {
      char[] characters = word.toCharArray();
      int wordLength = characters.length;
      char[] upperCaseCharacters = new char[wordLength];
      for (int i = 0; i < wordLength; i++) {
        char upperCaseCharacter = characters[i];
        if ('a' <= characters[i] && characters[i] < 'i'
            || 'i' < characters[i] && characters[i] <= 'z') {
          upperCaseCharacter = Character.toUpperCase(characters[i]);
        } else {
          switch (characters[i]) {
            case '\u00e7':
              upperCaseCharacter = '\u00c7';
              break;
            case '\u0131':
              upperCaseCharacter = 'I';
              break;
            case 'i':
              upperCaseCharacter = '\u0130';
              break;
            case '\u011f':
              upperCaseCharacter = '\u011e';
              break;
            case '\u00f6':
              upperCaseCharacter = '\u00d6';
              break;
            case '\u00fc':
              upperCaseCharacter = '\u00dc';
              break;
            case '\u015f':
              upperCaseCharacter = '\u015e';
              break;
            default:
              upperCaseCharacter = Character.toUpperCase(characters[i]);
              break;
          }
        }
        upperCaseCharacters[i] = upperCaseCharacter;
      }

      return new String(upperCaseCharacters);
    }

    /**
     * change given word to lower case.
     *
     * @param word word
     * @return word in lower case with respect to TR language equivalent
     */
    public static String toLowerCase(String word) {
      char[] characters = word.toCharArray();
      int wordLength = characters.length;
      char[] lowerCaseCharacters = new char[wordLength];
      for (int i = 0; i < wordLength; i++) {
        char lowerCaseCharacter = characters[i];
        if ('A' <= characters[i] && characters[i] < 'I'
            || 'I' < characters[i] && characters[i] <= 'Z') {
          lowerCaseCharacter = Character.toLowerCase(characters[i]);
        } else {
          switch (characters[i]) {
            case '\u00c7':
              lowerCaseCharacter = '\u00e7';
              break;
            case 'I':
              lowerCaseCharacter = '\u0131';
              break;
            case '\u0130':
              lowerCaseCharacter = 'i';
              break;
            case '\u011e':
              lowerCaseCharacter = '\u011f';
              break;
            case '\u00d6':
              lowerCaseCharacter = '\u00f6';
              break;
            case '\u00dc':
              lowerCaseCharacter = '\u00fc';
              break;
            case '\u015e':
              lowerCaseCharacter = '\u015f';
              break;
            default:
              lowerCaseCharacter = Character.toLowerCase(characters[i]);
              break;
          }
        }
        lowerCaseCharacters[i] = lowerCaseCharacter;
      }

      return new String(lowerCaseCharacters);
    }

    public static final class Lower {

      public static final String CH = "\u00e7";
      public static final String II = "\u0131";
      public static final String GH = "\u011f";
      public static final String OU = "\u00f6";
      public static final String UU = "\u00fc";
      public static final String SH = "\u015f";

      public static final String ALPHABET =
          "abc" + CH + "defg" + GH + "h" + II + "ijklmno" + OU + "prs" + SH + "tu" + UU
              + "vyz";
    }

    public static final class Upper {

      public static final String CH = "\u00c7";
      public static final String GH = "\u011e";
      public static final String II = "\u0130";
      public static final String OU = "\u00d6";
      public static final String UU = "\u00dc";
      public static final String SH = "\u015e";

      public static final String ALPHABET =
          "ABC" + CH + "DEFG" + GH + "HI" + II + "JKLMNO" + OU + "PRS" + SH + "TU" + UU
              + "VYZ";
    }
  }

}