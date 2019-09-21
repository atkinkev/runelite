package net.runelite.client.plugins.naturerunesum;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

public class RuneSumOverlay extends Overlay {
    private NatureRuneSum plugin;

    private PanelComponent panelComponent = new PanelComponent();

    @Inject
    public RuneSumOverlay(NatureRuneSum plugin){
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics){
        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Number Crafted:")
                .right(Integer.toString(plugin.getNumberCrafted()))
                .build());
        return panelComponent.render(graphics);
    }
}
