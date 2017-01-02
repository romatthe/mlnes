package romatthe.mlnes.cpu;

public enum CPUStatus {
    C((byte)1), // Carry flag
    Z((byte)2), // Zero flag
    I((byte)3), // Interrupt disable
    D((byte)4), // Decimal mode
    B((byte)5), // Break command
    V((byte)7), // Overflow flag
    N((byte)8); // negative flag

    private final byte code;

    CPUStatus(byte code) {
        this.code = code;
    }
}
