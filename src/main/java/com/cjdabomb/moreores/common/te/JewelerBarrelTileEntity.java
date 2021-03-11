package com.cjdabomb.moreores.common.te;

import com.cjdabomb.moreores.MoreOres;
import com.cjdabomb.moreores.common.blocks.JewelerBarrelBlock;
import com.cjdabomb.moreores.common.containers.JewelerBarrelContainer;
import com.cjdabomb.moreores.core.init.ItemInit;
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

public class JewelerBarrelTileEntity extends LockableLootTileEntity {

	public static int slots = 97;
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
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return slots;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		if (getStackInSlot(97).getStack() == ItemInit.JASPER.get().getDefaultInstance()) {
			canConfirm = true;
		} else {
			canConfirm = false;
		}
		return null;
	}

	public ItemStack getItem() {
		return this.items.get(slots);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUsableByPlayer(PlayerEntity player) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void clear() {
		this.items.clear();

	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + MoreOres.MOD_ID + ".jeweler_barrel");
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new JewelerBarrelContainer(id, player, this);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}

		return compound;
	}

	@Override
	public void read(BlockState state, CompoundNBT nbt) {
		super.read(state, nbt);
		this.items = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		// TODO Auto-generated method stub
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.items = itemsIn;

	}


	@Override
	public void openInventory(PlayerEntity player) {
		BlockState blockstate = this.getBlockState();
		boolean flag = blockstate.get(JewelerBarrelBlock.PROPERTY_OPEN);
		if (!flag) {
			this.playSound(blockstate, SoundEvents.BLOCK_BARREL_OPEN);
			this.setOpenProperty(blockstate, true);
		}
	}
	
	@Override
	public void closeInventory(PlayerEntity player) {
		BlockState blockstate = this.getBlockState();
		this.playSound(blockstate, SoundEvents.BLOCK_BARREL_CLOSE);
		this.setOpenProperty(blockstate, false);
		
	}

	private void setOpenProperty(BlockState state, boolean open) {
		this.world.setBlockState(this.getPos(), state.with(BarrelBlock.PROPERTY_OPEN, Boolean.valueOf(open)), 3);
	}

	private void playSound(BlockState state, SoundEvent sound) {
		Vector3i vector3i = state.get(BarrelBlock.PROPERTY_FACING).getDirectionVec();
		double d0 = (double) this.pos.getX() + 0.5D + (double) vector3i.getX() / 2.0D;
		double d1 = (double) this.pos.getY() + 0.5D + (double) vector3i.getY() / 2.0D;
		double d2 = (double) this.pos.getZ() + 0.5D + (double) vector3i.getZ() / 2.0D;
		this.world.playSound((PlayerEntity) null, d0, d1, d2, sound, SoundCategory.BLOCKS, 0.5F,
				this.world.rand.nextFloat() * 0.1F + 0.9F);
	}
}
