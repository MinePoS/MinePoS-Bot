package me.piggypiglet.minepos.bot.core.framework;

import com.google.inject.Inject;
import me.piggypiglet.minepos.bot.core.handlers.CommandHandler;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public abstract class Command {
    private final String name;
    private final String desc;
    private final boolean admin;
    @Inject protected CommandHandler commandHandler;

    protected Command() {
        this("null","null",false);
    }

    protected Command(String name, String desc, boolean admin) {
        this.name = name;
        this.desc = desc;
        this.admin = admin;
        commandHandler.addCommand(this);
    }

    protected abstract void execute(MessageReceivedEvent e, String[] args);

    public void run(MessageReceivedEvent e, String[] args) {
        if(admin){
            if(e.getMember().hasPermission(Permission.ADMINISTRATOR)){
                execute(e, args);
            }
        }else{
            execute(e, args);
        }
    }

    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public boolean getAdmin() {
        return admin;
    }
}
