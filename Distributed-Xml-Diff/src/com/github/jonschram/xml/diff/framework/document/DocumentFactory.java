package com.github.jonschram.xml.diff.framework.document;

import com.github.jonschram.xml.diff.framework.document.distributed.DistributedDocument;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DocumentFactory {
  /**
   * Parses the file or directory as the appropriate kind of document. If the
   * file is a directory, parses as a distributed XML using the XML fragments in
   * that directory. If the file is a file, parses as a single XML document.
   * 
   * @param file
   *          File to parse
   * @return Parsed XML document.
   * @throws FileNotFoundException
   *           If the file can't be found.
   * @throws IOException
   *           If there is some I/O error when reading the file
   */
  public Document parse(File file) throws FileNotFoundException, IOException {

    Document doc = null;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db;

    try {
      db = dbf.newDocumentBuilder();
      if (file.isDirectory()) {
        // distributed XML with no root
        doc = new DistributedDocument();

        File[] files = file.listFiles();
        for (File subFile : files) {
          if (subFile.isFile()) {
            try {
              Document part = db.parse(new FileInputStream(subFile));
              doc.getFirstChild().appendChild(part.getDocumentElement());

            } catch (FileNotFoundException fileException) {
              System.err.println(
                  "File " + subFile.getName() + " not found - skipping");
            }
          }
        }

      } else if (file.isFile()) {
        // regular xml
        doc = db.parse(new FileInputStream(file));
      }

    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    }

    return doc;
  }
}
