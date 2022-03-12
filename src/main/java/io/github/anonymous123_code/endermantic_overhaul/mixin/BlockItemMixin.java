package io.github.anonymous123_code.endermantic_overhaul.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.state.property.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * @author anonymous123-code
 */
@Mixin(BlockItem.class)
public interface BlockItemMixin {
    @Invoker
    static <T extends Comparable<T>> BlockState invokeWith(BlockState state, Property<T> property, String name){
        throw new AssertionError();
    }
}
