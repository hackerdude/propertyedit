package com.hackerdude.tools.propertyedit;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;


/**
 * This sample application illustrates how to use the
 * Property Editor.
 */
public class PropertyEditApp {
	boolean packFrame = false;
	private PropertyEditorFrame propertyEditorFrame;

	/**Construct the application*/
	public PropertyEditApp() {
		propertyEditorFrame = new PropertyEditorFrame();
		//Validate frames that have preset sizes
		//Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame) {
			propertyEditorFrame.pack();
		}
		else {
			propertyEditorFrame.validate();
		}
		//Center the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = propertyEditorFrame.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		propertyEditorFrame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		propertyEditorFrame.setVisible(true);
	}
	/**Main method*/
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		PropertyEditApp propertyEditApp = new PropertyEditApp();
		if (args.length>0 ) {
			String fileName = args[0];
			propertyEditApp.propertyEditorFrame.openFile(fileName);
		}
		
	}
}