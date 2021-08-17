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

import org.objectweb.asm.*;

/**
 *
 * @author jezek2
 */
class CheckMethod implements MethodVisitor {

	final private Instrumenter instr;

	public CheckMethod(Instrumenter instr) {
		this.instr = instr;
	}

        @Override
	public AnnotationVisitor visitAnnotationDefault() {
		return CheckClass.EMPTY_VISITOR;
	}

        @Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return CheckClass.EMPTY_VISITOR;
	}

        @Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
		return CheckClass.EMPTY_VISITOR;
	}

        @Override
	public void visitAttribute(Attribute attr) {
	}

        @Override
	public void visitCode() {
	}

        @Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
	}

        @Override
	public void visitInsn(int opcode) {
	}

        @Override
	public void visitIntInsn(int opcode, int operand) {
	}

        @Override
	public void visitVarInsn(int opcode, int var) {
	}

        @Override
	public void visitTypeInsn(int opcode, String desc) {
	}

        @Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
	}

        @Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		// check for already instrumented code to obtain list of used stack types:
		if (opcode == Opcodes.INVOKEVIRTUAL && owner.equals(instr.getStackInternalName())) {
			if (name.startsWith("get")) {
				String type = Type.getReturnType(desc).getInternalName();
				instr.addStackType(type);
			}
		}
		
		// check if we need to instrument this method:
		if (opcode == Opcodes.INVOKESTATIC && owner.equals(Instrumenter.STACK_NAME)) {
			if (name.equals("alloc") || name.equals("libraryCleanCurrentThread")) {
				instr.addInstrumentMethod();
			}
		}
	}

        @Override
	public void visitJumpInsn(int opcode, Label label) {
	}

        @Override
	public void visitLabel(Label label) {
	}

        @Override
	public void visitLdcInsn(Object cst) {
	}

        @Override
	public void visitIincInsn(int var, int increment) {
	}

        @Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
	}

        @Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
	}

        @Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
	}

        @Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
	}

        @Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
	}

        @Override
	public void visitLineNumber(int line, Label start) {
	}

        @Override
	public void visitMaxs(int maxStack, int maxLocals) {
	}

        @Override
	public void visitEnd() {
	}
	
}
