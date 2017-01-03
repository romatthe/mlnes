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
    }

    public short fetch(int address) {
        if (this.isReadsDisabled()) {
            return 0xff;
        } else {
            return this.mem[address];
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
}
