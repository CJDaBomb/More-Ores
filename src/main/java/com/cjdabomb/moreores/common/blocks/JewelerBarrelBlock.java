package com.cjdabomb.moreores.common.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class JewelerBarrelBlock extends DirectionalBlock{

	   public static final BooleanProperty PROPERTY_OPEN = BlockStateProperties.OPEN;
	   public static final DirectionProperty FACING = BlockStateProperties.FACING;

	   public JewelerBarrelBlock(AbstractBlock.Properties properties) {
	      super(properties);
	      this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(PROPERTY_OPEN, Boolean.valueOf(false)));
	   }
	   
	   @Override
	    public BlockState getStateForPlacement(BlockItemUseContext context) {
	      return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite()).with(PROPERTY_OPEN, Boolean.valueOf(false));
	   }

	    @Override
	    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	        super.fillStateContainer(builder);
	        builder.add(FACING, PROPERTY_OPEN);
	    }
}
