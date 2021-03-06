package me.piggypiglet.minepos.bot.commands.info;

import me.piggypiglet.minepos.bot.core.framework.Command;
import me.piggypiglet.minepos.bot.core.handlers.CommandHandler;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class Help extends Command {


    public Help() {
        super("?help","Display the MinePoS bot help message",false);
    }

    public void execute(MessageReceivedEvent e, String[] args) {
        boolean isAdmin = e.getMember().hasPermission(Permission.ADMINISTRATOR);
        String helpMessage = "";
        for(Command command : CommandHandler.getInstance().commands){
            if(command.getAdmin()){
                if(isAdmin){
                    helpMessage += command.getName()+" - "+command.getDesc()+System.lineSeparator();
                }
            }else{
                helpMessage += command.getName()+" - "+command.getDesc()+System.lineSeparator();

            }
        }

        MessageEmbed embed = new EmbedBuilder()
                .setAuthor(e.getAuthor().getName(),null,e.getAuthor().getAvatarUrl())
                .setDescription(helpMessage)
                .setFooter("MinePoS Bot",null)
                .build();
        e.getChannel().sendMessage(embed).queue();
    }
}
