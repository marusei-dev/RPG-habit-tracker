package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Attribute;
import main.java.com.rpgtracker.models.Profile;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private static JPanel attributesPanel;
    public ProfilePanel(Profile profile) {
        JLabel profileLabel = new JLabel(profile.getUserName());
        profileLabel.setHorizontalAlignment(SwingConstants.CENTER);
        profileLabel.setVerticalAlignment(SwingConstants.CENTER);
        profileLabel.setOpaque(true);
        setSize(500, 500);

        attributesPanel = new JPanel();

        setLayout(new BorderLayout());
        add(profileLabel, BorderLayout.NORTH);
        updateAttributes(profile);
        setVisible(true);
        add(attributesPanel, BorderLayout.CENTER);

    }
    public static void updateAttributes(Profile profile) {
        attributesPanel.removeAll();

        attributesPanel.setLayout(new GridLayout(10, 3));
        attributesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for(Attribute a: profile.getPower().keySet()){
            JLabel label = new JLabel(a.toString() + " --- " + profile.getPower().get(a).toString());
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            attributesPanel.add(label);
        }

    }
}
