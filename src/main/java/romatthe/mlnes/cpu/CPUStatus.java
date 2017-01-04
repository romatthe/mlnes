package romatthe.mlnes.cpu;

public enum CPUStatus {
    C((short)1), // Carry flag
    Z((short)2), // Zero flag
    I((short)3), // Interrupt disable
    D((short)4), // Decimal mode
    B((short)5), // Break command
    V((short)7), // Overflow flag
    N((short)8); // Negative flag

    private final short code;

    CPUStatus(short code) {
        this.code = code;
    }
}
