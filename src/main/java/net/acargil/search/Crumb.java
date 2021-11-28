package net.acargil.search;

import java.util.LinkedList;
import java.util.List;

/**
 * Crumb is holder class to reference target entity
 *
 * @author oguzhan acargil
 * @since 0.1
 */
public final class Crumb implements Comparable<Crumb> {

  private static final int UNSET_INDEX = -1;

  private int parentIndex;
  private int index;
  private List<Integer> childrenIndexes;
  private Object entity;

  /**
   * Constructs a part of bread crumb.
   *
   * @param entity will be referenced by this crumb
   */
  public Crumb(Object entity) {
    this.index = UNSET_INDEX;
    this.parentIndex = UNSET_INDEX;
    this.childrenIndexes = new LinkedList<Integer>();
    this.entity = entity;
  }

  public boolean hasParent() {
    return parentIndex != UNSET_INDEX;
  }

  public boolean hasChildren() {
    return !childrenIndexes.isEmpty();
  }

  public int getParentIndex() {
    return parentIndex;
  }

  public void setParentIndex(int parentIndex) {
    this.parentIndex = parentIndex;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public List<Integer> getChildrenIndexes() {
    return childrenIndexes;
  }

  public void addChildIndex(Integer childIndex) {
    this.childrenIndexes.add(childIndex);
  }

  public Object getEntity() {
    return entity;
  }

  /**
   * Return the entity object referenced by this crumb if it is instance of parameter class.
   *
   * @param clazz Target class to cast entity object
   * @return T Entity object
   */
  @SuppressWarnings("unchecked")
  public <T> T getEntity(Class<T> clazz) {
    if (clazz.isInstance(entity)) {
      return (T) getEntity();
    } else {
      return null;
    }
  }

  @Override
  public int compareTo(Crumb other) {
    if (other != null) {
      return other.index == this.index ? 0 : -1;
    } else {
      return -1;
    }
  }
}
