package com.hackerdude.tools.propertyedit.model;

import java.util.*;

/**
 * The PropertyEdit Mini-app edits PropertyEditorModel Objects.
 */
public class PropertyEditorModel {

	private ArrayList listeners = new ArrayList();

	public PropertyEditorNode propRootNode;

	public PropertyEditorModel(String modelName) {
		propRootNode = new PropertyEditorNode(null);
		propRootNode.setNodeName(modelName);
	}


	public PropertyEditorNode getRootNode() {
		return propRootNode;
	}
	/**
	 * This function returns a node based on a node path. If the node does not
	 * exist, it creates it and then returns it. The separator specifying
	 * parent-child relationships is a dot (.)
	 */
	public synchronized PropertyEditorNode getOrCreateNode(String nodePath, Object value) {
		StringTokenizer st = new StringTokenizer(nodePath, ".");
		PropertyEditorNode thisNode = propRootNode;
		while ( st.hasMoreTokens() ) {
			String nextNode = st.nextToken();
			if ( thisNode.hasChild(nextNode) ) {
				thisNode = thisNode.getChild(nextNode);
			} else {
				if ( value != null ) {
					//System.out.println("Adding child "+nextNode+" to "+thisNode.getNodeName());
					String className = value.getClass().getName();
					thisNode = thisNode.addChild(nodePath, nextNode);
					// Only set the value and data type on the last element of the path.
					if ( nodePath.endsWith(nextNode) ) {
						thisNode.setNodeValue(value);
						thisNode.setDataTypeClass(className);
					}
				}
			}
		}
		return thisNode;
	}


}