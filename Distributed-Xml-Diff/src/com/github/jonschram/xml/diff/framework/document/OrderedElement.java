package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

/**
 * Implementation of W3C Node.
 * 
 * @author Jonathan Schram
 *
 */
public class OrderedElement extends OrderedNode implements Element {

  private static String UNSUPPORTED_SUFFIX = " not supported for "
      + OrderedElement.class.toString();

  private String localName;
  private String prefix;
  private String namespaceUri;

  /**
   * Attributes this node owns.
   */
  private AttributesMap attributes;

  public OrderedElement() {
    this("", null);
  }

  public OrderedElement(String nodeName) {
    this(nodeName, null);
  }

  public OrderedElement(String nodeName, Document ownerDocument) {
    this(nodeName, ownerDocument, null);
  }

  public OrderedElement(String nodeName, Document ownerDocument,
      Node parentNode) {
    super(ownerDocument, parentNode);
    attributes = new AttributesMap();
    localName = nodeName;
  }

  @Override
  public Node cloneNode(boolean deep) {
    OrderedElement cloneElement = new OrderedElement(this.getNodeName(), null);
    cloneElement.setNamespaceUri(getNamespaceURI());
    cloneElement.setPrefix(getPrefix());
    cloneElement.setTextContent(getTextContent());
    for (int i = 0; i < attributes.getLength(); i++) {
      Node attribute = attributes.item(i);
      cloneElement.setAttribute(attribute.getNodeName(),
          attribute.getNodeValue());
    }
    if (deep) {
      NodeList children = getChildNodes();
      for (int i = 0; i < children.getLength(); i++) {
        cloneElement.appendChild(children.item(i).cloneNode(deep));
      }
    }
    return cloneElement;
  }

  @Override
  public String getAttribute(String attributeName) {
    Node attribute = attributes.getNamedItem(attributeName);
    if (attribute != null) {
      return attribute.getNodeValue();
    } else {
      return null;
    }
  }

  @Override
  public Attr getAttributeNode(String paramString) {
    Node attributeNode = attributes.getNamedItem(paramString);
    if (attributeNode instanceof Attr) {
      return (Attr) attributeNode;
    }
    return null;
  }

  @Override
  public Attr getAttributeNodeNS(String namespaceUri, String localName)
      throws DOMException {
    return (Attr) attributes.getNamedItemNS(namespaceUri, localName);
  }

  @Override
  public String getAttributeNS(String namespaceUri, String localName)
      throws DOMException {
    Node node = attributes.getNamedItemNS(namespaceUri, localName);
    if (node != null) {
      return ((Attr) node).getNodeValue();
    } else {
      return "";
    }
  }

  @Override
  public NamedNodeMap getAttributes() {
    return attributes;
  }

  @Override
  public NodeList getElementsByTagName(String name) {
    OrderedNodeList results = new OrderedNodeList();
    NodeList children = getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.getNodeType() == Node.ELEMENT_NODE) {
        if ("*".equals(name) || name.equals(((Element) child).getTagName())) {
          results.addNode(child);
          if (child instanceof Element) {
            NodeList childMatches = ((Element) child)
                .getElementsByTagName(name);
            for (int j = 0; j < childMatches.getLength(); j++) {
              results.addNode(childMatches.item(j));
            }
          }
        }
      }
    }
    return results;
  }

  @Override
  public NodeList getElementsByTagNameNS(String namespaceUri, String localName)
      throws DOMException {
    OrderedNodeList results = new OrderedNodeList();
    NodeList children = getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child.getNodeType() == Node.ELEMENT_NODE) {
        Element childElement = (Element) child;
        if (("*".equals(localName)
            || localName.equals(childElement.getLocalName()))
            && ("*".equals(namespaceUri)
                || namespaceUri.equals(childElement.getNamespaceURI()))) {
          results.addNode(child);
          NodeList childMatches = childElement
              .getElementsByTagNameNS(namespaceUri, localName);
          for (int j = 0; j < childMatches.getLength(); j++) {
            results.addNode(childMatches.item(j));
          }
        }
      }
    }
    return results;
  }

  @Override
  public String getLocalName() {
    return localName;
  }

  @Override
  public String getNamespaceURI() {
    return namespaceUri;
  }

  @Override
  public String getNodeName() {
    return getTagName();
  }

  @Override
  public short getNodeType() {
    return Node.ELEMENT_NODE;
  }

  @Override
  public String getNodeValue() throws DOMException {
    // defined as null in
    // https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Node.html
    return null;
  }

  @Override
  public String getPrefix() {
    return prefix;
  }

  @Override
  public TypeInfo getSchemaTypeInfo() {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getSchemaTypeInfo" + UNSUPPORTED_SUFFIX);
  }

  @Override
  public String getTagName() {
    if (getPrefix() != null) {
      return getPrefix() + ":" + getLocalName();
    }
    return getNodeName();
  }

  @Override
  public boolean hasAttribute(String name) {
    return attributes.getNamedItem(name) != null;
  }

  @Override
  public boolean hasAttributeNS(String namespaceUri, String localName)
      throws DOMException {
    return attributes.getNamedItemNS(namespaceUri, localName) != null;
  }

  @Override
  public boolean hasAttributes() {
    return attributes.getLength() > 0;
  }

  @Override
  public boolean isSupported(String arg0, String arg1) {
    // support nothing special
    return false;
  }

  @Override
  public void removeAttribute(String name) throws DOMException {
    attributes.removeNamedItem(name);
  }

  @Override
  public Attr removeAttributeNode(Attr paramAttr) throws DOMException {
    return (Attr) attributes.removeNamedItem(paramAttr.getName());
  }

  @Override
  public void removeAttributeNS(String namespaceUri, String localName)
      throws DOMException {
    attributes.removeNamedItemNS(namespaceUri, localName);
  }

  @Override
  public void setAttribute(String name, String value) throws DOMException {
    attributes.setNamedItem(new OrderedAttribute(name, value, this));
  }

  @Override
  public Attr setAttributeNode(Attr value) throws DOMException {
    return (Attr) attributes.setNamedItem(value);
  }

  @Override
  public Attr setAttributeNodeNS(Attr value) throws DOMException {
    return (Attr) attributes.setNamedItemNS(value);
  }

  @Override
  public void setAttributeNS(String namespaceUri, String qualifiedName,
      String value) throws DOMException {
    int prefixEnd = qualifiedName.indexOf(':');
    String prefix = null;
    String localName = null;
    if (prefixEnd > -1) {
      prefix = qualifiedName.substring(0, prefixEnd);
      localName = qualifiedName.substring(prefixEnd + 1);
    }
    attributes.setNamedItemNS(
        new OrderedAttribute(prefix, namespaceUri, localName, value, this));
  }

  @Override
  public void setIdAttribute(String name, boolean isId) throws DOMException {
    Node node = attributes.getNamedItem(name);
    if (node != null && node instanceof OrderedAttribute) {
      OrderedAttribute attribute = (OrderedAttribute) node;
      attribute.setId(isId);
    }

  }

  @Override
  public void setIdAttributeNode(Attr attribute, boolean isId)
      throws DOMException {
    setIdAttribute(attribute.getName(), isId);
  }

  @Override
  public void setIdAttributeNS(String namespaceUri, String localName,
      boolean isId) throws DOMException {
    Node node = attributes.getNamedItemNS(namespaceUri, localName);
    if (node != null) {
      ((OrderedAttribute) node).setId(isId);
    }
  }

  public void setLocalName(String name) {
    this.localName = name;
  }

  public void setNamespaceUri(String namespaceUri) {
    this.namespaceUri = namespaceUri;
  }

  @Override
  public void setNodeValue(String paramString) throws DOMException {
    // no node value
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {
    this.prefix = prefix;
  }

}
