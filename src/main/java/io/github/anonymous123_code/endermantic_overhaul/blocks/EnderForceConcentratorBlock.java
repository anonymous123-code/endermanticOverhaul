package io.github.anonymous123_code.endermantic_overhaul.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;


public class EnderForceConcentratorBlock extends FacingBlock {
    public static final BooleanProperty POWERED = Properties.POWERED;

    public EnderForceConcentratorBlock(Settings settings) {
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
                    List<EndermanEntity> endermen = world.getEntitiesByClass(EndermanEntity.class, new Box(facingBlock.add(8, 8, 8), facingBlock.add(-8, -8, -8)), endermanEntity -> facingBlock.isWithinDistance(endermanEntity.getPos(), 8) && !endermanEntity.isAiDisabled() && !endermanEntity.isDead());
                    endermen.forEach((EndermanEntity e) -> {
                        boolean teleportSuccesful = e.teleport(facingBlock.getX() + 0.5, facingBlock.getY(), facingBlock.getZ() + 0.5,true);
                        if (teleportSuccesful && !e.isSilent()) {
                            world.playSound(null, e.prevX, e.prevY, e.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, e.getSoundCategory(), 1.0f, 1.0f);
                            e.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                        }
                    });
                }

                world.setBlockState(pos, state.with(POWERED, powered));
            }

        }
    }
}
