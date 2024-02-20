package com.github.alexthe666.alexsmobs.entity.ai;

import com.github.alexthe666.alexsmobs.entity.EntityCapuchinMonkey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CapuchinAIMelee extends MeleeAttackGoal {

    private final EntityCapuchinMonkey monkey;

    public CapuchinAIMelee(EntityCapuchinMonkey monkey, double speedIn, boolean useLongMemory) {
        super(monkey, speedIn, useLongMemory);
        this.monkey = monkey;
    }

    @Override
    public boolean canUse() {
        return super.canUse() && !monkey.attackDecision;
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && !monkey.attackDecision;
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
