package me.piggypiglet.minepos.bot.commands.info;

import com.google.inject.Inject;
import me.piggypiglet.minepos.bot.MinePoSVersion;
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
        for(Command command : commandHandler.commands){
            if(command.getAdmin()){
                if(isAdmin){
                    helpMessage += command.getName()+" - "+command.getDesc();
                }
            }else{
                helpMessage += command.getName()+" - "+command.getDesc();
            }
        }

        MessageEmbed embed = new EmbedBuilder()
                .setAuthor(e.getAuthor().getName(),null,e.getAuthor().getAvatarUrl())
                .setDescription(helpMessage).setTitle("MinePoS Bot: "+ MinePoSVersion.MinePoSVersion).build();
        e.getChannel().sendMessage(embed).queue();
    }
}
