package com.github.jonschram.xml.diff;

import com.github.jonschram.xml.diff.framework.document.DocumentFactory;
import com.github.jonschram.xml.diff.framework.document.distributed.DistributedDocumentBuilder;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * Main runner class of my XML diff program.
 * 
 * @author Jonathan Schram
 *
 */
public class Main {
  /**
   * Runs the XML diff program using command line arguments as paths to the xml
   * files.
   * 
   * @param args
   *          Command line arguments
   */
  public static void main(String[] args) {
    String file1Path = "files/test1a.xml";
    String file2Path = "files/test1b.xml";

    // String file1Path = "files/test2a.xml";
    // String file2Path = "files/test2b.xml";

    String fileSections = "files/test3asections";

    // parseAsSax(new File(file1Path));
    parseAsSax(new File(fileSections));

    DocumentFactory factory = new DocumentFactory();
    try {

      Document d1 = factory.parse(new File(file1Path));
      Document d2 = factory.parse(new File(file2Path));

      System.out.println("---------------DONE READING-----------");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void parseAsSax(File file) {
    DistributedDocumentBuilder ddb = new DistributedDocumentBuilder();
    try {
      Document result = ddb.parse(file);
    } catch (SAXException | IOException e) {
      e.printStackTrace();
    }
  }
}
