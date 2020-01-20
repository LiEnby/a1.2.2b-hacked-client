package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;

public class CreativeInventory implements IInventory{
    private List<ItemStack> items = new ArrayList<ItemStack>();
    private int itemOffset = 0;

    public CreativeInventory() {
        int iLen = Item.itemsList.length;
        for (int itemId = 0; itemId < iLen; itemId++) {
            ItemStack itmStk = new ItemStack(itemId, 1);
            try {
                itmStk.getMaxStackSize();
            } catch (NullPointerException e) {
                continue;
            }
            items.add(itmStk);


            if (items.size() % 54 == 45) {
                if (items.size() >= 54) {
                    items.add(new ItemStack(75, 1));
                }
            }
            if (items.size() % 54 == 53) {
                items.add(new ItemStack(76, 1));
            }
        }
        while (items.size() % 54 != 0) {
            if (items.size() % 54 == 45) {
                if (items.size() >= 54) {
                    items.add(new ItemStack(75, 1));
                }
            } else {
                items.add(null);
            }
        }
    }


    public int getSizeInventory() {
        return 54;
    }


    public ItemStack getStackInSlot(int i) {
        ItemStack itm = items.get(itemOffset + i);
        return itm;
    }


    public ItemStack decrStackSize(int i, int j) {
        if(i == 45 && itemOffset != 0)
        {
            itemOffset -= this.getSizeInventory();
            return null;
        }
        else if(i == 53 && itemOffset <= items.size())
        {
            itemOffset += this.getSizeInventory();
            return null;
        }

        ItemStack itm = items.get(itemOffset+i);
        int itemID = itm.itemID;
        int stackSize = itm.getMaxStackSize();
        return new ItemStack(itemID,stackSize);
    }


    public void setInventorySlotContents(int i, ItemStack itemstack) {

    }


    public String getInvName() {
        return "Creative Menu";
    }


    public int getInventoryStackLimit() {
        return 64;
    }


    public void func_474_j_() {

    }
}

