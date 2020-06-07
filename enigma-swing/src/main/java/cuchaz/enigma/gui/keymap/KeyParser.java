package cuchaz.enigma.gui.keymap;

import java.awt.event.KeyEvent;

import javax.annotation.Nullable;
import javax.swing.KeyStroke;

public final class KeyParser {

	private KeyParser() {
	}

	public static String toString(KeyStroke ks) {
		String modifiersText = KeyEvent.getModifiersExText(ks.getModifiers());
		String keyText = KeyEvent.getKeyText(ks.getKeyCode());
		if (!modifiersText.isEmpty()) {
			return String.format("%s+%s", modifiersText, keyText);
		} else {
			return keyText;
		}
	}

	@Nullable
	public static KeyStroke fromString(String s) {
		return null;
	}

}
