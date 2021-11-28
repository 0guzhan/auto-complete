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
public class Primes {

  private static final long[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
      59, 61, 67, 71, 73, 79,
      83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179,
      181, 191, 193, 197,
      199};

  private static long euclidGCD(long a, long b) {
    if (b == 0) {
      return a;
    } else {
      return euclidGCD(b, a % b);
    }
  }

  public static void main(String[] args) {
    char[] chars = new char['z' - 'a' + 1];
    String sentence =
        "The Euclidean algorithm is based on the principle that the greatest common divisor "
            + "of two numbers does not change if the larger number is replaced "
            + "by its difference with the smaller number.";

    String firstName = "oguzhan writes";
    String secondName = "acargil reads";
    long firstHash = pHash(firstName);
    long secondHash = pHash(secondName);

    long gcd = euclidGCD(firstHash, secondHash);
    int i = 0;
    for (long prime : PRIMES) {
      if (gcd % prime == 0) {
        int times = howManyTimes(gcd, prime);
        System.out.println(times + " times " + (char) ('a' + i) + " ");
      }
      i++;
    }
  }

  /**
   * @param gcd
   * @param prime
   * @return
   */
  private static int howManyTimes(long gcd, long prime) {
    if (gcd % prime == 0) {
      return 1 + howManyTimes(gcd / prime, prime);
    } else {
      return 0;
    }
  }

  /**
   * @param text
   * @return
   */
  private static long pHash(String text) {
    long hash = 1;
    for (int i = 0; i < text.length(); i++) {
      if ('a' <= text.charAt(i) && text.charAt(i) <= 'z') {
        long prime = PRIMES[text.charAt(i) - 'a'];
        hash = hash * prime;
      }
    }
    return hash;
  }
}
