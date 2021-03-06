package com.github.alexthe666.iceandfire.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;

public class DragonAILookIdle extends EntityAIBase {
	private EntityDragonBase dragon;
	private double lookX;
	private double lookZ;
	private int idleTime;

	public DragonAILookIdle(EntityDragonBase prehistoric) {
		this.dragon = prehistoric;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute() {
		if (!this.dragon.canMove()) {
			return false;
		}
		return this.dragon.getRNG().nextFloat() < 0.02F;
	}

	@Override
	public boolean continueExecuting() {
		return this.idleTime >= 0;
	}

	@Override
	public void startExecuting() {
		double d0 = (Math.PI * 2D) * this.dragon.getRNG().nextDouble();
		this.lookX = Math.cos(d0);
		this.lookZ = Math.sin(d0);
		this.idleTime = 20 + this.dragon.getRNG().nextInt(20);
	}

	@Override
	public void updateTask() {
		--this.idleTime;
		this.dragon.getLookHelper().setLookPosition(this.dragon.posX + this.lookX, this.dragon.posY + this.dragon.getEyeHeight(), this.dragon.posZ + this.lookZ, this.dragon.getHorizontalFaceSpeed(), this.dragon.getVerticalFaceSpeed());
	}
}