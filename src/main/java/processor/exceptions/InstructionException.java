package processor.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InstructionException extends Exception {

    public InstructionException(String message) {
        super(message);
    }

    public InstructionException(String message, Exception e) {
        super(message, e);
    }

}
