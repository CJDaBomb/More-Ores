package com.cjdabomb.moreores.core.recipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class AlloySmeltingSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<AlloySmeltingRecipe> {


    @Nonnull
    @Override
    public AlloySmeltingRecipe read(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
        System.out.println("Reading Alloysmelting");
        JsonObject inputs = JSONUtils.getJsonObject(json, "input");
        ItemStack output = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "output"), true);
        Ingredient input1 = Ingredient.deserialize(inputs.getAsJsonObject("base"));
        Ingredient input2 = Ingredient.deserialize(inputs.getAsJsonObject("addition"));
        ItemStack input1stack = CraftingHelper.getItemStack(inputs.getAsJsonObject("base"), true);
        ItemStack input2stack = CraftingHelper.getItemStack(inputs.getAsJsonObject("addition"), true);
        Integer smelttime = JSONUtils.getInt(json, "smelttime");
        return new AlloySmeltingRecipe(recipeId, input1, input2, output, smelttime, input1stack, input2stack);
    }

    @Nullable
    @Override
    public AlloySmeltingRecipe read(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
        System.out.println("Reading Alloysmelting");
        ItemStack output = buffer.readItemStack();
        Ingredient input1 = Ingredient.read(buffer);
        Ingredient input2 = Ingredient.read(buffer);
        ItemStack input1stack = buffer.readItemStack();
        ItemStack input2stack = buffer.readItemStack();
        Integer smelttime = buffer.readInt();
        return new AlloySmeltingRecipe(recipeId, input1, input2, output, smelttime, input1stack, input2stack);
    }

    @Override
    public void write(@Nonnull PacketBuffer buffer, AlloySmeltingRecipe recipe) {
        Ingredient input1 = recipe.getIngredients().get(0);
        Ingredient input2 = recipe.getIngredients().get(1);
        ItemStack input1s = recipe.getInput1Stack();
        ItemStack input2s = recipe.getInput2Stack();

        buffer.writeItemStack(recipe.getRecipeOutput(), false);
        input1.write(buffer);
        input2.write(buffer);
        buffer.writeItemStack(input1s);
        buffer.writeItemStack(input2s);
        buffer.writeInt(recipe.getSmeltTime());

    }
}
