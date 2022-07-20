package teamHTBP.vida.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraftforge.network.PacketDistributor;
import teamHTBP.vida.network.PacketChannel;
import teamHTBP.vida.network.PacketMessage;

public class CommandTextTest implements Command<CommandSourceStack> {
    @Override
    public int run(CommandContext<CommandSourceStack> commandContext) throws CommandSyntaxException {
        PacketChannel.INSTANCE.send(PacketDistributor.PLAYER.with(() -> {
            try {
                return commandContext.getSource().getPlayerOrException();
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }), new PacketMessage(commandContext.getArgument("message", String.class)));
        return 0;
    }
}
