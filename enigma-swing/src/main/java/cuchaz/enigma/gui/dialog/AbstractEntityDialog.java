package cuchaz.enigma.gui.dialog;

import java.awt.Frame;

import cuchaz.enigma.gui.util.Entity;

public abstract class AbstractEntityDialog<T extends Entity> extends AbstractValidatableDialog {

	private T entity;

	public AbstractEntityDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	@Override
	public void reload() {
		// TODO check if entity has been modified, open change warning dialog
		this.entity = load();
		dataToUi();
	}

	@Override
	public final void validate() {
		dataFromUi();
		validateEntity();
		dataToUi();
	}

	@Override
	public final void save() {
		dataFromUi();
		this.entity.save();
	}

	protected abstract void validateEntity();

	protected abstract void dataToUi();

	protected abstract void dataFromUi();

	protected abstract T load();

	protected abstract void save(T entity);

}
