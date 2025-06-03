package main.java.com.rpgtracker.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

public class Task implements Serializable {
    private String name;
    private LocalDate duedate;
    private Map<Attribute, Integer> rewards;

    public Task(String name, LocalDate duedate, Map<Attribute, Integer> rewards) {
        this.name = name;
        this.duedate = duedate;
        this.rewards = rewards;
    }
    public String getName() {
        return name;
    }
    public LocalDate getDuedate() {
        return duedate;
    }
    public Map<Attribute, Integer> getRewards() {
        return rewards;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }
    public void setRewards(Map<Attribute, Integer> rewards) {
        this.rewards = rewards;
    }
}
