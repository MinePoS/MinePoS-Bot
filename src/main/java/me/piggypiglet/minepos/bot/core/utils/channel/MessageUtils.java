package me.piggypiglet.minepos.bot.core.utils.channel;

import java.util.Arrays;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class MessageUtils {
    public boolean startsWith(String msg, String str) {
        if (str.contains("/")) {
            String[] contain = str.split("/");
            return Arrays.stream(contain).anyMatch(msg.toLowerCase()::startsWith);
        }
        return msg.toLowerCase().startsWith(str);
    }
}
