package teamHTBP.vida.common.item.staff;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import teamHTBP.vida.common.itemGroup.ItemGroupLoader;
import teamHTBP.vida.common.particle.ParticleLoader;
import teamHTBP.vida.common.particle.base.BaseParticleData;
import teamHTBP.vida.common.particle.base.BaseVidaParticleType;

/**
 * 生命法杖
 *
 * @version 0.0.2
 **/
public class ItemVidaWand extends Item {
    public ItemVidaWand() {
        super(new Item.Properties().group(ItemGroupLoader.VIDA_ITEM_GROUP).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity entity, Hand handIn) {
        if(worldIn.isRemote){
            worldIn.addParticle(new BaseParticleData(ParticleLoader.trailParticle.get(),1,1,1,1,100), entity.getPosX(), entity.getPosY(), entity.getPosZ(),  1, 1, 1);
        }
        return super.onItemRightClick(worldIn, entity, handIn);
    }
}