package me.piggypiglet.minepos.bot.commands.info;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import me.piggypiglet.minepos.bot.core.framework.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class aiCommand extends Command {


    public aiCommand() {
        super("?ai","Run something via the AI",false);
    }

    public void execute(MessageReceivedEvent e, String[] args) {
        if(args.length == 0){
            e.getChannel().sendMessage("You must specify a message").queue();
            return;
        }
        String msg = e.getMessage().getContentDisplay().replace("?ai ","");

        AIConfiguration configuration = new AIConfiguration("1fac2a23967d4af4a895d77fda8ebb7f");
        AIDataService dataService = new AIDataService(configuration);
        try {
            AIRequest request = new AIRequest(msg);

            AIResponse response = dataService.request(request);

            if (response.getStatus().getCode() == 200) {
                e.getChannel().sendMessage(response.getResult().getFulfillment().getSpeech()).queue();
            } else {
                System.err.println(response.getStatus().getErrorDetails());
                e.getChannel().sendMessage("A error occurred, error log has been saved.").queue();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
