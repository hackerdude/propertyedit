package com.hackerdude.tools.propertyedit;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.hackerdude.tools.propertyedit.model.PropertyEditorModel;
import com.hackerdude.tools.propertyedit.model.PropertyEditorNode;


/**
 * Property Editor panel.
 *
 * @author David Martinez
 * @version 1.0
 */
public class PropertyEditorPanel extends JPanel {
/** @todo Wrap into a reusable JavaBean */
	JSplitPane jSplitPane1 = new JSplitPane();
	BorderLayout borderLayout1 = new BorderLayout();
	PropertyItemEditorPanel pnlPropertyItemEditor = new PropertyItemEditorPanel();
	PropertyEditorModel dataModel;

	TreeSelectionListener TREE_SELECTION_LISTENER = new PropertyItemTreeSelectionListener();
	JScrollPane jScrollPane1 = new JScrollPane();
	JTree jTree1 = new JTree();

	public PropertyEditorPanel() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setLayout(borderLayout1);
		jTree1.addTreeSelectionListener(TREE_SELECTION_LISTENER);
		jScrollPane1.setMinimumSize(new Dimension(150, 24));
		jScrollPane1.setPreferredSize(new Dimension(150, 324));
		this.add(jSplitPane1,  BorderLayout.CENTER);
		jSplitPane1.add(pnlPropertyItemEditor, JSplitPane.RIGHT);
		jSplitPane1.add(jScrollPane1, JSplitPane.LEFT);
		jScrollPane1.getViewport().add(jTree1, null);
		jSplitPane1.setDividerLocation(200);
	}

	public PropertyEditorNode getItemEditorModel() {
		return pnlPropertyItemEditor.getModel();
	}

	class PropertyItemTreeSelectionListener implements TreeSelectionListener {
		public PropertyItemTreeSelectionListener() {
		}

		public void valueChanged(TreeSelectionEvent evt) {
			TreePath selectedPath = evt.getPath();
			if ( selectedPath == null ) return;
			final PropertyEditorNode node = (PropertyEditorNode)selectedPath.getLastPathComponent();
			if ( node != null ) pnlPropertyItemEditor.setModel(node);
		}

	}

	public void setModel(PropertyEditorModel model) {
		dataModel = model;
		jTree1.setModel(new PropertyTreeModel(dataModel));
	}

	public PropertyEditorModel getModel() {
		return dataModel;
	}

	public PropertyItemEditorPanel getPropertyItemEditor() {
		return pnlPropertyItemEditor;
	}

	public JTree getTree() {
		return jTree1;
	}

}