package teamHTBP.vida.common.block.function;


import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamHTBP.vida.common.block.base.VidaBaseEntityBlock;
import teamHTBP.vida.common.blockentity.ElementCoreAltarBlockEntity;
import teamHTBP.vida.common.blockentity.VidaBlockEntityLoader;
import teamHTBP.vida.core.element.EnumElements;
import teamHTBP.vida.api.core.element.IElement;
import teamHTBP.vida.common.item.VidaItemLoader;
import teamHTBP.vida.common.particle.option.CubeParticleOptions;

import java.util.Random;

/*元素祭坛
@Version 0.0.1*/
public class ElementCoreAltarBlock extends VidaBaseEntityBlock<ElementCoreAltarBlockEntity> {
    public ElementCoreAltarBlock() {
        super(Properties.of(Material.METAL).noOcclusion().strength(5.0f, 5.0f).sound(SoundType.STONE),
                VidaBlockEntityLoader.TileEntityElementCoreAltar);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            ElementCoreAltarBlockEntity elementCoreAltarBlockEntity = (ElementCoreAltarBlockEntity) worldIn.getBlockEntity(pos);
            Item handItem = player.getInventory().getSelected().getItem();
            boolean handItemResult = (handItem == VidaItemLoader.WAND_VIDA.get());
            if (handIn == InteractionHand.MAIN_HAND && !player.isShiftKeyDown() && !handItemResult) {
                if (player.getInventory().getSelected() != ItemStack.EMPTY) {
                    //先检测是不是核心物品
                    ItemStack stack = player.getInventory().getSelected().copy();
                    stack.setCount(1);
                    boolean result = elementCoreAltarBlockEntity.setCoreItemStack(stack);
                    if (result) {
                        this.decreasePlayerItem(player);
                        worldIn.sendBlockUpdated(pos, state, state, 3);
                        return InteractionResult.SUCCESS;
                    } else {
                        //不是的话放入祭坛物品中
                        stack = player.getInventory().getSelected().copy();
                        stack.setCount(1);
                        result = elementCoreAltarBlockEntity.setAltarItemStack(stack);
                        if (result) {
                            this.decreasePlayerItem(player);
                            worldIn.sendBlockUpdated(pos, state, state, 3);
                            return InteractionResult.SUCCESS;
                        } else
                            return super.use(state, worldIn, pos, player, handIn, hit);
                    }
                }
                //拿到祭坛中的物品
            } else if (handIn == InteractionHand.MAIN_HAND && player.isShiftKeyDown() && !handItemResult) {
                ItemStack itemStack = elementCoreAltarBlockEntity.getAltarItemStack();
                if (itemStack != ItemStack.EMPTY || !itemStack.isEmpty()) {
                    player.getInventory().add(itemStack);
                }
                worldIn.sendBlockUpdated(pos, state, state, 3);
            } else if (!player.isShiftKeyDown() && handItemResult) {
                //如果法杖右键的话，开始检测
                elementCoreAltarBlockEntity.isWAND_VIDACilck = true;
                worldIn.sendBlockUpdated(pos, state, state, 3);
                return InteractionResult.SUCCESS;
            } else
                return super.use(state, worldIn, pos, player, handIn, hit);
        }
        return super.use(state, worldIn, pos, player, handIn, hit);
    }


    public void decreasePlayerItem(Player player) {
        if (player.isCreative()) {
            return;
        } else {
            player.getInventory().getSelected().shrink(1);
        }
    }

    /**当祭坛被破坏时,内部物品掉出*/
    @Override
    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        ElementCoreAltarBlockEntity elementCoreAltarBlockEntity = (ElementCoreAltarBlockEntity) worldIn.getBlockEntity(pos);
        if (elementCoreAltarBlockEntity != null && !worldIn.isClientSide) {
            // 如果被破坏掉出祭坛物品
            for(ItemStack altarItem : elementCoreAltarBlockEntity.altarItem){
                if(!altarItem.isEmpty()){
                    worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), altarItem));
                }
            }
            // 核心物品
            if (elementCoreAltarBlockEntity.coreItem != ItemStack.EMPTY && !elementCoreAltarBlockEntity.coreItem.isEmpty())
                worldIn.addFreshEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), elementCoreAltarBlockEntity.coreItem));

            // 清空祭坛内物品
            elementCoreAltarBlockEntity.altarItem.clear();
            elementCoreAltarBlockEntity.coreItem = ItemStack.EMPTY;
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        if (rand.nextBoolean()) {
            ElementCoreAltarBlockEntity elementCoreAltarBlockEntity = (ElementCoreAltarBlockEntity) worldIn.getBlockEntity(pos);
            if (elementCoreAltarBlockEntity != null && elementCoreAltarBlockEntity.isProgressing && elementCoreAltarBlockEntity.moveup >= 0.8f) {
                IElement element = elementCoreAltarBlockEntity.element;
                if (element instanceof EnumElements && rand.nextFloat() >= 0.65D) {
                    double speedX = rand.nextBoolean() ? 0 - rand.nextFloat() / 1000.0F : 0 + rand.nextFloat() / 1000.0F;
                    double speedY = 0.010 + rand.nextFloat() / 1000f;
                    double speedZ = rand.nextBoolean() ? 0 + rand.nextFloat() / 1000.0f : 0 - rand.nextFloat() / 1000.0f;
                    double posX = rand.nextBoolean() ? pos.getX() + 0.5f + rand.nextFloat() / 4 : pos.getX() + 0.5f - rand.nextFloat() / 4;
                    double posY = rand.nextBoolean() ? pos.getY() + 1.2F + rand.nextFloat() / 4 : pos.getY() + 1.2F - rand.nextFloat() / 4;
                    double posZ = rand.nextBoolean() ? pos.getZ() + 0.5f + rand.nextFloat() / 4 : pos.getZ() + 0.5f - rand.nextFloat() / 4;
                    float r = 1;
                    float g = 1;
                    float b = 1;
                    switch ((EnumElements) element) {
                        case GOLD:
                            r = 255;
                            g = 255;
                            b = rand.nextInt(30) + 180;
                            break;
                        case WOOD:
                            r = 73;
                            g = 175;
                            b = 92;
                            break;
                        case AQUA:
                            r = 73;
                            g = 203;
                            b = 255;
                            break;
                        case FIRE:
                            r = 255;
                            g = 30;
                            b = 43;
                            break;
                        case EARTH:
                            r = 186;
                            g = 184;
                            b = 111;
                            break;
                    }
                    worldIn.addParticle(new CubeParticleOptions(r, g, b, 0.03f), posX, posY, posZ, speedX, speedY, speedZ);
                }
            }
        }

    }
}


