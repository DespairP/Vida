package teamHTBP.vida.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import teamHTBP.vida.core.blueprint.BlueprintHelper;

public class RefreshBluePrintCommand implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        BlueprintHelper.refreshAllBluePrints();
        commandContext.getSource().sendSuccess(new TextComponent("blueprints has been reloaded"), true);
        return 0;
    }
}