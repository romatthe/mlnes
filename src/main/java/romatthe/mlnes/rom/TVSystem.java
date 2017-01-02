package romatthe.mlnes.rom;

public enum TVSystem {
    NTSC(0),
    PAL (1),
    BOTH(2);

    private final int code;

    TVSystem(int code) {
        this.code = code;
    }
}