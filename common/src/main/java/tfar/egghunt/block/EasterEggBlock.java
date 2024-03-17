package tfar.egghunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import tfar.egghunt.level.EggHuntData;

import javax.annotation.Nullable;

public class EasterEggBlock extends Block {
    public EasterEggBlock(Properties $$0) {
        super($$0);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            EggHuntData eggHuntData = EggHuntData.getInstance(pLevel.getServer().overworld());
            if (eggHuntData.isActive()) {
                if (eggHuntData.discover((ServerPlayer) pPlayer,pPos)) {
                    playSound(null,pLevel,pPos);
                }
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    protected void playSound(@Nullable Player pPlayer, Level pLevel, BlockPos pPos) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.IRON_GOLEM_DAMAGE, SoundSource.BLOCKS, .5f, 1);
    }

}
