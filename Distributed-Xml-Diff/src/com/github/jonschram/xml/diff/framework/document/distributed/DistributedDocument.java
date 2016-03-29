package com.github.jonschram.xml.diff.framework.document.distributed;

import java.util.List;
import java.util.Set;

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
import org.w3c.dom.UserDataHandler;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.traversal.TreeWalker;

import com.github.jonschram.xml.diff.framework.document.OrderedNode;

public class DistributedDocument extends OrderedNode
    implements Document, DocumentTraversal {
  private static final String ERROR_SUFFIX = " not supported for distributed document";

  private RandomAccessElement root;

  /**
   * Creates distributed document.
   */
  public DistributedDocument() {
    super();
    root = new RandomAccessElement("root", this);
    root.setParentNode(this);
  }

  @Override
  public Node adoptNode(Node source) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "adoptNode" + ERROR_SUFFIX);
  }

  @Override
  public Node appendChild(Node newChild) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "appendChild" + ERROR_SUFFIX);
  }

  @Override
  public Node cloneNode(boolean deep) {
    Element newNode = (Element) new RandomAccessElement();
    NodeList children = root.getChildNodes();
    if (deep) {
      for (int childNum = 0; childNum < children.getLength(); childNum++) {
        Node node = children.item(childNum);
        newNode.appendChild(node.cloneNode(true));
      }
    }
    NamedNodeMap attributes = root.getAttributes();
    for (int i = 0; i < attributes.getLength(); i++) {
      newNode.setAttributeNode((Attr) attributes.item(i));
    }

    return newNode;
  }

  @Override
  public CDATASection createCDATASection(String data) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createCDATASection" + ERROR_SUFFIX);
  }

  @Override
  public Comment createComment(String data) {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createComment" + ERROR_SUFFIX);
  }

  @Override
  public DocumentFragment createDocumentFragment() {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createDocumentFragment" + ERROR_SUFFIX);
  }

  @Override
  public Element createElement(String tagName) throws DOMException {
    RandomAccessElement ue = new RandomAccessElement();
    ue.setOwnerDocument(this);
    return ue;
  }

  @Override
  public Element createElementNS(String namespaceUri, String qualifiedName)
      throws DOMException {
    RandomAccessElement ue = new RandomAccessElement();
    ue.setNamespaceUri(namespaceUri);

    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createElementNS" + ERROR_SUFFIX);
  }

  @Override
  public EntityReference createEntityReference(String name)
      throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createEntityReference" + ERROR_SUFFIX);
  }

  @Override
  public ProcessingInstruction createProcessingInstruction(String target,
      String data) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createProcessingInstruction" + ERROR_SUFFIX);
  }

  @Override
  public Text createTextNode(String data) {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "createTextNode" + ERROR_SUFFIX);
  }

  @Override
  public NamedNodeMap getAttributes() {
    // no attributes on distributed XML
    return null;
  }

  @Override
  public String getBaseURI() {
    // no base URI on distributed XML
    return null;
  }

  /**
   * Gets all immediate children of the root node with the given name.
   * 
   * @param elementName
   *          Name of element to find.
   * @return All immediate children of the root node with the given name.
   */
  public List<Node> getChildByName(String elementName) {
    // convenience method to get children from root node
    List<Node> children = root.getChildrenByName(elementName);
    return children;
  }

  public List<Node> getChildElementsByName(String elementName) {
    return root.getChildElementsByName(elementName);
  }

  public Set<String> getChildElementNames() {
    return root.getElementNames();
  }

  @Override
  public NodeList getChildNodes() {
    return new NodeList() {
      @Override
      public int getLength() {
        return 1;
      }

      @Override
      public Node item(int paramInt) {
        if (paramInt == 0) {
          return root;
        } else {
          return null;
        }
      }
    };
  }

  @Override
  public DocumentType getDoctype() {
    // no doctype for this document
    return null;
  }

  @Override
  public Element getDocumentElement() {
    return (Element) root;
  }

  @Override
  public String getDocumentURI() {
    // no document URI
    return null;
  }

  @Override
  public DOMConfiguration getDomConfig() {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getDomConfig" + ERROR_SUFFIX);
  }

  @Override
  public Element getElementById(String elementId) {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getElementById" + ERROR_SUFFIX);
  }

  @Override
  public NodeList getElementsByTagName(String tagname) {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getElementsByTagName" + ERROR_SUFFIX);
  }

  @Override
  public NodeList getElementsByTagNameNS(String namespaceUri,
      String localName) {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getElementsByTagNameNS" + ERROR_SUFFIX);
  }

  @Override
  public Object getFeature(String feature, String version) {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getFeature" + ERROR_SUFFIX);
  }

  @Override
  public Node getFirstChild() {
    return root;
  }

  @Override
  public DOMImplementation getImplementation() {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getImplementation" + ERROR_SUFFIX);
  }

  @Override
  public String getInputEncoding() {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "getInputEncoding" + ERROR_SUFFIX);
  }

  @Override
  public Node getLastChild() {
    return root;
  }

  @Override
  public String getLocalName() {
    // local name is null for document
    return null;
  }

  @Override
  public String getNamespaceURI() {
    return null;
  }

  @Override
  public Node getNextSibling() {
    // no sibling
    return null;
  }

  @Override
  public String getNodeName() {
    return "#document";
  }

  @Override
  public short getNodeType() {
    return DOCUMENT_NODE;
  }

  @Override
  public String getNodeValue() throws DOMException {
    return null;
  }

  @Override
  public Document getOwnerDocument() {
    return null;
  }

  @Override
  public Node getParentNode() {
    return null;
  }

  @Override
  public String getPrefix() {
    return null;
  }

  @Override
  public Node getPreviousSibling() {
    return null;
  }

  @Override
  public boolean getStrictErrorChecking() {
    // very lenient about errors
    return false;
  }

  @Override
  public String getTextContent() throws DOMException {
    return root.getTextContent();
  }

  @Override
  public Object getUserData(String key) {
    return super.getUserData(key);
  }

  @Override
  public String getXmlEncoding() {
    return null;
  }

  @Override
  public boolean getXmlStandalone() {
    return false;
  }

  @Override
  public String getXmlVersion() {
    return null;
  }

  @Override
  public boolean hasAttributes() {
    // has no attributes
    return false;
  }

  @Override
  public boolean hasChildNodes() {
    // child node must exist
    return true;
  }

  @Override
  public Node importNode(Node importedNode, boolean deep) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "importNode" + ERROR_SUFFIX);
  }

  @Override
  public Node insertBefore(Node newChild, Node refChild) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "insertBefore" + ERROR_SUFFIX);
  }

  @Override
  public boolean isDefaultNamespace(String namespaceUri) {
    // since no namespace is supported decide this is the default
    return true;
  }

  @Override
  public boolean isEqualNode(Node arg) {
    if (arg.getClass().equals(this.getClass())) {
      DistributedDocument doc = (DistributedDocument) arg;
      // since they are the same class, can compare the root element
      if (root == null && doc.root == null) {
        return true;
      } else if (root == null || doc.root == null) {
        // since both == null has happened, guaranteed to be one or the other
        return false;
      }
      return root.isEqualNode(doc.root);
    }
    return false;
  }

  @Override
  public boolean isSameNode(Node other) {
    return this == other;
  }

  @Override
  public boolean isSupported(String feature, String version) {
    // not specified what this document supports
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "isSupported" + ERROR_SUFFIX);
  }

  @Override
  public String lookupNamespaceURI(String prefix) {
    // no URI
    return null;
  }

  @Override
  public String lookupPrefix(String namespaceUri) {
    // no prefix
    return null;
  }

  @Override
  public void normalize() {
    // do nothing
  }

  @Override
  public void normalizeDocument() {
    // do nothing
  }

  @Override
  public Node removeChild(Node oldChild) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "removeChild" + ERROR_SUFFIX);
  }

  @Override
  public Node renameNode(Node node, String namespaceUri, String qualifiedName)
      throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "renameNode" + ERROR_SUFFIX);
  }

  @Override
  public Node replaceChild(Node newChild, Node oldChild) throws DOMException {
    throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
        "replaceChild" + ERROR_SUFFIX);
  }

  @Override
  public void setDocumentURI(String documentUri) {
    // do nothing
  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException {
    // do nothing
  }

  @Override
  public void setPrefix(String prefix) throws DOMException {
    // do nothing
  }

  @Override
  public void setStrictErrorChecking(boolean strictErrorChecking) {
    // do nothing
  }

  @Override
  public void setTextContent(String textContent) throws DOMException {
    // do nothing
  }

  @Override
  public Object setUserData(String key, Object data, UserDataHandler handler) {
    // do nothing
    return null;
  }

  @Override
  public void setXmlStandalone(boolean xmlStandalone) throws DOMException {
    // do nothing
  }

  @Override
  public void setXmlVersion(String xmlVersion) throws DOMException {
    // do nothing
  }

  @Override
  public NodeIterator createNodeIterator(Node paramNode, int paramInt,
      NodeFilter paramNodeFilter, boolean paramBoolean) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TreeWalker createTreeWalker(Node paramNode, int paramInt,
      NodeFilter paramNodeFilter, boolean paramBoolean) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Attr createAttribute(String name) throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Attr createAttributeNS(String namespaceURI, String qualifiedName)
      throws DOMException {
    // TODO Auto-generated method stub
    return null;
  }

}
