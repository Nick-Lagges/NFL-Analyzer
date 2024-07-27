package org.nfl.data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

public class GUI implements ActionListener {
    private String playerName;
    private int playerYear;
    private String stat;
    private boolean nameTextEntered = false;
    private boolean yearTextEntered = false;

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public GUI() {
        Utils util = new Utils();
        JFrame frame = new JFrame();
        JLabel nameLabel = new JLabel("Player Name");
        JLabel yearLabel = new JLabel("Year");
        JLabel statLabel = new JLabel("Stat");
        JLabel chartLabel = new JLabel("Confirm");

        JTextField nameField = new JTextField("Player Name", 15);
        JTextField yearField = new JTextField("Year", 15);
        ArrayList<String> keys = new ArrayList<>(Utils.STAT_TO_ID.keySet());
        String[] choices = new String[keys.size()];
        for ( int i = 0; i < keys.size(); i++) {
            choices[i] = keys.get(i);
        }
        Arrays.sort(choices);
        final JComboBox<String> cb = new JComboBox<String>(choices);
        JButton execute = new JButton("GO");
        execute.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                playerYear = Integer.valueOf(yearField.getText());
                stat = cb.getSelectedItem().toString();
                System.out.println(playerName + playerYear + stat);
            }
        });

        nameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if ( ! nameTextEntered ) {
                    nameField.setText("");
                    nameTextEntered = true;
                }
            }

            public void focusLost(FocusEvent e) {
                //nothing
            }
        });
        yearField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if ( ! yearTextEntered ) {
                    yearField.setText("");
                    yearTextEntered = true;
                }
            }

            public void focusLost(FocusEvent e) {
                //nothing
            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(4,2));
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(yearLabel, BorderLayout.WEST);
        panel.add(yearField, BorderLayout.CENTER);
        panel.add(statLabel, BorderLayout.WEST);
        panel.add(cb, BorderLayout.CENTER);
        panel.add(chartLabel);
        panel.add(execute);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Player Stat Analysis");
        frame.setSize(500, 500);
        frame.setLocation(430, 100);
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args){
        new GUI();
    }
}
