package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class OrderedText extends OrderedCharacterData implements Text {

  public OrderedText(Document ownerDocument) {
    this(null, ownerDocument);
  }

  public OrderedText(String data) {
    this(data, null);
  }

  public OrderedText(String data, Document ownerDocument) {
    super(data, ownerDocument);
  }

  @Override
  public Node cloneNode(boolean deep) {
    return new OrderedText(getData());
  }

  @Override
  public String getNodeName() {
    return "#text";
  }

  @Override
  public short getNodeType() {
    return Node.TEXT_NODE;
  }

  @Override
  public String getWholeText() {
    String wholeText = getData();
    Node previous = getPreviousSibling();
    while (previous != null && (previous.getNodeType() == Node.TEXT_NODE
        || previous.getNodeType() == Node.CDATA_SECTION_NODE)) {
      wholeText = previous.getNodeValue() + wholeText;
    }
    Node next = getNextSibling();
    while (next != null && (next.getNodeType() == Node.TEXT_NODE
        || next.getNodeType() == Node.CDATA_SECTION_NODE)) {
      wholeText = wholeText + previous.getNodeValue();
    }
    return wholeText;
  }

  @Override
  public boolean isElementContentWhitespace() {
    // TODO element content whitespace
    return false;
  }

  @Override
  public Text replaceWholeText(String content) throws DOMException {
    this.setData(content);
    Node previous = getPreviousSibling();
    while (previous != null && (previous.getNodeType() == Node.TEXT_NODE
        || previous.getNodeType() == Node.CDATA_SECTION_NODE)) {
      getParentNode().removeChild(previous);
      previous = this.getPreviousSibling();
    }
    Node next = getNextSibling();
    while (next != null && (next.getNodeType() == Node.TEXT_NODE
        || next.getNodeType() == Node.CDATA_SECTION_NODE)) {
      getParentNode().removeChild(next);
      next = this.getNextSibling();
    }
    return null;
  }

  @Override
  public Text splitText(int offset) throws DOMException {
    String data = getData();
    String splitData = data.substring(offset);
    OrderedText newNode = new OrderedText(splitData);
    if (getParentNode() != null) {
      getParentNode().insertBefore(newNode, getNextSibling());
    }
    return newNode;
  }

}
