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
    private int registerPC =    0x0;    // Program Counter (16bit)
    private short registerSP =  0x0;    // Stack Pointer (8bit)
    private short registerA =   0x0;    // Accumulator (8bit)
    private short registerX =   0x0;    // Index Register X (8bit)
    private short registerY =   0x0;    // Index Register Y (8bit)

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
    private boolean carryFlag = false;
    private boolean zeroFlag = true;
    private boolean interruptDisable = true;
    private boolean decimalModeFlag = false;
    private boolean breakCommand = false;
    private boolean overflowFlag = false;
    private boolean negativeFlag = false;

    // Maskable Interrupt
    // One of "irq/brk", "nmi", "reset"
    private String interrupt = null;


}
