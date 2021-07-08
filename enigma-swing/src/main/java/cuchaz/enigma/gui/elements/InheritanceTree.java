package cuchaz.enigma.gui.elements;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.google.common.collect.Lists;

import cuchaz.enigma.analysis.ClassInheritanceTreeNode;
import cuchaz.enigma.analysis.MethodInheritanceTreeNode;
import cuchaz.enigma.gui.Gui;
import cuchaz.enigma.gui.elements.rpanel.RPanel;
import cuchaz.enigma.gui.renderer.InheritanceTreeCellRenderer;
import cuchaz.enigma.gui.util.GuiUtil;
import cuchaz.enigma.gui.util.MouseListenerUtil;
import cuchaz.enigma.gui.util.SingleTreeSelectionModel;
import cuchaz.enigma.translation.representation.entry.ClassEntry;
import cuchaz.enigma.translation.representation.entry.Entry;
import cuchaz.enigma.translation.representation.entry.MethodEntry;
import cuchaz.enigma.utils.I18n;

public class InheritanceTree {
	private final RPanel panel = new RPanel();

	private final JTree tree = new JTree();

	private final Gui gui;

	public InheritanceTree(Gui gui) {
		this.gui = gui;

		this.tree.setModel(null);
		this.tree.setCellRenderer(new InheritanceTreeCellRenderer(this.gui));
		this.tree.setSelectionModel(new SingleTreeSelectionModel());
		this.tree.setShowsRootHandles(true);
		this.tree.addMouseListener(MouseListenerUtil.onClick(this::onClick));

		this.panel.getContentPane().setLayout(new BorderLayout());
		this.panel.getContentPane().add(new JScrollPane(this.tree));
	}

	private void onClick(MouseEvent event) {
		if (event.getClickCount() >= 2 && event.getButton() == MouseEvent.BUTTON1) {
			// get the selected node
			TreePath path = tree.getSelectionPath();
			if (path == null) {
				return;
			}

			Object node = path.getLastPathComponent();
			if (node instanceof ClassInheritanceTreeNode) {
				ClassInheritanceTreeNode classNode = (ClassInheritanceTreeNode) node;
				gui.getController().navigateTo(new ClassEntry(classNode.getObfClassName()));
			} else if (node instanceof MethodInheritanceTreeNode) {
				MethodInheritanceTreeNode methodNode = (MethodInheritanceTreeNode) node;
				if (methodNode.isImplemented()) {
					gui.getController().navigateTo(methodNode.getMethodEntry());
				}
			}
		}
	}

	public void display(Entry<?> entry) {
		this.tree.setModel(null);

		DefaultMutableTreeNode node = null;

		if (entry instanceof ClassEntry classEntry) {
			node = this.gui.getController().getClassInheritance(classEntry);
		} else if (entry instanceof MethodEntry methodEntry) {
			node = this.gui.getController().getMethodInheritance(methodEntry);
		}

		if (node != null) {
			// show the tree at the root
			TreePath path = GuiUtil.getPathToRoot(node);
			this.tree.setModel(new DefaultTreeModel((TreeNode) path.getPathComponent(0)));
			this.tree.expandPath(path);
			this.tree.setSelectionRow(this.tree.getRowForPath(path));
		}

		this.panel.show();
	}

	public void retranslateUi() {
		this.panel.setTitle(I18n.translate("info_panel.tree.inheritance"));
	}

	public RPanel getPanel() {
		return this.panel;
	}
}
