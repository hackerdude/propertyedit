package com.hackerdude.tools.propertyedit.editors;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Title:        Resource Changer Opentool
 * Description:  This opentool allows you to change the UI resources for the running JBuilder instance.
 * Copyright:    Copyright (c)
 * Company:
 * @author David Martinez <david@hackerdude.com>
 * @version 1.0
 */

public class FontEditor extends PropValueEditorBase {

	static final String[] fontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	FontStyleModel styleList = new FontStyleModel();
	FontSizeModel  sizeList  = new FontSizeModel();

	Font fontToEdit;
	JSplitPane splitEditorView = new JSplitPane();
	BorderLayout brdrFontName = new BorderLayout();
	JPanel pnlFontNamePanel = new JPanel();
	JSplitPane splitfontNamesStats = new JSplitPane();
	JLabel lblSampleFont = new JLabel();
	JPanel pnlSampleFontDisplay = new JPanel();
	BorderLayout borderLayout1 = new BorderLayout();
	JList lstAvailableFontNames = new JList(fontFamilyNames);
	JScrollPane scrollFontNames = new JScrollPane();
	JPanel pnlStyleSize = new JPanel();
	JPanel pnlScrollStyleNames = new JPanel();
	JPanel pnlFontSizes = new JPanel();
	JList lstFontSizes = new JList();
	BorderLayout brdrFontSizes = new BorderLayout();
	JScrollPane scrollStyleNames = new JScrollPane();
	BorderLayout brdrStyleNames = new BorderLayout();
	JScrollPane scrollFontSizes = new JScrollPane();
	JSplitPane splitStyleSize = new JSplitPane();
	JList lstStyleNames = new JList();
	BorderLayout brdrStyleSize = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	JPanel jPanel1 = new JPanel();
	JLabel lblEditFont = new JLabel();
	BorderLayout borderLayout3 = new BorderLayout();

	public FontEditor() {
		try {
			jbInit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {

		pnlFontNamePanel.setLayout(brdrFontName);
		lblSampleFont.setHorizontalAlignment(SwingConstants.CENTER);
		lblSampleFont.setText("A B C D E a b c d e 1 2 3 4 5 $%^");

		lblEditFont.setText("Font Selection");
		jPanel1.setLayout(borderLayout3);
		this.setMinimumSize(new Dimension(250, 98));
		this.setPreferredSize(new Dimension(250, 98));
		splitEditorView.setDividerSize(2);
		splitfontNamesStats.setDividerSize(2);
		splitStyleSize.setDividerSize(2);
		pnlScrollStyleNames.setMaximumSize(new Dimension(2147483647, 50));
		scrollFontSizes.getViewport().add(lstFontSizes);
		scrollStyleNames.getViewport().add(lstStyleNames, null);
		scrollFontNames.getViewport().add(lstAvailableFontNames, null);

		pnlFontSizes.setLayout(brdrFontSizes);
		pnlSampleFontDisplay.setLayout(borderLayout2);
		pnlScrollStyleNames.setLayout(brdrStyleNames);
		pnlScrollStyleNames.add(scrollStyleNames, BorderLayout.CENTER);

		pnlFontNamePanel.add(scrollFontNames, BorderLayout.CENTER);

		pnlStyleSize.setLayout(brdrStyleSize);
		pnlStyleSize.add(splitStyleSize,  BorderLayout.CENTER);

		splitEditorView.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitEditorView.add(splitfontNamesStats, JSplitPane.TOP);
		splitStyleSize.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitStyleSize.setDividerLocation(150);
		splitStyleSize.add(pnlScrollStyleNames, JSplitPane.RIGHT);

		splitStyleSize.add(pnlFontSizes, JSplitPane.LEFT);
		pnlFontSizes.add(scrollFontSizes, BorderLayout.CENTER);
		splitEditorView.add(pnlSampleFontDisplay, JSplitPane.BOTTOM);
		pnlSampleFontDisplay.add(lblSampleFont,  BorderLayout.NORTH);
		this.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(lblEditFont, BorderLayout.CENTER);
		splitfontNamesStats.setDividerLocation(200);

		splitfontNamesStats.add(pnlFontNamePanel, JSplitPane.LEFT);
		splitfontNamesStats.add(pnlStyleSize, JSplitPane.RIGHT);


		this.add(splitEditorView,  BorderLayout.CENTER);

		lstFontSizes.setModel(sizeList);
		lstStyleNames.setModel(styleList);
		lstAvailableFontNames.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Font newFont = new Font(lstAvailableFontNames.getSelectedValue().toString(), fontToEdit.getStyle(), fontToEdit.getSize());
				setFontToEdit(newFont);
			}
		});
		lstFontSizes.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Font newFont = new Font(fontToEdit.getName(), fontToEdit.getStyle(), ((Integer)lstFontSizes.getSelectedValue()).intValue());
				setFontToEdit(newFont);
			}
		});
		lstStyleNames.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Font newFont = new Font(fontToEdit.getName(), getStyleFor(lstStyleNames.getSelectedValue().toString()), fontToEdit.getSize());
				setFontToEdit(newFont);
			}
		});
splitEditorView.setDividerLocation(200);

	}


	public class FontStyleModel extends AbstractListModel {

		public FontStyleModel() {
		}

		public int getSize() {
			return 4;
		}

		public Object getElementAt(int index) {
			switch ( index ) {
				case Font.BOLD:  return "Bold";
				case Font.PLAIN: return "Plain";
				case Font.ITALIC: return "Italic";
				default:
				if ( index == ( Font.BOLD + Font.ITALIC ) ) return "Bold, Italic";
				else return null;
			}
		}


	}


	public class FontSizeModel extends AbstractListModel {

		private final int SIZES = 20;
		private final int MIN_SIZE = 6;
		private final int SIZE_DELTA = 2;

		public FontSizeModel() {
		}

		public int getSize() {
			return SIZES;
		}

		public Object getElementAt(int index) {
			return new Integer(MIN_SIZE+(index*SIZE_DELTA));
		}

	}

	public void writeValue() {
		valueToEdit = fontToEdit;
	}

	public void readValue() {
		setFontToEdit((Font)valueToEdit);

	}

	public void setFontToEdit(Font font) {
//		System.out.println("Setting font...");
		fontToEdit = font;
		lstAvailableFontNames.setSelectedValue(font.getFamily(), true);
		lblSampleFont.setFont(fontToEdit);

	}


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		FontEditor fEditor = new FontEditor();
		fEditor.setFontToEdit(fEditor.getFont());
		frame.getContentPane().add(fEditor);
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});
		frame.show();

	}

		public static int getStyleFor(String styleName) {
			if ( styleName.equalsIgnoreCase("Bold") ) return Font.BOLD;
			if ( styleName.equalsIgnoreCase("Plain") ) return Font.PLAIN;
			if ( styleName.equalsIgnoreCase("Italic") ) return Font.ITALIC;
			else return -1;
		}
	void lstFontSizes_valueChanged(ListSelectionEvent e) {

	}
	void lstStyleNames_valueChanged(ListSelectionEvent e) {

	}


}