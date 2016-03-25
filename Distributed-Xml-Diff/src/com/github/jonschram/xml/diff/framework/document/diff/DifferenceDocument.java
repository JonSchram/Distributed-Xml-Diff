package com.github.jonschram.xml.diff.framework.document.diff;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface DifferenceDocument extends Document {

  /**
   * Create element indicating that the specified attribute is to be added.
   * 
   * @param attribute
   *          Attribute to be added to the element
   * @param referenceElement
   *          Element the attribute will be added to.
   * @return Element created.
   */
  public Element createAddElement(Attr attribute, Element referenceElement);

  /**
   * Create element that is to be added to the main element. Element is to be
   * added as the last child element of referenceElement.
   * 
   * @param element
   *          Element to be added to the main element
   * @param referenceElement
   *          Element indicating desired parent element.
   * @return Element created
   */
  public Element createAddElement(Element element, Element referenceElement);

  /**
   * Creates an element indicating an element to add to the difference document.
   * 
   * @param element
   *          Element the difference document should record as added into the
   *          main document.
   * @param referenceElement
   *          Element that should be used as a reference to indicate added
   *          element's relative position.
   * @param position
   *          Where the new element should go in relation to the reference
   *          element.
   * @return Element created
   */
  public Element createAddElement(Element element, Element referenceElement,
      AddPosition position);

  /**
   * Create element indicating that the specified attribute is to be added to
   * the referenceElement.
   * 
   * @param attributeName
   *          Name of attribute to add
   * @param attributeValue
   *          Value of new attribute
   * @param referenceElement
   *          Element to which the attribute will be added
   * @return Element created
   */
  public Element createAddElement(String attributeName, String attributeValue,
      Element referenceElement);

  /**
   * Creates an element indicating that the given attribute should be removed
   * from the element in the main document.
   * 
   * @param attribute
   *          Attribute to be removed
   * @param ownerElement
   *          Element from which the attribute should be removed
   * @return Element created
   */
  public Element createRemoveElement(Attr attribute, Element ownerElement);

  /**
   * Creates an element indicating that the given element should be removed from
   * the main document.
   * 
   * @param element
   *          Element to be removed from the main document.
   * @return Element created
   */
  public Element createRemoveElement(Element element);

  /**
   * Creates an element indicating that the value of the specified attribute
   * should be replaced with the new value.
   * 
   * @param oldAttribute
   *          Attribute whose value will be replaced
   * @param newValue
   *          New value for attribute
   * @param ownerElement
   *          Element whose attribute should be modified
   * @return Element created
   */
  public Element createReplaceElement(Attr oldAttribute, String newValue,
      Element ownerElement);

  /**
   * Creates an element indicating that the given element should be replaced
   * with the new element.
   * 
   * @param oldElement
   *          Current element in the document
   * @param newElement
   *          Element that should replace oldElement
   * 
   * @return Element created
   */
  public Element createReplaceElement(Element oldElement, Element newElement);

  /**
   * Creates an element indicating that the value of the specified attribute
   * should be replaced with the new value.
   * 
   * @param oldAttributeName
   *          Name of attribute to replace
   * @param newValue
   *          New value for this attribute
   * @param ownerElement
   *          Element whose attribute should be modified
   * @return Created element
   */
  public Element createReplaceElement(String oldAttributeName, String newValue,
      Element ownerElement);
}
