package romatthe.mlnes.cpu;

public class CPU {

    private Clock clock;
    private Registers registers;
    private Memory memory;
    private InstructionTable instructionTable;
    private int cyclesToWait;

    public void step() {
        // Process the cycles left to wait
        if (this.cyclesToWait > 0) {
            this.cyclesToWait--;
            return;
        }

        // Fetch the next opcode
        short opcode = this.memory.fetch(this.registers.getProgramCounter());

        // Get the instruction from the InstructionsTable based on the opcode
        Instruction instruction = this.instructionTable.getInstruction(opcode);

        // Excecute, exec() to get the number of cycles consumed
        int cycles = instruction.exec(this);

        //TODO: match up cyclesToWait with required cycles
    }
}
