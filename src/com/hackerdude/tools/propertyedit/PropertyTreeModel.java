package com.hackerdude.tools.propertyedit;

import javax.swing.tree.*;
import javax.swing.event.*;
import com.hackerdude.tools.propertyedit.model.*;
import java.util.*;

/**
 * Title:        Property Editor SwingUI
 * Description:  A Swing UI to edit properties files in a tree-like format.
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author David Martinez
 * @version 1.0
 */

public class PropertyTreeModel implements TreeModel  {


	ArrayList treeModelListeners = new ArrayList();

	PropertyEditorModel propEditorModel;

	public PropertyTreeModel(PropertyEditorModel editorModel) {
		propEditorModel = editorModel;
	}

	public Object getRoot() {
		return propEditorModel.getRootNode();
	}

	public Object getChild(Object parent, int index) {
		PropertyEditorNode node = (PropertyEditorNode)parent;
		return node.getChild(index);
	}

	public int getChildCount(Object parent) {
		PropertyEditorNode node = (PropertyEditorNode)parent;
		return node.getChildCount();
	}

	public boolean isLeaf(Object node) {
		PropertyEditorNode propNode = (PropertyEditorNode)node;
		return propNode.getChildCount() == 0;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		/**@todo: Implement this javax.swing.tree.TreeModel method*/
		throw new java.lang.UnsupportedOperationException("Method valueForPathChanged() not yet implemented.");
	}

	public int getIndexOfChild(Object parent, Object child) {
		PropertyEditorNode parentNode = (PropertyEditorNode)parent;
		PropertyEditorNode childNode = (PropertyEditorNode)child;
		return parentNode.getChildIndex(childNode);
	}

	public void addTreeModelListener(TreeModelListener l) {
		treeModelListeners.add(l);

	}

	public void removeTreeModelListener(TreeModelListener l) {
		treeModelListeners.remove(l);
	}

	private void notifyAllListeners(TreeModelEvent ev) {
		Iterator iter = treeModelListeners.iterator();
		while ( iter.hasNext() ) {
			TreeModelListener listener = (TreeModelListener)iter.next();
			listener.treeNodesChanged(ev);
		}
	}
}