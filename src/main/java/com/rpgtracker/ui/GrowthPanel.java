package main.java.com.rpgtracker.ui;

import main.java.com.rpgtracker.models.Attribute;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class GrowthPanel extends JPanel {
    MainFrame mainFrame;
    public GrowthPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        if(mainFrame.getGrowth().size() <= 1) {
            JLabel growthLabel = new JLabel("at least 2 days of data needed to shsow growth.");
            growthLabel.setHorizontalAlignment(SwingConstants.CENTER);
            growthLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
            growthLabel.setVerticalAlignment(SwingConstants.CENTER);
            add(growthLabel, BorderLayout.CENTER);
            return;
        }
        Map<LocalDate, Integer> strength = new HashMap<>();
        Map<LocalDate, Integer> intelect = new HashMap<>();
        Map<LocalDate, Integer> felicity = new HashMap<>();
        Map<LocalDate, Integer> agility = new HashMap<>();
        Map<LocalDate, Integer> imagination = new HashMap<>();
        Map<LocalDate, Integer> quick = new HashMap<>();
        for(LocalDate localDate: mainFrame.getGrowth().keySet()){
            strength.put(localDate, mainFrame.getGrowth().get(localDate).getPower().get(Attribute.STRENGTH));
            intelect.put(localDate, mainFrame.getGrowth().get(localDate).getPower().get(Attribute.INTELLECT));
            felicity.put(localDate, mainFrame.getGrowth().get(localDate).getPower().get(Attribute.FELICITY_OF_PHRASE));
            agility.put(localDate, mainFrame.getGrowth().get(localDate).getPower().get(Attribute.AGILITY));
            imagination.put(localDate, mainFrame.getGrowth().get(localDate).getPower().get(Attribute.IMAGINATION));
            quick.put(localDate, mainFrame.getGrowth().get(localDate).getPower().get(Attribute.QUICK_WIT));
        }

        JMenuBar menuBar = new JMenuBar();
        JMenu attributes = new JMenu("attributes");
        attributes.setFont(new Font("Verdana", Font.PLAIN, 14));
        JMenuItem strengthButton = new JMenuItem("strength");
        strengthButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        JMenuItem intelectButton = new JMenuItem("intelect");
        intelectButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        JMenuItem felicityButton = new JMenuItem("felicity");
        felicityButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        JMenuItem agilityButton = new JMenuItem("agility");
        agilityButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        JMenuItem imaginationButton = new JMenuItem("imagination");
        imaginationButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        JMenuItem quickButton = new JMenuItem("quick wit");
        quickButton.setFont(new Font("Verdana", Font.PLAIN, 14));
        attributes.add(strengthButton);
        attributes.add(intelectButton);
        attributes.add(felicityButton);
        attributes.add(agilityButton);
        attributes.add(imaginationButton);
        attributes.add(quickButton);
        menuBar.add(attributes);
        add(menuBar, BorderLayout.NORTH);

        JPanel graphPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        graphPanel.setLayout(cardLayout);

        strengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.removeAll();
                LineGraphPanel graph = new LineGraphPanel(strength, "Strength");
                graphPanel.add(graph);
                add(graphPanel, BorderLayout.CENTER);
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });

        intelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.removeAll();
                LineGraphPanel graph = new LineGraphPanel(intelect, "Intelect");
                graphPanel.add(graph);
                add(graphPanel, BorderLayout.CENTER);
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });
        felicityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.removeAll();
                LineGraphPanel graph = new LineGraphPanel(felicity, "Felicity");
                graphPanel.add(graph);
                add(graphPanel, BorderLayout.CENTER);
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });
        agilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.removeAll();
                LineGraphPanel graph = new LineGraphPanel(agility, "Agility");
                graphPanel.add(graph);
                add(graphPanel, BorderLayout.CENTER);
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });
        imaginationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.removeAll();
                LineGraphPanel graph = new LineGraphPanel(imagination, "Imagination");
                graphPanel.add(graph);
                add(graphPanel, BorderLayout.CENTER);
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });
        quickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphPanel.removeAll();
                LineGraphPanel graph = new LineGraphPanel(quick, "Quick wit");
                graphPanel.add(graph);
                add(graphPanel, BorderLayout.CENTER);
                graphPanel.revalidate();
                graphPanel.repaint();
            }
        });



        setVisible(true);

    }

}
