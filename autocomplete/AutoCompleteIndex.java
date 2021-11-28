package net.acargil.autocomplete;

import java.util.List;

public interface AutoCompleteIndex<T> {

  /**
   * Filters and returns entities that contains given phrase. If phrase null or size of phrase less
   * than minimum word length; returns empty list.
   *
   * @param phrase any non empty word which contains characters of target alphabet
   * @return list of entities that contains given phrase
   */
  List<T> filter(String phrase);

  /**
   * Add given items to index with using provider function
   *
   * @param entities list that will provide content to be indexed
   * @return number of indexed items
   */
  int index(List<T> entities, Extractor<T> extractor);


}
