package com.github.jonschram.xml.diff.framework.document.distributed;

import com.github.jonschram.xml.diff.framework.document.OrderedElement;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomAccessElement extends OrderedElement implements Element {

  private static final String UNSUPPORTED_SUFFIX = " not supported for "
      + RandomAccessElement.class.toString();

  public RandomAccessElement() {
    this("", null);
  }

  public RandomAccessElement(String nodeName, Document ownerDocument) {
    this(nodeName, ownerDocument, null);
  }

  public RandomAccessElement(String nodeName, Document ownerDocument,
      Node parentNode) {
    super(nodeName, ownerDocument, parentNode);
  }

  @Override
  public Node cloneNode(boolean deep) {
    RandomAccessElement cloneElement = new RandomAccessElement(
        this.getNodeName(), null);
    cloneElement.setTextContent(getTextContent());
    if (deep) {
      NodeList children = getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        cloneElement.appendChild(children.item(i).cloneNode(deep));
      }
    }
    return cloneElement;
  }

  public List<Node> getChildrenByName(String elementName) {
    List<Node> results = new ArrayList<>();
    NodeList children = getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.getNodeName().equals(elementName)) {
        results.add(child);
      }
    }
    return results;
  }

  public List<Node> getChildElementsByName(String elementName) {
    List<Node> results = new ArrayList<>();
    NodeList children = getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.getNodeType() == ELEMENT_NODE
          && child.getNodeName().equals(elementName)) {
        results.add(child);
      }
    }
    return results;
  }

  /**
   * Returns a set of all node names of immediate children.
   * 
   * @return All node names of children.
   */
  public Set<String> getElementNames() {
    Set<String> results = new HashSet<>();
    NodeList children = getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.getNodeType() == ELEMENT_NODE) {
        results.add(child.getNodeName());
      }
    }
    return results;
  }

}
