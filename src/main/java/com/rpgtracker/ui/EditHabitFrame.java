package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Habit;
import main.java.com.rpgtracker.models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditHabitFrame extends JFrame {
    private JTextField nameField;
    Profile profile;
    Habit habit;
    HabitPanel habitPanel;

    public EditHabitFrame(Profile profile, Habit habit, HabitPanel habitPanel) {
        this.habitPanel = habitPanel;
        this.profile = profile;
        this.habit = habit;
        setTitle("Edit Habit");
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(nameLabel);

        nameField = new JTextField();
        add(nameField);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profile.editHabit(habit, nameField.getText().trim());
                habitPanel.updateHabits();
                dispose();
            }
        });
        add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(cancelButton);
        setVisible(true);
    }


}
