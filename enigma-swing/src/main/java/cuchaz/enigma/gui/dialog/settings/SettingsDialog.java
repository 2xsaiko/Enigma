package cuchaz.enigma.gui.dialog.settings;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;

import cuchaz.enigma.gui.dialog.AbstractValidatableDialog;
import cuchaz.enigma.gui.keymap.Keymap;

public class SettingsDialog extends AbstractValidatableDialog {

	private final JList<String> categories = new JList<>();
	private final JPanel generalPanel = new JPanel();
	private final JPanel keymapPanel = new JPanel();

	private final JTree keymapTree = new JTree();

	public SettingsDialog(Frame owner) {
		super(owner, "Settings", true);

		DefaultTreeModel tm = new DefaultTreeModel(new KeymapRootNode(Keymap.DEFAULT));
		this.keymapTree.setModel(tm);
		this.keymapTree.setCellRenderer(new KeymapTreeRenderer());
		this.keymapPanel.add(this.keymapTree);

		DefaultListModel<String> settings = new DefaultListModel<>();
		settings.addElement("General");
		settings.addElement("Keymap");
		this.categories.setModel(settings);

		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(this.categories, BorderLayout.WEST);

		this.categories.addListSelectionListener(_e -> this.updateSelectedPage());
	}

	private void updateSelectedPage() {
		String selectedValue = categories.getSelectedValue();
		if (selectedValue == null) return;
		this.getContentPane().remove(this.generalPanel);
		this.getContentPane().remove(this.keymapPanel);
		switch (selectedValue) {
			case "General":
				this.getContentPane().add(this.generalPanel, BorderLayout.CENTER);
				break;
			case "Keymap":
				this.getContentPane().add(this.keymapPanel, BorderLayout.CENTER);
				break;
		}
		this.getContentPane().validate();
		this.getContentPane().repaint();
	}

	@Override
	public void validate() {

	}

	@Override
	public void save() {

	}

	@Override
	public void reload() {

	}

}
