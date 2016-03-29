package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;

public abstract class OrderedCharacterData extends OrderedNode
    implements CharacterData {

  private String data;

  public OrderedCharacterData(String data) {
    this.data = data;
  }

  @Override
  public String getNodeValue() throws DOMException {
    return getData();
  }

  @Override
  public void setNodeValue(String nodeValue) throws DOMException {
    setData(nodeValue);
  }

  @Override
  public NamedNodeMap getAttributes() {
    return null;
  }

  @Override
  public boolean isSupported(String paramString1, String paramString2) {
    return false;
  }

  @Override
  public String getNamespaceURI() {
    return null;
  }

  @Override
  public String getPrefix() {
    return null;
  }

  @Override
  public void setPrefix(String paramString) throws DOMException {

  }

  @Override
  public String getLocalName() {
    return null;
  }

  @Override
  public boolean hasAttributes() {
    return false;
  }

  @Override
  public String getData() throws DOMException {
    return data;
  }

  @Override
  public void setData(String data) throws DOMException {
    this.data = data;
  }

  @Override
  public int getLength() {
    return data.length();
  }

  @Override
  public String substringData(int offset, int count) throws DOMException {
    if (offset + count > data.length()) {
      return data.substring(offset);
    } else {
      return data.substring(offset, offset + count);
    }
  }

  @Override
  public void appendData(String arg) throws DOMException {
    data += arg;
  }

  @Override
  public void insertData(int offset, String arg) throws DOMException {
    if (offset < 0 || offset > data.length()) {
      throw new DOMException(DOMException.INDEX_SIZE_ERR,
          "Offset is outside allowed range");
    }

    data = data.substring(0, offset) + arg + data.substring(offset);
  }

  @Override
  public void deleteData(int offset, int count) throws DOMException {
    if (offset < 0 || offset > data.length() || count < 0) {
      throw new DOMException(DOMException.INDEX_SIZE_ERR,
          "Offset or count is outside allowed range");
    }
    data = data.substring(0, offset) + data.substring(offset + count);
  }

  @Override
  public void replaceData(int offset, int count, String arg)
      throws DOMException {
    if (offset < 0 || offset > data.length() || count < 0) {
      throw new DOMException(DOMException.INDEX_SIZE_ERR,
          "Offset or count is outside allowed range");
    }
    data = data.substring(0, offset) + arg + data.substring(offset + count);
  }

}
