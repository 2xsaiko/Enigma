package cuchaz.enigma.gui.dialog.settings;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.TreeCellRenderer;

public class KeymapTreeRenderer implements TreeCellRenderer {

	private final JLabel comp = new JLabel("test");

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		return comp;
	}

}
