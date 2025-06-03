package main.java.com.rpgtracker.ui;


import main.java.com.rpgtracker.models.Profile;
import main.java.com.rpgtracker.models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class TaskPanel extends JPanel {
    private Profile profile;
    public TaskPanel(Profile profile) {
        this.profile = profile;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 245));

        updateTasks();

        setVisible(true);
    }

    public void updateTasks() {
        removeAll();
        JButton addTaskButton = new JButton("Add Task");
        addTaskButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskFrame addTaskFrame = new AddTaskFrame(profile, TaskPanel.this);
                addTaskFrame.setVisible(true);

            }
        });
        add(addTaskButton);
        for (Task task : profile.getTasks()) {
            JPanel taskPanel = new JPanel();
            taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
            taskPanel.setPreferredSize(new Dimension(1000, 90));
            taskPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            taskPanel.setPreferredSize(new Dimension(1000, 90));
            taskPanel.setMaximumSize(new Dimension(1000, 90));
            taskPanel.setBackground(new Color(173, 216, 230));
            JLabel habitLabel = new JLabel(task.getName());
            habitLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            habitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            taskPanel.add(habitLabel);

            JLabel dueDateLabel = new JLabel("Due Date:" +task.getDuedate().toString());
            dueDateLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            dueDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            taskPanel.add(dueDateLabel);

            JLabel rewardsLabel = new JLabel("Rewards:" +task.getRewards().toString().toLowerCase(Locale.ROOT));
            rewardsLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            rewardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            taskPanel.add(rewardsLabel);


            JMenuBar menuBar = new JMenuBar();
            JMenu habitMenu = new JMenu("Menu");
            habitMenu.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem delete = new JMenuItem("Delete");
            delete.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem edit = new JMenuItem("Edit");
            edit.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem finish = new JMenuItem("Finish");
            finish.setFont(new Font("Verdana", Font.PLAIN, 14));

            habitMenu.add(delete);
            habitMenu.add(edit);
            habitMenu.add(finish);
            menuBar.add(habitMenu);
            menuBar.setAlignmentX(Component.CENTER_ALIGNMENT);
            taskPanel.add(menuBar);

            add(taskPanel);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profile.deleteTask(task);
                    updateTasks(); // Refresh the habit list
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EditTaskFrame editTaskFrame = new EditTaskFrame(profile, task, TaskPanel.this);
                }
            });
            finish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profile.finishTask(task);
                    ProfilePanel.updateAttributes(profile);
                    updateTasks();
                }
            });
        }


        revalidate();
        repaint();
    }
}
