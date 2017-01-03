package romatthe.mlnes.cpu;

public class CPU {

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
    private short[] memory = new short[0x10000];

    // Registers
    // =========
    private int programCounter =    0x0;    // Program Counter (16bit)
    private short stackPointer =    0x0;    // Stack Pointer (8bit)
    private short registerA =       0x0;    // Accumulator (8bit)
    private short registerX =       0x0;    // Index Register X (8bit)
    private short registerY =       0x0;    // Index Register Y (8bit)

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
    private byte status = 0x0;

    // Part of the Processor Status register
    // Separated for convenience.
    private CPUFlags flags = new CPUFlags();

    // Maskable Interrupt
    // One of "irq/brk", "nmi", "reset"
    private String interrupt = null;

    public void reset() {
        this.resetMemory(); //TODO Split into seperate method after memory is seperate object
        this.flags.reset();

        this.programCounter = this.getResetVector();
        this.stackPointer = 0xFD;

        this.registerA = 0x0;
        this.registerX = 0x0;
        this.registerY = 0x0;

        this.status = this.flags.getProcessorFlags();

    }

    private void resetMemory() {
        this.memory = new short[0x10000];

        // Set 2kb Internal RAM
        for (int i = 0; i <= 0x2000; i++) {
            this.memory[i] = 0xFF;
        }

        // Set all others set to 0.
        for (int i = 0x2000; i <= 0x8000; i++) {
            this.memory[i] = 0;
        }
    }

    /*
    // Serialise the list of individual processor flags into the register's value.
    CPU.prototype.getProcessorFlags = function() {
        return +this.carryFlag | +this.zeroFlag << 1 | +this.interruptDisable << 2 | +this.decimalModeFlag << 3 | +this.breakCommand << 4 | 0x20 | +this.overflowFlag << 6 | +this.negativeFlag << 7;
    };
    */
}
