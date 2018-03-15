package me.piggypiglet.minepos.bot.core.handlers;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.minepos.bot.commands.info.Help;
import me.piggypiglet.minepos.bot.core.framework.BinderModule;
import me.piggypiglet.minepos.bot.core.framework.Command;
import me.piggypiglet.minepos.bot.core.utils.channel.MessageUtils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class CommandHandler extends ListenerAdapter {
    @Inject private MessageUtils mutil;
    public ArrayList<Command> commands;

    public CommandHandler() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);
        commands = new ArrayList<Command>();

        new Help();
    }

    public void addCommand(Command cmd){
        commands.add(cmd);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String msg = e.getMessage().getContentRaw();

        if (!e.getAuthor().isBot()) {
            for (Command command : commands) {
                String name = command.getName();
                String[] args = msg.toLowerCase().replace(name.toLowerCase(), "").trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if (mutil.startsWith(msg, name)) {
                    command.run(e, args);
                }
            }
        }
    }
}
