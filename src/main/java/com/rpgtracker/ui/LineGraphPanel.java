package main.java.com.rpgtracker.ui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class LineGraphPanel extends JPanel {
    private final Map<LocalDate, Integer> data;
    private final String title;

    public LineGraphPanel(Map<LocalDate, Integer> data, String title) {
        this.data = data;
        this.title = title;
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g2.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        g2.drawString(title, (getWidth() - titleWidth) / 2, 20);

        int padding = 60;
        int graphWidth = getWidth() - 2 * padding;
        int graphHeight = getHeight() - 2 * padding;
        int originX = padding;
        int originY = getHeight() - padding;

        g2.drawLine(originX, originY, originX + graphWidth, originY);
        g2.drawLine(originX, originY, originX, originY - graphHeight);

        if (data == null || data.isEmpty()) {
            g2.drawString("No data available", originX + graphWidth / 2 - 50, originY - graphHeight / 2);
            return;
        }

        int minValue = data.values().stream().min(Integer::compareTo).orElse(0);
        int maxValue = data.values().stream().max(Integer::compareTo).orElse(1);
        int valueRange = maxValue - minValue;

        int pointCount = data.size();
        int xStep = graphWidth / (pointCount - 1);
        double yScale = (double) graphHeight / valueRange;

        int previousX = -1;
        int previousY = -1;
        int index = 0;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");

        for (Map.Entry<LocalDate, Integer> entry : data.entrySet()) {
            LocalDate date = entry.getKey();
            int value = entry.getValue();

            int x = originX + index * xStep;
            int y = originY - (int) ((value - minValue) * yScale);

            g2.setColor(Color.BLUE);
            g2.fillOval(x - 3, y - 3, 6, 6);

            if (previousX != -1 && previousY != -1) {
                g2.setColor(Color.RED);
                g2.drawLine(previousX, previousY, x, y);
            }

            g2.setColor(Color.BLACK);
            String dateLabel = date.format(dateFormatter);
            int labelWidth = g2.getFontMetrics().stringWidth(dateLabel);
            g2.drawString(dateLabel, x - labelWidth / 2, originY + 20);

            previousX = x;
            previousY = y;
            index++;
        }

        int yLabelStep = valueRange / 10 + 1; // Adjust step for readability
        for (int i = 0; i <= valueRange; i += yLabelStep) {
            int y = originY - (int) (i * yScale);
            String label = String.valueOf(minValue + i);
            int labelWidth = g2.getFontMetrics().stringWidth(label);
            g2.drawString(label, originX - labelWidth - 5, y + 5);
        }
    }

}
