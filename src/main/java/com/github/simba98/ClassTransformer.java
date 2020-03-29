package com.github.simba98;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.Opcodes;

public class ClassTransformer implements IClassTransformer {
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (!"cam72cam.immersiverailroading.physics.PhysicsAccummulator".equals(transformedName))
            return basicClass;

        //使用ASM读入basicClass
        ClassReader cr = new ClassReader(basicClass);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        //遍历methods
        for (MethodNode mn : cn.methods) {
            //调用FML接口获得方法名，运行时获得的是srg，测试时获得的是mcp
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(name, mn.name, mn.desc);
            if(!"getVelocity".equals(methodName))
                continue;

            //TODO: 在这里进行ASM操作
            InsnList methodInstList = mn.instructions;


            AbstractInsnNode inst = methodInstList.getFirst();
            int num = methodInstList.size();
            for(int i = 0; i<num;i++){
                System.out.println(inst.getOpcode());
                if(inst instanceof FieldInsnNode) {
                    FieldInsnNode Finst1 = (FieldInsnNode)inst;
                    System.out.println(Finst1.name);
                    // We expect Finst1 is getfield of tractiveEffortNewtons
                    // and Finst2 is getfield of massToMoveKg, which is two insts after finst1
                    // and Dinst1 is Op DDIV, which one inst after finst2
                    // After checking these insts, we get the var number of DSTORE inst and insert invoke opcodes
                    if(Finst1.getOpcode() == Opcodes.GETFIELD) {
                        if(Finst1.name.equals("tractiveEffortNewtons")) {
                            if(Finst1.getNext().getNext() instanceof FieldInsnNode){
                                FieldInsnNode Finst2 = (FieldInsnNode)Finst1.getNext().getNext();
                                if(Finst2.getOpcode() == Opcodes.GETFIELD) {
                                    if(Finst2.name.equals("massToMoveKg")) {
                                        if(Finst2.getNext().getOpcode() == Opcodes.DDIV){
                                            if(Finst2.getNext().getNext() instanceof VarInsnNode) {
                                                // Insert insts
                                                VarInsnNode varinst = (VarInsnNode) Finst2.getNext().getNext();
                                                int varnumber = varinst.var;
                                                InsnList insertList = new InsnList();
                                                insertList.add(new VarInsnNode(Opcodes.DLOAD,varnumber));
                                                insertList.add(new MethodInsnNode(
                                                        Opcodes.INVOKESTATIC,
                                                        "com/github/simba98/Carminite_Ghastguard",
                                                        "AccellLimit",
                                                        "(D)D",
                                                        false));
                                                insertList.add(new VarInsnNode(Opcodes.DSTORE,varnumber));

                                                methodInstList.insert(varinst,insertList);
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                inst = inst.getNext();
            }
            mn.visitEnd();
        }

        //返回修改后的bytes
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cn.accept(cw);
        return cw.toByteArray();
    }

    public void Test () {
        double acc = 5.0;
        acc = Carminite_Ghastguard.AccellLimit(acc);
    }
}
