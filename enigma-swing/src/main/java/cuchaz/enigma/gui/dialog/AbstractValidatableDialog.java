package cuchaz.enigma.gui.dialog;

import java.awt.*;
import java.awt.event.ItemEvent;

import javax.swing.*;

import cuchaz.enigma.gui.elements.MessageList;
import cuchaz.enigma.gui.util.GridBagConstraintsBuilder;
import cuchaz.enigma.gui.util.ScaleUtil;
import cuchaz.enigma.utils.validation.ValidationContext;

public abstract class AbstractValidatableDialog {

	protected final ValidationContext vc = new ValidationContext();

	private final JDialog ui;

	private final JPanel innerContentPane = new JPanel();
	private final MessageList messageList = new MessageList();

	private final JPanel buttonBar = new JPanel(new GridBagLayout());
	private final JToggleButton validationDockButton = new JToggleButton("V");
	private final JButton validateButton = new JButton("Validate");
	private final JButton reloadButton = new JButton("Reload");
	private final JButton saveButton = new JButton("Save");
	private final JButton cancelButton = new JButton("Cancel");

	public AbstractValidatableDialog(Frame owner, String title, boolean modal) {
		this.ui = new JDialog(owner, title, modal);

		Container contentPane = this.ui.getContentPane();
		contentPane.setLayout(new BorderLayout());

		this.innerContentPane.setBorder(BorderFactory.createLoweredBevelBorder());
		contentPane.add(this.innerContentPane, BorderLayout.CENTER);

		GridBagConstraintsBuilder cb = GridBagConstraintsBuilder.create().insets(4);
		this.buttonBar.add(this.validationDockButton, cb.pos(0, 0).anchor(GridBagConstraints.WEST).weight(1, 0).build());
		this.buttonBar.add(this.validateButton, cb.pos(1, 0).build());
		this.buttonBar.add(this.reloadButton, cb.pos(2, 0).build());
		this.buttonBar.add(this.saveButton, cb.pos(3, 0).build());
		this.buttonBar.add(this.cancelButton, cb.pos(4, 0).build());
		contentPane.add(this.buttonBar, BorderLayout.SOUTH);
		contentPane.add(this.messageList.getUi(), BorderLayout.WEST);

		this.setDockState(this.validationDockButton.isSelected());

		this.validationDockButton.addItemListener(e -> this.setDockState(e.getStateChange() == ItemEvent.SELECTED));
		this.validateButton.addActionListener(_e -> this.doValidate());
		this.reloadButton.addActionListener(_e -> this.doReload());
		this.saveButton.addActionListener(_e -> this.doSave());
		this.cancelButton.addActionListener(_e -> this.doCancel());

		this.ui.setSize(ScaleUtil.getDimension(640, 480));
		this.ui.setLocationRelativeTo(owner);
	}

	public void show() {
		this.ui.setVisible(true);
	}

	public void hide() {
		this.ui.setVisible(false);
	}

	public abstract void validate();

	public abstract void save();

	public abstract void reload();

	private void setDockState(boolean open) {
		this.messageList.getUi().setVisible(open);
	}

	private void doReload() {
		this.vc.reset();
		this.reload();
	}

	private void doValidate() {
		this.vc.reset();
		this.validate();
	}

	private void doSave() {
		this.vc.reset();
		this.validate();
		if (!this.vc.canProceed()) return;
		this.save();
		if (!this.vc.canProceed()) return;
		this.ui.setVisible(false);
		this.ui.dispose();
	}

	private void doCancel() {
		this.reload();
		this.close();
	}

	protected final void close() {
		this.close0();
	}

	private void close0() {
		this.ui.setVisible(false);
		this.ui.dispose();
	}

	protected final Container getContentPane() {
		return this.innerContentPane;
	}

}
