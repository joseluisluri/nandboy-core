package memory;

import common.DWord;
import common.MemoryAddr;
import common.Word;
import memory.exceptions.MemoryException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static common.DataHelper.*;
public class Memory {
    private static final Word INITIAL_WORD_VALUE = parseWord(0);
    private Map<MemoryAddr, Word> map;

    private static Memory singleton = null;

    public static Memory getInstance() {
        if (singleton == null) {
            singleton = new Memory();
        }
        return singleton;
    }

    private Memory() {
        map = new HashMap<>(MemoryAddr.MAX_INDEX);
        IntStream.range(MemoryAddr.MIN_INDEX, map.size()).forEach(i -> {
            map.put(parseMemoryAddr(i),INITIAL_WORD_VALUE.clone());
        });
    }

    public void loadWord(MemoryAddr address, Word word) {
        map.put(address, word.clone());
    }



    public void loadDWord(MemoryAddr memoryAddr, DWord dWord) throws MemoryException {
        if (!memoryAddr.equals(MemoryAddr.MAX_VALUE)) {
            MemoryAddr nextAddr = MemoryHelper.getNextFrom(memoryAddr);
            loadWord(memoryAddr, dWord.getHighWord());
            loadWord(nextAddr, dWord.getLowWord());
        } else {
            throw new MemoryException("Memory address out of bounds");
        }
    }

    public Word retrieveWord(MemoryAddr memoryAddr) {
        return map.get(memoryAddr).clone();
    }

    public DWord retrieveDWord(MemoryAddr memoryAddr) throws MemoryException {
        if (!memoryAddr.equals(MemoryAddr.MAX_VALUE)) {
            MemoryAddr nextAddr = MemoryHelper.getNextFrom(memoryAddr);
            return createDWord(map.get(memoryAddr), map.get(nextAddr));
        } else {
            throw new MemoryException("Memory address out of bounds");
        }

    }

    public void clear() {
        map.forEach((k,v) -> v.setValue(INITIAL_WORD_VALUE.clone()));
    }
}