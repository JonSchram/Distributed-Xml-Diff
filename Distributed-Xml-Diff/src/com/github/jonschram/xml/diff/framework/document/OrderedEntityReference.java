package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class OrderedEntityReference extends OrderedNode
    implements EntityReference {

  private String referencedEntity;

  public OrderedEntityReference() {
    this("");
  }

  public OrderedEntityReference(String entityName) {
    this(entityName, null);
  }

  public OrderedEntityReference(String entityName, Document owningDocument) {
    super(owningDocument, null);
    referencedEntity = entityName;
  }

  @Override
  public Node cloneNode(boolean deep) {
    return new OrderedEntityReference(referencedEntity);
  }

  @Override
  public NamedNodeMap getAttributes() {
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
    return referencedEntity;
  }

  @Override
  public short getNodeType() {
    return Node.ENTITY_REFERENCE_NODE;
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

  public void setReferencedEntity(String referencedEntity) {
    this.referencedEntity = referencedEntity;
  }

}
