package com.hackerdude.tools.propertyedit.editors;

import javax.swing.*;
import java.awt.*;

/**
 * Title:        Property Editor SwingUI
 * Description:  A Swing UI to edit properties files in a tree-like format.
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author David Martinez
 * @version 1.0
 */

public class BooleanValueEditor extends PropValueEditorBase {
	JPanel jPanel1 = new JPanel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();
	JCheckBox cbSelected = new JCheckBox();

	public BooleanValueEditor() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		jPanel1.setLayout(gridBagLayout1);
		cbSelected.setText("Select");
		this.add(jPanel1, BorderLayout.CENTER);
		jPanel1.add(cbSelected,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 10, 0, 10), 0, 0));
	}

	/**
	 * Override this to set the display fields to the value.
	 */
	public void readValue() {
		if ( valueToEdit != null ) {
			cbSelected.setSelected(((Boolean)valueToEdit).booleanValue());
		}
	}

	/**
	 * Override this to write the display fields to the value object.
	 */
	public void writeValue() {
		valueToEdit = new Boolean(cbSelected.isSelected());
	}



}