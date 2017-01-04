package romatthe.mlnes.cpu;

public class MemoryBasic implements Memory {

    private short[] mem;
    private boolean disableReads;
    private boolean disableWrites;

    public MemoryBasic(int size) {
        this.mem = new short[size];
    }

    public boolean isReadsDisabled() {
        return disableReads;
    }

    public void disableReads() {
        this.disableReads = true;
    }

    public void enableReads() {
        this.disableReads = false;
    }

    public boolean isWritesDisabled() {
        return disableWrites;
    }

    public void disableWrites() {
        this.disableWrites = true;
    }

    public void enableWrites() {
        this.disableWrites = false;
    }

    public void reset() {
        this.mem = new short[mem.length];

        // Set 2kb Internal RAM
        for (int i = 0; i <= 0x2000; i++) {
            this.mem[i] = 0xFF;
        }

        // Set all others set to 0.
        for (int i = 0x2000; i <= 0x8000; i++) {
            this.mem[i] = 0x0;
        }
    }

    public short store(int address, short value) {
        if (!this.isWritesDisabled()) {
            short oldValue = this.mem[address];
            this.mem[address] = value;

            return oldValue;
        }

        return 0xff;
    }

    public short fetch(int address) {
        if (this.isReadsDisabled()) {
            return 0xff;
        } else {
            return this.mem[address];
        }
    }

    // Returns true if the two addresses are located in the same page in memory.
    // Two addresses are on the same page if their high bytes are both the same,
    // i.e. 0x0101 and 0x0103 are on the same page but 0x0101 and 0x0203 are not.
    public boolean isSamePage(int address1, int address2) {
        return (address1 ^ address2) >> 8 == 0;
    }
}
