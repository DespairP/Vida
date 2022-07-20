package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import teamHTBP.vida.event.server.BlockEventLoaderServer;
import teamHTBP.vida.item.staff.IElementTools;

public class CommandToolLevelUp implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        int exp = commandContext.getArgument("exp", Integer.class);
        Player entity = commandContext.getSource().getPlayerOrException();
        if (entity.getMainHandItem() != ItemStack.EMPTY) {
            ItemStack stack = entity.getMainHandItem();
            if (stack.getItem() instanceof IElementTools) {
                stack.getOrCreateTag().putInt("pickaxeExp", exp);
                BlockEventLoaderServer.levelupTool(stack);
            } else {
                commandContext.getSource().sendFailure(new TranslatableComponent("error.tool_unsuitable.anno"));
            }
        } else {
            commandContext.getSource().sendFailure(new TranslatableComponent("error.player_not_found.anno"));
        }
        return 0;
    }
}
