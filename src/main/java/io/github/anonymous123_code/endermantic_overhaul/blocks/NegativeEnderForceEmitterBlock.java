package io.github.anonymous123_code.endermantic_overhaul.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

/**
 * @author anonymous123-code
 */
public class NegativeEnderForceEmitterBlock extends FacingBlock {
    public static final BooleanProperty POWERED = Properties.POWERED;

    public NegativeEnderForceEmitterBlock(Settings settings) {
        super(settings);
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.UP).with(POWERED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, POWERED);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            boolean powered = world.isReceivingRedstonePower(pos);
            if (powered != state.get(POWERED)) {
                if (powered) {
                    BlockPos facingBlock = pos.offset(state.get(FACING), 1);
                    List<EndermanEntity> endermen = world.getEntitiesByClass(EndermanEntity.class, new Box(facingBlock.add(2, 2, 2), facingBlock.add(-2, -3, -2)), EntityPredicates.VALID_ENTITY.and(enderman -> ((EndermanEntity) enderman).getCarriedBlock()!=null));
                    if(endermen.isEmpty()) return;
                    EndermanEntity enderman = endermen.get(0);
                    if(enderman.getCarriedBlock().canPlaceAt(world, facingBlock) && world.getBlockState(facingBlock).isAir() && world.getOtherEntities(enderman, Box.from(Vec3d.of(facingBlock))).isEmpty()) {
                        world.setBlockState(facingBlock, enderman.getCarriedBlock(), Block.NOTIFY_ALL);
                        world.emitGameEvent(enderman, GameEvent.BLOCK_PLACE, facingBlock);
                        enderman.setCarriedBlock(null);
                    }
                }

                world.setBlockState(pos, state.with(POWERED, powered));
            }

        }
    }
}
