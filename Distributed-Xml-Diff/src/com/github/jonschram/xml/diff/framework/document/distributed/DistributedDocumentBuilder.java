package com.github.jonschram.xml.diff.framework.document.distributed;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class DistributedDocumentBuilder extends DocumentBuilder {

  /**
   * Doesn't validate.
   */
  private boolean validating;

  private boolean namespaceAware;
  private EntityResolver entityResolver;
  private ErrorHandler errorHandler;

  public DistributedDocumentBuilder() {
    this(false, null, null);
  }

  public DistributedDocumentBuilder(boolean namespaceAware) {
    this(namespaceAware, null, null);
  }

  public DistributedDocumentBuilder(EntityResolver entityResolver) {
    this(false, entityResolver, null);
  }

  public DistributedDocumentBuilder(ErrorHandler errorHandler) {
    this(false, null, errorHandler);
  }

  /**
   * Constructs a new distributed document builder.
   * 
   * @param namespaceAware
   *          Whether this builder should be namespace aware.
   * @param entityResolver
   *          The entity resolver
   * @param errorHandler
   *          The error handler.
   */
  public DistributedDocumentBuilder(boolean namespaceAware,
      EntityResolver entityResolver, ErrorHandler errorHandler) {
    this.namespaceAware = namespaceAware;
    this.entityResolver = entityResolver;
    this.errorHandler = errorHandler;
    this.validating = false;
  }

  @Override
  public DOMImplementation getDOMImplementation() {
    return new DistributedDocument().getImplementation();
  }

  @Override
  public boolean isNamespaceAware() {
    return namespaceAware;
  }

  @Override
  public boolean isValidating() {
    return validating;
  }

  @Override
  public Document newDocument() {
    DistributedDocument doc = new DistributedDocument();
    return doc;
  }

  @Override
  public Document parse(InputSource source) throws SAXException, IOException {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(namespaceAware);
      factory.setValidating(validating);

      SAXParser sp = factory.newSAXParser();
      sp.getXMLReader().setErrorHandler(errorHandler);
      sp.getXMLReader().setEntityResolver(entityResolver);

      DistributedDocument finishedDocument = new DistributedDocument();
      DistributedXmlHandler handler = new DistributedXmlHandler(
          finishedDocument);

      sp.parse(source, handler);

      return finishedDocument;

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Document parse(File file) throws SAXException, IOException {
    if (file.isDirectory()) {
      // build true distributed document
      Document document = new DistributedDocument();

      File[] parts = file.listFiles();
      for (File part : parts) {
        if (part.isFile()) {
          Document docPart = super.parse(part);
          document.adoptNode(docPart.getDocumentElement());
        }
      }

      return document;

    } else {
      // only a file, so just one part
      return super.parse(file);
    }
  }

  @Override
  public void setEntityResolver(EntityResolver er) {
    this.entityResolver = er;
  }

  @Override
  public void setErrorHandler(ErrorHandler eh) {
    this.errorHandler = eh;
  }

}
