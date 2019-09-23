package net.runelite.client.plugins.naturerunesum;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("naturerunesumconfig")
public interface NatureRuneSumConfig extends Config
{
    @ConfigItem(
            name="Include Bank",
            keyName = "includebank",
            description="Toggle to select if the total count should include the bank",
            position = 1
    )
    default boolean countBank() {return true;}
}
