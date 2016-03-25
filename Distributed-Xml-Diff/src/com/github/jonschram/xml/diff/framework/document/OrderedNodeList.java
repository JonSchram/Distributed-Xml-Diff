package com.github.jonschram.xml.diff.framework.document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class OrderedNodeList implements NodeList {

  /**
   * Nodes contained in this node list.
   */
  ArrayList<Node> nodes;

  /**
   * Adds the node to the end of this node list.
   * 
   * @param node
   *          Node to add
   */
  public void addNode(Node node) {
    nodes.add(node);
  }

  /**
   * Adds the node to the node list at the specified index.
   * 
   * @param node
   *          Node to add
   * @param index
   *          Index to put new node at, existing nodes are shifted to the next
   *          spot
   */
  public void addNode(Node node, int index) {
    nodes.add(index, node);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OrderedNodeList other = (OrderedNodeList) obj;
    if (nodes == null) {
      if (other.nodes != null) {
        return false;
      }
    } else if (!nodes.equals(other.nodes)) {
      return false;
    }
    return true;
  }

  public int findNode(Node node) {
    return nodes.indexOf(node);
  }

  @Override
  public int getLength() {
    return nodes.size();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
    return result;
  }

  @Override
  public Node item(int index) {
    if (index >= 0 && index < nodes.size()) {
      return nodes.get(index);
    }
    return null;
  }

  /**
   * Removes this node from the node list.
   * 
   * @param index
   *          Index of node to remove
   */
  public void removeNode(int index) {
    if (index >= 0 && index < nodes.size()) {
      nodes.remove(index);
    }
  }

  /**
   * Sets the node at the given index to be the given value.
   * 
   * @param index
   *          Index of node to replace
   * @param newNode
   *          Node to replace it with
   */
  public void replaceNode(int index, Node newNode) {
    nodes.set(index, newNode);
  }

}
