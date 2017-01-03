package romatthe.mlnes.rom;

import java.util.zip.CRC32;
import org.apache.commons.io.FileUtils;

public class ROM {

    public static final int PRG_ROM_SIZE = 16384;
    public static final int CHR_ROM_SIZE = 8192;
    public static final int PRG_RAM_SIZE = 8192;

    private final ROMHeader header;
    private final byte[] data;

    public ROM(byte[] header, byte[] data) {
        this.header = new ROMHeader(header);
        this.data = data;
    }

    public ROMHeader getHeader() {
        return header;
    }

    public int getPRGROMSize() {
        return this.header.getPRGROMSizeNr() * PRG_ROM_SIZE;
    }

    public int getPRGRAMSize() {
        return this.header.getPRGRAMSizeNr() * PRG_RAM_SIZE;
    }

    public int getCHRROMSize() {
        return this.header.getCHRROMSizeNr() * CHR_ROM_SIZE;
    }

    public String getCRC32() {
        CRC32 checksum = new CRC32();
        checksum.update(data);
        return Long.toHexString(checksum.getValue()).toUpperCase();
    }

    public boolean isValidHeader() {
        return this.header.isValidHeader();
    }

    @Override
    public String toString() {
        return String.format(
            "CRC32\t\t : %s\nPGR ROM\t\t : %s\nPRG RAM\t\t : %s\nCHR ROM\t\t : %s\nMapper\t\t : %d\nTV System\t : %s\nIs PlayChoice-10 : %b\nIs VS. UniSystem : %b\n",
            this.getCRC32(),
            FileUtils.byteCountToDisplaySize(this.getPRGROMSize()),
            FileUtils.byteCountToDisplaySize(this.getPRGRAMSize()),
            FileUtils.byteCountToDisplaySize(this.getCHRROMSize()),
            this.getHeader().getMapper(),
            this.getHeader().getTVSystem().toString(),
            this.getHeader().isPlaychoice10(),
            this.getHeader().isVsUnisystem()
        );
    }
}
