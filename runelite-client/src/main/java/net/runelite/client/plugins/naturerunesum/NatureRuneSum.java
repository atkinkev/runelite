package net.runelite.client.plugins.naturerunesum;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
        name = "Nature Rune Counter",
        description = "Counts nature runes in bank and inventory"
)

public class NatureRuneSum extends Plugin {
    private int numCrafted;
    @Getter
    private Item[] equippedItems;

    @Getter
    private ItemManager itemManager;


    @Getter
    private Item[] inventoryItems;

    @Inject
    private Client client;
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private RuneSumOverlay runeSumOverlay;

    @Override
    public void startUp() {
        overlayManager.add(runeSumOverlay);
    }

    @Override
    public void shutDown() {
        overlayManager.remove(runeSumOverlay);
    }

    int getNumberCrafted() {
        int sumOfRunes = 0;
        Item[] itemsInBank = {};
        Item[] itemsInInvent = {};
        if (client.getItemContainer(InventoryID.INVENTORY) != null) {
            itemsInInvent = client.getItemContainer(InventoryID.INVENTORY).getItems();
        }
        if (client.getItemContainer(InventoryID.BANK) != null){
            itemsInBank = client.getItemContainer(InventoryID.BANK).getItems();
        }
        for(Item item : itemsInInvent) {
            if (item.getId() == ItemID.NATURE_RUNE) {
                sumOfRunes += item.getQuantity();
            }
        }
        for(Item item : itemsInBank){
            if(item.getId() == ItemID.NATURE_RUNE){
                sumOfRunes += item.getQuantity();
            }
        }
        return sumOfRunes;
    }
}
