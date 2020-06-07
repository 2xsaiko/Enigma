package cuchaz.enigma.gui.keymap;

import javax.swing.KeyStroke;

import cuchaz.enigma.utils.I18n;

public class KeyBinding {

	public final String text;
	public final KeyGroup group;
	public final KeyStroke defaultKeyStroke;
	private KeyStroke keyStroke;

	public KeyBinding(String localizationKey, KeyGroup group, KeyStroke defaultKeyStroke) {
		this.text = I18n.translate(localizationKey);
		this.group = group;
		this.defaultKeyStroke = defaultKeyStroke;
		this.keyStroke = defaultKeyStroke;
	}

}
