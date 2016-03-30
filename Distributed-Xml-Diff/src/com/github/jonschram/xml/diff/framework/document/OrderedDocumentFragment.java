package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class OrderedDocumentFragment extends OrderedNode
    implements DocumentFragment {

  public OrderedDocumentFragment() {
  }

  public OrderedDocumentFragment(Document ownerDocument) {
    setOwnerDocument(ownerDocument);
  }

  @Override
  public Node cloneNode(boolean deep) {
    OrderedDocumentFragment cloneFragment = new OrderedDocumentFragment();
    if (deep) {
      NodeList children = getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        cloneFragment.appendChild(children.item(i).cloneNode(deep));
      }
    }
    return cloneFragment;
  }

  @Override
  public NamedNodeMap getAttributes() {
    // no attributes
    return null;
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
    return "#document-fragment";
  }

  @Override
  public short getNodeType() {
    return Node.DOCUMENT_FRAGMENT_NODE;
  }

  @Override
  public String getNodeValue() throws DOMException {
    return null;
  }

  @Override
  public String getPrefix() {
    return null;
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
  public void setNodeValue(String nodeValue) throws DOMException {
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {
  }

}
