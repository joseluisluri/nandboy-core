package cpu.exceptions;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class NoSuchOpcodeException extends Exception {
    public NoSuchOpcodeException(@NonNull String message) {
        super(message);
    }
    public NoSuchOpcodeException(@NonNull String message, @NonNull Exception e) {
        super(message, e);
    }
}
