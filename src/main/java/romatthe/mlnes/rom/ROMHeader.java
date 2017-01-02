package romatthe.mlnes.rom;

public class ROMHeader {

    private final byte[] headerBytes;
    private final int prgROMSizeNr;
    private final int prgRAMSizeNr;
    private final int chrROMSizeNr;
    private final int mapper;
    private final int flag6;
    private final int flag7;

    public ROMHeader(byte[] header) {
        this.headerBytes = header;

        this.prgROMSizeNr = header[4];
        this.prgRAMSizeNr = header[8];
        this.chrROMSizeNr = header[5];
        this.flag6 = header[6];
        this.flag7 = header[7];

        // Extract low and high nibbles from first 4 bits of flag6 and flag7
        // https://en.wikipedia.org/wiki/Nibble#Low_and_high_nibbles
        this.mapper = this.flag7 & 0xF0 | this.flag6 >> 4;
    }


    public int getMapper() {
        return this.mapper;
    }

    public int getPRGROMSizeNr() {
        return this.prgROMSizeNr;
    }

    public int getPRGRAMSizeNr() {
        return this.prgRAMSizeNr;
    }

    public int getCHRROMSizeNr() {
        return this.chrROMSizeNr;
    }

    public TVSystem getTVSystem() {
        switch(this.headerBytes[9] & 1) {
            case 0:
                return TVSystem.NTSC;
            case 1:
                return TVSystem.PAL;
            case 2:
                return TVSystem.BOTH;
            default:
                return TVSystem.BOTH;
        }
    }

    public boolean isVsUnisystem() {
        return (this.flag7 & 1) == 1;
    }

    public boolean isPlaychoice10() {
        return (this.flag7 >> 1 & 1) == 1;
    }

    public boolean isValidHeader() {
        // First bytes should be $4E $45 $53 $1A AKA "NES" + MSDOS End of File
        if (headerBytes[0] == 0x4E && headerBytes[1] == 0x45 && headerBytes[2] ==0x53 && headerBytes[3] ==0x1A)  {
            return true;
        }
        else {
            return false;
        }
    }
}
