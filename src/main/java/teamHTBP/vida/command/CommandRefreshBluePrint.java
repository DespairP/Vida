package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.util.text.StringTextComponent;
import teamHTBP.vida.helper.BlueprintHelper;

public class CommandRefreshBluePrint implements Command<CommandSource> {
    @Override
    public int run(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        BlueprintHelper.refreshAllBluePrints();
        commandContext.getSource().sendFeedback(new StringTextComponent("blueprints has been reloaded"), true);
        return 0;
    }
}
