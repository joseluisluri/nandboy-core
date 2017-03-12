package cpu.opcode;

import lombok.Getter;

@Getter
class OpCode0x00 implements OpCode {

    short cycles = 1;

    public void execute() {

    }
}
