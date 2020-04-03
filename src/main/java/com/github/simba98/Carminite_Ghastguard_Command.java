package com.github.simba98;


import net.minecraft.client.gui.ChatLine;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Carminite_Ghastguard_Command extends CommandBase {

    @Override
    public String getName() {
        return "setaccel";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        // TODO I18n
        return "/setaccel [double]MaxtractiveAccell [double]MaxbrakeAccell";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(Carminite_Ghastguard_Config.AllowCommand) {
            if(args.length ==2) {
                Carminite_Ghastguard_Config.MaxtractiveAccell = Double.parseDouble(args[0]);
                Carminite_Ghastguard_Config.MaxbrakeAccell = Double.parseDouble(args[1]);
            } else {
                new TextComponentString("Arguments are not suitable for Carminite Ghastguard Command.");
            }
        } else {
            sender.sendMessage(
                    new TextComponentString("Carminite Ghastguard Command has been disabled.")
            );
        }
    }

    // Default permission requirement is 4
    @Override
    public int getRequiredPermissionLevel() {
        return 4;
    }

}