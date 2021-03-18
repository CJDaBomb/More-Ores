package com.cjdabomb.moreores.core.util;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.core.init.ItemInit;
import com.google.gson.JsonObject;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.Alternative;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.MatchTool;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod(MoreOres.MOD_ID)
public class GlobalLootModifiers {

    public static final boolean ENABLE = true;

    public GlobalLootModifiers() {
        if (ENABLE) {
            GLM.register(FMLJavaModLoadingContext.get().getModEventBus());
        }
    }

    private static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, MoreOres.MOD_ID);

    private static final RegistryObject<SmeltingModifier.Serializer> SMELTING = GLM.register("smelting", SmeltingModifier.Serializer::new);
    private static final RegistryObject<DoubleSmeltingModifier.Serializer> DOUBLE_SMELTING = GLM.register("double_drop_smelting", DoubleSmeltingModifier.Serializer::new);
    private static final RegistryObject<DoubleDropsModifier.Serializer> DOUBLE_DROPS = GLM.register("double_drops", DoubleDropsModifier.Serializer::new);

    @Mod.EventBusSubscriber(modid = MoreOres.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class EventHandlers {
        @SubscribeEvent
        public static void runData(GatherDataEvent event) {
            if (ENABLE)
                event.getGenerator().addProvider(new DataProvider(event.getGenerator(), MoreOres.MOD_ID));
        }
    }

    private static class DataProvider extends GlobalLootModifierProvider {


        public DataProvider(DataGenerator gen, String modid) {
            super(gen, MoreOres.MOD_ID);
        }

        @Override
        protected void start() {
            add("smelting", SMELTING.get(), new SmeltingModifier(
                    new ILootCondition[]{new Alternative.Builder(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.SUNSTONE_AXE.get())),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.SUNSTONE_PICKAXE.get())),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.SUNSTONE_SHOVEL.get())),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.SUNSTONE_SWORD.get())
                            )).build()

                    }
            ));

            add("double_drop_smelting", DOUBLE_SMELTING.get(), new DoubleSmeltingModifier(
                    new ILootCondition[]{new Alternative.Builder(
                            BlockStateProperty.hasBlockStateProperties(Blocks.GOLD_ORE),
                            BlockStateProperty.hasBlockStateProperties(Blocks.IRON_ORE)).build(),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.ALEXANDRITE_PICKAXE.get())).build()
                    }
            ));

            add("double_drops", DOUBLE_DROPS.get(), new DoubleDropsModifier(
                    new ILootCondition[]{new Alternative.Builder(
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.ALEXANDRITE_AXE.get())),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.ALEXANDRITE_PICKAXE.get())),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.ALEXANDRITE_SHOVEL.get())),
                            MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemInit.ALEXANDRITE_SWORD.get())
                            )).build()

                    }
            ));

        }
    }

    private static class DoubleSmeltingModifier extends LootModifier {
        public DoubleSmeltingModifier(ILootCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @Nonnull
        @Override
        public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            ArrayList<ItemStack> ret = new ArrayList<>();
            generatedLoot.forEach((stack) -> ret.add(smelt(stack, context)));
            return ret;
        }

        private static ItemStack smelt(ItemStack stack, LootContext context) {
            return context.getLevel().getRecipeManager().getRecipeFor(IRecipeType.SMELTING, new Inventory(stack), context.getLevel())
                    .map(FurnaceRecipe::getResultItem)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        }

        private static class Serializer extends GlobalLootModifierSerializer<DoubleSmeltingModifier> {
            @Override
            public DoubleSmeltingModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
                return new DoubleSmeltingModifier(conditionsIn);
            }


            @Override
            public JsonObject write(DoubleSmeltingModifier instance) {
                return makeConditions(instance.conditions);
            }
        }
    }


    private static class SmeltingModifier extends LootModifier {
        public SmeltingModifier(ILootCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @Nonnull
        @Override
        public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            ArrayList<ItemStack> ret = new ArrayList<>();
            generatedLoot.forEach((stack) -> ret.add(smelt(stack, context)));
            return ret;
        }

        private static ItemStack smelt(ItemStack stack, LootContext context) {
            return context.getLevel().getRecipeManager().getRecipeFor(IRecipeType.SMELTING, new Inventory(stack), context.getLevel())
                    .map(FurnaceRecipe::getResultItem)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        }

        private static class Serializer extends GlobalLootModifierSerializer<SmeltingModifier> {
            @Override
            public SmeltingModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
                return new SmeltingModifier(conditionsIn);
            }

            @Override
            public JsonObject write(SmeltingModifier instance) {
                return makeConditions(instance.conditions);
            }
        }
    }

    private static class DoubleDropsModifier extends LootModifier {
        public DoubleDropsModifier(ILootCondition[] conditionsIn) {
            super(conditionsIn);
        }

        @Nonnull
        @Override
        public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context){
            List<ItemStack> ret = Arrays.asList();
            for(ItemStack stack : generatedLoot){
                ret.add(stack);
                ret.add(stack.copy());
            }
            return ret;
        }

        private static ItemStack smelt(ItemStack stack, LootContext context) {
            return context.getLevel().getRecipeManager().getRecipeFor(IRecipeType.SMELTING, new Inventory(stack), context.getLevel())
                    .map(FurnaceRecipe::getResultItem)
                    .filter(itemStack -> !itemStack.isEmpty())
                    .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount()))
                    .orElse(stack);
        }

        private static class Serializer extends GlobalLootModifierSerializer<DoubleDropsModifier> {
            @Override
            public DoubleDropsModifier read(ResourceLocation name, JsonObject json, ILootCondition[] conditionsIn) {
                return new DoubleDropsModifier(conditionsIn);
            }

            @Override
            public JsonObject write(DoubleDropsModifier instance) {
                return makeConditions(instance.conditions);
            }
        }
    }
}
