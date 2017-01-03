package romatthe.mlnes.cpu;

public enum Interrupt {
    IRQBRK  ((byte)0),
    NMI     ((byte)1),
    RESET   ((byte)2);

    private final byte code;

    Interrupt(byte code) {
        this.code = code;
    }

    public byte getValue() {
        return this.code;
    }
}
