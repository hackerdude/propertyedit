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

public class StringEditor extends PropValueEditorBase {
	JPanel jPanel1 = new JPanel();
	JLabel lblNewValue = new JLabel();
	JTextField txValueField = new JTextField();
	JPanel jPanel2 = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JPanel jPanel3 = new JPanel();
	GridBagLayout gridBagLayout1 = new GridBagLayout();

	public StringEditor() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		jPanel1.setLayout(gridBagLayout1);
		lblNewValue.setDisplayedMnemonic('V');
		lblNewValue.setLabelFor(txValueField);
		lblNewValue.setText("Value: ");
		this.setPreferredSize(new Dimension(100, 38));
		jPanel1.setMaximumSize(new Dimension(2147483647, 400));
		jPanel1.setPreferredSize(new Dimension(100, 21));
		jPanel2.setLayout(borderLayout1);
		this.add(jPanel1, BorderLayout.CENTER);
		jPanel1.add(jPanel2,        new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 25, 10, 25), 362, 0));
		jPanel2.add(lblNewValue,  BorderLayout.WEST);
		jPanel2.add(txValueField,  BorderLayout.CENTER);
		this.add(jPanel3,  BorderLayout.SOUTH);
	}

	/**
	 * Override this to set the display fields to the value.
	 */
	public void readValue() {
		if ( valueToEdit != null ) {
			txValueField.setText(valueToEdit.toString());
		}
	}

	/**
	 * Override this to write the display fields to the value object.
	 */
	public void writeValue() {
		valueToEdit = txValueField.getText();
	}


}