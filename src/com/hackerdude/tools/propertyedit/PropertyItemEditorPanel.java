package com.hackerdude.tools.propertyedit;

import com.hackerdude.tools.propertyedit.model.*;
import com.hackerdude.tools.propertyedit.editors.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Title:        Property Editor SwingUI
 * Description:  A Swing UI to edit properties files in a tree-like format.
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author David Martinez
 * @version 1.0
 */



public class PropertyItemEditorPanel extends JPanel {


	private ArrayList listeners = new ArrayList();

	private PropertyEditorNode itemNode;
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel lblNodeName = new JLabel();
	JPanel jPanel1 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JPanel pnlDescription = new JPanel();
	JPanel pnlValueEditor = new JPanel();
	JLabel lblNodeDescription = new JLabel();
	BorderLayout borderLayout2 = new BorderLayout();
	BorderLayout borderLayout3 = new BorderLayout();
	PropValueEditor currentEditor;

	public PropertyEditorNode getModel() {
		return itemNode;
	}

	public PropertyItemEditorPanel() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void setModel( PropertyEditorNode itemNode ) {
		// If there was already an editor open, save whatever you have.
		if ( currentEditor != null ) {
			applyChanges();
			if ( currentEditor != null ) pnlValueEditor.remove(currentEditor);
		}
		this.itemNode = itemNode;
		readNodeValues();
	}

	public PropertyEditorNode getNode() {
		return itemNode;
	}

	private void readNodeValues() {
		if ( itemNode == null ) return;
		lblNodeName.setText(itemNode.getNodeName());
		lblNodeDescription.setText("<HTML><P>"+itemNode.getNodeDescription());
		currentEditor = null;

		if ( itemNode.getDataTypeClass() != null ) {
			currentEditor = PropertyEditorFactory.createPropertyEditor(itemNode.getDataTypeClass());
			if ( currentEditor != null ) {
				currentEditor.setModel(itemNode.getNodeValue());
				pnlValueEditor.add(currentEditor, BorderLayout.CENTER);
				currentEditor.validate();
			}
		}

		pnlValueEditor.repaint();
		pnlValueEditor.validate();

	}

	private void jbInit() throws Exception {
		lblNodeName.setText("Node Name");
		this.setLayout(borderLayout1);
		jPanel2.setLayout(borderLayout3);
		jPanel2.setMinimumSize(new Dimension(200, 50));
		lblNodeDescription.setText("<HTML><P>Node Full Description");
		pnlDescription.setPreferredSize(new Dimension(200, 50));
		pnlValueEditor.setPreferredSize(new Dimension(200, 200));
		pnlValueEditor.setLayout(borderLayout2);
		this.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(lblNodeName, null);
		this.add(jPanel2, BorderLayout.CENTER);
		jPanel2.add(pnlDescription, BorderLayout.NORTH);
		jPanel2.add(pnlValueEditor, BorderLayout.CENTER);
		pnlDescription.add(lblNodeDescription, null);
	}

	public void addEditorPanelListener(PropertyEditorPanelListener listener) {
		listeners.add(listener);
	}

	public void removeEditorPanelListener(PropertyEditorPanelListener listener) {
		listeners.remove(listener);
	}

	public void notifyListeners() {
		Iterator iter = listeners.iterator();
		while (iter.hasNext() ) {
			PropertyEditorPanelListener listener = (PropertyEditorPanelListener)iter.next();
			listener.propertyItemChanged(itemNode);
		}
	}

	public void applyChanges() {
		if ( currentEditor == null ) return;
		Object editedValue = currentEditor.getEditedValue();
		if ( ! this.itemNode.getNodeValue().equals(editedValue) ) {
			this.itemNode.setNodeValue(editedValue);
			this.itemNode.setModified(true);
			notifyListeners();
		}

	}

}