package memory.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemoryException extends Exception {

    public MemoryException(String message) {
        super(message);
    }

    public MemoryException(String message, Exception e) {
        super(message, e);
    }

}
