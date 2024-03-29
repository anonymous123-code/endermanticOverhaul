package io.github.anonymous123_code.endermantic_overhaul.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.recipe.VanillaEmiRecipeCategories;
import dev.emi.emi.api.stack.EmiStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Items;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

/**
 * @author anonymous123-code
 */
@Environment(EnvType.CLIENT)
public class EndermanticOverhaulEmiPlugin implements EmiPlugin {
    public static final EmiStack ENDERMAN_PLACEMENT_WORKSTATION = EmiStack.of(Items.ENDERMAN_SPAWN_EGG.getDefaultStack().setCustomName(Text.translatable("entity.minecraft.enderman").setStyle(Style.EMPTY.withItalic(false))));
    public static final EmiRecipeCategory ENDERMAN_PLACEMENT_CATEGORY
            = new EmiRecipeCategory(new Identifier("endermantic_overhaul", "enderman_placement"), ENDERMAN_PLACEMENT_WORKSTATION, VanillaEmiRecipeCategories.WORLD_INTERACTION.simplified);



    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(ENDERMAN_PLACEMENT_CATEGORY);

        registry.addWorkstation(ENDERMAN_PLACEMENT_CATEGORY, ENDERMAN_PLACEMENT_WORKSTATION);

        registry.addRecipe(new EndermanPlacementRecipe());
    }
}
