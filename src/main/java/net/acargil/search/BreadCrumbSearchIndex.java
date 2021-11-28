package net.acargil.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class BreadCrumbSearchIndex {

  private static final int MIN_WORD_LENGTH = 3;

  private Crumb[] crumbs;
  private List<Integer> rootIndexes;
  private List<Integer> leafIndexes;
  private Map<String, Set<Integer>> searchIndex;

  private BreadCrumbSearchIndex(Crumb[] crumbs, List<Integer> rootIndexes,
      List<Integer> leafIndexes,
      Map<String, Set<Integer>> searchIndex) {
    this.crumbs = crumbs;
    this.rootIndexes = rootIndexes;
    this.leafIndexes = leafIndexes;
    this.searchIndex = searchIndex;
  }

  /**
   * Create bread crumb index for given list of crumbs.
   *
   * @param crumbs list of crumbs
   * @return <code>BreadcrumbIndex</code> object
   */
  public static final BreadCrumbSearchIndex getInstance(List<Crumb> crumbs) {
    if (crumbs.isEmpty()) {
      throw new RuntimeException("Crumbs list cannot be empty");
    } else {
      List<Integer> rootIndexes = new LinkedList<>();
      List<Integer> leafIndexes = new LinkedList<>();
      int totalSize = crumbs.size();

      // fill crumbs array
      Crumb[] crumbsArray = new Crumb[totalSize];
      for (Crumb crumb : crumbs) {
        int index = crumb.getIndex();
        crumbsArray[index] = crumb;
      }

      // connect with parent, check is root?
      for (Crumb crumb : crumbs) {
        int index = crumb.getIndex();
        if (crumb.hasParent()) {
          int parentIndex = crumb.getParentIndex();
          if (parentIndex < 0 || totalSize <= parentIndex) {
            throw new RuntimeException(
                "ParentIndex is invalid. parentIndex:" + parentIndex + " totalSize:" + totalSize);
          } else {
            Crumb parent = crumbsArray[parentIndex];
            parent.addChildIndex(index);
          }
        } else {
          rootIndexes.add(index);
        }
      }

      // find leaves
      for (Crumb crumb : crumbsArray) {
        if (!crumb.hasChildren()) {
          int leafIndex = crumb.getIndex();
          leafIndexes.add(leafIndex);
        }
      }

      // create search index
      Map<String, Set<Integer>> searchIndex = new TreeMap<>();
      WordPermutation wordPermutation = new WordPermutation(MIN_WORD_LENGTH);
      for (int i = 0; i < crumbsArray.length; i++) {
        Crumb crumb = crumbsArray[i];
        String content = crumb.toString();
        Set<String> variations = wordPermutation.getVariationsFromStart(content);
        for (String variation : variations) {
          if (searchIndex.containsKey(variation)) {
            Set<Integer> indexes = searchIndex.get(variation);
            indexes.add(i);
          } else {
            Set<Integer> indexes = new HashSet<Integer>();
            indexes.add(i);
            searchIndex.put(variation, indexes);
          }
        }
      }

      return new BreadCrumbSearchIndex(crumbsArray, rootIndexes, leafIndexes, searchIndex);
    }
  }

  /**
   * Returns list of crumb, matches with given phrase.
   *
   * @param phrase any non empty phrase taht contains characters of target alphabet.
   * @return list of crumbs matches with given parameter
   */
  public List<Crumb> search(String phrase) {
    List<Crumb> filteredCrumbs = new LinkedList<>();
    if (phrase != null && phrase.length() >= MIN_WORD_LENGTH && searchIndex.containsKey(phrase)) {
      Set<Integer> indexes = searchIndex.get(phrase);
      for (Integer index : indexes) {
        Crumb crumb = crumbs[index];
        filteredCrumbs.add(crumb);
      }
    }

    return filteredCrumbs;
  }

  /**
   * Filters list of crumbs that has no parent crumb.
   *
   * @return list of root crumbs
   */
  public List<Crumb> getRoots() {
    List<Crumb> rootCrumbs = new LinkedList<>();
    for (Integer rootIndex : rootIndexes) {
      Crumb root = crumbs[rootIndex];
      rootCrumbs.add(root);
    }

    return rootCrumbs;
  }

  /**
   * Filters list of crumbs that has no child crumb.
   *
   * @return list of leaf crumbs
   */
  public List<Crumb> getLeaves() {
    List<Crumb> leafCrumbs = new LinkedList<>();
    for (Integer leafIndex : leafIndexes) {
      Crumb leaf = crumbs[leafIndex];
      leafCrumbs.add(leaf);
    }

    return leafCrumbs;
  }

  public List<Crumb> getChildren(int index) {
    checkIndex(index);
    return getChildren(crumbs[index]);
  }

  /**
   * Return child crumbs of given crumb.
   *
   * @param crumb crumb in altread in <code>BreadcrumbIndex</code>
   * @return list of crumbs
   */
  public List<Crumb> getChildren(Crumb crumb) {
    List<Crumb> childCrumbs = new LinkedList<>();
    List<Integer> childrenIndexes = crumb.getChildrenIndexes();
    for (Integer childIndex : childrenIndexes) {
      Crumb childCrumb = crumbs[childIndex];
      childCrumbs.add(childCrumb);
    }

    return childCrumbs;
  }

  public List<Crumb> getPath(int index) {
    checkIndex(index);
    return getPath(crumbs[index]);
  }

  private List<Crumb> getPath(Crumb crumb) {
    List<Crumb> path = null;
    if (crumb.hasParent()) {
      Crumb parent = getParent(crumb);
      path = getPath(parent);
    } else {
      path = new ArrayList<>();
    }

    path.add(crumb);
    return path;
  }

  private Crumb getParent(Crumb crumb) {
    int parentIndex = crumb.getParentIndex();
    Crumb parent = crumbs[parentIndex];
    return parent;
  }

  private void checkIndex(int index) {
    if (index < 0 || crumbs.length <= index) {
      throw new RuntimeException("Index is invalid. index:" + index + " size:" + crumbs.length);
    }
  }

}
