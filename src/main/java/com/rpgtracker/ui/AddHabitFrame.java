package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Attribute;
import main.java.com.rpgtracker.models.Habit;
import main.java.com.rpgtracker.models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddHabitFrame extends JFrame {
    private JTextField nameField;
    private JTextField rewardField;
    private HabitPanel habitPanel;
    private Profile profile;

    public AddHabitFrame(Profile profile, HabitPanel habitPanel) {
        this.profile = profile;
        this.habitPanel = habitPanel;
        setTitle("Add Habit");
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

        JLabel rewardLabel = new JLabel("Reward: ");
        rewardLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(rewardLabel);
        rewardField = new JTextField();
        add(rewardField);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Attribute, Integer> rewards = new HashMap<>();
                ArrayList<Integer> rewardNumbers = new ArrayList<>();

                try {
                    String trimmedNumbers = rewardField.getText().trim();
                    for (String numberStr : trimmedNumbers.split(" ")) {
                        rewardNumbers.add(Integer.parseInt(numberStr));
                    }

                    if(rewardNumbers.size() != 6){
                        JLabel messageLabel = new JLabel("plese enter 6 inetegers seperated by a space");
                        messageLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(AddHabitFrame.this, messageLabel, "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        int i = 0;
                        for (Attribute attribute : Attribute.values()) {
                            rewards.put(attribute, rewardNumbers.get(i));
                            i++;
                        }

                        profile.addHabit(nameField.getText().trim(), rewards);
                        dispose();
                    }


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddHabitFrame.this,
                            "Invalid reward input. Please enter valid space-separated numbers.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                habitPanel.updateHabits();
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
