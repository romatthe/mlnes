package romatthe.mlnes.cpu;

import romatthe.mlnes.cpu.instructions.InstructionStatus;

public class CPU {

    private final int PAGE_CROSS = 1;
    private final int BRANCHED = 2;

    // NES Memory Layout
    // ======
    // 0x100 => Zero Page
    // 0x200 => Stack
    // 0x800 => RAM
    // 0x2000 => Mirrors (0-0x7FF)
    // 0x2008 => I/O Registers
    // 0x4000 => Mirrors (0x2000-0x2007)
    // 0x4020 => I/O Registers
    // 0x6000 => Expansion ROM
    // 0x8000 => SRAM
    // 0xC000 => PRG-ROM (Lower Bank)
    // 0x10000 => PRG-ROM (Upper Bank)
    private MemoryBasic memory = new MemoryBasic(0x10000);

    // Registers
    // =========
    private Registers registers = new Registers();

    // Processor Status
    // =========
    // 0 => Carry (if last instruction resulted in under/overflow)
    // 1 => Zero (if last instruction's result was 0)
    // 2 => Interrupt Disable (Enable to prevent system from responding to interrupts)
    // 3 => Decimal mode (unsupported on this chip variant)
    // 4 => Empty
    // 5 => Empty
    // 6 => Overflow (if previous instruction resulted in an invalid two's complement)
    // 7 => Negative
    private short status  = 0x0;

    // Part of the Processor Status register
    // Separated for convenience.
    private CPUFlags flags = new CPUFlags();

    // Maskable Interrupt
    // One of "irq/brk", "nmi", "reset"
    private String interrupt = null;

    public Memory getMemory() {
        return this.memory;
    }

    public CPUFlags getFlags() {
        return this.flags;
    }

    public Registers getRegisters() {
        return this.registers;
    }

    public short getStatus() {
        return this.status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void reset() {
        this.memory.reset();
        this.flags.reset();
        this.registers.reset(this.getResetVector());
        this.status = this.flags.getProcessorFlags();
    }

    // Find where the program begins
    private int getResetVector() {
        return this.memory.fetch(0xfffc);
    };

    private void step() {
        // TODO CPU logic goes here? ┐(￣ヘ￣;)┌
    }

    // Immediate Addressing Mode
    public int immediateAddress() {
        int result = this.registers.getProgramCounter();
        this.registers.incrementProgramCounter();

        return result;
    }

    public int zeroPageAddress() {
        short result = this.memory.fetch(this.registers.getProgramCounter());
        this.registers.incrementProgramCounter();

        return result;
    }

    public int zeroPageIndexedAddress(short index) {
        short value = this.memory.fetch(this.registers.getProgramCounter());
        int result = value + this.indexToRegister(index);
        this.registers.incrementProgramCounter();

        return result;
    }

    public int relativeAddress() {
        short value = this.memory.fetch(this.registers.getProgramCounter());
        this.registers.incrementProgramCounter();

        int offset = 0x0;

        if (value > 0x7f) {
            offset = -(0x0100 - value);
        } else {
            offset = value;
        }

        return this.registers.getProgramCounter() + offset;
    }

    public int absoluteAddress() {
        int low = this.memory.fetch(this.registers.getProgramCounter());
        int high = this.memory.fetch(this.registers.getProgramCounter() + 1);
        this.registers.setProgramCounter(this.registers.getProgramCounter() + 2);

        return (high << 8) | low;
    }

    public int indirectAddress() {
        int low = this.memory.fetch(this.registers.getProgramCounter());
        int high = this.memory.fetch(this.registers.getProgramCounter() + 1);
        this.registers.setProgramCounter(this.registers.getProgramCounter() + 2);

        // XXX: The 6502 had a bug in which it incremented only the
        // high byte instead of the whole 16-bit address when computing the address.
        //
        // See http://www.obelisk.demon.co.uk/6502/reference.html#JMP
        // and http://www.6502.org/tutorials/6502opcodes.html#JMP for details
        int aHigh = high << 8 | low + 1;
        int aLow = high << 8 | low;

        low = this.memory.fetch(aLow);
        high = this.memory.fetch(aHigh);

        return high << 8 | low;
    }

    public int absoluteIndexedAddress(short index, InstructionStatus instructionStatus) {
        int low = this.memory.fetch(this.registers.getProgramCounter());
        int high = this.memory.fetch(this.registers.getProgramCounter() + 1);
        this.registers.setProgramCounter(this.registers.getProgramCounter() + 2);

        int address = high << 8 | low;
        int result = address + this.indexToRegister(index);

        if (instructionStatus != null && !this.memory.isSamePage(address, result)) {
            instructionStatus.setStatus(instructionStatus.getStatus() | PAGE_CROSS);
        }

        return result;
    }

    public int indexedIndirectAddress() {
        short value = this.memory.fetch(this.registers.getProgramCounter());
        int address = value + this.registers.getRegisterX();
        this.registers.incrementProgramCounter();

        int low = this.memory.fetch(address);
        int high = this.memory.fetch((address + 1) & 0x00ff);

        return high << 8 | low;
    }

    public int indirectIndexedAddress(InstructionStatus instructionStatus) {
        int address = this.memory.fetch(this.registers.getProgramCounter());
        this.registers.incrementProgramCounter();

        int low = this.memory.fetch(address);
        int high = this.memory.fetch((address + 1) & 0x00ff);

        address = high << 8 | low;

        int result = address + this.registers.getRegisterY();

        if (instructionStatus != null && !this.memory.isSamePage(address, result)) {
            instructionStatus.setStatus(instructionStatus.getStatus() | PAGE_CROSS);
        }

        return result;
    }


    public short indexToRegister(short index) {
        if (index == this.registers.getRegisterX()) {
            return this.registers.getRegisterX();
        }
        else if (index == this.registers.getRegisterY()) {
            return this.registers.getRegisterY();
        }

        return 0x00;
    }

}
