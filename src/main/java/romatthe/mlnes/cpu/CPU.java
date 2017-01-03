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
    private byte status = 0x0;

    // Part of the Processor Status register
    // Separated for convenience.
    private CPUFlags flags = new CPUFlags();

    // Maskable Interrupt
    // One of "irq/brk", "nmi", "reset"
    private String interrupt = null;

    public void reset() {
        this.memory.reset();
        this.flags.reset();
        this.registers.reset(this.getResetVector());
        this.status = this.flags.getProcessorFlags();
    }

    // Find where the program begins
    private int getResetVector() {
        return this.memory.fetch(0xfffc, true);
    };
}
