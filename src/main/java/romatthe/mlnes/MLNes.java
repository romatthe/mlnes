package romatthe.mlnes;

import org.apache.commons.io.FileUtils;
import romatthe.mlnes.rom.ROM;

import java.io.IOException;

public class MLNes {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to MLNES");
        ROM rom = new ROM();

        System.out.println(rom);
    }
}
