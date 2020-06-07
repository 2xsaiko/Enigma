package cuchaz.enigma.gui.keymap;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.KeyStroke;

public class Keymap {

	public static final Keymap DEFAULT;
	private final List<KeyGroup> groups;
	private final List<KeyBinding> bindings;

	public Keymap(List<KeyBinding> bindings) {
		this.groups = bindings.stream().map(b -> b.group).distinct().collect(Collectors.toList());
		this.bindings = bindings;
	}

	public List<KeyGroup> groups() {
		return Collections.unmodifiableList(this.groups);
	}

	public List<KeyBinding> bindings() {
		return Collections.unmodifiableList(this.bindings);
	}

	static {
		DEFAULT = new Keymap(Arrays.asList(
			new KeyBinding("kb.test", KeyGroup.GENERAL, KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.CTRL_DOWN_MASK))
		));
	}

}
