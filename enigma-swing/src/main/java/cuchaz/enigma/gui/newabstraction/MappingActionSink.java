package cuchaz.enigma.gui.newabstraction;

import cuchaz.enigma.newabstraction.EntryChange;

public interface MappingActionSink {

    void apply(EntryChange<?> cs);

}
