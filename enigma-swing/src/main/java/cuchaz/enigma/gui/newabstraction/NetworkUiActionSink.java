package cuchaz.enigma.gui.newabstraction;

import cuchaz.enigma.network.EnigmaClient;
import cuchaz.enigma.network.packet.EntryChangeC2SPacket;
import cuchaz.enigma.newabstraction.EntryChange;

public class NetworkUiActionSink implements MappingActionSink {

    private EnigmaClient client;

    @Override
    public void apply(EntryChange<?> cs) {
        this.client.sendPacket(new EntryChangeC2SPacket(cs));
    }

}
