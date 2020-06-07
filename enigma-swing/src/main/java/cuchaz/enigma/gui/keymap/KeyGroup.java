package cuchaz.enigma.gui.keymap;

import cuchaz.enigma.utils.I18n;

public class KeyGroup {

	public static final KeyGroup GENERAL = new KeyGroup("general");

	public final String name;

	public KeyGroup(String localizationKey) {
		this.name = I18n.translate(String.format("keygroup.%s", localizationKey));
	}

}
