package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.TypeInfo;

/**
 * Implementation of {@link Attr} for ordered document. Doesn't support
 * namespaces.
 * <p>
 * Note in https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Attr.html that
 * attributes are not considered part of the document tree, so parent node,
 * next/previous sibling should be null.
 * </p>
 * 
 * @author Jonathan Schram
 *
 */
public class OrderedAttribute extends OrderedNode implements Attr {

  private String prefix;
  private String namespaceUri;
  private boolean idAttribute;
  private boolean specified;
  private String value;
  private Element ownerElement;
  /**
   * Unqualified name of this node.
   */
  private String localName;

  /**
   * Constructs a new OrderedAttribute with given name, value, and owning
   * element.
   * 
   * @param name
   *          Attribute name
   * @param value
   *          Attribute value
   * @param ownerElement
   *          Element that owns this attribute.
   */
  public OrderedAttribute(String localName, String value,
      Element ownerElement) {

  }

  public OrderedAttribute(String prefix, String namespaceUri, String localName,
      String value, Element ownerElement) {
    super(null, null);
    this.prefix = prefix;
    this.namespaceUri = namespaceUri;
    this.localName = localName;
    this.value = value;
    this.ownerElement = ownerElement;
    idAttribute = false;
    specified = false;
  }

  @Override
  public Node cloneNode(boolean paramBoolean) {
    return new OrderedAttribute(getNodeName(), value, ownerElement);
  }

  @Override
  public NamedNodeMap getAttributes() {
    // defined as null
    return null;
  }

  @Override
  public String getLocalName() {
    return localName;
  }

  @Override
  public String getName() {
    if (getPrefix() != null) {
      return getPrefix() + ":" + getLocalName();
    }
    return getLocalName();
  }

  @Override
  public String getNamespaceURI() {
    return namespaceUri;
  }

  @Override
  public String getNodeName() {
    return getName();
  }

  @Override
  public short getNodeType() {
    return Node.ATTRIBUTE_NODE;
  }

  @Override
  public String getNodeValue() throws DOMException {
    return value;
  }

  @Override
  public Element getOwnerElement() {
    return ownerElement;
  }

  @Override
  public String getPrefix() {
    return prefix;
  }

  @Override
  public TypeInfo getSchemaTypeInfo() {
    // unimplemented
    return null;
  }

  @Override
  public boolean getSpecified() {
    return specified;
  }

  @Override
  public String getValue() {
    return value;
  }

  @Override
  public boolean hasAttributes() {
    return false;
  }

  @Override
  public boolean isEqualNode(Node otherNode) {
    if (!(otherNode instanceof OrderedAttribute)) {
      return false;
    }
    OrderedAttribute otherAttribute = (OrderedAttribute) otherNode;

    return this.getName().equals(otherAttribute.getName())
        && this.getValue().equals(otherAttribute.getValue());
  }

  @Override
  public boolean isId() {
    return idAttribute;
  }

  @Override
  public boolean isSupported(String paramString1, String paramString2) {
    return false;
  }

  public void setId(boolean isId) {
    this.idAttribute = isId;
  }

  public void setLocalName(String localName) {
    this.localName = localName;
  }

  public void setNamespaceUri(String namespaceUri) {
    this.namespaceUri = namespaceUri;
  }

  @Override
  public void setNodeValue(String newValue) throws DOMException {
    value = newValue;
  }

  public void setOwnerElement(Element ownerElement) {
    this.ownerElement = ownerElement;
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {
    this.prefix = prefix;
  }

  public void setSpecified(boolean specified) {
    this.specified = specified;
  }

  @Override
  public void setValue(String newValue) throws DOMException {
    value = newValue;
  }

}
