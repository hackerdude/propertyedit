package com.hackerdude.tools.propertyedit.editors;

import javax.swing.JPanel;

/**
 * Title:        Property Editor SwingUI
 * Description:  A Swing UI to edit properties files in a tree-like format.
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author David Martinez
 * @version 1.0
 */

public class PropertyEditorFactory extends JPanel {

	public PropertyEditorFactory() {
	}

	public static PropValueEditor createPropertyEditor(String dataTypeClass) {
		if ( dataTypeClass == null ) return null;
		if ( dataTypeClass.equals("java.awt.Color") || dataTypeClass.equals("javax.swing.plaf.ColorUIResource") ) {
			return new ColorEditorPanel();
		}
		else if ( dataTypeClass.equals("java.awt.Font") || dataTypeClass.equals("javax.swing.plaf.FontUIResource") ) {
			return new FontEditor();
		} else if ( dataTypeClass.equals("java.lang.String") ) {
			return new StringEditor();
		} else if ( dataTypeClass.equals("java.lang.Boolean") ) {
			return new BooleanValueEditor();
		}
		else {
			System.out.println("I don't know how to edit "+dataTypeClass);
			return null; //new PropValueEditor();
		}
	}

}