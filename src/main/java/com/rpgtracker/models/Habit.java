package main.java.com.rpgtracker.models;

import java.io.Serializable;
import java.util.Map;

public class Habit implements Serializable {
    private String name;
    private Map<Attribute, Integer> rewards;
    public Habit(String name, Map<Attribute, Integer> rewards) {
        this.name = name;
        this.rewards = rewards;

    }
    public String getName() {
        return name;
    }
    public Map<Attribute, Integer> getRewards() {
        return rewards;
    }
    public void setRewards(Map<Attribute, Integer> rewards) {
        this.rewards = rewards;
    }

    public void setName(String name) {
        this.name = name;
    }
}
