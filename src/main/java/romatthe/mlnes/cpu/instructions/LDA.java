package romatthe.mlnes.cpu.instructions;

import romatthe.mlnes.cpu.CPU;

import java.util.function.BiFunction;
import java.util.function.Function;

public class LDA {

    private BiFunction<CPU, Integer, Short> funcLDA = (cpu, address) -> {
        short aVal = cpu.getMemory().fetch(address);
        cpu.getRegisters().setAccumulator(aVal);
        cpu.getFlags().setProcessorFlags(aVal);

        return aVal;
    };

    public Function<CPU, Integer> funcLDAImmediate = cpu -> {
        short aVal = funcLDA.apply(cpu, cpu.immediateAddress());
        return 2;
    };

    public Function<CPU, Integer> funcLDAZeroPage = cpu -> {
        short aVal = funcLDA.apply(cpu, cpu.zeroPageAddress());
        return 3;
    };

    public Function<CPU, Integer> funcLDAZeroPageIndexed = cpu -> {
        short aVal = funcLDA.apply(cpu, cpu.zeroPageIndexedAddress(cpu.getRegisters().getRegisterX()));
        return 4;
    };

    public Function<CPU, Integer> funcLDAAbsolute = cpu -> {
        short aVal = funcLDA.apply(cpu, cpu.absoluteAddress());
        return 4;
    };

    public Function<CPU, Integer> funcLDAAbsoluteIndexed = cpu -> {
        short aVal = funcLDA.apply(cpu, cpu.absoluteIndexedAddress(cpu.getRegisters().getRegisterX(), cpu.getStatus()));
        return 4;
    };

}
