package com.hackerdude.tools.propertyedit.editors;

import javax.swing.*;
import java.awt.*;


/**
 * Title:        Resource Changer Opentool
 * Description:  This opentool allows you to change the UI resources for the running JBuilder instance.
 * Copyright:    Copyright (c)
 * Company:
 * @author David Martinez <david@hackerdude.com>
 * @version 1.0
 */

public class ColorEditorPanel extends PropValueEditorBase {
	JColorChooser jColorChooser1 = new JColorChooser();

	public ColorEditorPanel() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		this.add(jColorChooser1, BorderLayout.CENTER);
	}

	public void readValue() {
		Color colorToEdit;
		if ( valueToEdit instanceof Color ) {
			jColorChooser1.setColor((Color)valueToEdit);
		}

	}

	public void writeValue() {
		valueToEdit = new Color(jColorChooser1.getColor().getRed(), jColorChooser1.getColor().getGreen(), jColorChooser1.getColor().getBlue());
	}

}