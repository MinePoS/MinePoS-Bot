package me.piggypiglet.minepos.bot.commands.info;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.dialogflow.v2beta1.Dialogflow;
import com.google.api.services.dialogflow.v2beta1.DialogflowRequest;
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
public class Oof extends Command {


    public Oof() {
        super("?oof","Send a oof",false);
    }

    public void execute(MessageReceivedEvent e, String[] args) {
       e.getChannel().sendMessage(e.getAuthor().getAsMention()+" Oof indeed!").queue();
    }
}
