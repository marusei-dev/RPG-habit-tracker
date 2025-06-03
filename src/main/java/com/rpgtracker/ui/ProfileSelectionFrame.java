package main.java.com.rpgtracker.ui;


import main.java.com.rpgtracker.models.Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ProfileSelectionFrame extends JFrame {
    private static Map<String, Profile> profileList;
    public ProfileSelectionFrame() {
        setTitle("Profile Selection");
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        File file = new File("src/main/resources/files/profiles.txt");
        if(file.length() != 0){
            try(FileInputStream fileIn = new FileInputStream("src/main/resources/files/profiles.txt");
                ObjectInputStream objIn = new ObjectInputStream(fileIn)){
                profileList = (Map<String, Profile>)objIn.readObject();
            } catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        } else {
            profileList = new HashMap<>();
        }
        this.updateList();
        setVisible(true);
    }
    public void updateList(){
        getContentPane().removeAll();
        for(Profile profile: profileList.values()){
            JPanel panel = new JPanel();
            JLabel label = new JLabel(profile.getUserName());
            panel.setBackground(new Color(137, 207, 230));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);

            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("...");
            menu.setFont(new Font("Verdana", Font.PLAIN, 9));
            JMenuItem delete = new JMenuItem("delete");
            delete.setFont(new Font("Verdana", Font.PLAIN, 14));
            JMenuItem select = new JMenuItem("select");
            select.setFont(new Font("Verdana", Font.PLAIN, 14));
            menu.add(delete);
            menu.add(select);
            menuBar.add(menu);
            panel.add(menuBar, BorderLayout.EAST);
            getContentPane().add(panel);

            select.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainFrame mainFrame = new MainFrame(profile);
                    dispose();
                }
            });

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileList.remove(profile.getUserName());
                    updateList();
                    try(FileOutputStream fileOut = new FileOutputStream("src/main/resources/files/profiles.txt");
                        ObjectOutputStream objOut = new ObjectOutputStream(fileOut)){
                        objOut.writeObject(profileList);
                    } catch(IOException ex){
                        ex.printStackTrace();
                    }
                    File file = new File("src/main/resources/files/growth" + profile.getUserName() + ".txt");
                    file.delete();
                }
            });
        }

        JButton createProfileButton = new JButton("Create Profile");
        createProfileButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        add(createProfileButton);
        createProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Create Profile");
                frame.setFont(new Font("Verdana", Font.PLAIN, 14));
                frame.setSize(200, 120);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new GridLayout(3,2));
                JTextField userNameField = new JTextField();
                JLabel userNameLabel = new JLabel("User Name", SwingConstants.CENTER);
                userNameLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                frame.add(userNameLabel);
                frame.add(userNameField);
                JButton okButton = new JButton("ok");
                okButton.setFont(new Font("Verdana", Font.PLAIN, 14));
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!profileList.containsKey(userNameField.getText())) {
                            Profile profile = new Profile(userNameField.getText().trim());
                            profileList.put(userNameField.getText().trim(), profile);
                            try (FileOutputStream fielOut = new FileOutputStream("src/main/resources/files/profiles.txt");
                                 ObjectOutputStream objOut = new ObjectOutputStream(fielOut)) {
                                objOut.writeObject(profileList);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            JLabel messageLabel = new JLabel("Profile already exists");
                            messageLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
                            JOptionPane.showMessageDialog(frame, messageLabel, "Error", JOptionPane.ERROR_MESSAGE);

                        }

                        updateList();
                        frame.dispose();
                    }
                });
                frame.add(okButton);
                frame.setVisible(true);
            }
        });
        revalidate();
        repaint();
        setVisible(true);
    }
    public static Map<String, Profile> getProfileList(){
        return profileList;
    }
    public static void setProfileList(Map<String,Profile> list){ profileList = list; }
    public static void addProfile(Profile profile){
        profileList.put(profile.getUserName(), profile);
    }
}
