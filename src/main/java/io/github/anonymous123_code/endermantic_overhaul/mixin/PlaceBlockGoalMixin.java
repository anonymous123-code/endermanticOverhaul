package io.github.anonymous123_code.endermantic_overhaul.mixin;

import io.github.anonymous123_code.endermantic_overhaul.EndermanticOverhaulMod;
import net.minecraft.block.BlockState;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * @author anonymous123-code
 */
@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal")
public class PlaceBlockGoalMixin {

    @Shadow @Final private EndermanEntity enderman;

    @ModifyArg(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;postProcessState(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"))
    public BlockState placementRecipeCheck(BlockState state, WorldAccess world, BlockPos pos) {
        if(state.isOf(EndermanticOverhaulMod.ENDER_FORCE_CONCENTRATOR)) {
            if(this.enderman.world.getRegistryKey().equals(World.NETHER)) {
                return EndermanticOverhaulMod.NEGATIVE_ENDER_FORCE_EMITTER.getDefaultState().with(Properties.POWERED, state.get(Properties.POWERED)).with(Properties.FACING, state.get(Properties.FACING));
            }
        }

        return state;
    }
}
