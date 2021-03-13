package com.cjdabomb.moreores.common.te;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ISidedInventoryProvider;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public abstract class InventoryTile extends TileEntity implements ITickableTileEntity, ISidedInventory, ISidedInventoryProvider {

    public int size;
    public int timer;
    public boolean requiresUpdate = true;

    net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
            net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    protected LazyOptional<IItemHandlerModifiable> handler = LazyOptional.of(this::createHandler);

    public InventoryTile(TileEntityType<?> tileEntityTypeIn, int size) {
        super(tileEntityTypeIn);
        this.size = size;
    }

    @Override
    public void tick() {
        this.timer++;
        if (this.level != null) {
            if (this.requiresUpdate) {
                updateTile();
                this.requiresUpdate = false;
            }
        }
    }


    @Nonnull
    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(@Nonnull net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
        if (!this.remove && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == Direction.UP)
                return handlers[0].cast();
            else if (facing == Direction.DOWN)
                return handlers[1].cast();
            else
                return handlers[2].cast();
        }
        return super.getCapability(capability, facing);
    }

    public LazyOptional<IItemHandlerModifiable> getHandler() {
        return this.handler;
    }

    public IItemHandlerModifiable getInventory() {
        return this.handler.orElse(createHandler());
    }

    @Nonnull
    public IItemHandlerModifiable createHandler() {
        return new ItemStackHandler(this.size) {
            @Override
            protected void onContentsChanged(int slot) {
                updateTile();
                setChanged();
            }
        };
    }

    public ItemStack getItemInSlot(int slot) {
        return this.handler.map(inventory -> inventory.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        ItemStack itemIn = stack.copy();
        stack.shrink(itemIn.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inventory -> inventory.insertItem(slot, itemIn, false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack extractItem(int slot) {
        int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inventory -> inventory.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }

    public int getSize() {
        return this.size;
    }


    @Override
    public void load(@NotNull BlockState state, @Nonnull CompoundNBT compound) {
        super.load(state, compound);
        ListNBT list = compound.getList("Items", 10);
        for (int x = 0; x < list.size(); ++x) {
            CompoundNBT nbt = list.getCompound(x);
            int r = nbt.getByte("Slot") & 255;
            this.handler.ifPresent(inventory -> {
                int invslots = inventory.getSlots();
                if (r < invslots) {
                    inventory.setStackInSlot(r, ItemStack.of(nbt));
                }
            });
        }
        this.requiresUpdate = true;
    }

    @Nonnull
    @Override
    public CompoundNBT save(@Nonnull CompoundNBT compound) {
        super.save(compound);
        ListNBT list = new ListNBT();
        this.handler.ifPresent(inventory -> {
            int slots = inventory.getSlots();
            for (int x = 0; x < slots; ++x) {
                ItemStack stack = inventory.getStackInSlot(x);
                if (!stack.isEmpty()) {
                    CompoundNBT nbt = new CompoundNBT();
                    nbt.putByte("Slot", (byte) x);
                    stack.save(nbt);
                    list.add(nbt);
                }
            }
        });
        if (!list.isEmpty()) {
            compound.put("Items", list);
        }
        return compound;
    }

    public void updateTile() {
        this.requestModelDataUpdate();
        this.setChanged();
        if (this.getLevel() != null) {
            this.getLevel().sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), 3);
        }
    }


    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.getBlockPos(), -1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.handleUpdateTag(getBlockState(), pkt.getTag());
    }

    @Override
    @Nonnull
    public CompoundNBT getUpdateTag() {
        return this.serializeNBT();
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.deserializeNBT(tag);
    }


    @Nonnull
    @Override
    abstract public int[] getSlotsForFace(@Nonnull Direction side);

    @Override
    abstract public boolean canPlaceItemThroughFace(int index, @Nonnull ItemStack itemStackIn, @Nullable Direction direction);

    @Override
    abstract public boolean canTakeItemThroughFace(int index, @Nonnull ItemStack stack, @Nonnull Direction direction);

    @Override
    public int getContainerSize() {
        return size;
    }

    @Override
    abstract public boolean canPlaceItem(int index, @Nonnull ItemStack stack);


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Nonnull
    @Override
    public ItemStack getItem(int index) {
        return this.getInventory().getStackInSlot(index);
    }

    @Nonnull
    @Override
    public ItemStack removeItem(int index, int count) {
        return this.getInventory().extractItem(index, count, false);
    }

    @Nonnull
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return this.extractItem(index);
    }

    @Override
    public void setItem(int index, @Nonnull ItemStack stack) {
        this.getInventory().setStackInSlot(index, stack);
    }

    @Override
    public boolean stillValid(@Nonnull PlayerEntity player) {
        return false;
    }

    @Override
    public void clearContent() {

    }

	public Container createMenu(int index, PlayerInventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
