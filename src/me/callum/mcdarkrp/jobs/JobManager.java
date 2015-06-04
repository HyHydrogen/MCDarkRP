package me.callum.mcdarkrp.jobs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import me.callum.mcdarkrp.core.DarkRP;
import me.callum.mcdarkrp.engine.Configuration;
import org.bukkit.configuration.ConfigurationSection;

public class JobManager {
    private DarkRP plugin;

    private Configuration jobsConfiguration;

    private ArrayList<Job> differentJobs;

    public JobManager(DarkRP plugin) {
        this.plugin = plugin;
        this.differentJobs = new ArrayList<Job>();

        this.jobsConfiguration = new Configuration(new File(plugin.getDataFolder(), "jobs.yml"));
        this.loadJobsConfig();

        loadJobs();
        listJobs();
    }

    private void loadJobs() {
        ConfigurationSection jobsConfigSection = getJobsConfiguration().getConfigurationSection("jobs");

        for (String section : jobsConfigSection.getKeys(false)) {
            Job job = new Job(getJobsConfiguration().getString(section));

            job.setDisplayName(getJobsConfiguration().getString("jobs." + section + ".name"));
            job.setPay((float) getJobsConfiguration().getDouble("jobs." + section + ".pay"));
            job.setPermission(getJobsConfiguration().getString("jobs." + section + ".permission"));
            job.setJobLimit(getJobsConfiguration().getInt("jobs." + section + ".limit"));
            job.setDescription(getJobsConfiguration().getStringList("jobs." + section + ".description"));
            job.setInventory(getJobsConfiguration().getStringList("jobs." + section + ".inventory"));
            differentJobs.add(job);
        }
    }

    public void listJobs() {
        int count = 1;
        for(Job job : differentJobs) {
            System.out.println("Job: [" + count + "]    ID:" + job.getDisplayName());
            count++;
        }
    }

    public void writeToConfig(Job job) {
        getJobsConfiguration().set("jobs." + job.getName() + ".name", job.getDisplayName());
        getJobsConfiguration().set("jobs." + job.getName() + ".pay", (int) job.getPay());
        getJobsConfiguration().set("jobs." + job.getName() + ".permission", job.getPermission());
        getJobsConfiguration().set("jobs." + job.getName() + ".limit", job.getJobLimit());
        getJobsConfiguration().set("jobs." + job.getName() + ".description", job.getDescription());
        getJobsConfiguration().set("jobs." + job.getName() + ".inventory", job.getInventory());
        getJobsConfiguration().save();
    }

    public void loadJobsConfig() {
        if (!jobsConfiguration.fileExists()) {
            jobsConfiguration.create();
            Job citizen = new Job("citizen");
            Job gangster = new Job("gangster");

            citizen.setDisplayName("&8Citizen");
            citizen.setJobLimit(-1);
            citizen.setPay(45);
            citizen.setPermission("null");
            citizen.setDescription(Arrays.asList("&cYou are a basic citizen in a complex society.", "&cYou have no specific role in this city."));
            citizen.setInventory(Arrays.asList("rp_atmcard", "rp_keys"));

            gangster.setDisplayName("&7Gangster");
            gangster.setJobLimit(12);
            gangster.setPay(80);
            gangster.setPermission("null");
            gangster.setDescription(Arrays.asList("&cYour job is to try run the city under your terms", "&cYou don't like the police and you intentially try to break (roleplay) rules!"));
            gangster.setInventory(Arrays.asList("rp_atmcard", "rp_keys", "rp_crowbar"));

            writeToConfig(citizen);
            writeToConfig(gangster);
        }

        jobsConfiguration.reload();
    }

    public Configuration getJobsConfiguration() {
        return jobsConfiguration;
    }

}
