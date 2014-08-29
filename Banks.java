package org.parabot.Kozs.kCannonballs;

import java.util.ArrayList;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

public class Banks 
{
public static final int BANK_INTERFACE = 23350;
public static final int ITEM_INTERFACE = 5382;
public static final int BUTTON_DEPOSIT_ALL = 23412;
public static final int INV_PARENT_ID = 5064;
public static final int[] BANKERS = { 44, 45, 494, 495, 498, 499, 909, 958, 1036, 2271, 2354, 2355, 3824, 5488, 5901, 4456, 4457, 4458, 4459, 5912, 5913, 6362, 6532, 6533, 6534, 6535, 7605, 8948, 9710, 14367 };
public static final int[] BANKS = { 782, 2213, 2995, 5276, 6084, 10517, 11402, 11758, 12759, 14367, 19230, 20325, 24914, 25808, 26972, 29085, 52589, 34752, 35647, 36786, 2012, 2015, 2019, 693, 4483, 12308, 20607, 21301, 27663, 42192 };


public static SceneObject[] getNearestBanks()
{
return SceneObjects.getNearest(BANKS);
}

public static SceneObject getBank()
{
SceneObject[] banks = getNearestBanks();
if ((banks != null) && (banks[0] != null)) {
    return banks[0];
}
return null;
}

public static boolean open()
{
if (isOpen()) {
    return false;
}
SceneObject bank = getBank();
if (bank != null)
{
    bank.interact(0);
    return true;
}

return false;
}

public static void depositAll(int sleep)
{
Menu.clickButton(23412);
Time.sleep(sleep);
}

public static void withdraw(int id, int amount, int sleep)
{
if (!isOpen()) {
    return;
}
Item b = getItem(id);
if (b == null) {
    return;
}
if (amount == 1)
{
    b.transform(0, ITEM_INTERFACE);
}
else if (amount == 5)
{
    b.transform(1, ITEM_INTERFACE);
}
else if (amount == 10)
{
    b.transform(2, ITEM_INTERFACE);
}
else if (amount == 0)
{
    b.transform(4, ITEM_INTERFACE);
}
else
{
    b.transform(4, 5064);
    Time.sleep(1500 + sleep);
    Keyboard.getInstance().sendKeys("" + amount);
}
}

public static Item getItem(int id)
{
if (!isOpen()) {
    return null;
}
for (Item i : getBankItems()) {
    if (i.getId() == id) {
        return i;
    }
}
return null;
}

public static int getCount(int id)
{
if (!isOpen()) {
    return 0;
}
return getItem(id).getStackSize();
}

public static void open(SceneObject bank)
{
if (isOpen()) {
    return;
}
if (bank.getLocation().distanceTo() > 8)
{
    bank.getLocation().walkTo();
    return;
}
bank.interact(1);
}

public static void close()
{
if (!isOpen()) {
    return;
}
Menu.sendAction(200, -1, -1, 5384);
}

public static void depositAllExcept(int... exceptions)
{
if (isOpen())
{
    ArrayList<Integer> ignored = new ArrayList();
    for (int i : exceptions) {
        ignored.add(Integer.valueOf(i));
    }
    for (Item i : Inventory.getItems()) {
        if (!ignored.contains(Integer.valueOf(i.getId()))) {
            while (isOpen())
            {
                if (Inventory.getCount(new int[]{i.getId()}) <= 0) {
                    break;
                }
                i.transform(3, 5064);
                ignored.add(Integer.valueOf(i.getId()));
                Time.sleep(50);
            }
        }
    }
}
}

public static int[] getBankItemIDs()
{
if (!isOpen()) {
    return null;
}
return Loader.getClient().getInterfaceCache()[5382].getItems();
}

public static int[] getBankStacks()
{
if (!isOpen()) {
    return null;
}
return Loader.getClient().getInterfaceCache()[5382].getStackSizes();
}

public static Item[] getBankItems()
{
if (!isOpen()) {
    return null;
}
ArrayList<Item> items = new ArrayList();
int[] ids = getBankItemIDs();
int[] stacks = getBankStacks();
for (int i = 0; i < ids.length; i++) {
    if (ids[i] > 0) {
        items.add(new Item(ids[i], stacks[i], i));
    }
}
return (Item[])items.toArray(new Item[items.size()]);
}

public static int getBankCount()
{
if (!isOpen()) {
    return 0;
}
return getBankItemIDs().length;
}

public static boolean isOpen()
{
return Loader.getClient().getOpenInterfaceId() == BANK_INTERFACE;
}
}
