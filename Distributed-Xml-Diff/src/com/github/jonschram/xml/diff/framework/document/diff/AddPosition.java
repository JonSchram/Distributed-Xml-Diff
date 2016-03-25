package com.github.jonschram.xml.diff.framework.document.diff;

public enum AddPosition {
  /**
   * Element should be added as first child of selected element.
   */
  PREPEND,
  /**
   * Element should be added as last child of selected element.
   */
  APPEND,
  /**
   * Element should be inserted as sibling before selected element.
   */
  BEFORE,
  /**
   * Element should be inserted as sibling after selected element.
   */
  AFTER;
}