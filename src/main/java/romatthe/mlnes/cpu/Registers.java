package romatthe.mlnes.cpu;

public class Registers {
    private short accumulator;          // Accumulator (8bit)
    private short registerX;            // Index Register X (8bit)
    private short registerY;            // Index Register Y (8bit)
    private short stackPointer;         // Stack Pointer (8bit)
    private int programCounter;         // Program Counter (16bit)
    private CPUStatus processorStatus;

    public Registers() {
        this.accumulator    = 0x0;
        this.registerX      = 0x0;
        this.registerY      = 0x0;
        this.stackPointer   = 0x0;
        this.programCounter = 0x0;
    }

    public short getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(short accumulator) {
        this.accumulator = accumulator;
    }

    public short getRegisterX() {
        return registerX;
    }

    public void setRegisterX(short registerX) {
        this.registerX = registerX;
    }

    public short getRegisterY() {
        return registerY;
    }

    public void setRegisterY(short registerY) {
        this.registerY = registerY;
    }

    public short getStackPointer() {
        return stackPointer;
    }

    public void setStackPointer(short stackPointer) {
        this.stackPointer = stackPointer;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public CPUStatus getProcessorStatus() {
        return processorStatus;
    }

    public void setProcessorStatus(CPUStatus processorStatus) {
        this.processorStatus = processorStatus;
    }

    public void incrementProgramCounter() {
        this.programCounter++;
    }

    public void reset(int resetProgramCounter) {
        this.setProgramCounter(resetProgramCounter);
        this.setStackPointer((short)0xFD);
        this.setAccumulator((short)0x0);
        this.setRegisterX((short)0x0);
        this.setRegisterY((short)0x0);
    }
}
