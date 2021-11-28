package net.acargil.search;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BreadCrumbSearchIndexTest {

  private static final int NUMBER_OF_CRUMBS = 1000;
  private static final int MAX_CHILDREN = 20;
  private static final int MAX_LEVEL = 3;
  private static final int WORD_SIZE = 50;

  private BreadCrumbSearchIndex index;
  private List<Crumb> crumbs;

  /**
   * Setup test.
   */
  @Before
  public void setup() {
    BreadCrumbGenerator breadcrumbGenerator = new BreadCrumbGenerator(NUMBER_OF_CRUMBS, MAX_LEVEL,
        MAX_CHILDREN, WORD_SIZE);
    crumbs = breadcrumbGenerator.generateCrumbs();
    index = BreadCrumbSearchIndex.getInstance(crumbs);
  }

  @Test
  public void testGetLeaves() {
    List<Crumb> leaves = index.getLeaves();
    assertNotNull(leaves);
    assertFalse(leaves.isEmpty());
  }

  @Test
  public void testGetRoots() {
    List<Crumb> roots = index.getRoots();
    assertNotNull(roots);
    assertFalse(roots.isEmpty());
  }

  @Test
  public void testSearch() {
    Crumb randomCrumb = RandomTestUtil.selectRandomElement(crumbs);
    String word = randomCrumb.toString();
    String phrase = RandomTestUtil.getRandomSubstringFromStart(word);
    List<Crumb> crumbs = index.search(phrase);
    assertNotNull(crumbs);
    assertFalse(crumbs.isEmpty());
    assertTrue(crumbs.contains(randomCrumb));
  }

  @Test
  public void testSearchEmpty() {
    List<Crumb> crumbs = index.search("");
    assertNotNull(crumbs);
    assertTrue(crumbs.isEmpty());
  }

  @Test
  public void testSearchNull() {
    List<Crumb> crumbs = index.search(null);
    assertNotNull(crumbs);
    assertTrue(crumbs.isEmpty());
  }
}
