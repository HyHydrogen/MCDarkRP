package me.callum.mcdarkrp.engine;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;


public class Configuration extends YamlConfiguration {
    public File file;

    public Configuration(File file) {
        this.file = file;
    }

    public void reload() {
        try {
            if (!file.exists())
                return;
            load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (!file.exists())
                file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (!file.exists())
                return;
            save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean fileExists() {
        return file.exists();
    }

}
