package romatthe.mlnes.rom;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class ROMLoader {
    public static ROM load(String filePath) throws IOException {
        InputStream in = FileUtils.openInputStream(new File(filePath));
        byte[] header = IOUtils.readFully(in, 16);
        ROMHeader romHeader = new ROMHeader(header);

        int romDataSize = romHeader.getPRGROMSizeNr() * ROM.PRG_ROM_SIZE + romHeader.getCHRROMSizeNr() * ROM.CHR_ROM_SIZE;
        byte[] data = IOUtils.readFully(in, romDataSize);

        if (data.length != romDataSize) {
            throw new IOException("ROM size does not match size declared in header!");
        }

        return new ROM(header, data);
    }
}
