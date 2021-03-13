package com.cjdabomb.moreores.common.containers;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.cjdabomb.moreores.common.te.InventoryTile;
import com.cjdabomb.moreores.core.init.BlockInit;
import com.cjdabomb.moreores.core.init.ContainerTypeInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class AlloySynthesizerContainer extends AbstractMachineContainer {
	/**
	 * 
	 */
	
	private final IWorldPosCallable canInteractWithCallable;
	public TileEntity tile;

	public AlloySynthesizerContainer(int id, PlayerInventory inventory,
			final TileEntity te) {
		super(ContainerTypeInit.ALLOY_SYNTHESIZER_CONTAINER.get(), id, (IItemHandlerModifiable) inventory);
		this.tile = te;
		IItemHandlerModifiable tileInv = ((InventoryTile) te).getInventory();
		this.canInteractWithCallable = IWorldPosCallable.create(Objects.requireNonNull(te.getLevel()), te.getBlockPos());
		this.addSlot(new SlotItemHandler(tileInv, 0, 34, 16));
		this.addSlot(new SlotItemHandler(tileInv, 1, 119, 16));
		this.addSlot(new SlotItemHandler(tileInv, 2, 34, 57));
		this.addSlot(new SlotItemHandler(tileInv, 3, 119, 57));
		this.addSlot(new SlotItemHandler(tileInv, 4, 77, 41));
		System.out.println(inventory.toString());

	}

	public AlloySynthesizerContainer(final int windowId, final PlayerInventory playerInventory,
			final PacketBuffer data) {
		this(windowId, playerInventory, Objects.requireNonNull(getTileEntity(data, playerInventory.player.level)));
	}
	@SuppressWarnings("unused")
	private static TileEntity getTileEntity(PacketBuffer data, World world) {
		// TODO Auto-generated method stub
		return null;
	}
	@SuppressWarnings("unused")
	public AlloySynthesizerContainer(final int windowId, final PlayerInventory playerInventory,
            final PacketBuffer data, TileEntity te) {
        this(windowId, playerInventory, te);
    }


	@Override
	public boolean stillValid(@Nonnull PlayerEntity playerIn) {
		return stillValid(canInteractWithCallable, playerIn, BlockInit.ALLOY_SYNTHESIZER.get());
	}


	public static boolean isFuel(ItemStack stack) {
		return net.minecraftforge.common.ForgeHooks.getBurnTime(stack) > 0;
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		Slot slot = this.slots.get(index);
		ItemStack slotStack = slot.getItem().copy();
		// 36/37 SmeltSlots 38/39FuelSlots 40 OutputSLot
		if (!slot.hasItem()) {
			return ItemStack.EMPTY;
		}
		if (index < 36) {
			// Fuel
			if (isFuel(slot.getItem())) {
				Slot slot1 = this.slots.get(38);
				Slot slot2 = this.slots.get(39);
				int slotcount = slot.getItem().getCount();
				// Set Stacks if not there
				if (slotcount > 0 && !slot1.hasItem()) {
					slotcount--;
					slot1.set(new ItemStack(slot.getItem().getItem(), 1));
				}
				if (slotcount > 0 && !slot2.hasItem()) {
					slotcount--;
					slot2.set(new ItemStack(slot.getItem().getItem(), 1));
				}

				int missing1 = 64 - slot1.getItem().getCount();
				int missing2 = 64 - slot2.getItem().getCount();
				if (missing1 == 0 && missing2 == 0 && (slot1.getItem().getItem().equals(slot.getItem().getItem())
						&& slot2.getItem().getItem().equals(slot.getItem().getItem()))) {
					return ItemStack.EMPTY;
				}
				boolean onlyOne = !slot1.getItem().getItem().equals(slot.getItem().getItem())
						|| !slot2.getItem().getItem().equals(slot.getItem().getItem())
						|| (missing1 == 0 || missing2 == 0);

				if (!onlyOne) {
					while (slotcount > 0 && (missing1 != 0 || missing2 != 0)) {
						if (missing1 > missing2) {
							slot1.getItem().setCount(slot1.getItem().getCount() + 1);
							missing1--;
						} else {
							slot2.getItem().setCount(slot2.getItem().getCount() + 1);
							missing2--;
						}
						slotcount--;
					}
				} else {
					if (missing1 != 0 && slot1.getItem().getItem().equals(slot.getItem().getItem())) {
						while (slotcount > 0 && missing1 != 0) {
							slot1.getItem().setCount(slot1.getItem().getCount() + 1);
							missing1--;
							slotcount--;
						}
					} else {
						while (slotcount > 0 && missing2 != 0) {
							slot2.getItem().setCount(slot2.getItem().getCount() + 1);
							missing2--;
							slotcount--;
						}
					}
				}
				slot.getItem().setCount(slotcount);
			} else if (Objects.requireNonNull(slot.getItem().getItem().getRegistryName()).toString().toLowerCase()
					.contains("ingot")) {
				Slot slot1 = this.slots.get(36);
				Slot slot2 = this.slots.get(37);
				if (slot1.getItem().getItem().equals(slotStack.getItem())) {
					moveItemStackTo(slot.getItem(), 36, 37, false);
				} else if (slot2.getItem().getItem().equals(slotStack.getItem())) {
					moveItemStackTo(slot.getItem(), 37, 38, false);
				} else if (!slot1.hasItem()) {
					slot1.set(new ItemStack(slotStack.getItem(), slotStack.getCount()));
					slot.getItem().setCount(0);
				} else if (!slot2.hasItem()) {
					slot2.set(new ItemStack(slotStack.getItem(), slotStack.getCount()));
					slot.getItem().setCount(0);
				}
			}
		}
		// Transfer to Player inventory
		else {
			moveItemStackTo(slot.getItem(), 0, 35, true);
		}
		return ItemStack.EMPTY;
	}

}
