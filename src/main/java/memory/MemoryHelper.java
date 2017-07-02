package memory;

import common.DWord;
import common.DataHelper;
import common.MemoryAddr;

import static common.DataHelper.parseMemoryAddr;

public class MemoryHelper {

    public final static int MIN_ADDR_VALUE = 0x0000;
    public final static int MAX_ADDR_VALUE = 0xFFFF;
/*
    public final static MemoryAddr CARTRIDGE_ROM_ADDR = MemoryAddr.getByAddress(0x0000);
    public final static MemoryAddr CARTRIDGE_HEADER_ADDR = MemoryAddr.getByAddress(0x0100);
    public final static MemoryAddr BIOS_ADDR = MemoryAddr.getByAddress(0x0000);
    public final static MemoryAddr GRAPHIC_RAM_ADDR = MemoryAddr.getByAddress(0x8000);
    public final static MemoryAddr CARTRIDGE_EXT_ADDR = MemoryAddr.getByAddress(0xA000);
    public final static MemoryAddr WORKING_ADDR = MemoryAddr.getByAddress(0xC000);
    public final static MemoryAddr WORKING_SHADOW_ADDR = MemoryAddr.getByAddress(0xE000);
    public final static MemoryAddr GRAPHIPS_INFO_ADDR = MemoryAddr.getByAddress(0xFE00);
    public final static MemoryAddr IO_ADDR = MemoryAddr.getByAddress(0xFF00);
    public final static MemoryAddr ZERO_PAGE_ADDR = MemoryAddr.getByAddress(0xFF80);*/

    public static MemoryAddr getNextFrom(MemoryAddr memoryAddr) {
        if (!memoryAddr.equals(MAX_ADDR_VALUE)) {
            DWord address = memoryAddr.getAddress();
            DataHelper.inc(address, 1);
            return parseMemoryAddr(address);
        } else {
            return null;
        }
    }
}
