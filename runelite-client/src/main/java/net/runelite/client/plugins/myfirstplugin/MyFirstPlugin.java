package net.runelite.client.plugins.myfirstplugin;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.ExperienceChanged;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "First Plugin",
        description = "Congratulates you for all XP"
)

public class MyFirstPlugin extends Plugin {
    @Subscribe
    public void onExperienceChanged(ExperienceChanged event){
        System.out.println("Gratz Friend!");
    }
}
