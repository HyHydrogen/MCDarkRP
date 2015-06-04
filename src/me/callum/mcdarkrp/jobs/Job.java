package me.callum.mcdarkrp.jobs;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Job {
    private String jobName;
    private String permission;
    private List<String> description;

    private float pay;
    private ItemStack[] inventory;

    private ItemStack icon;

    private int jobLimit;

    public Job(String name) {
        setName(name);
        setPermission(null);
        setDescription(Arrays.asList(""));
        setPay(0);
        setInventory(new ItemStack[36]);
        setJobLimit(0);
        setIcon(new ItemStack(Material.COBBLESTONE));
    }

    public void setName(String name) {
        this.jobName = name;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setDescription(List<String> list) {
        this.description = list;
    }

    public void setPay(float pay){
        this.pay = pay;
    }

    public void setInventory(ItemStack[] inv) {
        this.inventory = inv;
    }

    public void setSlot(int slot, ItemStack stack) {
        this.inventory[slot] = stack;
    }

    public void setIcon(ItemStack item) {
        this.icon = icon;
    }

    public void setJobLimit(int limit) {
        this.jobLimit = limit;
    }

    public String getName() {
        return jobName;
    }

    public String getPermission() {
        return permission;
    }

    public List<String> getDescription() {
        return description;
    }

    public float getPay() {
        return pay;
    }

    public ItemStack[] getInventory() {
        return inventory;
    }

    public ItemStack getIcon() {
        return icon;
    }
}
