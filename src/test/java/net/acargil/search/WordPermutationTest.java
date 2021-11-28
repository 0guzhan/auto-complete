package net.acargil.search;

import java.util.Set;
import net.acargil.i18n.Alphabets;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordPermutationTest {

  private int wordSize;
  private WordPermutation wordPermutation;

  @Before
  public void setup() {
    wordSize = 50;
    wordPermutation = new WordPermutation();
  }

  @Test
  public void getVariationsFromStart() {
    String word = RandomStringUtils.random(wordSize, Alphabets.Turkish.ALPHABET);
    Set<String> variations = wordPermutation.getVariationsFromStart(word);

    Assert.assertNotNull(variations);
    Assert.assertTrue(variations.contains(word));
    Assert.assertTrue(variations.contains(RandomTestUtil.getRandomSubstringFromStart(word)));
    Assert.assertFalse(variations.contains(word + word));
    Assert.assertFalse(variations.contains(""));
  }

  @Test
  public void getAllVariations() {
    String word = RandomStringUtils.random(wordSize, Alphabets.Turkish.ALPHABET);
    Set<String> variations = wordPermutation.getAllVariations(word);

    Assert.assertNotNull(variations);
    Assert.assertTrue(variations.contains(word));
    Assert.assertTrue(variations.contains(RandomTestUtil.getRandomSubstring(word)));
    Assert.assertFalse(variations.contains(word + word));
    Assert.assertFalse(variations.contains(""));
  }

  @Test
  public void getMaxWordLengthAsWord() {
    int maxWordLength = wordPermutation.getMaxWordLength(wordSize);
    Assert.assertEquals(wordSize, maxWordLength);
  }

  @Test
  public void getMaxWordLengthAsWordPermutation() {
    int wordLength = wordSize - 1;
    WordPermutation wordPermutation = new WordPermutation(0, wordLength);
    int maxWordLength = wordPermutation.getMaxWordLength(wordSize);
    Assert.assertEquals(wordLength, maxWordLength);
  }

}
