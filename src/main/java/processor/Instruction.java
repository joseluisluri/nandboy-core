package processor;

import processor.exceptions.InstructionException;
import lombok.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
public class Instruction {
    private final int code;
    private final short cycles;

    @Getter(AccessLevel.NONE)
    private final Method command;

    public Instruction(@NonNull Opcode opcode, @NonNull Method command) {
        this.code = opcode.code();
        this.cycles = opcode.cycles();
        this.command = command;
    }

    public void exec() throws InstructionException {
        try {
            this.command.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new InstructionException("Unable to execute the command: " + e, e);
        }
    }
}
