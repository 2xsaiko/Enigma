package cuchaz.enigma.gui.dialog.settings;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.tree.TreeNode;

import cuchaz.enigma.gui.keymap.KeyGroup;

public class KeymapGroupNode implements TreeNode {

	private final KeymapRootNode parent;

	private final KeyGroup group;

	private final List<KeymapBindingNode> children;

	public KeymapGroupNode(KeymapRootNode parent, KeyGroup group) {
		this.parent = parent;
		this.group = group;
		this.children = parent.keymap.bindings().stream().filter(b -> b.group == group).map(b -> new KeymapBindingNode(this, b)).collect(Collectors.toList());
	}

	@Override
	public KeymapBindingNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return this.children.size();
	}

	@Override
	public TreeNode getParent() {
		return this.parent;
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
	public Enumeration<KeymapBindingNode> children() {
		return Collections.enumeration(this.children);
	}

}
