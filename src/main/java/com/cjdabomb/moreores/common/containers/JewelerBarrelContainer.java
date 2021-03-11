package com.cjdabomb.moreores.common.containers;

import java.util.Objects;

import com.cjdabomb.moreores.common.te.JewelerBarrelTileEntity;
import com.cjdabomb.moreores.core.init.BlockInit;
import com.cjdabomb.moreores.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class JewelerBarrelContainer extends Container {

	public final JewelerBarrelTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;
	public static int upgrade;

	public JewelerBarrelContainer(final int windowId, final PlayerInventory playerInv,
			final JewelerBarrelTileEntity te) {
		super(ContainerTypeInit.JEWELER_BARREL_CONTAINER.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.of(te.getWorld(), te.getPos());
		int i = 0;

		int slotSizePlus2 = 18;
		for (int column = 0; column < 12; column++) {
			for (int row = 0; row < upgrade + 4; row++) {
				this.addSlot(new Slot((IInventory) te, i++, column * slotSizePlus2, row * slotSizePlus2));
			}
		}

		if (upgrade == 0) {
			this.addSlot(new Slot((IInventory) te, 97, 80, 30));
		} else if (upgrade == 1) {
			this.addSlot(new Slot((IInventory) te, 97, 80, 46));
		} else if (upgrade == 2) {
			this.addSlot(new Slot((IInventory) te, 97, 80, 62));
		} else if (upgrade == 3) {
			this.addSlot(new Slot((IInventory) te, 97, 80, 78));
		} else if (upgrade == 4) {
			this.addSlot(new Slot((IInventory) te, 97, 80, 94));
		}

		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col * row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 142));
		}
	}

	public JewelerBarrelContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static JewelerBarrelTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packer Buffer cannot be null.");
		final TileEntity te = playerInv.player.world.getTileEntity(data.readBlockPos());
		if (te instanceof JewelerBarrelTileEntity) {
			return (JewelerBarrelTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		// TODO Auto-generated method stub
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.JEWELER_BARREL.get());
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {

		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack2 = slot.getStack();
			stack = stack2.copy();
			if (index < 36
					&& !this.mergeItemStack(stack2, JewelerBarrelTileEntity.slots, this.inventorySlots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.mergeItemStack(stack2, 0, JewelerBarrelTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}
			if (stack2.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}

	public void syncContainer() {
		this.detectAndSendChanges();
	}

}
