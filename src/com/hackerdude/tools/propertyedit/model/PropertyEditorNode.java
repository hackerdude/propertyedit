package com.hackerdude.tools.propertyedit.model;

import java.util.*;

/**
 * A Property Editor node represents a specific node in the
 * Property Hierarchy.
 */
public class PropertyEditorNode {

	private HashMap childNodes = new HashMap();
	private ArrayList childNodesList = new ArrayList();

	private String nodeName;
	private String nodeDescription;
	private String dataTypeClass;
	private Object nodeValue;

	private boolean modified;

	private String fullPropertyName;

	public PropertyEditorNode(String propertyName) {
		fullPropertyName = propertyName;

	}


	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
	}
	public void setDataTypeClass(String dataType) {
		this.dataTypeClass = dataType;
	}
	public String getNodeName() {
		return nodeName;
	}
	public String getNodeDescription() {
		return nodeDescription;
	}
	public String getDataTypeClass() {
		return dataTypeClass;
	}

	public Set getChildrenNames() {
		return childNodes.keySet();
	}

	public Set getChildren() {
		return childNodes.entrySet();
	}

	public boolean hasChild(String childName) {
		return childNodes.containsKey(childName);
	}

	public PropertyEditorNode getChild(String childName) {
		return (PropertyEditorNode)childNodes.get(childName);
	}

	public PropertyEditorNode addChild(String fullPropertyName, String childName) {
		PropertyEditorNode result = new PropertyEditorNode(fullPropertyName);
		result.setNodeName(childName);
		// Default Description.
		result.setNodeDescription(nodeName+"."+childName);
		childNodes.put(childName, result);
		childNodesList.add(result);
		return result;
	}

	public PropertyEditorNode getChild(int i) {
		return (PropertyEditorNode)childNodesList.get(i);
	}

	public int getChildCount() { return childNodesList.size(); }

	public int getChildIndex(PropertyEditorNode node) {
		return childNodesList.indexOf(node);
	}

	public String toString() {
		return nodeName;
	}

	public void setNodeValue(Object nodeValue) {
		this.nodeValue = nodeValue;
	}
	public Object getNodeValue() {
		return nodeValue;
	}

	public String getFullPropertyName() { return fullPropertyName; }

	public boolean isModified() { return modified; }

	public void setModified(boolean modified) { this.modified = modified; }
}