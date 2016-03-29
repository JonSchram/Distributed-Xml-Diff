package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class OrderedNode implements Node {

  /**
   * Child nodes of this node.
   */
  private OrderedNodeList children;

  /**
   * Previous sibling node.
   */
  private Node previousSibling;

  /**
   * Next sibling node.
   */
  private Node nextSibling;

  /**
   * Parent node of this child.
   */
  private Node parentNode;

  /**
   * Document that contains this node.
   */
  private Document ownerDocument;

  /**
   * URI of this node.
   */
  private String baseUri;

  private String textContent;

  private Map<String, Object> userData;

  /**
   * Whether this element is of the default namespace.
   */
  private String defaultNamespaceName;

  public OrderedNode() {
    this(null, null);
  }

  /**
   * Creates a new ordered node.
   * 
   * @param ownerDocument
   *          Document owning this node
   */
  public OrderedNode(Document ownerDocument) {
    this(null, ownerDocument);
  }

  /**
   * Constructs a new ordered node with given parameters and everything else
   * default.
   * 
   * @param nodeName
   *          Name of node
   * @param ownerDocument
   *          Owner document
   * @param parentNode
   *          Parent of this node
   */
  public OrderedNode(Document ownerDocument, Node parentNode) {
    this.ownerDocument = ownerDocument;
    this.parentNode = parentNode;

    children = new OrderedNodeList();
    userData = new HashMap<>();

    this.defaultNamespaceName = "";
    this.baseUri = "";
    this.previousSibling = null;
    this.nextSibling = null;
    this.textContent = null;
  }

  @Override
  public Node appendChild(Node newChild) throws DOMException {
    if (newChild.getOwnerDocument().equals(this.getOwnerDocument())) {
      OrderedNode lastChild = (OrderedNode) getLastChild();
      lastChild.setNextSibling(newChild);
      ((OrderedNode) newChild).setPreviousSibling(lastChild);
      children.addNode(newChild);

      return newChild;
    } else {
      throw new DOMException(DOMException.WRONG_DOCUMENT_ERR,
          "New child is from a different document");
    }
  }

  @Override
  public short compareDocumentPosition(Node otherNode) throws DOMException {

    if (this.getClass().equals(otherNode.getClass())) {
      if (isSameNode(otherNode)) {
        return 0;
      } else {
        OrderedNode otherOrderedNode = (OrderedNode) otherNode;
        if (parentNode != null) {
          if (parentNode instanceof OrderedNode) {
            OrderedNode parentElement = (OrderedNode) parentNode;
            short thisPosition = parentElement.nodePosition(this);
            short otherPosition = parentElement.nodePosition(otherOrderedNode);
            return (short) (otherPosition - thisPosition);
          } else {
            throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                "Nodes not of compatible types");
          }
        } else {
          return 0;
        }
      }
    } else {
      throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
          "Nodes not of compatible types");
    }
  }

  @Override
  public String getBaseURI() {
    return baseUri;
  }

  @Override
  public NodeList getChildNodes() {
    return children;
  }

  @Override
  public Object getFeature(String paramString1, String paramString2) {
    // no special features supported at this time
    return null;
  }

  @Override
  public Node getFirstChild() {
    if (children.getLength() > 0) {
      return children.item(0);
    }
    return null;
  }

  @Override
  public Node getLastChild() {
    int length = children.getLength();
    if (length > 0) {
      return children.item(length - 1);
    }
    return null;
  }

  @Override
  public Node getNextSibling() {
    return nextSibling;
  }

  @Override
  public Document getOwnerDocument() {
    return ownerDocument;
  }

  @Override
  public Node getParentNode() {
    return parentNode;
  }

  @Override
  public Node getPreviousSibling() {
    return previousSibling;
  }

  @Override
  public String getTextContent() throws DOMException {
    return textContent;
  }

  @Override
  public Object getUserData(String name) {
    return userData.get(name);
  }

  @Override
  public boolean hasChildNodes() {
    return children.getLength() > 0;
  }

  @Override
  public Node insertBefore(Node newChild, Node refChild) throws DOMException {
    if (refChild == null) {
      if (getLastChild() != null) {
        ((OrderedNode) getLastChild()).setNextSibling(newChild);
        ((OrderedNode) newChild).setPreviousSibling(getLastChild());
      }
      children.addNode(newChild);
    } else {
      int position = children.findNode(refChild);
      if (position == 0) {
        OrderedNode next = (OrderedNode) children.item(1);
        if (next != null) {
          next.setPreviousSibling(newChild);
          ((OrderedNode) newChild).setNextSibling(next);
        }
        children.addNode(newChild, 0);
      } else if (position > 0 && position < children.getLength() - 1) {
        OrderedNode previous = (OrderedNode) children.item(position - 1);
        OrderedNode next = (OrderedNode) children.item(position + 1);
        previous.setNextSibling(newChild);
        next.setPreviousSibling(newChild);
        ((OrderedNode) newChild).setPreviousSibling(previous);
        ((OrderedNode) newChild).setNextSibling(next);
        children.addNode(newChild, position);
      } else {
        OrderedNode previous = (OrderedNode) getLastChild();
        if (previous != null) {
          ((OrderedNode) newChild).setPreviousSibling(previous);
          previous.setNextSibling(newChild);
        }
        children.addNode(newChild);
      }
    }
    return newChild;
  }

  @Override
  public boolean isDefaultNamespace(String paramString) {
    return defaultNamespaceName.equals(paramString);
  }

  @Override
  public boolean isEqualNode(Node paramNode) {
    // comparison defined by
    // https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Node.html#isEqualNode%28org.w3c.dom.Node%29
    if (paramNode == null || !this.getClass().equals(paramNode.getClass())) {
      return false;
    }
    if (!stringsEqual(this.getNodeName(), paramNode.getNodeName())) {
      return false;
    }
    if (!stringsEqual(this.getNamespaceURI(), paramNode.getNamespaceURI())) {
      return false;
    }
    if (!stringsEqual(this.getPrefix(), paramNode.getPrefix())) {
      return false;
    }
    if (!stringsEqual(this.getNodeValue(), paramNode.getNodeValue())) {
      return false;
    }
    if (!this.getAttributes().equals(paramNode.getAttributes())) {
      return false;
    }
    if (!this.getChildNodes().equals(paramNode.getChildNodes())) {
      return false;
    }
    return false;
  }

  @Override
  public boolean isSameNode(Node paramNode) {
    return this == paramNode;
  }

  @Override
  public String lookupNamespaceURI(String prefix) {
    // details in
    // https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Node.html#lookupNamespaceURI%28java.lang.String%29
    if (prefix == null) {
      return defaultNamespaceName;
    }

    if (this.getPrefix().equals(prefix)) {
      return this.getNamespaceURI();
    } else {
      // this is not the node you are looking for
      if (parentNode != null) {
        return parentNode.lookupNamespaceURI(prefix);
      } else {
        return null;
      }
    }
  }

  @Override
  public String lookupPrefix(String namespaceUri) {
    // details in
    // https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Node.html#lookupPrefix%28java.lang.String%29
    if (namespaceUri == null) {
      return defaultNamespaceName;
    }

    if (this.getNamespaceURI().equals(namespaceUri)) {
      return this.getPrefix();
    } else {
      // this is not the node you are looking for
      if (parentNode != null) {
        return parentNode.lookupPrefix(namespaceUri);
      } else {
        return null;
      }
    }
  }

  public short nodePosition(Node node) {
    return (short) children.findNode(node);
  }

  @Override
  public void normalize() {
    int childNum = 0;
    while (childNum < children.getLength()) {
      Node child = children.item(childNum);
      if (child instanceof Text) {
        Text textNode = (Text) child;
        if (textNode.getNodeValue() == null
            || "".equals(textNode.getNodeValue())) {
          children.removeNode(childNum);
          // removed a node, so don't increment counter
        } else if (childNum < children.getLength() - 1) {
          Node nextChild = children.item(childNum + 1);
          if (nextChild instanceof Text) {
            // next child is a text node, so remove it
            Text nextText = (Text) nextChild;
            textNode.setNodeValue(
                textNode.getNodeValue() + nextText.getNodeValue());
            children.removeNode(childNum + 1);
            // child removed, don't increment i
          } else {
            childNum++;
          }
        }
      } else {
        // not a text node, so normalize it
        child.normalize();
        childNum++;
      }
    }

  }

  @Override
  public Node removeChild(Node paramNode) throws DOMException {
    int position = children.findNode(paramNode);
    if (position >= 0) {
      OrderedNode previous = (OrderedNode) paramNode.getPreviousSibling();
      OrderedNode next = (OrderedNode) paramNode.getNextSibling();
      previous.setNextSibling(next);
      next.setPreviousSibling(previous);
      children.removeNode(position);
    }
    return paramNode;
  }

  @Override
  public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
    int position = children.findNode(oldChild);
    if (position >= 0) {
      children.replaceNode(position, newChild);
    }
    return oldChild;
  }

  /**
   * Sets the base URI for this node.
   * 
   * @param baseUri
   *          new base URI
   */
  protected void setBaseUri(String baseUri) {
    this.baseUri = baseUri;
  }

  public void setNextSibling(Node nextSibling) {
    this.nextSibling = nextSibling;
  }

  public void setOwnerDocument(Document ownerDocument) {
    this.ownerDocument = ownerDocument;
    NodeList children = getChildNodes();
    for (int i = 0; i < children.getLength(); i++) {
      Node child = children.item(i);
      if (child instanceof OrderedNode) {
        ((OrderedNode) child).setOwnerDocument(ownerDocument);
      }
    }
  }

  public void setParentNode(Node parentNode) {
    this.parentNode = parentNode;
  }

  public void setPreviousSibling(Node previousSibling) {
    this.previousSibling = previousSibling;
  }

  @Override
  public void setTextContent(String newContent) throws DOMException {
    this.textContent = newContent;
  }

  @Override
  public Object setUserData(String key, Object value,
      UserDataHandler userDataHandler) {
    Object previousValue = null;
    if (userData.containsKey(key)) {
      previousValue = userData.get(key);
    }
    this.userData.put(key, value);
    // do nothing with user data handler

    return previousValue;
  }

  /**
   * Utility method to compare if two strings are equal. If both strings are
   * null, they are equal. If one is null and the other non-null, the strings
   * are not equal. Otherwise, both strings must match exactly.
   * 
   * @param s1
   *          First string
   * @param s2
   *          Second string
   * @return True of both strings are equal by this definition, false otherwise.
   */
  private boolean stringsEqual(String s1, String s2) {
    if (s1 == null && s2 == null) {
      return true;
    } else if (s1 == null || s2 == null) {
      return false;
    } else {
      return s1.equals(s2);
    }
  }

}
