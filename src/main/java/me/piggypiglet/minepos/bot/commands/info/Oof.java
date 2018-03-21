package me.piggypiglet.minepos.bot.commands.info;


import me.piggypiglet.minepos.bot.core.framework.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class Oof extends Command {


    public Oof() {
        super("?oof","Send a oof",false);
    }

    public void execute(MessageReceivedEvent e, String[] args) {
       e.getChannel().sendMessage(e.getAuthor().getAsMention()+" Oof indeed!").queue();
    }
}
