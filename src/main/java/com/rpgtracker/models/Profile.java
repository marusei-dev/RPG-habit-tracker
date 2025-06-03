package main.java.com.rpgtracker.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profile implements Serializable {
    private String userName;
    private Map<Attribute, Integer> power;
    private List<Habit> habits;
    private List<Task> tasks;

    public Profile(String name) {
        this.userName = name;
        this.power = new HashMap<Attribute, Integer>();
        power.put(Attribute.AGILITY, 0);
        power.put(Attribute.INTELLECT, 0);
        power.put(Attribute.STRENGTH, 0);
        power.put(Attribute.FELICITY_OF_PHRASE, 0);
        power.put(Attribute.QUICK_WIT, 0);
        power.put(Attribute.IMAGINATION, 0);
        this.habits = new ArrayList<Habit>();
        this.tasks = new ArrayList<>();
    }

    public void addTask(String taskName, LocalDate dueDate, Map<Attribute, Integer> rewards) {
        Task task = new Task(taskName,dueDate, rewards);
        tasks.add(task);
    }
    public void addHabit(String habitName, Map<Attribute, Integer> rewards) {
        Habit habit = new Habit(habitName, rewards);
        habits.add(habit);
    }

    public void finishTask(Task task){
        for(Attribute attribute : task.getRewards().keySet()) {
            power.put(attribute, task.getRewards().get(attribute) + power.get(attribute));
        }
        tasks.remove(task);
    }
    public void followHabit(Habit habit) {
        for(Attribute attribute : habit.getRewards().keySet()) {
            power.put(attribute, habit.getRewards().get(attribute) + power.get(attribute));
        }
    }

    public void editHabit(Habit habit, String name) {
        habit.setName(name);
    }
    public void editTask(Task task, String name, LocalDate dueDate) {
        task.setDuedate(dueDate);
        task.setName(name);
    }

    public void deleteTask(Task task){
        tasks.remove(task);
    }
    public void deleteHabit(Habit habit){
        habits.remove(habit);
    }
    public Map<Attribute, Integer> getPower() {
        return power;
    }
    public void setPower(Map<Attribute, Integer> power) { this.power = power; }
    public List<Habit> getHabits() {
        return habits;
    }
    public List<Task> getTasks() {
        return tasks;
    }
    public String getUserName() { return userName; }
}
