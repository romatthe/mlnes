package romatthe.mlnes.cpu;

public enum AddressingMode {
    ZERO_PAGE               ((byte)0),
    INDEXED_ZERO_PAGE_X     ((byte)1),
    INDEXED_ZERO_PAGE_Y     ((byte)2),
    ABSOLUTE                ((byte)3),
    INDEXED_ABSOLUTE__X     ((byte)4),
    INDEXED_ABSOLUTE_Y      ((byte)5),
    IMPLIED                 ((byte)6),
    ACCUMULATOR             ((byte)7),
    IMMEDIATE               ((byte)8),
    RELATIVE                ((byte)9),
    INDEXED_INDIRECT        ((byte)10),
    INDIRECT_INDEXED        ((byte)11),
    INDIRECT                ((byte)12);

    private final byte code;

    AddressingMode(byte code) {
        this.code = code;
    }

    public byte getValue() {
        return this.code;
    }
}
