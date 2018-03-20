package me.piggypiglet.minepos.bot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import jdk.nashorn.internal.objects.annotations.Getter;
import me.piggypiglet.minepos.bot.commands.info.Help;
import me.piggypiglet.minepos.bot.commands.info.Oof;
import me.piggypiglet.minepos.bot.core.framework.BinderModule;
import me.piggypiglet.minepos.bot.core.handlers.CommandHandler;
import me.piggypiglet.minepos.bot.core.objects.Config;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.TextChannel;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class MinePoSBot {
    @Inject private Config config;
    @Inject private CommandHandler commandHandler;
    private JDA jda;
    private MinePoSBot() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(config.getItem("token"))
                    .setGame(Game.watching("oof"))
                    .addEventListener(commandHandler)
                    .buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initCommands();

        TextChannel channel = jda.getGuilds().get(0).getTextChannelsByName("logging",true).get(0);
        MessageEmbed embed = new EmbedBuilder()
                .setAuthor(jda.getSelfUser().getName(),jda.getSelfUser().getAvatarUrl())
                .setDescription("MinePoS Bot has been started")
                .setFooter("MinePoS Bot",null)
                .build();
       channel.sendMessage(embed).queue();
    }

    private void initCommands() {
        new Help();
        new Oof();
    }

    public String getVersion(){
        return "here";
    }
    public static void main(String[] args) {
        new MinePoSBot();
    }
}
