package teamHTBP.vida.Entity;


import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityFaintLight extends Entity {

    public EntityFaintLight(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }
    public EntityFaintLight(EntityType<?> entityTypeIn, World worldIn,int type) {
        super(entityTypeIn, worldIn);this.type = type;
    }
    private static final Logger PRIVATE_LOGGER = LogManager.getLogger();
    private static final DataParameter<Integer> TYPE = EntityDataManager.createKey(EntityFaintLight.class, DataSerializers.VARINT);
    private int type = 0;
    private int ticks = 0;
    public int meta  =  0;

    @Override
    protected void registerData() {
          this.dataManager.register(TYPE,type);
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

}
