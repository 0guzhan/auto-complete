package net.acargil.search;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang3.RandomUtils;

/**
 * Bread Crumb data generator utility class.
 *
 * @author oguzhan acargil
 */
public class BreadCrumbGenerator {

  // numberOfCrumb, maxLevel, maxChildren and wordSize is class field
  // since it is used by recursive functions to keep bread crumb tree in limits
  private int numberOfCrumbs;
  private int maxLevel;
  private int maxChildren;
  private int wordSize;
  private List<Crumb> crumbs = new LinkedList<>();

  /**
   * Constructs a <code>BreadcrumbGenerator</code> object.
   *
   * @param numberOfCrumbs total number of crumb objects
   * @param maxLevel       maximum depth of bread crumb tree
   * @param maxChildren    maximum number of child, a crumb can have
   * @param wordSize       word size of crumb
   */
  public BreadCrumbGenerator(int numberOfCrumbs, int maxLevel, int maxChildren, int wordSize) {
    super();
    this.numberOfCrumbs = numberOfCrumbs;
    this.maxLevel = maxLevel;
    this.maxChildren = maxChildren;
    this.wordSize = wordSize;
    this.crumbs = new LinkedList<>();
  }

  /**
   * Generate list of crumbs.
   *
   * @return list of crumbs
   */
  public List<Crumb> generateCrumbs() {
    while (numberOfCrumbs > 0) {
      generateCrumb(maxLevel);
    }
    return crumbs;
  }

  private Crumb generateCrumb(int level) {
    if (numberOfCrumbs == 0) {
      return null;
    } else {
      numberOfCrumbs--;
      int index = numberOfCrumbs;
      if (level == 0) {
        Crumb crumb = generateCrumbLeaf();
        crumb.setIndex(index);

        crumbs.add(crumb);
        return crumb;
      } else {
        Crumb crumb = generateCrumbLeaf();
        crumb.setIndex(index);

        level--;
        int numberOfChildren = RandomUtils.nextInt(1, maxChildren);
        for (int i = 0; i < numberOfChildren && numberOfCrumbs >= 0; i++) {
          Crumb childCrumb = generateCrumb(level);
          if (childCrumb == null) {
            break;
          }
          childCrumb.setParentIndex(index);
          int childIndex = childCrumb.getIndex();
          crumb.addChildIndex(childIndex);
        }

        crumbs.add(crumb);
        return crumb;
      }
    }
  }

  private Crumb generateCrumbLeaf() {
    String randomWord = RandomTestUtil.generateRandomWord(wordSize);
    DummyEntity entity = new DummyEntity(randomWord);
    return new Crumb(entity);
  }

  private static final class DummyEntity {

    private String name;

    public DummyEntity(String name) {
      super();
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }

}
