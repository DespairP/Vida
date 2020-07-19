package teamHTBP.vida.Entity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityFaintLight extends Entity implements IEntityAdditionalSpawnData {

    public EntityFaintLight(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
        types = 1;
        double d1 = (double)this.getPosX() + 0.5D;
        double d2 = (double)this.getPosY() + 0.5D;
        double d3 = (double)this.getPosZ() + 0.5D ;
        this.setBoundingBox(new AxisAlignedBB(d1 - 0, d2 - 0, d3 - 0, d1 + 1, d2 + 1, d3 + 1));

    }
    public EntityFaintLight(EntityType<?> entityTypeIn, World worldIn,int type) {
        super(entityTypeIn, worldIn);
        this.types = type;
        double d1 = (double)this.getPosX() + 0.5D;
        double d2 = (double)this.getPosY() + 0.5D;
        double d3 = (double)this.getPosZ() + 0.5D;
        this.setBoundingBox(new AxisAlignedBB(d1 - 0, d2 - 0, d3 - 0, d1 + 2, d2 + 2, d3 + 2));
    }
    private static final Logger PRIVATE_LOGGER = LogManager.getLogger();
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityFaintLight.class, DataSerializers.VARINT);
    private int types = 0;
    private int ticks = 0;
    public int meta  =  0;

    @Override
    protected void registerData() {
          this.dataManager.register(TYPE,types);
    }

    @Override
    protected void readAdditional(CompoundNBT compound) {
        this.dataManager.set(TYPE,compound.getInt("type"));
    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {
        compound.putInt("type",this.dataManager.get(TYPE));
    }

    //提醒客户端生成实体
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public int getFaintLightType(){
        return this.dataManager.get(TYPE);
    }

    public void setFaintLightType(int type){
        this.dataManager.set(TYPE, type);
    }


    @Override
    public void tick() {
        super.tick();
            this.meta = this.meta + 1 < 29?this.meta + 1 : 0 ;
    }

    public void notifyDataManagerChange(DataParameter<?> key) {
        super.notifyDataManagerChange(key);
        if(TYPE.equals(key)){
            this.types = this.dataManager.get(TYPE);
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
        return 1.0F;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }
}
