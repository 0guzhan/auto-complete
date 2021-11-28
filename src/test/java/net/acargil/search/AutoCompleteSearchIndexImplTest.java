package net.acargil.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AutoCompleteSearchIndexImplTest {

  private static final int NUMBER_OF_WORDS = 1000;
  private static final int WORD_SIZE = 50;
  private static final int MIN_WORD_SIZE = 2;

  private AutoCompleteSearchIndex<String> wordIndex;
  private AutoCompleteSearchIndex<DummyEntity> entityIndex;
  private List<String> words;
  private List<DummyEntity> dummyEntities;

  /**
   * Test setup.
   */
  @Before
  public void setup() {
    words = RandomTestUtil.generateRandomWords(WORD_SIZE, NUMBER_OF_WORDS);
    wordIndex = AutoCompleteSearchIndex.newInstance(words, new StringContentProvider(),
        MIN_WORD_SIZE);

    dummyEntities = new LinkedList<>();
    List<String> names = RandomTestUtil.generateRandomWords(WORD_SIZE, NUMBER_OF_WORDS);
    for (String name : names) {
      dummyEntities.add(new DummyEntity(name));
    }

    entityIndex = AutoCompleteSearchIndex.newInstance(dummyEntities,
        new ContentProvider<DummyEntity>() {
          @Override
          public String get(DummyEntity item) {
            return item.getName();
          }
        }, MIN_WORD_SIZE);
  }

  @Test
  public void filterStrings() {
    // select the word in the middle
    String selectedWord = words.get(NUMBER_OF_WORDS / 2);
    // select random substring from selected word
    String phrase = RandomTestUtil.getRandomSubstringFromStart(selectedWord);
    // then search phrase in index
    List<String> filteredWords = wordIndex.filter(phrase);
    assertNotNull(filteredWords);
    assertTrue(filteredWords.size() >= 1);
    assertTrue(filteredWords.contains(selectedWord));
  }

  @Test
  public void filterEntities() {
    DummyEntity selectedDummyEntity = dummyEntities.get(NUMBER_OF_WORDS / 2);
    String phrase = RandomTestUtil.getRandomSubstringFromStart(selectedDummyEntity.getName());
    List<DummyEntity> filteredEntities = entityIndex.filter(phrase);
    assertNotNull(filteredEntities);
    assertEquals(1, filteredEntities.size());
    assertTrue(filteredEntities.contains(selectedDummyEntity));
  }

  @Test
  public void filterWithEmpty() {
    List<String> filteredWords = wordIndex.filter("");
    assertNotNull(filteredWords);
    assertEquals(0, filteredWords.size());
  }

  private static class DummyEntity {

    private String name;

    public DummyEntity(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

}
