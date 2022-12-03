package io.github.anonymous123_code.endermantic_overhaul.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

/**
 * @author anonymous123-code
 */
@Mixin(ItemDispenserBehavior.class)
public class ItemDispenserBehaviorMixin {
    @Inject(method = "dispenseSilently", at = @At("HEAD"), cancellable = true)
    private void endermanticOverhaul$injectDispenseSilently(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (!(stack.getItem() instanceof BlockItem)) return;

        BlockState blockState = pointer.getBlockState();
        if (!blockState.getBlock().equals(Blocks.DISPENSER)) return;

        // TODO Enderman doesn't handle Blockentities, the default Blocks should work, filled shulker boxes won't work
        NbtCompound blockEntityNbt = BlockItem.getBlockEntityNbtFromStack(stack);
        if(blockEntityNbt != null) return;


        Direction facing = blockState.get(DispenserBlock.FACING);
        BlockPos targetPos = pointer.getPos().offset(facing);
        List<EndermanEntity> endermen = pointer.getWorld().getEntitiesByClass(EndermanEntity.class, new Box(targetPos), EntityPredicates.VALID_ENTITY.and((e) -> ((EndermanEntity) e).getCarriedBlock() == null));
        if (endermen.isEmpty()) return;

        EndermanEntity enderman = endermen.get(0);

        ItemPlacementContext context = new AutomaticItemPlacementContext(pointer.getWorld(), targetPos, facing, stack, facing);
        BlockState placementState = ((BlockItem) stack.getItem()).getBlock().getPlacementState(context);
        if (placementState == null) return;

        NbtCompound stackData = stack.getNbt();
        if (stackData != null) {
            NbtCompound blockStateData = stackData.getCompound(BlockItem.BLOCK_STATE_TAG_KEY);
            StateManager<Block, BlockState> stateManager = placementState.getBlock().getStateManager();
            for (String string : blockStateData.getKeys()) {
                Property<?> property = stateManager.getProperty(string);
                if (property == null) continue;
                String content = blockStateData.get(string).asString();
                placementState = BlockItemMixin.invokeWith(placementState, property, content);
            }
        }

        enderman.setCarriedBlock(placementState);
        stack.decrement(1);

        cir.setReturnValue(stack);
    }
}
