package teamHTBP.vida.entity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import teamHTBP.vida.helper.element.EnumElements;
import teamHTBP.vida.helper.element.IElement;

import java.util.Optional;

public class EntityFaintLight extends Entity implements IEntityAdditionalSpawnData {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityFaintLight.class, DataSerializers.STRING);
    private IElement types;

    public EntityFaintLight(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        types = EnumElements.GOLD;
        double d1 = this.getPosX() + 0.5D;
        double d2 = this.getPosY() + 0.5D;
        double d3 = this.getPosZ() + 0.5D;
        //this.setBoundingBox(new AxisAlignedBB(d1 - 0, d2 - 0, d3 - 0, d1 + 1, d2 + 1, d3 + 1));

    }
    public EntityFaintLight(EntityType<?> entityTypeIn, World worldIn, IElement type) {
        super(entityTypeIn, worldIn);
        this.types = type;
        double d1 = this.getPosX() + 0.5D;
        double d2 = this.getPosY() + 0.5D;
        double d3 = this.getPosZ() + 0.5D;
        //this.setBoundingBox(new AxisAlignedBB(d1 - 0, d2 - 0, d3 - 0, d1 + 2, d2 + 2, d3 + 2));
    }

    @Override
    protected void registerData() {
        this.dataManager.register(TYPE, Optional.ofNullable(types).orElseGet(()->EnumElements.NONE).getElementName());
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        this.dataManager.set(TYPE, compound.getString("type"));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putString("type", this.dataManager.get(TYPE));
    }

    //提醒客户端生成实体
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public IElement getFaintLightType() {
        return EnumElements.valueOf(this.dataManager.get(TYPE));
    }

    public void setFaintLightType(IElement type) {
        this.dataManager.set(TYPE, type.getElementName());
    }


    @Override
    public void tick() {
        super.tick();
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if (TYPE.equals(key)) {
            this.types = EnumElements.valueOf(dataManager.get(TYPE));
        }
    }


    @Override
    public void writeSpawnData(PacketBuffer buffer) {

    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
    }

    @Override
    public float getCollisionBorderSize() {
        return 0.5F;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
