package net.runelite.client.plugins.myfirstplugin;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ExperienceChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.stream.Stream;

@Slf4j
@PluginDescriptor(
        name = "First Plugin",
        description = "Congratulates you for all XP"
)

public class MyFirstPlugin extends Plugin {
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
    private MyOverlay myOverlay;

    @Override
    public void startUp() {
        overlayManager.add(myOverlay);
    }

    @Override
    public void shutDown() {
        overlayManager.remove(myOverlay);
    }

    @Subscribe
    public void onItemContainerChanged(final ItemContainerChanged event){
        boolean foundNat = false;
        if (event.getItemContainer() != client.getItemContainer(InventoryID.INVENTORY))
        {
            System.out.println("Something happened to another container than the inventory.");
            return;
        }

        System.out.println("The inventory changed.");

        final Item[] items = event.getItemContainer().getItems();

        for(Item item : items){
            if(item.getId() == ItemID.NATURE_RUNE){
                System.out.println("You are holding the most beloved item in Runescape");
                foundNat = true;
            }
        }
        if(!foundNat){
            System.out.println("You are not holding a nature rune.");
        }
    }


    @Subscribe
    public void onExperienceChanged(ExperienceChanged event){
        Skill skill = event.getSkill();
        int xp = client.getSkillExperience(skill);
        int level = client.getRealSkillLevel(skill);

        if (level != 0){
            System.out.println("Congrats on gaining xp. I'm really proud! " + skill.getName() + " is now at: " + xp + " XP");
        }
    }

    int getFiremakingLevel(){
        return client.getRealSkillLevel(Skill.FIREMAKING);
    }

}
