package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.CDATASection;

public class OrderedCData extends OrderedText implements CDATASection {

  public OrderedCData(String data) {
    super(data);
  }

}
