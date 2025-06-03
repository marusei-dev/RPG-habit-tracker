package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Attribute;
import main.java.com.rpgtracker.models.Habit;
import main.java.com.rpgtracker.models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddTaskFrame extends JFrame {
    private JTextField nameField;
    private JTextField rewardField;
    private JTextField dueDateField;
    private TaskPanel taskPanel;
    private Profile profile;

    public AddTaskFrame(Profile profile, TaskPanel taskPanel) {
        this.profile = profile;
        this.taskPanel = taskPanel;
        setTitle("Add Task");
        setFont(new Font("Verdana", Font.PLAIN, 14));
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

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

        JLabel dueDateLabel = new JLabel("Due Date: ");
        dueDateLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(dueDateLabel);
        dueDateField = new JTextField();
        add(dueDateField);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<Attribute, Integer> rewards = new HashMap<>();
                ArrayList<Integer> rewardNumbers = new ArrayList<>();
                LocalDate dueDate = LocalDate.parse(dueDateField.getText());

                try {
                    String trimmedNumbers = rewardField.getText().trim();
                    for (String numberStr : trimmedNumbers.split(" ")) {
                        rewardNumbers.add(Integer.parseInt(numberStr));
                    }
                    if(rewardNumbers.size() != 6){
                        JLabel messageLabel = new JLabel("plese enter 6 inetegers seperated by a space");
                        messageLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                        JOptionPane.showMessageDialog(AddTaskFrame.this, messageLabel, "Error", JOptionPane.ERROR_MESSAGE);
                    } else {

                        int i = 0;
                        for (Attribute attribute : Attribute.values()) {
                            rewards.put(attribute, rewardNumbers.get(i));
                            i++;
                        }

                        profile.addTask(nameField.getText().trim(), dueDate, rewards);

                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddTaskFrame.this,
                            "Invalid reward input. Please enter valid space-separated numbers.",
                            "Input Error", JOptionPane.ERROR_MESSAGE);
                }
                taskPanel.updateTasks();
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
