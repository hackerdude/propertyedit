package com.hackerdude.tools.propertyedit.editors;

import java.awt.*;
import javax.swing.*;

/**
 * Title:        Property Editor SwingUI
 * Description:  A Swing UI to edit properties files in a tree-like format.
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author David Martinez
 * @version 1.0
 */

public abstract class PropValueEditor extends JPanel {
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	BorderLayout borderLayout2 = new BorderLayout();

	Object valueToEdit;

	public PropValueEditor() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception {
		this.setLayout(borderLayout1);
		jPanel1.setLayout(borderLayout2);
		this.add(jPanel1, BorderLayout.NORTH);
	}

	public void setModel(Object valueToEdit) {
		this.valueToEdit = valueToEdit;
		readValue();
	}

	/**
	 * Return the edited value.
	 */
	public Object getEditedValue() {
		writeValue();
		return valueToEdit;
	}

	/**
	 * Override this to set the display fields to the value.
	 */
	public abstract void readValue();

	/**
	 * Override this to write the display fields to the value object.
	 */
	public abstract void writeValue();


}