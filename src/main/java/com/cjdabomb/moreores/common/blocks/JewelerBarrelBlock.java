package com.cjdabomb.moreores.common.blocks;

import com.cjdabomb.moreores.common.te.JewelerBarrelTileEntity;
import com.cjdabomb.moreores.core.init.TileEntityTypeInit;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class JewelerBarrelBlock extends DirectionalBlock {

	public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;
	public static final DirectionProperty FACING = BlockStateProperties.FACING;

	public JewelerBarrelBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(PROPERTY_OPEN, Boolean.FALSE));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite())
				.setValue(PROPERTY_OPEN, Boolean.FALSE);
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, PROPERTY_OPEN);
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {

		return TileEntityTypeInit.JEWELER_BARREL_TILE_ENTITY.get().create();
	}
	@SuppressWarnings("deprecation")
	@Override
	public @NotNull ActionResultType use(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull PlayerEntity player,
										 @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
		if (worldIn.isClientSide()) {
			return ActionResultType.SUCCESS;
		} else {
			TileEntity te = worldIn.getBlockEntity(pos);
			if (te instanceof JewelerBarrelTileEntity) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (JewelerBarrelTileEntity) te, pos);
			}
		}
		return ActionResultType.CONSUME;
	}

}
