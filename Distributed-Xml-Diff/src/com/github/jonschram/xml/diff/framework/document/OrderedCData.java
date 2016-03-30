package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;

public class OrderedCData extends OrderedText implements CDATASection {

  public OrderedCData() {
    super("");
  }

  public OrderedCData(String data) {
    super(data);
  }

  public OrderedCData(Document ownerDocument) {
    super("");
    this.setOwnerDocument(ownerDocument);
  }

}
