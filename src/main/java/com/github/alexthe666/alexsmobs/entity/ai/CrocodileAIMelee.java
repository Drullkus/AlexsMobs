package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityCrocodile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CrocodileAIMelee extends MeleeAttackGoal {

    private final EntityCrocodile crocodile;

    public CrocodileAIMelee(EntityCrocodile crocodile, double speedIn, boolean useLongMemory) {
        super(crocodile, speedIn, useLongMemory);
        this.crocodile = crocodile;
    }

    @Override
    public boolean canUse() {

        return super.canUse() && crocodile.getPassengers().isEmpty();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && crocodile.getPassengers().isEmpty();
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy) {
        if (this.canPerformAttack(enemy)) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(enemy);
        }

    }

}
