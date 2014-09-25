package edu.columbia.psl.cc.inst;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.util.CheckClassAdapter;

import edu.columbia.psl.cc.annot.analyzeClass;
import edu.columbia.psl.cc.annot.extractTemplate;
import edu.columbia.psl.cc.annot.testTemplate;

public class MIBClassFileTransformer implements ClassFileTransformer {
	
	private static String classAnnot = Type.getType(analyzeClass.class).getDescriptor();
	
	private static String templateAnnot = Type.getType(extractTemplate.class).getDescriptor();
	
	private static String testAnnot = Type.getType(testTemplate.class).getDescriptor();

	@Override
	public byte[] transform(ClassLoader loader, 
			String className,
			Class<?> classBeingRedefined, 
			ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		// TODO Auto-generated method stub
		
		String name = className.replace("/", ".");
		System.out.println("Class name: " + name);
		if (!name.startsWith("java") && !name.startsWith("sun")) {
			//Start the instrumentation here;
			try {
				ClassReader cr = new ClassReader(name);
				ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
				ClassMiner cm = new ClassMiner(new CheckClassAdapter(cw, false), 
						name.replace(".", "/"), classAnnot, templateAnnot, testAnnot);
				cr.accept(cm, 0);
				System.out.println("After accept");
				return cw.toByteArray();
			} catch (Exception ex) {
				System.out.println("In the exception");
				ex.printStackTrace();
			}
		}
		return classfileBuffer;
	}

	

}
