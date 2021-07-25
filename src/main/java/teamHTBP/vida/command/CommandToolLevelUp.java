package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.item.staff.IElementTools;
import teamHTBP.vida.block.BlockEventLoaderServer;

public class CommandToolLevelUp implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        int exp = commandContext.getArgument("exp", Integer.class);
        PlayerEntity entity = commandContext.getSource().asPlayer();
        if(entity != null && entity.getHeldItemMainhand() != ItemStack.EMPTY){
            ItemStack stack = entity.getHeldItemMainhand();
            if(stack.getItem() instanceof IElementTools){
                stack.getOrCreateTag().putInt("pickaxeEXP", exp);
                BlockEventLoaderServer.levelupTool(stack);
            }else {
                commandContext.getSource().sendErrorMessage(new StringTextComponent(I18n.format("error.tool_unsuitable.anno")));
            }
        }else{
            commandContext.getSource().sendErrorMessage(new StringTextComponent(I18n.format("error.player_not_found.anno")));
        }
        return 0;
    }
}
