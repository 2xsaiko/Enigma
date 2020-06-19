package cuchaz.enigma.gui.newabstraction;

import cuchaz.enigma.network.EnigmaClient;
import cuchaz.enigma.network.EnigmaServer;
import cuchaz.enigma.network.packet.ChangeDocsC2SPacket;
import cuchaz.enigma.network.packet.RemoveMappingC2SPacket;
import cuchaz.enigma.network.packet.RenameC2SPacket;
import cuchaz.enigma.newabstraction.EntryChange;

public class NetworkUiActionSink implements MappingActionSink {

    private EnigmaClient client;

    @Override
    public void apply(EntryChange<?> cs) {
        // this might cause issues when multiple fields are changed at the same
        // time, but that can't happen right now

        if (cs.getJavadoc().isSet()) {
            this.client.sendPacket(new ChangeDocsC2SPacket(cs.getTarget(), cs.getJavadoc().getNewValue()));
        } else if (cs.getJavadoc().isReset()) {
            this.client.sendPacket(new ChangeDocsC2SPacket(cs.getTarget(), ""));
        }

        if (cs.getDeobfName().isSet()) {
            this.client.sendPacket(new RenameC2SPacket(cs.getTarget(), cs.getDeobfName().getNewValue(), true));
        } else if (cs.getDeobfName().isReset()) {
            this.client.sendPacket(new RemoveMappingC2SPacket(cs.getTarget()));
        }
    }

}
