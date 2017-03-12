package cpu.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InstructionException extends Exception {

    InstructionException(String message) {
        super(message);
    }

    InstructionException(String message, Exception e) {
        super(message, e);
    }

}
