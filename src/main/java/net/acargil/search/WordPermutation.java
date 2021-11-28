package net.acargil.search;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * WordPermutations is a helper to create variations of given word. <br/> Each variation's
 * characters are in order that is same as original word. <br/> e.g. "kebab" => {"k",
 * "e",...,"ke","eb","ba","ab",...,"keba","ebab","kebab"} <br/> It is possible exclude variations
 * according to their length. Use constructor takes min-max word lengths.
 *
 * @author oguzhan acargil
 */
public final class WordPermutation {

  private static final Logger LOGGER = LoggerFactory.getLogger(WordPermutation.class);
  private static final int MIN_WORD_LENGTH = 1;

  private int minWordLength;
  private int maxWordLength;

  public WordPermutation() {
    this(MIN_WORD_LENGTH);
  }

  public WordPermutation(int minWordLength) {
    this(minWordLength, Integer.MAX_VALUE);
  }

  /**
   * Constructs a <code>WordPermutation</code> object which generate words in the interval of
   * min-max values.
   *
   * @param minWordLength mininmum word length
   * @param maxWordLength maximum word length
   */
  public WordPermutation(int minWordLength, int maxWordLength) {
    super();
    this.minWordLength = minWordLength;
    this.maxWordLength = maxWordLength;
  }

  /**
   * Generating variations of given word.
   *
   * @param word non empty word
   * @return set of words is contained by given word
   */
  public Set<String> getAllVariations(String word) {
    Set<String> variations = new HashSet<>();
    int length = 0;
    if (word == null || (length = word.length()) == 0) {
      return variations;
    }

    int index = 0;
    int beginIndex = 0;
    int endIndex = minWordLength;
    int maxWordLength = getMaxWordLength(length);
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
      endIndex = minWordLength + index;
    }
    LOGGER.trace("getAllVariations({}):{}", word, variations.size());
    return variations;
  }

  /**
   * Generate variations of given word. Each of them starts with same character of given word and
   * follow with others. e.g. given word is "kebab", and it results {"k","ke","keb","keba","kebab"}
   *
   * @param word non empty word
   * @return set of words is contained by given word
   */
  public Set<String> getVariationsFromStart(String word) {
    Set<String> variations = new HashSet<>();
    int length = 0;
    if (word == null || (length = word.length()) == 0) {
      return variations;
    }

    int endIndex = minWordLength;
    int maxWordLength = getMaxWordLength(length);
    while (endIndex <= maxWordLength) {
      String variation = word.substring(0, endIndex);
      variations.add(variation);
      endIndex++;
    }
    LOGGER.trace("getVariationsFromStart({}):{}", word, variations.size());
    return variations;
  }

  /**
   * Decide maximum word length between given word length or predefined max word length.
   *
   * @param wordLength word length
   * @return maximum possible word length
   */
  protected int getMaxWordLength(int wordLength) {
    if (this.maxWordLength > wordLength) {
      return wordLength;
    } else {
      return this.maxWordLength;
    }
  }
}
