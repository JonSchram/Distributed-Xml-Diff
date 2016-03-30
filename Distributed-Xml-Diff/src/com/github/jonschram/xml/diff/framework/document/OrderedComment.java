package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class OrderedComment extends OrderedCharacterData implements Comment {

  public OrderedComment() {
    this(null, null);
  }

  public OrderedComment(Document ownerDocument) {
    this(null, ownerDocument);
  }

  public OrderedComment(String data) {
    this(data, null);
  }

  public OrderedComment(String data, Document ownerDocument) {
    super(data, ownerDocument);
  }

  @Override
  public Node cloneNode(boolean deep) {
    return new OrderedComment(getData());
  }

  @Override
  public String getNodeName() {
    return "#comment";
  }

  @Override
  public short getNodeType() {
    return Node.COMMENT_NODE;
  }

}
