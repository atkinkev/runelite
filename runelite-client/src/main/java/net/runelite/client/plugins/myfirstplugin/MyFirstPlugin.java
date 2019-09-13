package net.runelite.client.plugins.myfirstplugin;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.events.ExperienceChanged;
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
