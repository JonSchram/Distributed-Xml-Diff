package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.HashMap;

public class AttributesMap implements NamedNodeMap {

  private class Namespace {
    private String namespaceUri;
    private String localName;

    public Namespace(String namespaceUri, String localName) {
      this.namespaceUri = namespaceUri;
      this.localName = localName;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      Namespace other = (Namespace) obj;
      if (!getOuterType().equals(other.getOuterType())) {
        return false;
      }
      if (localName == null) {
        if (other.localName != null) {
          return false;
        }
      } else if (!localName.equals(other.localName)) {
        return false;
      }
      if (namespaceUri == null) {
        if (other.namespaceUri != null) {
          return false;
        }
      } else if (!namespaceUri.equals(other.namespaceUri)) {
        return false;
      }
      return true;
    }

    private AttributesMap getOuterType() {
      return AttributesMap.this;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result
          + ((localName == null) ? 0 : localName.hashCode());
      result = prime * result
          + ((namespaceUri == null) ? 0 : namespaceUri.hashCode());
      return result;
    }

  }

  /**
   * Attributes contained in this object.
   */
  HashMap<Namespace, Node> attributes;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AttributesMap other = (AttributesMap) obj;
    if (attributes == null) {
      if (other.attributes != null) {
        return false;
      }
    } else if (!attributes.equals(other.attributes)) {
      return false;
    }
    return true;
  }

  @Override
  public int getLength() {
    return attributes.size();
  }

  @Override
  public Node getNamedItem(String name) {
    return attributes.get(new Namespace(null, name));
  }

  @Override
  public Node getNamedItemNS(String namespaceUri, String localName)
      throws DOMException {
    return attributes.get(new Namespace(namespaceUri, localName));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((attributes == null) ? 0 : attributes.hashCode());
    return result;
  }

  @Override
  public Node item(int index) {
    if (index > attributes.size()) {
      // TODO test if this works
      Node[] attributeValues = new Node[attributes.size()];
      attributes.values().toArray(attributeValues);
      return attributeValues[index];
    }
    return null;
  }

  @Override
  public Node removeNamedItem(String name) throws DOMException {
    Namespace key = new Namespace(null, name);
    if (attributes.containsKey(key)) {
      return attributes.remove(key);
    }
    throw new DOMException(DOMException.NOT_FOUND_ERR,
        "Attribute with name " + name + " not found");
  }

  @Override
  public Node removeNamedItemNS(String namespaceUri, String name)
      throws DOMException {
    Namespace key = new Namespace(namespaceUri, name);
    if (attributes.containsKey(key)) {
      return attributes.remove(key);
    }
    throw new DOMException(DOMException.NOT_FOUND_ERR,
        "Attribute with name " + name + " not found");
  }

  @Override
  public Node setNamedItem(Node value) throws DOMException {
    if (value != null) {
      Namespace key = new Namespace(null, value.getLocalName());
      return attributes.put(key, value);
    } else {
      return null;
    }
  }

  @Override
  public Node setNamedItemNS(Node value) throws DOMException {
    if (value != null) {
      Namespace key = new Namespace(value.getNamespaceURI(),
          value.getLocalName());
      return attributes.put(key, value);
    } else {
      return null;
    }
  }

}
