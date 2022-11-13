package io.github.anonymous123_code.endermantic_overhaul.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import io.github.anonymous123_code.endermantic_overhaul.EndermanticOverhaulMod;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EndermanPlacementRecipe implements EmiRecipe {

    @Override
    public EmiRecipeCategory getCategory() {
        return EndermanticOverhaulEmiPlugin.ENDERMAN_PLACEMENT_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
        return new Identifier("endermantic_overhaul", "/enderman_placement/negative_ender_force_emitter");
    }

    @Override
    public List<EmiIngredient> getInputs() {
        List<EmiIngredient> inputs = new ArrayList<>();
        inputs.add(EmiStack.of(EndermanticOverhaulMod.ENDER_FORCE_CONCENTRATOR));
        return inputs;
    }

    @Override
    public List<EmiStack> getOutputs() {
        List<EmiStack> outputs = new ArrayList<>();
        outputs.add(EmiStack.of(EndermanticOverhaulMod.NEGATIVE_ENDER_FORCE_EMITTER));
        return outputs;
    }

    @Override
    public int getDisplayWidth() {
        return 0;
    }

    @Override
    public int getDisplayHeight() {
        return 0;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {

    }
}
