package net.runelite.client.plugins.myfirstplugin;
import com.google.common.graph.Graph;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

import javax.inject.Inject;
import java.awt.*;

public class MyOverlay extends Overlay {
    private MyFirstPlugin plugin;

    private PanelComponent panelComponent = new PanelComponent();

    @Inject
    public MyOverlay(MyFirstPlugin plugin){
        super(plugin);
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics){
        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Firemaking:")
                .right(Integer.toString(plugin.getFiremakingLevel()))
                .build());
        return panelComponent.render(graphics);
    }
}
