package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;

public class OrderedProcessingInstruction extends OrderedNode
    implements ProcessingInstruction {

  private String data;
  private String target;

  public OrderedProcessingInstruction() {
    this(null);
  }

  public OrderedProcessingInstruction(Document ownerDocument) {
    this(null, null, ownerDocument);
  }

  public OrderedProcessingInstruction(String target, String data,
      Document ownerDocument) {
    super(ownerDocument);
    this.data = data;
    this.target = target;
  }

  @Override
  public Node cloneNode(boolean deep) {
    return new OrderedProcessingInstruction(target, data, getOwnerDocument());
  }

  @Override
  public NamedNodeMap getAttributes() {
    return null;
  }

  @Override
  public String getData() {
    return data;
  }

  @Override
  public String getLocalName() {
    return null;
  }

  @Override
  public String getNamespaceURI() {
    return null;
  }

  @Override
  public String getNodeName() {
    return getTarget();
  }

  @Override
  public short getNodeType() {
    return PROCESSING_INSTRUCTION_NODE;
  }

  @Override
  public String getNodeValue() throws DOMException {
    return getData();
  }

  @Override
  public String getPrefix() {
    return null;
  }

  @Override
  public String getTarget() {
    return target;
  }

  @Override
  public boolean hasAttributes() {
    return false;
  }

  @Override
  public boolean isSupported(String feature, String version) {
    return false;
  }

  @Override
  public void setData(String data) throws DOMException {
    this.data = data;
  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException {
    setData(nodeValue);
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {
  }

}
