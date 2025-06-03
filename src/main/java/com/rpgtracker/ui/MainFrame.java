package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Attribute;
import main.java.com.rpgtracker.models.Profile;
import org.w3c.dom.Attr;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Profile profile;
    private Map<LocalDate,Profile> growth;

    public MainFrame(Profile profile) {
        this.profile = profile;

        setTitle("RPG Habit Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        File file = new File("src/main/resources/files/growth" + profile.getUserName() + ".txt");
        try{
            file.createNewFile();
            System.out.println("file created");
        } catch(IOException e){
            e.printStackTrace();
        }
        if(file.length() == 0){
            growth = new HashMap<>();
            System.out.println("file is empty");
        } else {
            try (FileInputStream fileIn = new FileInputStream("src/main/resources/files/growth" + profile.getUserName() + ".txt");
                 ObjectInputStream objIn = new ObjectInputStream(fileIn);) {
                growth = (Map<LocalDate, Profile>) objIn.readObject();

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        JPanel sideMenu = createSideMenu();

        contentPanel = createContentPanel();

        add(sideMenu, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setVisible(true);



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("window closing");

                growth.put(LocalDate.now(), profile);
                try(FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/files/growth" + profile.getUserName() + ".txt");
                    ObjectOutputStream objOut = new ObjectOutputStream(fileOutputStream)){
                    objOut.writeObject(growth);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                ProfileSelectionFrame.addProfile(profile);
                try(FileOutputStream fileOut = new FileOutputStream("src/main/resources/files/profiles.txt");
                    ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
                    objOut.writeObject(ProfileSelectionFrame.getProfileList());
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
    }


    private JPanel createSideMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(11, 1));
        menuPanel.setPreferredSize(new Dimension(60, 0));
        menuPanel.setBackground(Color.WHITE);


        ImageIcon originalTaskIcon = new ImageIcon("src/main/resources/icons/tasks.png");
        Image originalTaskImage = originalTaskIcon.getImage();
        Image resizedTaskImage = originalTaskImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedTaskImage);
        JButton tasksButton = new JButton();
        tasksButton.setBackground(Color.WHITE);
        tasksButton.setBorder(new LineBorder(Color.WHITE, 2));
        tasksButton.setIcon(resizedIcon);

        ImageIcon originalHabitIcon = new ImageIcon("src/main/resources/icons/habits.png");
        Image originalHabitImage = originalHabitIcon.getImage();
        Image resizedHabitImage = originalHabitImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedHabitIcon = new ImageIcon(resizedHabitImage);
        JButton habitsButton = new JButton();
        habitsButton.setBackground(Color.WHITE);
        habitsButton.setBorder(new LineBorder(Color.WHITE, 2));
        habitsButton.setIcon(resizedHabitIcon);

        ImageIcon originalGrowthIcon = new ImageIcon("src/main/resources/icons/growth.png");
        Image originalGrowthImage = originalGrowthIcon.getImage();
        Image resizedGrowthImage = originalGrowthImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedGrowthIcon = new ImageIcon(resizedGrowthImage);
        JButton growthButton = new JButton();
        growthButton.setBackground(Color.WHITE);
        growthButton.setBorder(new LineBorder(Color.WHITE, 2));
        growthButton.setIcon(resizedGrowthIcon);

        ImageIcon originalProfileIcon = new ImageIcon("src/main/resources/icons/profile.png");
        Image originalProfileImage = originalProfileIcon.getImage();
        Image resizedProfileImage = originalProfileImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedProfileIcon = new ImageIcon(resizedProfileImage);
        JButton profileButton = new JButton();
        profileButton.setBackground(Color.WHITE);
        profileButton.setBorder(new LineBorder(Color.WHITE, 2));
        profileButton.setIcon(resizedProfileIcon);

        tasksButton.addActionListener(e -> switchContent("tasks"));
        habitsButton.addActionListener(e -> switchContent("habits"));
        growthButton.addActionListener(e -> switchContent("growth"));
        profileButton.addActionListener(e -> switchContent("profile"));

        menuPanel.add(tasksButton);
        menuPanel.add(habitsButton);
        menuPanel.add(growthButton);
        menuPanel.add(profileButton);

        return menuPanel;
    }

    public Map<LocalDate, Profile> getGrowth(){
        return growth;
    }

    private JPanel createContentPanel() {
        cardLayout = new CardLayout();
        JPanel panel = new JPanel(cardLayout);

        panel.add(createTasksPanel(), "tasks");
        panel.add(createHabitsPanel(), "habits");
        panel.add(createDevelopmentPanel(), "growth");
        panel.add(createProfilePanel(), "profile");

        return panel;
    }

    private void switchContent(String panelName) {
        cardLayout.show(contentPanel, panelName);
    }

    private JPanel createTasksPanel() {
        JPanel panel = new TaskPanel(this.profile);
        return panel;
    }

    private JPanel createHabitsPanel() {
        JPanel panel = new HabitPanel(profile);
        return panel;
    }

    private JPanel createDevelopmentPanel() {
        GrowthPanel panel = new GrowthPanel(MainFrame.this);
        return panel;
    }

    private JPanel createProfilePanel() {
        ProfilePanel panel = new ProfilePanel(this.profile);
        return panel;
    }

}
