package cuchaz.enigma.gui.dialog.settings;

import java.util.Collections;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import cuchaz.enigma.gui.keymap.KeyBinding;

public class KeymapBindingNode implements TreeNode {

	private final KeymapGroupNode parent;

	private final KeyBinding kb;

	public KeymapBindingNode(KeymapGroupNode parent, KeyBinding kb) {
		this.parent = parent;
		this.kb = kb;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return null;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
	}

	@Override
	public int getIndex(TreeNode node) {
		return -1;
	}

	@Override
	public boolean getAllowsChildren() {
		return false;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public Enumeration<? extends TreeNode> children() {
		return Collections.emptyEnumeration();
	}

}
