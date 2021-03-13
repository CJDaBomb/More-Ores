package com.cjdabomb.moreores.common.blocks;

import javax.annotation.Nullable;

import com.cjdabomb.moreores.common.entities.DarkMatterEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class DarkMatterBlock extends TNTBlock {
	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

	   public DarkMatterBlock(AbstractBlock.Properties properties) {
	      super(properties);
	      this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.FALSE));
	   }
	   
	   @Override
	   public void catchFire(@NotNull BlockState state, @NotNull World world, @NotNull BlockPos pos, @Nullable net.minecraft.util.Direction face, @Nullable LivingEntity igniter) {
	      explode(world, pos, igniter);
	   }
	   
	   @Override
	   public void onPlace(BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, BlockState oldState, boolean isMoving) {
	      if (!oldState.is(state.getBlock())) {
	         if (worldIn.hasNeighborSignal(pos)) {
	            catchFire(state, worldIn, pos, null, null);
	            worldIn.removeBlock(pos, false);
	         }

	      }
	   }
	   
	   @Override
	   public void neighborChanged(@NotNull BlockState state, World worldIn, @NotNull BlockPos pos, @NotNull Block blockIn, @NotNull BlockPos fromPos, boolean isMoving) {
	      if (worldIn.hasNeighborSignal(pos)) {
	         catchFire(state, worldIn, pos, null, null);
	         worldIn.removeBlock(pos, false);
	      }

	   }

	  @Override
	   public void playerWillDestroy(World worldIn, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull PlayerEntity player) {
	      if (!worldIn.isClientSide() && !player.isCreative() && state.getValue(UNSTABLE)) {
	         catchFire(state, worldIn, pos, null, null);
	      }

	      super.playerWillDestroy(worldIn, pos, state, player);
	   }

	   @Override
	   public void wasExploded(World worldIn, @NotNull BlockPos pos, @NotNull Explosion explosionIn) {
	      if (!worldIn.isClientSide) {
	         DarkMatterEntity tntentity = new DarkMatterEntity(worldIn, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, explosionIn.getSourceMob());
	         tntentity.setFuse((short)(worldIn.random.nextInt(tntentity.getLife() / 4) + tntentity.getLife() / 8));
	         worldIn.addFreshEntity(tntentity);
	      }
	   }
	   
	   @Deprecated
	   public static void explode(World world, @NotNull BlockPos worldIn) {
	      explode(world, worldIn, null);
	   }
	   
	   @Deprecated
	   private static void explode(World worldIn, BlockPos pos, @Nullable LivingEntity entityIn) {
	      if (!worldIn.isClientSide) {
	    	  DarkMatterEntity tntentity = new DarkMatterEntity(worldIn, (double)pos.getX() + 0.5D, pos.getY(), (double)pos.getZ() + 0.5D, entityIn);
	         worldIn.addFreshEntity(tntentity);
	         worldIn.playSound(null, tntentity.getX(), tntentity.getY(), tntentity.getZ(), SoundEvents.TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
	      }
	   }
	   
	   @Override
	   public @NotNull ActionResultType use(@NotNull BlockState state, @NotNull World worldIn, @NotNull BlockPos pos, PlayerEntity player, @NotNull Hand handIn, @NotNull BlockRayTraceResult hit) {
	      ItemStack itemstack = player.getItemInHand(handIn);
	      Item item = itemstack.getItem();
	      if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
	         return super.use(state, worldIn, pos, player, handIn, hit);
	      } else {
	         catchFire(state, worldIn, pos, hit.getDirection(), player);
	         worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
	         if (!player.isCreative()) {
	            if (item == Items.FLINT_AND_STEEL) {
	               itemstack.hurtAndBreak(1, player, (player1) -> player1.broadcastBreakEvent(handIn));
	            } else {
	               itemstack.shrink(1);
	            }
	         }

	         return ActionResultType.sidedSuccess(worldIn.isClientSide);
	      }
	   }
	   
	   @Override
	   public void onProjectileHit(World worldIn, @NotNull BlockState state, @NotNull BlockRayTraceResult hit, @NotNull ProjectileEntity projectile) {
	      if (!worldIn.isClientSide) {
	         Entity entity = projectile.getOwner();
	         if (projectile.isOnFire()) {
	            BlockPos blockpos = hit.getBlockPos();
	            catchFire(state, worldIn, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
	            worldIn.removeBlock(blockpos, false);
	         }
	      }

	   }

	   @Override
	   public boolean dropFromExplosion(@NotNull Explosion explosionIn) {
	      return false;
	   }
	   
	   @Override
	   protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(UNSTABLE);
	   }
}	   