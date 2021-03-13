package com.cjdabomb.moreores.common.te;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.blocks.JewelerBarrelBlock;
import com.cjdabomb.moreores.common.containers.JewelerBarrelContainer;
import com.cjdabomb.moreores.core.init.SoundInit;
import com.cjdabomb.moreores.core.init.TileEntityTypeInit;
import net.minecraft.block.BarrelBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class JewelerBarrelTileEntity extends LockableLootTileEntity {

    public static int slots = 97    ;
    public static boolean canConfirm;

    protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);

    public JewelerBarrelTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
        // TODO Auto-generated constructor stub
    }

    public JewelerBarrelTileEntity() {
        this(TileEntityTypeInit.JEWELER_BARREL_TILE_ENTITY.get());
    }

    @Override
    public int getContainerSize() {
        // TODO Auto-generated method stub
        return slots;
    }

    @Override
    public boolean isEmpty() {

        for(ItemStack stack : this.items) {
            return true;
        }
        return false;
    }



    @Override
    public void setItem(int index, @Nonnull ItemStack stack) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean stillValid(@NotNull PlayerEntity player) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public void clearContent() {
        this.items.clear();

    }

    @Override
    public @NotNull ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
        return super.removeItem(p_70298_1_, p_70298_2_).split(p_70298_2_);
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int p_70304_1_) {
        return super.removeItemNoUpdate(p_70304_1_);
    }

    @Override
    protected @NotNull ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + MoreOres.MOD_ID + ".jeweler_barrel");
    }

    @Override
    protected @NotNull Container createMenu(int id, @Nonnull PlayerInventory player) {
        return new JewelerBarrelContainer(id, player, this);
    }

    @Override
    public @NotNull CompoundNBT save(@NotNull CompoundNBT compound) {
        super.save(compound);
        if (!this.trySaveLootTable(compound)) {
            ItemStackHelper.saveAllItems(compound, this.items);
        }

        return compound;
    }

    @Override
    public void load(@NotNull BlockState state, @NotNull CompoundNBT nbt) {
        super.load(state, nbt);
        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(nbt)) {
            ItemStackHelper.loadAllItems(nbt, this.items);
        }
    }

    @Override
    protected @NotNull NonNullList<ItemStack> getItems() {
        // TODO Auto-generated method stub
        return this.items;
    }

    @Override
    protected void setItems(@NotNull NonNullList<ItemStack> itemsIn) {
        this.items = itemsIn;

    }


    @Override
    public void startOpen(@NotNull PlayerEntity player) {
        BlockState blockstate = this.getBlockState();
        boolean flag = blockstate.getValue(JewelerBarrelBlock.PROPERTY_OPEN);
        if (!flag) {
            this.playSound(blockstate, SoundInit.JEWELER_BARREL_OPEN.get());
            this.setOpenProperty(blockstate, true);
        }
    }


    public void stopOpen(@NotNull PlayerEntity player) {
        BlockState blockstate = this.getBlockState();
        this.playSound(blockstate, SoundEvents.BARREL_CLOSE);
        this.setOpenProperty(blockstate, false);

    }

    private void setOpenProperty(BlockState state, boolean open) {
        assert this.level != null;
        this.level.setBlock(this.getBlockPos(), state.setValue(BarrelBlock.OPEN, open), 3);
    }

    private void playSound(BlockState state, SoundEvent sound) {
        Vector3i vector3i = state.getValue(BarrelBlock.FACING).getNormal();
        double d0 = (double) this.worldPosition.getX() + 0.5D + (double) vector3i.getX() / 2.0D;
        double d1 = (double) this.worldPosition.getY() + 0.5D + (double) vector3i.getY() / 2.0D;
        double d2 = (double) this.worldPosition.getZ() + 0.5D + (double) vector3i.getZ() / 2.0D;
        assert this.level != null;
        this.level.playSound(null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F,
                this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
