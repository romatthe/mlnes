package romatthe.mlnes.cpu;

public interface Memory {
    void reset();
    short fetch(int address, boolean isDouble);
    short store(int address, short value);
}