package cuchaz.enigma.gui.util;

public interface Entity<Self> {

	boolean isChanged();

	void setChanged(boolean changed);

	default void compareAndSetChanged(Self other) {
		setChanged(!this.equals(other));
	}

	void save();

	Self copy();

}
