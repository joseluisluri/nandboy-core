package core;

public class MemoryAddr {
    public static final int MIN_INDEX = 0;
    public static final int MAX_INDEX = 0xFFFF;
    public static final MemoryAddr MIN_VALUE = Datatype.parseMemoryAddr(MIN_INDEX);
    public static final MemoryAddr MAX_VALUE = Datatype.parseMemoryAddr(MAX_INDEX);

    protected final DWord address;

    protected MemoryAddr() {
        address = new DWord();
    }

    protected MemoryAddr(DWord address) {
        this.address = address.clone();
    }

    public DWord getAddress() {
        return address.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemoryAddr that = (MemoryAddr) o;

        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        return address != null ? address.hashCode() : 0;
    }

    @Override
    public String toString() {
        return address.toString();
    }
}

