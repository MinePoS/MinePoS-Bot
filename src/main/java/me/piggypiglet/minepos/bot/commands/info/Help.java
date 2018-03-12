package me.piggypiglet.minepos.bot.commands.info;

import me.piggypiglet.minepos.bot.core.framework.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class Help extends Command {
    public Help() {
        super("?help");
    }

    public void execute(MessageReceivedEvent e, String[] args) {
        e.getChannel().sendMessage(
                "**Help**\n" +
                        "**-** Soon\n" +
                        "**-** Soon\n" +
                        "**-** Soon\n" +
                        "oof"
        ).queue();
    }
}
