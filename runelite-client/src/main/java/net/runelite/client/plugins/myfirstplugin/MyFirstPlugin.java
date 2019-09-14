package net.runelite.client.plugins.myfirstplugin;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.Skill;
import net.runelite.api.World;
import net.runelite.api.events.ExperienceChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
        name = "First Plugin",
        description = "Congratulates you for all XP"
)

public class MyFirstPlugin extends Plugin {
    @Getter
    private Item[] equippedItems;
    @Inject
    private Client client;
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private MyOverlay myOverlay;

    @Inject
    private World world;

    @Override
    public void startUp() {
        overlayManager.add(myOverlay);
        client.changeWorld(world);
    }

    @Override
    public void shutDown() {
        overlayManager.remove(myOverlay);
    }

    @Subscribe
    public void onItemContainerChanged(final ItemContainerChanged event){
        equippedItems = event.getItemContainer().getItems();
        for (int i = 0; i < equippedItems.length; i++) {
            if (equippedItems[i].getId() != -1) {
                System.out.println("You are carrying: " + equippedItems[i].toString());
            }
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
