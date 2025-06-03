package main.java.com.rpgtracker.test;

import main.java.com.rpgtracker.models.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    private Profile profile;
    private Task testTask1;
    private Task testTask2;

    @BeforeEach
    void setUp() {
        profile = new Profile("TestUser");

        Map<Attribute, Integer> rewards1 = new HashMap<>();
        rewards1.put(Attribute.STRENGTH, 10);
        rewards1.put(Attribute.INTELLECT, 5);

        Map<Attribute, Integer> rewards2 = new HashMap<>();
        rewards2.put(Attribute.AGILITY, 15);
        rewards2.put(Attribute.QUICK_WIT, 8);

        profile.addTask("Test Task 1", LocalDate.now(), rewards1);
        profile.addTask("Test Task 2", LocalDate.now().plusDays(1), rewards2);

        testTask1 = profile.getTasks().get(0);
        testTask2 = profile.getTasks().get(1);
    }

    @Test
    void testAddTask() {
        List<Task> tasks = profile.getTasks();
        assertEquals(2, tasks.size(), "There should initially be 2 tasks in the profile.");

        Map<Attribute, Integer> rewards3 = new HashMap<>();
        rewards3.put(Attribute.IMAGINATION, 20);
        profile.addTask("Test Task 3", LocalDate.now().plusDays(2), rewards3);

        tasks = profile.getTasks();
        assertEquals(3, tasks.size(), "There should be 3 tasks in the profile after adding a new task.");
        assertEquals("Test Task 3", tasks.get(2).getName(), "Third task name should match.");
        assertEquals(LocalDate.now().plusDays(2), tasks.get(2).getDuedate(), "Third task due date should match.");
        assertEquals(20, (int) tasks.get(2).getRewards().get(Attribute.IMAGINATION), "Third task rewards should match.");
    }


    @Test
    void testFinishTask() {
        profile.finishTask(testTask1);

        assertEquals(1, profile.getTasks().size(), "Task list should contain 1 task after finishing.");
        assertFalse(profile.getTasks().contains(testTask1), "Task list should not contain the finished task.");

        assertEquals(10, (int) profile.getPower().get(Attribute.STRENGTH), "Strength power should be updated to 10.");
        assertEquals(5, (int) profile.getPower().get(Attribute.INTELLECT), "Intellect power should be updated to 5.");
    }

    @Test
    void testDeleteTask() {
        profile.deleteTask(testTask1);

        assertEquals(1, profile.getTasks().size(), "Task list should contain 1 task after deletion.");
        assertFalse(profile.getTasks().contains(testTask1), "Task list should not contain the deleted task.");
        assertTrue(profile.getTasks().contains(testTask2), "Task list should still contain the second task.");
    }

    @Test
    void testEditTask() {
        LocalDate newDueDate = LocalDate.now().plusDays(3);
        profile.editTask(testTask1, "Updated Task 1", newDueDate);

        assertEquals("Updated Task 1", testTask1.getName(), "Task name should be updated.");
        assertEquals(newDueDate, testTask1.getDuedate(), "Task due date should be updated.");
    }

}
