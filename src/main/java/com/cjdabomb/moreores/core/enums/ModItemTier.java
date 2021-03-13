package com.cjdabomb.moreores.core.enums;

import java.util.function.Supplier;

import com.cjdabomb.moreores.core.init.ItemInit;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum ModItemTier implements IItemTier {
			ALEXANDRITE(4, 1642, 14.0F, 3.0F, 29, () -> Ingredient.of(ItemInit.ALEXANDRITE.get())), 
			ALUMINIUM(2, 288, 6.0F, 3.0F, 15, () -> Ingredient.of(ItemInit.ALUMINIUM_INGOT.get())),
			COBALT(3, 1678, 5.5F, 3.0F, 17, () -> Ingredient.of(ItemInit.COBALT_INGOT.get())),
			ELECTRUM(2, 344, 6.1F, 3.0F, 21, () -> Ingredient.of(ItemInit.ELECTRUM_INGOT.get())),
			JASPER(2, 1290, 12.0F, 3.0F, 20, () -> Ingredient.of(ItemInit.JASPER.get())),
			ROSE_GOLD(2, 768, 12.0F, 3.0F, 22, () -> Ingredient.of(ItemInit.ROSE_GOLD_INGOT.get())),
			SAPPHIRE(3, 1311, 12.2F, 3.0F, 19, () -> Ingredient.of(ItemInit.SAPPHIRE.get())),
			SHADOW(5, 3655, 18.0F, 3.0F, 31, () -> Ingredient.of(ItemInit.SHADOW_INGOT.get())),
			SILVER(2, 444, 7.0F, 6.5F, 16, () -> Ingredient.of(ItemInit.SILVER_INGOT.get())),
			SUNSTONE(4, 1978, 16.0F, 3.0F, 27, () -> Ingredient.of(ItemInit.SUNSTONE.get())),
			TURQUOISE(3, 1340, 12.5F, 3.0F, 16, () -> Ingredient.of(ItemInit.TURQUOISE.get()));
			
		   
		private final LazyValue<Ingredient> repairMaterial;
   
		   private final int enchantability;
   
		   private final float attackDamage;
  
		   private final float efficiency;
		   
		   private final int maxUses;
   
		   private final int harvestLevel;
   
		   ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
			 this.harvestLevel = harvestLevel;
			 this.maxUses = maxUses;
			 this.efficiency = efficiency;
			 this.attackDamage = attackDamage;
			 this.enchantability = enchantability;
			 this.repairMaterial = new LazyValue<>(repairMaterial);
		  }
		   
    @Override
    public int getUses() {
        return this.maxUses;
    }
 
    @Override
    public float getSpeed() {
        return this.efficiency;
    }
 
    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }
 
    @Override
    public int getLevel() {
        return this.harvestLevel;
    }
 
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }
 
    public Ingredient getRepairIngredient() {
		return this.repairMaterial.get();
    	
    }

}
