package me.piggypiglet.minepos.bot.core.handlers;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.minepos.bot.commands.info.Help;
import me.piggypiglet.minepos.bot.core.framework.BinderModule;
import me.piggypiglet.minepos.bot.core.framework.Command;
import me.piggypiglet.minepos.bot.core.utils.channel.MessageUtils;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class CommandHandler extends ListenerAdapter {
    @Inject private MessageUtils mutil;
    @Inject private Help help;
    public Command[] commands;

    public CommandHandler() {
        BinderModule module = new BinderModule(this.getClass());
        Injector injector = module.createInjector();
        injector.injectMembers(this);

        commands = new Command[] { help };
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
