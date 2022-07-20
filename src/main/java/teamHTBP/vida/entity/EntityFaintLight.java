package teamHTBP.vida.entity;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.elementHelper.ElementManager;
import teamHTBP.vida.helper.elementHelper.EnumElements;
import teamHTBP.vida.helper.elementHelper.IElement;

import java.util.Optional;

public class EntityFaintLight extends Entity implements IEntityAdditionalSpawnData {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final EntityDataAccessor<String> TYPE = SynchedEntityData.defineId(EntityFaintLight.class, EntityDataSerializers.STRING);
    private IElement types;

    public EntityFaintLight(EntityType<?> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
        types = EnumElements.GOLD;
        double d1 = this.getX() + 0.5D;
        double d2 = this.getY() + 0.5D;
        double d3 = this.getZ() + 0.5D;
        //this.setBoundingBox(new AxisAlignedBB(d1 - 0, d2 - 0, d3 - 0, d1 + 1, d2 + 1, d3 + 1));

    }
    public EntityFaintLight(EntityType<?> entityTypeIn, Level worldIn, IElement type) {
        super(entityTypeIn, worldIn);
        this.types = type;
        double d1 = this.getX() + 0.5D;
        double d2 = this.getY() + 0.5D;
        double d3 = this.getZ() + 0.5D;
        //this.setBoundingBox(new AxisAlignedBB(d1 - 0, d2 - 0, d3 - 0, d1 + 2, d2 + 2, d3 + 2));
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TYPE, Optional.ofNullable(types).orElse(EnumElements.NONE).getElementName().toString());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.entityData.set(TYPE, compound.getString("type"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("type", this.entityData.get(TYPE));
    }

    //提醒客户端生成实体
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public IElement getFaintLightType() {
        return ElementManager.get(this.entityData.get(TYPE));
    }

    public void setFaintLightType(IElement type) {
        this.entityData.set(TYPE, type.getElementName().toString());
    }


    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (TYPE.equals(key)) {
            this.types = ElementManager.get(entityData.get(TYPE));
        }
    }


    @Override
    public void writeSpawnData(FriendlyByteBuf buffer) {

    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
    }

    @Override
    public float getPickRadius() {
        return 0.5F;
    }

    @Override
    public boolean isPickable() {
        return true;
    }
}
