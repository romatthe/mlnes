package romatthe.mlnes.rom;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;

public class ROM {

    private static final int PRG_ROM_SIZE = 16384;
    private static final int CHR_ROM_SIZE = 8192;
    private static final int PRG_RAM_SIZE = 8192;

    private final ROMHeader header;
    private final byte[] data;

    public ROM() throws IOException {
        InputStream in = FileUtils.openInputStream(new File("/Users/robinm/Source/mlnes/src/main/resources/test.bin"));
        byte[] header = IOUtils.readFully(in, 16);
        this.header = new ROMHeader(header);

        int romDataSize = this.header.getPRGROMSizeNr() * PRG_ROM_SIZE + this.header.getCHRROMSizeNr() * CHR_ROM_SIZE;
        byte[] data = IOUtils.readFully(in, romDataSize);

        if (data.length != romDataSize) {
            throw new IOException("ROM size does not match size declared in header!");
        }

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
            "CRC32\t\t\t : %s\nPGR ROM\t\t\t : %s\nPRG RAM\t\t\t : %s\nCHR ROM\t\t\t : %s\nMapper\t\t\t : %d\nTV System\t\t : %s\nIs PlayChoice-10 : %b\nIs VS. UniSystem : %b\n",
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
