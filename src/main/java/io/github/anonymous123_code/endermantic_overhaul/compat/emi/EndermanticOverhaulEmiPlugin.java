package io.github.anonymous123_code.endermantic_overhaul.compat.emi;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class EndermanticOverhaulEmiPlugin implements EmiPlugin {
    public static final EmiStack ENDERMAN_PLACEMENT_WORKSTATION = EmiStack.of(Items.ENDERMAN_SPAWN_EGG);
    public static final EmiRecipeCategory ENDERMAN_PLACEMENT_CATEGORY
            = new EmiRecipeCategory(new Identifier("endermantic_overhaul", "enderman_placement_recipe"), ENDERMAN_PLACEMENT_WORKSTATION);

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(ENDERMAN_PLACEMENT_CATEGORY);

        registry.addWorkstation(ENDERMAN_PLACEMENT_CATEGORY, ENDERMAN_PLACEMENT_WORKSTATION);
    }
}
