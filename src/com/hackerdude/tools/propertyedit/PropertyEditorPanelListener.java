package com.hackerdude.tools.propertyedit;

import com.hackerdude.tools.propertyedit.model.PropertyEditorNode;

/**
 * Title:        Resource Changer Opentool
 * Description:  This opentool allows you to change the UI resources for the running JBuilder instance.
 * Copyright:    Copyright (c)
 * Company:
 * @author David Martinez <david@hackerdude.com>
 * @version 1.0
 */

public interface PropertyEditorPanelListener {

	public void propertyItemChanged(PropertyEditorNode itemChanged);

}