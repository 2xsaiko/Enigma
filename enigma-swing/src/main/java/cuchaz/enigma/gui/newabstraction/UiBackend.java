package cuchaz.enigma.gui.newabstraction;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.swing.tree.DefaultMutableTreeNode;

import cuchaz.enigma.analysis.*;
import cuchaz.enigma.classhandle.ClassHandle;
import cuchaz.enigma.newabstraction.EntryChange;
import cuchaz.enigma.source.Token;
import cuchaz.enigma.translation.mapping.EntryMapping;
import cuchaz.enigma.translation.representation.entry.ClassEntry;
import cuchaz.enigma.translation.representation.entry.Entry;
import cuchaz.enigma.translation.representation.entry.FieldEntry;
import cuchaz.enigma.translation.representation.entry.MethodEntry;
import cuchaz.enigma.utils.validation.ValidationContext;

public interface UiBackend {

	void applyChange(ValidationContext vc, EntryChange<?> cs);

	void validateChange(ValidationContext vc, EntryChange<?> cs);

	boolean isDirty();

	<T extends Entry<?>> T deobfToObf(T deobf);

	/**
	 * Save the currently loaded mappings to the specified directory.
	 *
	 * @param dir the directory to save to
	 * @return a future running the saving operation
	 */
	CompletableFuture<Void> saveMappings(Path dir);

	/**
	 * Returns the mapping for the specified entry.
	 *
	 * @param entry the obfuscated entry
	 * @return the mapping
	 */
	EntryMapping getMapping(Entry<?> entry);

	StructureTreeNode getClassStructure(ClassEntry classEntry, boolean shouldHideDeobfuscated);

	ClassInheritanceTreeNode getClassInheritance(ClassEntry entry);

	MethodInheritanceTreeNode getMethodInheritance(MethodEntry entry);

	DefaultMutableTreeNode getClassImplementations(ClassEntry entry);

	DefaultMutableTreeNode getMethodImplementations(MethodEntry entry);

	ClassReferenceTreeNode getClassReferences(ClassEntry entry);

	FieldReferenceTreeNode getFieldReferences(FieldEntry entry);

	MethodReferenceTreeNode getMethodReferences(MethodEntry entry, boolean recurse);

	boolean isRenamable(Entry<?> e);

	default boolean isRenamable(EntryReference<?, ?> ref) {
		// TODO is a more specific impl of this necessary?
		return this.isRenamable(ref.getNameableEntry());
	}

	/**
	 * Searches for references to the specified entry.
	 *
	 * @param ch  the class handle to search in
	 * @param ref the entry to search for
	 * @return the references represented as a list of tokens
	 */
	List<Token> findReferences(ClassHandle ch, EntryReference<Entry<?>, Entry<?>> ref);

}
