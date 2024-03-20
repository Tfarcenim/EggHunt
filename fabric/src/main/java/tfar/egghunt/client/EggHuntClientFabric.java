package tfar.egghunt.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import tfar.egghunt.block.EasterEggBlock;

public class EggHuntClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WorldRenderEvents.BLOCK_OUTLINE.register(this::noEggOutline);
    }

    private boolean noEggOutline(WorldRenderContext worldRenderContext, WorldRenderContext.BlockOutlineContext blockOutlineContext) {
        if (blockOutlineContext.blockState().getBlock() instanceof EasterEggBlock) {
         return false;
        }
        return true;
    }


}
