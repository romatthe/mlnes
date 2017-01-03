package romatthe.mlnes.cpu;

public class CPUFlags {
    private boolean carryFlag;
    private boolean zeroFlag;
    private boolean interruptDisable;
    private boolean decimalModeFlag;
    private boolean breakCommand;
    private boolean overflowFlag;
    private boolean negativeFlag;

    public CPUFlags() {
        this.reset();
    }

    public void reset() {
        this.carryFlag = false;
        this.zeroFlag = true;
        this.interruptDisable = true;
        this.decimalModeFlag = false;
        this.breakCommand = false;
        this.overflowFlag = false;
        this.negativeFlag = false;
    }

    public boolean isCarryFlag() {
        return carryFlag;
    }

    public boolean isZeroFlag() {
        return zeroFlag;
    }

    public boolean isInterruptDisable() {
        return interruptDisable;
    }

    public boolean isDecimalModeFlag() {
        return decimalModeFlag;
    }

    public boolean isBreakCommand() {
        return breakCommand;
    }

    public boolean isOverflowFlag() {
        return overflowFlag;
    }

    public boolean isNegativeFlag() {
        return negativeFlag;
    }

    public void setZeroFlag(boolean zeroFlag) {
        this.zeroFlag = zeroFlag;
    }

    public void setCarryFlag(boolean carryFlag) {
        this.carryFlag = carryFlag;
    }

    public void setInterruptDisable(boolean interruptDisable) {
        this.interruptDisable = interruptDisable;
    }

    public void setDecimalModeFlag(boolean decimalModeFlag) {
        this.decimalModeFlag = decimalModeFlag;
    }

    public void setBreakCommand(boolean breakCommand) {
        this.breakCommand = breakCommand;
    }

    public void setOverflowFlag(boolean overflowFlag) {
        this.overflowFlag = overflowFlag;
    }

    public void setNegativeFlag(boolean negativeFlag) {
        this.negativeFlag = negativeFlag;
    }

    public byte getCarryFlagValue() {
        return (this.isCarryFlag()) ? (byte)1 : (byte)0;
    }

    public byte getZeroFlagValue() {
        return (this.isZeroFlag()) ? (byte)1 : (byte)0;
    }

    public byte getInterruptDisableValue() {
        return (this.isInterruptDisable()) ? (byte)1 : (byte)0;
    }

    public byte getDecimalModeFlagValue() {
        return (this.isDecimalModeFlag()) ? (byte)1 : (byte)0;
    }

    public byte getBreakCommandFlagValue() {
        return (this.isBreakCommand()) ? (byte)1 : (byte)0;
    }

    public byte getOverflowFlagValue() {
        return (this.isOverflowFlag()) ? (byte)1 : (byte)0;
    }

    public byte getNegativeFlagValue() {
        return (this.isNegativeFlag()) ? (byte)1 : (byte)0;
    }

    // Get the numeric representation of the processor flags
    // i.e.: flags = 5 --> 0101 --> Flag 1: 0, Flag 2: 1, Flag 3: 0, Flag 4: 1
    // https://developer.mozilla.org/en/docs/Web/JavaScript/Reference/Operators/Bitwise_Operators#Flags_and_bitmasks
    public byte getProcessorFlags() {
        return
            (byte)(+this.getCarryFlagValue() | +this.getZeroFlagValue() << 1
                | +this.getInterruptDisableValue() << 2 | +this.getDecimalModeFlagValue() << 3
                | +this.getBreakCommandFlagValue() << 4 | 0x20 | +this.getOverflowFlagValue() << 6
                | +this.getNegativeFlagValue() << 7);
    }

}
