package me.piggypiglet.minepos.bot;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.minepos.bot.commands.info.Help;
import me.piggypiglet.minepos.bot.core.framework.BinderModule;
import me.piggypiglet.minepos.bot.core.handlers.CommandHandler;
import me.piggypiglet.minepos.bot.core.objects.Config;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class MinePoSBot {
    @Inject private Config config;
    @Inject private CommandHandler commandHandler;

    private MinePoSBot() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        try {
            new JDABuilder(AccountType.BOT)
                    .setToken(config.getItem("token"))
                    .setGame(Game.watching("oof"))
                    .addEventListener(commandHandler)
                    .buildBlocking();
        } catch (Exception e) {
            e.printStackTrace();
        }

        initCommands();
    }

    private void initCommands() {
        new Help();
    }

    public String getVersion(){
        return "here";
    }
    public static void main(String[] args) {
        new MinePoSBot();
    }
}
