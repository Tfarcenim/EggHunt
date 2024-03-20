package tfar.egghunt.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import tfar.egghunt.EggHunt;
import tfar.egghunt.init.ModBlocks;
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
                    playSound(null,pLevel,pPos,this == ModBlocks.EASTER_EGG5);

                    double d0 = pState.getShape(pLevel, pPos).max(Direction.Axis.Y, 0.5D, 0.5D) + 0.03125D;
                    double d1 = 0.13125F;
                    double d2 = 0.7375F;
                    RandomSource randomsource = pLevel.getRandom();

                    for(int i = 0; i < 10; ++i) {
                        double d3 = randomsource.nextGaussian() * 0.02D;
                        double d4 = randomsource.nextGaussian() * 0.02D;
                        double d5 = randomsource.nextGaussian() * 0.02D;

                      //  ((ServerLevel)pLevel).sendParticles((ServerPlayer) pPlayer,ParticleTypes.COMPOSTER,false,pPos.getX(),pPos.getY(),pPos.getZ(),1,0,0,0,0);

                        double x = pPos.getX() + d1 + d2 * randomsource.nextFloat();
                        double y = pPos.getY() + d0 + randomsource.nextFloat() * (1.0D - d0);
                        double z = pPos.getZ() + d1 + d2 * randomsource.nextFloat();

                        ((ServerLevel)pLevel).sendParticles((ServerPlayer)pPlayer,ParticleTypes.COMPOSTER,false, x,
                               y,
                                z,1, d3, d4, d5,0);
                    }


                }
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide);
    }

    protected void playSound(@Nullable Player pPlayer, Level pLevel, BlockPos pPos, boolean b) {
        SoundEvent soundEvent = b ? EggHunt.JOE : SoundEvents.PLAYER_LEVELUP;
        pLevel.playSound(pPlayer, pPos, soundEvent, SoundSource.BLOCKS, 1, 1);
    }

}
