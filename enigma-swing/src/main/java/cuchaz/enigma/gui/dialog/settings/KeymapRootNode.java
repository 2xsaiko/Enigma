package cuchaz.enigma.gui.dialog.settings;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.tree.TreeNode;

import cuchaz.enigma.gui.keymap.Keymap;

public class KeymapRootNode implements TreeNode {

	public final Keymap keymap;

	private final List<KeymapGroupNode> children;

	public KeymapRootNode(Keymap keymap) {
		this.keymap = keymap;
		this.children = keymap.groups().stream().map(g -> new KeymapGroupNode(this, g)).collect(Collectors.toList());
	}

	@Override
	public KeymapGroupNode getChildAt(int childIndex) {
		return this.children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return this.children.size();
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return this.children.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public Enumeration<KeymapGroupNode> children() {
		return Collections.enumeration(this.children);
	}

}
