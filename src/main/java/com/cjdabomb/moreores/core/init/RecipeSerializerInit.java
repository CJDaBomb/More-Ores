package com.cjdabomb.moreores.core.init;

import java.util.Objects;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.recipe.AlloySmeltingRecipe;
import com.cjdabomb.moreores.core.recipe.AlloySmeltingSerializer;
import com.cjdabomb.moreores.core.recipe.IAlloySmeltingRecipe;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipeSerializerInit {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MoreOres.MOD_ID);


    public static final IRecipeSerializer<AlloySmeltingRecipe> ALLOY_SMELTING_RECIPE_SERIALIZER = new AlloySmeltingSerializer();

    public static final IRecipeType<IAlloySmeltingRecipe> ALLOY_TYPE = Registry.register(Registry.RECIPE_TYPE, IAlloySmeltingRecipe.RECIPE_TYPE_ID, new RecipeType<>());


    public static final RegistryObject<IRecipeSerializer<?>> ALLOY_SMELTING_SERIALIZER = RECIPE_SERIALIZERS.register("alloysmelting",
            () -> ALLOY_SMELTING_RECIPE_SERIALIZER);


    private static class RecipeType<T extends IRecipe<?>> implements IRecipeType<T> {
        @Override
        public String toString() {
            return Objects.requireNonNull(Registry.RECIPE_TYPE.getKey(this)).toString();
        }
    }

}