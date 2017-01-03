package romatthe.mlnes.cpu;

public interface Instruction {
    // Represents an instruction for the 6502 CPU.  The exec method implements the instruction
    // and returns the total clock cycles to be consumed by the instruction.
    int exec(CPU cpu);

    String getMneumonic();
    short getOpcode();
}
