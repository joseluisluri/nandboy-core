package cpu.opcode;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OpCodeException extends Exception {

    OpCodeException(String message) {
        super(message);
    }

    OpCodeException(String message, Exception e) {
        super(message, e);
    }

}
