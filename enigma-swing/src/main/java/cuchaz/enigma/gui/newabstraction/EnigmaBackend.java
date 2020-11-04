package cuchaz.enigma.gui.newabstraction;

import java.nio.file.Path;
import java.util.Objects;

import cuchaz.enigma.Enigma;
import cuchaz.enigma.EnigmaProject;
import cuchaz.enigma.analysis.IndexTreeBuilder;
import cuchaz.enigma.classhandle.ClassHandle;
import cuchaz.enigma.classhandle.ClassHandleProvider;
import cuchaz.enigma.newabstraction.EntryChange;
import cuchaz.enigma.newabstraction.EntryUtil;
import cuchaz.enigma.translation.mapping.EntryMapping;
import cuchaz.enigma.translation.mapping.serde.MappingFormat;
import cuchaz.enigma.translation.representation.entry.Entry;
import cuchaz.enigma.utils.validation.ValidationContext;

public class EnigmaBackend implements MappingActionSink {

    public final Enigma enigma;

    public EnigmaProject project;
    private IndexTreeBuilder indexTreeBuilder;

    private Path loadedMappingPath;
    private MappingFormat loadedMappingFormat;

    private ClassHandleProvider chp;

    private ClassHandle tokenHandle;

    public EnigmaBackend(Enigma enigma) {
        this.enigma = enigma;
    }

    @Override
    public void apply(EntryChange<?> cs) {
        ValidationContext vc = new ValidationContext();
        Entry<?> target = cs.getTarget();
        EntryMapping prev = this.project.getMapper().getDeobfMapping(target);
        EntryMapping mapping = EntryUtil.applyChange(vc, this.project.getMapper(), cs);
        vc.throwOnError();

        boolean renamed = !cs.getDeobfName().isUnchanged();

        // if (renamed && target instanceof ClassEntry && !((ClassEntry) target).isInnerClass()) {
        //     this.gui.moveClassTree(target, prev.getTargetName() == null, mapping.getTargetName() == null);
        // }

        if (!Objects.equals(prev.getTargetName(), mapping.getTargetName())) {
            this.chp.invalidateMapped();
        }

        if (!Objects.equals(prev.getJavadoc(), mapping.getJavadoc())) {
            this.chp.invalidateJavadoc(target.getTopLevelClass());
        }
    }

}
