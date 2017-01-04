package romatthe.mlnes.cpu.instructions;

import romatthe.mlnes.cpu.CPU;
import romatthe.mlnes.cpu.CPUAddressingMode;

import java.util.function.Function;

public class Instruction {

    private final short opcode;
    private final String mneumonic;
    private final Function<CPU, Integer> execFunc;  // Represents an instruction for the 6502 CPU.

    public Instruction(short opcode, String mneumonic, Function<CPU, Integer> execFunc) {
        this.opcode = opcode;
        this.mneumonic = mneumonic;
        this.execFunc = execFunc;
    }

    public short getOpcode() {
        return opcode;
    }

    public String getMneumonic() {
        return mneumonic;
    }

    // The exec method applies instruction and returns the total clock cycles to be consumed by the instruction.
    int exec(CPU cpu) {
        return this.execFunc.apply(cpu);
    }

}
