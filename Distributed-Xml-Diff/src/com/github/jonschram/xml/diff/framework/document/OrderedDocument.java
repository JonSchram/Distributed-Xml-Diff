package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class OrderedDocument extends OrderedNode implements Document {
  private static final String NODE_NAME = "#document";

  private OrderedElement root;

  public OrderedDocument() {
    super();
    root = new OrderedElement("root", this);
  }

  public OrderedDocument(OrderedElement rootNode) {
    super();
    root = rootNode;
  }

  @Override
  public Node adoptNode(Node source) throws DOMException {
    /*
     * see
     * "https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/Document.html#adoptNode%28org.w3c.dom.Node%29"
     * for details on adoptable nodes
     */
    short sourceType = source.getNodeType();
    if (sourceType == DOCUMENT_NODE || sourceType == DOCUMENT_TYPE_NODE
        || sourceType == ENTITY_NODE || sourceType == NOTATION_NODE) {
      throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
          "Error adopting node: node type cannot be adopted.");
    }
    if (sourceType == ATTRIBUTE_NODE && source instanceof OrderedAttribute) {
      OrderedAttribute sourceAttribute = ((OrderedAttribute) source);
      sourceAttribute.setSpecified(true);
      sourceAttribute.setOwnerElement(null);
    }
    if (source instanceof OrderedNode) {
      OrderedNode orderedSource = (OrderedNode) source;
      orderedSource.setOwnerDocument(this);
      orderedSource.setParentNode(root);
      return source;
    }
    // different implementation, so can't adopt
    return null;
  }

  @Override
  public Node cloneNode(boolean deep) {
    OrderedDocument cloneDoc = new OrderedDocument(
        (OrderedElement) root.cloneNode(deep));
    cloneDoc.setBaseUri(getBaseURI());
    cloneDoc.setDocumentURI(getDocumentURI());
    cloneDoc.setStrictErrorChecking(getStrictErrorChecking());
    cloneDoc.setTextContent(getTextContent());
    cloneDoc.setXmlStandalone(getXmlStandalone());
    cloneDoc.setXmlVersion(getXmlVersion());
    return cloneDoc;
  }

  @Override
  public Attr createAttribute(String name) throws DOMException {
    OrderedAttribute attribute = new OrderedAttribute(name, "", null);
    attribute.setLocalName(null);
    attribute.setPrefix(null);
    attribute.setNamespaceUri(null);
    return attribute;
  }

  @Override
  public Attr createAttributeNS(String namespaceUri, String qualifiedName)
      throws DOMException {
    OrderedAttribute attribute = new OrderedAttribute(qualifiedName, "", null);
    attribute.setNamespaceUri(namespaceUri);
    int prefixEnd = qualifiedName.indexOf(':');
    if (prefixEnd > -1) {
      attribute.setPrefix(qualifiedName.substring(0, prefixEnd));
      attribute.setLocalName(qualifiedName.substring(prefixEnd + 1));
    } else {
      attribute.setPrefix(null);
      attribute.setLocalName(qualifiedName);
    }

    return attribute;
  }

  @Override
  public CDATASection createCDATASection(String arg0) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment createComment(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DocumentFragment createDocumentFragment() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createElement(String arg0) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createElementNS(String arg0, String arg1) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public EntityReference createEntityReference(String arg0)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProcessingInstruction createProcessingInstruction(String arg0,
      String arg1) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Text createTextNode(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NamedNodeMap getAttributes() {
    // no attributes
    return null;
  }

  @Override
  public DocumentType getDoctype() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element getDocumentElement() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getDocumentURI() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DOMConfiguration getDomConfig() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element getElementById(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeList getElementsByTagName(String arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeList getElementsByTagNameNS(String arg0, String arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DOMImplementation getImplementation() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getInputEncoding() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getLocalName() {
    // defined as null
    return null;
  }

  @Override
  public String getNamespaceURI() {
    // defined as null
    return null;
  }

  @Override
  public String getNodeName() {
    return NODE_NAME;
  }

  @Override
  public short getNodeType() {
    return Node.DOCUMENT_NODE;
  }

  @Override
  public String getNodeValue() throws DOMException {
    // defined as null
    return null;
  }

  @Override
  public String getPrefix() {
    // defined as null
    return null;
  }

  @Override
  public boolean getStrictErrorChecking() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getXmlEncoding() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean getXmlStandalone() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getXmlVersion() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasAttributes() {
    // defined to have no attributes
    return false;
  }

  @Override
  public Node importNode(Node arg0, boolean arg1) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isEqualNode(Node otherNode) {
    return super.isEqualNode(otherNode);
  }

  @Override
  public boolean isSupported(String feature, String version) {
    return false;
  }

  @Override
  public void normalizeDocument() {
    // TODO Auto-generated method stub

  }

  @Override
  public Node renameNode(Node arg0, String arg1, String arg2)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDocumentURI(String arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException {
    // no action
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {
    // no prefix, do nothing
  }

  @Override
  public void setStrictErrorChecking(boolean arg0) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setXmlStandalone(boolean arg0) throws DOMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setXmlVersion(String arg0) throws DOMException {
    // TODO Auto-generated method stub

  }

}
