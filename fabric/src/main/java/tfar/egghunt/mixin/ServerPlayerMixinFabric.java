package tfar.egghunt.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.egghunt.EggHunt;
import tfar.egghunt.duck.ServerPlayerDuckFabric;

import java.util.Objects;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixinFabric implements ServerPlayerDuckFabric {

    @Shadow @Final public MinecraftServer server;
    // We need this as tablistDisplayname may be null even if the event was fired.
    private boolean hasTabListName = false;
    private Component tabListDisplayName = null;

    @Inject(method = "getTabListDisplayName", at = @At("HEAD"), cancellable = true)
    private void init(CallbackInfoReturnable<Component> cir) {
        if (!this.hasTabListName) {
            this.tabListDisplayName = EggHunt.getTabName((ServerPlayer)(Object)this);
            this.hasTabListName = true;
        }
        if (tabListDisplayName != null) {
            cir.setReturnValue(tabListDisplayName);
        }
    }

    /**
     * Force the name displayed in the tab list to refresh.
     */
    @Override
    public void refreshTabListName() {
        Component oldName = this.tabListDisplayName;
        this.tabListDisplayName = EggHunt.getTabName((ServerPlayer)(Object)this);
        if (!Objects.equals(oldName, this.tabListDisplayName)) {
            this.server.getPlayerList().broadcastAll(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME,(ServerPlayer) (Object)this));
        }
    }
}