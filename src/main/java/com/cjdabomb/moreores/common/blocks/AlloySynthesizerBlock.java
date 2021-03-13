package com.cjdabomb.moreores.common.blocks;

import com.cjdabomb.moreores.common.te.AlloySynthesizerTileEntity;
import com.cjdabomb.moreores.core.init.TileEntityTypeInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class AlloySynthesizerBlock extends DirectionalBlock {

	public static final DirectionProperty FACING = DirectionProperty.create("outwards", Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public AlloySynthesizerBlock(Properties builder) {
		super(builder);
		this.registerDefaultState(
				this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, Boolean.FALSE));
	}
	@SuppressWarnings("deprecation")
	@Override
	@Nonnull
	public ActionResultType use(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player,
								@NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
		if (worldIn.isClientSide()) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity te = worldIn.getBlockEntity(pos);
			if (te instanceof AlloySynthesizerTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (AlloySynthesizerTileEntity) te, pos);
			}
		}
		return ActionResultType.CONSUME;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, @NotNull World worldIn, @NotNull BlockPos pos, @NotNull Random rand) {
		if (stateIn.getValue(LIT)) {
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;
			if (rand.nextDouble() < 0.1D) {
				worldIn.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F,
						false);
			}

			Direction direction = stateIn.getValue(FACING);
			Direction.Axis direction$axis = direction.getAxis();
			@SuppressWarnings("unused")
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double) direction.getStepX() * 0.52D : d4;
			double d6 = rand.nextDouble() * 6.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getStepZ() * 0.52D : d4;
			worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	@SuppressWarnings("deprecation")
	@Override
	public @NotNull BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public @NotNull BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {

		return TileEntityTypeInit.ALLOY_SYNTHESIZER_TILE_ENTITY.get().create();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof AlloySynthesizerTileEntity) {
                InventoryHelper.dropContents(worldIn, pos, (AlloySynthesizerTileEntity) tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		if (state.getValue(LIT)) {
			return 15;
		}
		return super.getLightValue(state, world, pos);
	}

	@Override
	public void setPlacedBy(@NotNull World worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer,
							@NotNull ItemStack stack) {

	}

}
