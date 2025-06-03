package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Habit;
import main.java.com.rpgtracker.models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class HabitPanel extends JPanel {
    private Profile profile;
    public HabitPanel(Profile profile) {
        this.profile = profile;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(245, 245, 245));

        updateHabits();

        setVisible(true);
    }

    public void updateHabits() {
        removeAll();
        JButton addHabitButton = new JButton("Add Habit");
        addHabitButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        addHabitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddHabitFrame addHabitFrame = new AddHabitFrame(profile, HabitPanel.this);
                addHabitFrame.setVisible(true);
            }
        });
        add(addHabitButton);
        for (Habit habit : profile.getHabits()) {
            JPanel habitPanel = new JPanel();
            habitPanel.setLayout(new BoxLayout(habitPanel, BoxLayout.Y_AXIS));
            habitPanel.setPreferredSize(new Dimension(1000, 70));
            habitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            habitPanel.setPreferredSize(new Dimension(1000, 70));
            habitPanel.setMaximumSize(new Dimension(1000, 70));
            habitPanel.setBackground(new Color(173, 216, 230));
            JLabel habitLabel = new JLabel(habit.getName());
            habitLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            habitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            habitPanel.add(habitLabel);

            JLabel rewardsLabel = new JLabel("Rewards" + habit.getRewards().toString().toLowerCase());
            rewardsLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
            rewardsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            habitPanel.add(rewardsLabel);

            JMenuBar menuBar = new JMenuBar();
            JMenu habitMenu = new JMenu("Menu");
            habitMenu.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem delete = new JMenuItem("Delete");
            delete.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem edit = new JMenuItem("Edit");
            edit.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem follow = new JMenuItem("Follow");
            follow.setFont(new Font("Verdana", Font.PLAIN, 14));

            habitMenu.add(delete);
            habitMenu.add(edit);
            habitMenu.add(follow);
            menuBar.add(habitMenu);
            menuBar.setAlignmentX(Component.CENTER_ALIGNMENT);
            habitPanel.add(menuBar);

            add(habitPanel);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profile.deleteHabit(habit);
                    updateHabits();
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EditHabitFrame editHabitFrame = new EditHabitFrame(profile, habit, HabitPanel.this);
                }
            });
            follow.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profile.followHabit(habit);
                    ProfilePanel.updateAttributes(profile);
                }
            });
        }

        revalidate();
        repaint();
    }
}
