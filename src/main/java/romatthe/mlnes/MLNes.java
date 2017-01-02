package romatthe.mlnes;

import romatthe.mlnes.rom.ROM;
import romatthe.mlnes.rom.RomLoader;

import java.io.IOException;

public class MLNes {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to MLNES");
        ROM rom = RomLoader.load("/Users/robinm/Source/mlnes/src/main/resources/test.bin");

        System.out.println(rom);
    }
}
