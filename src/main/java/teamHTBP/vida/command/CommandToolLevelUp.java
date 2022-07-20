package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.event.server.BlockEventLoaderServer;
import teamHTBP.vida.item.staff.IElementTools;

public class CommandToolLevelUp implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        int exp = commandContext.getArgument("exp", Integer.class);
        PlayerEntity entity = commandContext.getSource().getPlayerOrException();
        if (entity.getMainHandItem() != ItemStack.EMPTY) {
            ItemStack stack = entity.getMainHandItem();
            if (stack.getItem() instanceof IElementTools) {
                stack.getOrCreateTag().putInt("pickaxeExp", exp);
                BlockEventLoaderServer.levelupTool(stack);
            } else {
                commandContext.getSource().sendFailure(new StringTextComponent(I18n.get("error.tool_unsuitable.anno")));
            }
        } else {
            commandContext.getSource().sendFailure(new StringTextComponent(I18n.get("error.player_not_found.anno")));
        }
        return 0;
    }
}
