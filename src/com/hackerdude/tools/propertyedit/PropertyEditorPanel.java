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
	JSplitPane splitPane = new JSplitPane();
	BorderLayout mainLayout = new BorderLayout();
	PropertyItemEditorPanel pnlPropertyItemEditor = new PropertyItemEditorPanel();
	PropertyEditorModel dataModel;

	TreeSelectionListener TREE_SELECTION_LISTENER = new PropertyItemTreeSelectionListener();
	JScrollPane scrollPane = new JScrollPane();
	JTree propertyTree = new JTree();

	public PropertyEditorPanel() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		this.setLayout(mainLayout);
		propertyTree.addTreeSelectionListener(TREE_SELECTION_LISTENER);
		scrollPane.setMinimumSize(new Dimension(150, 24));
		scrollPane.setPreferredSize(new Dimension(150, 324));
		this.add(splitPane,  BorderLayout.CENTER);
		splitPane.add(pnlPropertyItemEditor, JSplitPane.RIGHT);
		splitPane.add(scrollPane, JSplitPane.LEFT);
		scrollPane.getViewport().add(propertyTree, null);
		splitPane.setDividerLocation(200);
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
		propertyTree.setModel(new PropertyTreeModel(dataModel));
	}

	public PropertyEditorModel getModel() {
		return dataModel;
	}

	public PropertyItemEditorPanel getPropertyItemEditor() {
		return pnlPropertyItemEditor;
	}

	public JTree getTree() {
		return propertyTree;
	}

}