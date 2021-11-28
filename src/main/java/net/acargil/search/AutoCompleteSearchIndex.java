package net.acargil.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import net.acargil.i18n.Alphabets;

public class AutoCompleteSearchIndex<T> {

  private static final String EXCLUDE_PATTERN = "[^" + Alphabets.Turkish.ALPHABET + " ]";
  private static final String SPACE = " ";
  private static final String EMPTY = "";

  private T[] terms;
  private TreeMap<String, Set<Integer>> searchIndex;
  private WordPermutation wordPermutations;
  private int minWordLength;

  private AutoCompleteSearchIndex(T[] terms, int minWordLength) {
    this.terms = terms;
    this.searchIndex = new TreeMap<>();
    this.wordPermutations = new WordPermutation(minWordLength);
    this.minWordLength = minWordLength;
  }

  /**
   * Create index for content to search in best time. The generic type must provide content that
   * will be indexed in
   * <code>toString</code> method. <br/>
   * <br/>
   *
   * <code>
   * class AnyEntity {<br/> private String content;<br/> //...<br/> public String toString() {<br/>
   * return content;<br/> }<br/> }<br/>
   * </code>
   */
  public static final <T> AutoCompleteSearchIndex<T> newInstance(List<T> terms,
      ContentProvider<T> contentProvider,
      int minWordLength) {
    if (terms != null && terms.size() > 0) {
      @SuppressWarnings("unchecked")
      T[] termArray = (T[]) new Object[terms.size()];
      terms.toArray(termArray);
      AutoCompleteSearchIndex<T> autoCompleteIndex = new AutoCompleteSearchIndex<T>(termArray,
          minWordLength);
      autoCompleteIndex.createIndexes(contentProvider);
      return autoCompleteIndex;
    } else {
      throw new IllegalArgumentException("No words found!");
    }
  }

  private void createIndexes(ContentProvider<T> contentProvider) {
    AtomicInteger index = new AtomicInteger();
    for (T term : terms) {
      String word = contentProvider.get(term);
      String[] subwords = getWords(word);
      for (String subword : subwords) {
        subword = cleanWhitespaces(subword);
        Set<String> variations = wordPermutations.getVariationsFromStart(subword);
        for (String variation : variations) {
          insertIndex(index.get(), variation);
        }
      }
      index.incrementAndGet();
    }
  }

  private void insertIndex(int index, String variation) {
    if (searchIndex.containsKey(variation)) {
      Set<Integer> idSet = searchIndex.get(variation);
      idSet.add(index);
    } else {
      Set<Integer> idSet = new HashSet<>();
      idSet.add(index);
      searchIndex.put(variation, idSet);
    }
  }

  private String[] getWords(String word) {
    word = cleanWhitespaces(word);
    if (word != null && !word.isEmpty()) {
      return word.split(SPACE);
    } else {
      return new String[]{};
    }
  }

  /**
   * Filters and returns entities that contains given phrase. If phrase null or size of phrase less
   * than minimum word length; returns empty list.
   *
   * @param phrase any non empty word which contains characters of target alphabet
   * @return list of entities that contains given phrase
   */
  public List<T> filter(String phrase) {
    if (phrase != null && !phrase.isEmpty()) {

      Set<Integer> indexes = new TreeSet<>();
      phrase = cleanWhitespaces(phrase);
      String[] subphrases = getWords(phrase);
      for (String subphrase : subphrases) {
        if (subphrase.length() >= minWordLength) {
          Set<Integer> phraseIndexes = searchIndex.get(subphrase);
          if (!indexes.isEmpty()) {
            // select intersection of indexes
            indexes = selectIntersection(indexes, phraseIndexes);
            if (indexes.isEmpty()) {
              break;
            }
          } else if (phraseIndexes != null) {
            // only first iteration
            indexes = phraseIndexes;
          }
        }
      }
      // selecting terms with indexes
      List<T> terms = new LinkedList<>();
      for (Integer i : indexes) {
        terms.add((T) this.terms[i]);
      }
      return terms;
    } else {
      return new LinkedList<>();
    }
  }

  /**
   * select intersection of two given sets.
   *
   * @param firstSet  first set
   * @param secondSet second set
   * @return intersection of both sets or empty if none common item exists
   */
  private Set<Integer> selectIntersection(Set<Integer> firstSet, Set<Integer> secondSet) {
    // if any of given sets are empty or null; return empty set
    if (firstSet == null || firstSet.isEmpty() || secondSet == null || secondSet.isEmpty()) {
      return new TreeSet<>();
    }

    // deciding small and big sets
    Set<Integer> bigSet = firstSet;
    Set<Integer> smallSet = secondSet;
    if (firstSet.size() < secondSet.size()) {
      smallSet = firstSet;
      bigSet = secondSet;
    }

    // if big set contains item of small set; add this item to intersection
    Set<Integer> intersection = new TreeSet<>();
    for (Integer itemInSmall : smallSet) {
      if (bigSet.contains(itemInSmall)) {
        intersection.add(itemInSmall);
      }
    }

    return intersection;
  }

  /**
   * remove whitespaces and redundant spaces then return lower case.
   *
   * @param word word
   * @return lower case clean word
   */
  private String cleanWhitespaces(String word) {
    // remove non alphabetic characters
    word = word.replaceAll(EXCLUDE_PATTERN, EMPTY);
    // replace multiple redundant spaces with single space
    word = word.replaceAll("[ ]{2,}", SPACE);
    // trim spaces from start and end
    word = word.trim();
    // make lower case
    word = Alphabets.Turkish.toLowerCase(word);
    return word;
  }
}
