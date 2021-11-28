package net.acargil.search;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.acargil.i18n.Alphabets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Random test utilities.
 *
 * @author oguzhan acargil
 */
public class RandomTestUtil {

  /**
   * Select random element of given colletion.
   *
   * @return randomly selected element
   */
  public static final <T> T selectRandomElement(Collection<T> collection) {
    if (collection != null && collection.isEmpty()) {
      return null;
    } else {
      int size = collection.size();
      int randomIndex = RandomUtils.nextInt(0, size);
      T randomElement = null;
      Iterator<T> iterator = collection.iterator();
      while (iterator.hasNext()) {
        T next = iterator.next();
        if (randomIndex == 0) {
          randomElement = next;
          break;
        } else {
          randomIndex--;
        }
      }

      return randomElement;
    }
  }

  public static List<String> generateRandomWords(int wordSize, int numberOfWords) {
    return generateRandomWords(wordSize, numberOfWords, Alphabets.Turkish.ALPHABET);
  }

  /**
   * Generates list of random <code>String</code> objects.
   *
   * @param wordSize      size of each <code>String</code>
   * @param numberOfWords number of <code>String</code>s to be created
   * @param alphabet      character set will be used while generating random <code>String</code>s
   */
  public static List<String> generateRandomWords(int wordSize, int numberOfWords, String alphabet) {
    List<String> words = new LinkedList<>();
    for (int i = 0; i < numberOfWords; i++) {
      String word = RandomStringUtils.random(wordSize, alphabet);
      words.add(word);
    }

    return words;
  }

  /**
   * Select random part of given word.
   *
   * @param word target word
   * @return random part
   */
  public static String getRandomSubstring(String word) {
    if (StringUtils.isNotEmpty(word)) {
      int length = word.length();
      if (length == 1) {
        return word.substring(0);
      } else {
        int middleIndex = length / 2;
        int beginIndex = RandomUtils.nextInt(0, middleIndex);
        int endIndex = RandomUtils.nextInt(middleIndex, length);
        return word.substring(beginIndex, endIndex);
      }
    } else {
      return "";
    }
  }

  /**
   * Select random part of given word.
   *
   * @param word target non empty word
   * @return random part
   */
  public static String getRandomSubstringFromStart(String word) {
    if (StringUtils.isNotEmpty(word)) {
      int length = word.length();
      if (length == 1) {
        return word.substring(0);
      } else {
        int beginIndex = 0;
        int endIndex = RandomUtils.nextInt(1, length - 1);
        return word.substring(beginIndex, endIndex);
      }
    } else {
      return "";
    }
  }

  /**
   * Generate a random word with given size.
   *
   * @param wordSize word size
   * @return generated random word
   */
  public static String generateRandomWord(int wordSize) {
    List<String> randomWords = generateRandomWords(wordSize, 1);
    String firstRandomWord = randomWords.get(0);
    return firstRandomWord;
  }

}
