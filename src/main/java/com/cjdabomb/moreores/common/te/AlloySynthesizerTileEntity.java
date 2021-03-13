package com.cjdabomb.moreores.common.te;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.cjdabomb.moreores.common.blocks.AlloySynthesizerBlock;
import com.cjdabomb.moreores.common.containers.AlloySynthesizerContainer;
import com.cjdabomb.moreores.core.init.RecipeSerializerInit;
import com.cjdabomb.moreores.core.init.TileEntityTypeInit;
import com.cjdabomb.moreores.core.recipe.AlloySmeltingRecipe;
import com.cjdabomb.moreores.core.recipe.IAlloySmeltingRecipe;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class AlloySynthesizerTileEntity extends InventoryTile implements ITickableTileEntity, INamedContainerProvider {
    public static IRecipeType<?> getRecipeType() {
        return recipeType;
    }

    private static final IRecipeType<?> recipeType = RecipeSerializerInit.ALLOY_TYPE;
    public boolean isSmelting = false;
    public boolean isBurning = false;
    public int smelttime = 0;
    public int burntime1 = 0;
    public int burntime2 = 0;
    public int totalburntime1 = 200;
    public int totalburntime2 = 200;
    public int recipeTime = 200;

    public int getRecipeID() {
        return recipeID;
    }

    private int recipeID = -1;
    @SuppressWarnings("unused")
	private final int tick = 0;


    //*Set the Recipe remotely
    public void setRecipeIn(AlloySmeltingRecipe recipeIn) {
        this.recipeIn = recipeIn;
        if (recipeIn != null) {
            this.recipeID = idFromRecipe(recipeIn);
        } else {
            this.recipeID = -1;
        }
        this.setChanged();
    }

    private AlloySmeltingRecipe recipeIn;

    //*read NBT from SaveFile to get the Data
    @Override
    public void load(@NotNull BlockState state, @Nonnull CompoundNBT compound) {
        super.load(state, compound);
        smelttime = compound.getInt("smelttime");
        burntime1 = compound.getInt("bt1");
        burntime2 = compound.getInt("bt2");
        totalburntime1 = compound.getInt("tbt1");
        totalburntime2 = compound.getInt("tbt2");
        recipeTime = compound.getInt("rct");
        isBurning = compound.getBoolean("burning");
        isSmelting = compound.getBoolean("smelting");
        recipeID = compound.getInt("selected");
        recipeIn = recipeFromId(recipeID);
    }

    //*Write Data to Disk when needed
    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT compound) {
        super.save(compound);
        compound.putInt("smelttime", smelttime);
        compound.putInt("bt1", burntime1);
        compound.putInt("bt2", burntime2);
        compound.putInt("tbt1", totalburntime1);
        compound.putInt("tbt2", totalburntime2);
        compound.putInt("rct", recipeTime);
        compound.putBoolean("smelting", isSmelting);
        compound.putBoolean("burning", isBurning);
        compound.putInt("selected", recipeID);
        return compound;
    }

    //*Get the Recipe by the Recipe ID
    public AlloySmeltingRecipe recipeFromId(int id) {
        Set<IRecipe<?>> recipes = AlloySynthesizerTileEntity.findRecipeByType(RecipeSerializerInit.ALLOY_TYPE, this.getLevel());
        if (id >= 0 && recipes.size() - 1 >= id && recipes.toArray()[id] instanceof AlloySmeltingRecipe) {
            return (AlloySmeltingRecipe) recipes.toArray()[id];
        }
        return null;
    }

    //*Get the ID by the Recipe
    public Integer idFromRecipe(AlloySmeltingRecipe recipeIn) {
        int i = 0;
        Set<IRecipe<?>> recipes = AlloySynthesizerTileEntity.findRecipeByType(RecipeSerializerInit.ALLOY_TYPE, this.getLevel());
        for (IRecipe<?> r : recipes) {
            if (r.equals(recipeIn)) {
                return i;
            }
            i++;
        }
        return null;
    }

    //*Slots for Hopper Injection
    @Nonnull
    @Override
    public int[] getSlotsForFace(@Nonnull Direction side) {
        switch (side) {
            case WEST:
            case EAST:
            case SOUTH:
            case NORTH:
                return fuelSlotSelect();
            case DOWN:
                return new int[]{4};
            case UP:
                return new int[]{0, 1};
            default:
                return new int[0];
        }
    }


    //*Validate the Item the Hopper wants to put in
    @Override
    public boolean canPlaceItemThroughFace(int index, @Nonnull ItemStack itemStackIn, @Nullable Direction direction) {
        return canPlaceItem(index, itemStackIn);
    }

    //*Select the Fuel slot based on the other one/also allows from Items to pass in from the Side
    public int[] fuelSlotSelect() {
        if (this.getItemInSlot(2).getItem().equals(this.getItemInSlot(3).getItem())) {
            if (this.getItemInSlot(2).getCount() > this.getItemInSlot(3).getCount()) {
                return new int[]{3, 0, 1};
            } else if (this.getItemInSlot(2).getCount() == this.getItemInSlot(3).getCount()) {
                return new int[]{2, 3, 0, 1};
            } else {
                return new int[]{2, 0, 1};
            }
        } else {
            if (this.getItemInSlot(2).isEmpty()) {
                return new int[]{2, 0, 1};
            } else if (this.getItemInSlot(3).isEmpty()) {
                return new int[]{3, 0, 1};
            }
            return new int[]{2, 3, 0, 1};
        }
    }

    //*Shortened Method
    public static boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

    //*Check if the Item is actually valid for a given Slot Index
    @Override
    public boolean canPlaceItem(int index, @Nonnull ItemStack stack) {
        if (index == 4) {
            return false;
        }
        if (index == 3 || index == 2) {
            return isFuel(stack);
        }
        if (index == 1) {
            if (this.getItemInSlot(0).getItem().equals(stack.getItem())) {
                return false;
            }
            if (this.getItemInSlot(1).getItem().equals(stack.getItem()) && isItemInRecipe(stack)) {
                return true;
            }
            return this.getItemInSlot(1).isEmpty() && isItemInRecipe(stack);
        }
        if (index == 0) {
            if (this.getItemInSlot(1).getItem().equals(stack.getItem())) {
                return false;
            }
            if (this.getItemInSlot(0).getItem().equals(stack.getItem()) && isItemInRecipe(stack)) {
                return true;
            }
            return this.getItemInSlot(0).isEmpty() && isItemInRecipe(stack);
        }
        return false;
    }

    //*Check if the Item is inside a given Recipe
    private boolean isItemInRecipe(ItemStack stack) {
        if (recipeIn == null) {
            return true;
        }
        return recipeIn.getInput1Stack().getItem().equals(stack.getItem()) || recipeIn.getInput2Stack().getItem().equals(stack.getItem());
    }

    //*Hopper Item Extraction
    @Override
    public boolean canTakeItemThroughFace(int index, @Nonnull ItemStack stack, @Nonnull Direction direction) {
        return index == 4 && this.getItemInSlot(4).getCount() > 0;
    }

    //*Constructor
    public AlloySynthesizerTileEntity() {
        super(TileEntityTypeInit.ALLOY_SYNTHESIZER_TILE_ENTITY.get(), 5);
        this.recipeIn = null;
    }

    public static List<IRecipe<?>> getRecipesWithInput(IRecipeType<?> type, World world, Ingredient ingredient) {
        Set<IRecipe<?>> recipes = findRecipeByType(IRecipeType.SMELTING, world);
        LootTable table = Objects.requireNonNull(world.getServer()).getLootTables().get(new ResourceLocation(""));
        try {
            Field f = table.getClass().getDeclaredField("combinedFunctions");
            f.setAccessible(true);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return recipes.parallelStream().filter(k -> k.getIngredients().contains(ingredient)).collect(Collectors.toList());
    }

    //*Tick for the Furnace
    @Override
    public void tick() {
        //*If the World is Null return immediately
        if (this.level == null) {
            return;
            //*This is for the Client Render to have the Screen Update
        } else if (this.level.isClientSide) {
            if (this.smelttime < this.recipeTime) {
                this.smelttime++;
            } else {
                this.isSmelting = false;
            }
            if (this.burntime1 > 0) {
                burntime1--;
            }
            if (this.burntime2 > 0) {
                burntime2--;
            }
            return;
        }
        //*Controls if the Client needs to be rendered
        boolean updateRender = false;
        //*If we have a RecipeID set the Recipe Accordingly
        if (recipeID >= 0 && this.recipeIn == null) {
            this.recipeIn = recipeFromId(recipeID);
        }
        //*If there is a valid Recipe matching the Items in the Slot without a selected Recipe, select it
        if (getRecipe(this.getItemInSlot(0), this.getItemInSlot(1), this.level) != null && this.recipeIn == null) {
            this.recipeIn = getRecipe(this.getItemInSlot(0), this.getItemInSlot(1), this.level);
            //*If the Recipe becomes invalid it is nulled
        } else if (getRecipe(this.getItemInSlot(0), this.getItemInSlot(1), this.level) == null && this.recipeIn != null && !this.getItemInSlot(1).isEmpty() && !this.getItemInSlot(0).isEmpty()) {
            this.recipeIn = null;
        }
        //*Check if there needs to be more fuel
        if (this.recipeIn != null && canSmeltRecipe()) {
            if (recipeTime != recipeIn.getSmeltTime()) {
                recipeTime = recipeIn.getSmeltTime();
            }

            //*Check if the render needs to be Updated
            if (checkSmelting()) {
                updateRender = true;
            }
            //*Reduce the Burn time
            if (burntime1 > 0) {
                this.burntime1--;
            }
            //*Reapply Fuel if needed
            if (this.burntime1 == 0 && isFuel(this.getItemInSlot(2)) && (isFuel(this.getItemInSlot(3)) || this.burntime2 > 0)) {
                ItemStack stack1 = this.getInventory().extractItem(2, 1, false);
                this.burntime1 = ForgeHooks.getBurnTime(stack1);
                this.totalburntime1 = ForgeHooks.getBurnTime(stack1);
                updateRender = true;
            }
            //*Reduce the Burn time
            if (burntime2 > 0) {
                burntime2--;
            }
            //*Re apply Fuel if needed
            if (this.burntime2 == 0 && isFuel(this.getItemInSlot(3)) && (isFuel(this.getItemInSlot(2)) || this.burntime1 > 0)) {
                ItemStack stack2 = this.getInventory().extractItem(3, 1, false);
                this.burntime2 = ForgeHooks.getBurnTime(stack2);
                this.totalburntime2 = ForgeHooks.getBurnTime(stack2);
                updateRender = true;
            }
            //*Reset the Smelting Process
        } else {
            this.smelttime = 0;
            this.recipeTime = 0;
            if (this.burntime1 > 0) {
                this.burntime1--;
            }
            if (this.burntime2 > 0) {
                this.burntime2--;
            }
            updateRender = true;
            this.isSmelting = false;
            if (this.level != null) {
                this.level.setBlock(this.getBlockPos(), this.level.getBlockState(this.getBlockPos()).setValue(AlloySynthesizerBlock.LIT, false), 3);
            }
        }
        //*Check if the Furnace is operating
        this.isBurning = this.burntime1 > 0 && this.burntime2 > 0;
        if (!this.isBurning && this.isSmelting) {
            this.isSmelting = false;
            if (this.level != null) {
                this.level.setBlock(this.getBlockPos(), this.level.getBlockState(this.getBlockPos()).setValue(AlloySynthesizerBlock.LIT, false), 3);
            }
        }
        //*Update the Render
        if (updateRender) {
            super.updateTile();
        }
        //*Tell the Game it needs to save Data because values changed
        this.setChanged();
    }

    //*Check if Recipe can be actually done
    public boolean canSmeltRecipe() {
        ItemStack s1 = this.getItemInSlot(0);
        ItemStack s2 = this.getItemInSlot(1);
        ItemStack r1 = this.recipeIn.getInput1Stack();
        ItemStack r2 = this.recipeIn.getInput2Stack();
        if (s1.getItem().equals(r1.getItem())) {
            if (s2.getItem().equals(r2.getItem())) {
                if (s1.getCount() >= r1.getCount() && s2.getCount() >= r2.getCount()) {
                    return this.getItemInSlot(4).getMaxStackSize() != this.getItemInSlot(4).getCount();
                }
            }
        } else if (s1.getItem().equals(r2.getItem())) {
            if (s2.getItem().equals(r1.getItem())) {
                if (s1.getCount() >= r2.getCount() && s2.getCount() >= r1.getCount()) {
                    return this.getItemInSlot(4).getMaxStackSize() != this.getItemInSlot(4).getCount();
                }
            }
        } else {
            return false;
        }
        return false;
    }

    //*Check and Oversee the Smelting Process
    public boolean checkSmelting() {
        //initialize Smelting
        //*If there is no Fuel/recipe is null no smelting possible
        if (!this.isBurning || this.recipeIn == null) {
            return false;
        } else if (this.smelttime < this.recipeTime && !this.isSmelting) {
            if (!canSmeltRecipe()) {
                return false;
            } else {
                this.isSmelting = true;
                if (this.level != null) {
                    this.level.setBlock(this.getBlockPos(), this.level.getBlockState(this.getBlockPos()).setValue(AlloySynthesizerBlock.LIT, true), 3);
                }
                this.smelttime = 0;
                this.smelttime++;
                return true;
            }
            //smelting the Item
        } else if (this.smelttime < this.recipeTime) {
            smelttime++;
            //finished smelting the Recipe
        } else if (this.smelttime == this.recipeTime) {
            this.smelttime = 0;
            int stackcount = this.recipeIn.getResultItem().getCount() + this.getInventory().getStackInSlot(4).getCount();
            this.getInventory().setStackInSlot(4, new ItemStack(this.recipeIn.getResultItem().getItem(), stackcount));
            if (this.getInventory().getStackInSlot(0).getItem().equals(this.recipeIn.getInput1Stack().getItem())) {
                this.getInventory().extractItem(0, this.recipeIn.getInput1Stack().getCount(), false);
                this.getInventory().extractItem(1, this.recipeIn.getInput2Stack().getCount(), false);
            } else {
                this.getInventory().extractItem(1, this.recipeIn.getInput1Stack().getCount(), false);
                this.getInventory().extractItem(0, this.recipeIn.getInput2Stack().getCount(), false);
            }
            this.isSmelting = false;
        }
        return false;
    }

    //*NamedContainerProvider Method
    @Nonnull
    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.alloy_synthesizer");
    }

    //*Create the Container if it is needed
    
	

    //*Gets the Recipe from the World Object
    @Nullable
    private AlloySmeltingRecipe getRecipe(ItemStack input1, ItemStack input2, World w) {
        if (input1 == null || this.level == null || input2 == null) {
            return null;
        }
        Set<IRecipe<?>> recipes = findRecipeByType(RecipeSerializerInit.ALLOY_TYPE, w);
        for (IRecipe<?> recipe : recipes) {
            if (recipe instanceof AlloySmeltingRecipe && ((AlloySmeltingRecipe) recipe).matches(new RecipeWrapper(this.getInventory()), level)) {
                return (AlloySmeltingRecipe) recipe;
            }
        }
        findRecipeByType(IRecipeType.SMELTING, level);
        return null;
    }

    public static Set<IRecipe<?>> findRecipeByType(IRecipeType<?> type, World world) {
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType().equals(type)).collect(Collectors.toSet())
                : new HashSet<>();
    }

    @OnlyIn(Dist.CLIENT)
    public static Set<IRecipe<?>> findRecipeByType(IRecipeType<IAlloySmeltingRecipe> alloyType) {
        @SuppressWarnings("resource")
		ClientWorld world = Minecraft.getInstance().level;
        return world != null ? world.getRecipeManager().getRecipes().stream().filter(recipe -> recipe.getType().equals(getRecipeType())).collect(Collectors.toSet())
                : new HashSet<>();
    }

    public static Set<ItemStack> getAllRecipeInputs(IRecipeType<?> typeIn, World w) {
        Set<ItemStack> inputs = new HashSet<>();
        Set<IRecipe<?>> recipes = findRecipeByType(typeIn, w);
        for (IRecipe<?> recipe : recipes) {
            NonNullList<Ingredient> ingredients = recipe.getIngredients();
            ingredients.forEach(ingredient -> inputs.addAll(Arrays.asList(ingredient.getItems())));
        }
        return inputs;
    }

    //*Help Method for the Hopper
    @Nonnull
    @Override
    public ISidedInventory getContainer(@Nonnull BlockState state, @Nonnull IWorld world, @Nonnull BlockPos pos) {
        return this;
    }

	public Object getItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Nullable
	@Override
	public Container createMenu(int index, @NotNull PlayerInventory inventory, @NotNull PlayerEntity player) {
		// TODO Auto-generated method stub
		return new AlloySynthesizerContainer(index, inventory, this);
	}
}
