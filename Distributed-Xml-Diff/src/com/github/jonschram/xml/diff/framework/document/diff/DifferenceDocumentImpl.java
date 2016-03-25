package com.github.jonschram.xml.diff.framework.document.diff;

import com.github.jonschram.xml.diff.framework.document.OrderedDocument;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class DifferenceDocumentImpl extends OrderedDocument
    implements DifferenceDocument {

  @Override
  public Node adoptNode(Node paramNode) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Node cloneNode(boolean paramBoolean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createAddElement(Attr attribute, Element referenceElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createAddElement(Element element, Element referenceElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createAddElement(Element element, Element referenceElement,
      AddPosition position) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createAddElement(String attributeName, String attributeValue,
      Element referenceElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Attr createAttribute(String paramString) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Attr createAttributeNS(String paramString1, String paramString2)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CDATASection createCDATASection(String paramString)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Comment createComment(String paramString) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DocumentFragment createDocumentFragment() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createElement(String paramString) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createElementNS(String paramString1, String paramString2)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public EntityReference createEntityReference(String paramString)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ProcessingInstruction createProcessingInstruction(String paramString1,
      String paramString2) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createRemoveElement(Attr attribute, Element ownerElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createRemoveElement(Element element) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createReplaceElement(Attr oldAttribute, String newValue,
      Element ownerElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createReplaceElement(Element oldElement, Element newElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Element createReplaceElement(String oldAttributeName, String newValue,
      Element ownerElement) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Text createTextNode(String paramString) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NamedNodeMap getAttributes() {
    // TODO Auto-generated method stub
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
  public Element getElementById(String paramString) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeList getElementsByTagName(String paramString) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public NodeList getElementsByTagNameNS(String paramString1,
      String paramString2) {
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
  public String getNodeValue() throws DOMException {
    // TODO Auto-generated method stub
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
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Node importNode(Node paramNode, boolean paramBoolean)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isEqualNode(Node paramNode) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isSupported(String paramString1, String paramString2) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void normalizeDocument() {
    // TODO Auto-generated method stub

  }

  @Override
  public Node renameNode(Node paramNode, String paramString1,
      String paramString2) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setDocumentURI(String paramString) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setNodeValue(String paramString) throws DOMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setStrictErrorChecking(boolean paramBoolean) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setXmlStandalone(boolean paramBoolean) throws DOMException {
    // TODO Auto-generated method stub

  }

  @Override
  public void setXmlVersion(String paramString) throws DOMException {
    // TODO Auto-generated method stub

  }

}
