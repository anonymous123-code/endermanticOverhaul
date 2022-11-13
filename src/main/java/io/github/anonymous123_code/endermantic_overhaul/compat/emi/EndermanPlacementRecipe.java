package io.github.anonymous123_code.endermantic_overhaul.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import io.github.anonymous123_code.endermantic_overhaul.EndermanticOverhaulMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

//TODO: Unhardcode
/**
 * @author anonymous123-code
 */
@Environment(EnvType.CLIENT)
public class EndermanPlacementRecipe implements EmiRecipe {
    private OrderedText text = Text.translatable("predicate.endermantic_overhaul.negative_ender_force_emitter_crafting").asOrderedText();

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
        return Math.max(MinecraftClient.getInstance().textRenderer.getWidth(this.text)+4, EmiTexture.EMPTY_ARROW.width + 68);
    }

    @Override
    public int getDisplayHeight() {
        return 32;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addSlot(getInputs().get(0), getDisplayWidth()/2 - EmiTexture.EMPTY_ARROW.width/2 - 32,0);
        widgets.addTexture(EmiTexture.EMPTY_ARROW, getDisplayWidth()/2 - EmiTexture.EMPTY_ARROW.width/2 - 1,1);
        widgets.addSlot(getOutputs().get(0), getDisplayWidth()/2 + EmiTexture.EMPTY_ARROW.width/2 + 16,0);
        widgets.addText(text,  getDisplayWidth()/2 - MinecraftClient.getInstance().textRenderer.getWidth(this.text)/2, 22, -1, true);
    }
}
