package com.cjdabomb.moreores.common.entities;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.cjdabomb.moreores.core.init.EntityTypeInit;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class DarkMatterEntity extends TNTEntity {
	private static final DataParameter<Integer> FUSE = EntityDataManager.defineId(DarkMatterEntity.class, DataSerializers.INT);
	   @Nullable
	   private LivingEntity tntPlacedBy;
	   private int fuse = 200;

	public DarkMatterEntity(EntityType<? extends TNTEntity> type, World worldIn) {
		super(type, worldIn);
		this.blocksBuilding = true;
	}
	
	public DarkMatterEntity(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
	      this(EntityTypeInit.DARK_MATTER.get(), worldIn);
	      this.setPos(x, y, z);
	      double d0 = worldIn.random.nextDouble() * (double)((float)Math.PI * 2F);
	      this.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
	      this.setFuse(120);
	      this.xo = x;
	      this.yo = y;
	      this.zo = z;
	      this.tntPlacedBy = igniter;
	   }
		
	   @Override
	   protected void defineSynchedData() {
	      this.entityData.define(FUSE,120);
	   }
	   
	   @Override
	   protected boolean isMovementNoisy() {
	      return false;
	   }

	   /**
	    * Returns true if other Entities should be prevented from moving through this Entity.
	    */
	   @Override
	public boolean isPickable() {
		return false;
	}

	   /**
	    * Called to update the entity's position/logic.
	    */
	   @Override
	   public void tick() {
	      if (!this.isNoGravity()) {
	         this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
	      }

	      this.move(MoverType.SELF, this.getDeltaMovement());
	      this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
	      if (this.onGround) {
	         this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
	      }

	      --this.fuse;
	      if (this.fuse <= 0) {
	         this.remove();
	         if (!this.level.isClientSide) {
	            this.explode();
	         }
	      } else {
	         this.updateInWaterStateAndDoFluidPushing();
	         if (this.level.isClientSide) {
	            this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D, 0.0D);
	         }
	      }

	   }
	   
	   @Override
	   protected void explode() {
	      float f = 20.0F;
	      this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), f, Explosion.Mode.BREAK);
	   }
	   
	   @Override
	   protected void addAdditionalSaveData(CompoundNBT compound) {
	      compound.putShort("Fuse", (short)this.getLife());
	   }

	   /**
	    * (abstract) Protected helper method to read subclass entity data from NBT.
	    */
	   @Override
	   protected void readAdditionalSaveData(CompoundNBT compound) {
	      this.setFuse(compound.getShort("Fuse"));
	   }

	   /**
	    * returns null or the entityliving it was placed or ignited by
	    */
	   @Nullable
	   public LivingEntity getOwner() {
	      return this.tntPlacedBy;
	   }

	   @Override
	   protected float getEyeHeight(@Nonnull Pose poseIn, @Nonnull EntitySize sizeIn) {
	      return 0.15F;
	   }
	   
	   @Override
	   public void setFuse(int fuseIn) {
	      this.entityData.set(FUSE, fuseIn);
	      this.fuse = fuseIn;
	   }
	   
	   @Override
	   public void onSyncedDataUpdated(@Nonnull DataParameter<?> key) {
	      if (FUSE.equals(key)) {
	         this.fuse = this.getFuse();
	      }

	   }

	   @Override
	   public int getFuse() {
	      return this.entityData.get(FUSE);
	   }
	   
	   @Override
	   public int getLife() {
	      return this.fuse;
	   }

	   @Nonnull
	   @Override
	   public IPacket<?> getAddEntityPacket() {
	      return new SSpawnObjectPacket(this);
	   }

}
