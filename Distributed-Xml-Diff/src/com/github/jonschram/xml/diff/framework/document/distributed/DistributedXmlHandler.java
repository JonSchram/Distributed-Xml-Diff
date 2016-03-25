package com.github.jonschram.xml.diff.framework.document.distributed;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class DistributedXmlHandler extends DefaultHandler {

  private Document parentDocument;

  private Stack<Node> parsingNodes;

  public DistributedXmlHandler(Document parent) {
    parentDocument = parent;
    parsingNodes = new Stack<>();
  }

  @Override
  public void startElement(String uri, String localName, String qualifiedName,
      Attributes attributes) throws SAXException {
    Element newElement = parentDocument.createElement(qualifiedName);
    for (int i = 0; i < attributes.getLength(); i++) {
      System.out.println(attributes.getLocalName(i));
      System.out.println(attributes.getQName(i));
      String name = attributes.getLocalName(i);
      String value = attributes.getValue(i);
      newElement.setAttribute(name, value);
    }
    parsingNodes.push(newElement);
  }

  @Override
  public void endElement(String uri, String localName, String qName)
      throws SAXException {
    Node finishedElement = parsingNodes.pop();
    try {
      Node parentElement = parsingNodes.peek();
      parentElement.appendChild(finishedElement);
    } catch (EmptyStackException e) {
      // this was the root element
      parentDocument.appendChild(finishedElement);
    }
  }

  @Override
  public void startDocument() throws SAXException {
  }

  @Override
  public void endDocument() throws SAXException {
  }

  @Override
  public void characters(char[] ch, int start, int length) throws SAXException {
    Node containingNode = parsingNodes.peek();
    StringBuilder sb = new StringBuilder();
    sb.append(Arrays.copyOfRange(ch, start, start + length));
    String textContent = sb.toString();
    containingNode.setTextContent(textContent);
  }

}
