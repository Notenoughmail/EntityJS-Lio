package net.liopyu.entityjs.entities;

import net.liopyu.entityjs.builders.BaseEntityBuilder;
import net.liopyu.entityjs.builders.BaseEntityJSBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class BaseEntityJS extends LivingEntity implements IAnimatableJS {

    private final AnimationFactory animationFactory;

    protected final BaseEntityJSBuilder builder;

    public BaseEntityJS(BaseEntityJSBuilder builder, EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.builder = builder;
        animationFactory = GeckoLibUtil.createFactory(this);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return NonNullList.withSize(0, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot p_21127_) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot p_21036_, ItemStack p_21037_) {

    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    public BaseEntityBuilder<?> getBuilder() {
        return builder;
    }

    @Override
    public AnimationFactory getFactory() {
        return animationFactory;
    }


    public boolean isPushable() {
        return getBuilder().canBePushed;
    }

    @Override
    public HumanoidArm getMainArm() {
        return null;
    }

    public boolean canBeCollidedWith() {
        return getBuilder().canBeCollidedWith;
    }

    public boolean isAttackable() {
        return getBuilder().isAttackable;
    }

    //Start of the method adding madness - liopyu
    @Override
    protected boolean canAddPassenger(Entity entity) {
        return builder.passengerPredicate.test(entity);
    }

    @Override
    protected boolean shouldDropLoot() {
        return builder.shouldDropLoot;
    }

    @Override
    protected boolean canRide(Entity entity) {
        return builder.passengerPredicate.test(entity);
    }

    @Override
    protected boolean isAffectedByFluids() {
        return builder.isAffectedByFluids;
    }

    @Override
    protected boolean isAlwaysExperienceDropper() {
        return builder.isAlwaysExperienceDropper;
    }

    @Override
    protected boolean isImmobile() {
        return builder.isImmobile;
    }

    @Override
    protected boolean onSoulSpeedBlock() {
        return builder.onSoulSpeedBlock;
    }

    @Override
    protected float getBlockJumpFactor() {
        return builder.getBlockJumpFactor;
    }

    @Override
    protected float getBlockSpeedFactor() {
        return builder.getBlockSpeedFactor;
    }

    @Override
    protected float getJumpPower() {
        return builder.getJumpPower;
    }

    @Override
    protected float getSoundVolume() {
        return builder.getSoundVolume;
    }

    @Override
    protected float getWaterSlowDown() {
        return builder.getWaterSlowDown;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return builder.getDeathSound;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return builder.getSwimSound;
    }

    @Override
    public int calculateFallDamage(float fallDamage, float fallDistance) {
        return builder.fallDamageFunction.apply(fallDamage, fallDistance);
    }
}
