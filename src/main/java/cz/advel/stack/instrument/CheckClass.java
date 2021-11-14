/*
 * JStackAlloc (c) 2008 Martin Dvorak <jezek2@advel.cz>
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from
 * the use of this software.
 * 
 * Permission is granted to anyone to use this software for any purpose, 
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 * 
 * 1. The origin of this software must not be misrepresented; you must not
 *    claim that you wrote the original software. If you use this software
 *    in a product, an acknowledgment in the product documentation would be
 *    appreciated but is not required.
 * 2. Altered source versions must be plainly marked as such, and must not be
 *    misrepresented as being the original software.
 * 3. This notice may not be removed or altered from any source distribution.
 */

package cz.advel.stack.instrument;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.EmptyVisitor;

/**
 *
 * @author jezek2
 */
class CheckClass implements ClassVisitor {

	public static EmptyVisitor EMPTY_VISITOR = new EmptyVisitor();
	
	final private Instrumenter instr;
	final private CheckMethod methodVisitor;

	CheckClass(Instrumenter instr) {
		this.instr = instr;
		methodVisitor = new CheckMethod(instr);
	}
	
        @Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
	}

        @Override
	public void visitSource(String source, String debug) {
	}

        @Override
	public void visitOuterClass(String owner, String name, String desc) {
	}

        @Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return EMPTY_VISITOR;
	}

        @Override
	public void visitAttribute(Attribute attr) {
	}

        @Override
	public void visitInnerClass(String name, String outerName, String innerName, int access) {
	}

        @Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		return EMPTY_VISITOR;
	}

        @Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		instr.setCurrentMethod(name);
		return methodVisitor;
	}

        @Override
	public void visitEnd() {
	}

}
