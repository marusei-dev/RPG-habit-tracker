package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Habit;
import main.java.com.rpgtracker.models.Profile;
import main.java.com.rpgtracker.models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class EditTaskFrame extends JFrame {
    private JTextField nameField;
    private JTextField dueDateField;
    Profile profile;
    Task task;
    TaskPanel taskPanel;

    public EditTaskFrame(Profile profile, Task task, TaskPanel taskPanel) {
        this.task = task;
        this.profile = profile;
        this.taskPanel = taskPanel;
        setTitle("Edit Task");
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

        JLabel dueLabel = new JLabel("Dua Date: ");
        dueLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(dueLabel);
        dueDateField = new JTextField();
        add(dueDateField);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dueDate = LocalDate.parse(dueDateField.getText());
                profile.editTask(task, nameField.getText().trim(), dueDate);
                taskPanel.updateTasks();
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
