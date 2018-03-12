package me.piggypiglet.minepos.bot.core.objects;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import me.piggypiglet.minepos.bot.MinePoSBot;
import me.piggypiglet.minepos.bot.core.utils.file.ConfigUtils;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public class Config {
    @Inject private ConfigUtils cutil;
    @Inject private Gson gson;
    private File config;

    public Config() {
        config = new File("./config.json");
        try {
            if (!config.exists()) {
                if (config.createNewFile()) {
                    if (cutil.exportResource(MinePoSBot.class.getResourceAsStream("/config.json"), "./config.json")) {
                        System.out.println("Config successfully created!");
                    } else {
                        System.out.println("Config creation failed!");
                    }
                }
            } else {
                System.out.println("Config successfully loaded");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getItem(String item) {
        try {
            JsonReader reader = new JsonReader(new FileReader(config));
            Map<String, String> data = gson.fromJson(reader, LinkedTreeMap.class);
            if (data.containsKey(item)) {
                return data.get(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item + " not found in the config.";
    }
}
