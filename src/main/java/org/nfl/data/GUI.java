package org.nfl.data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class GUI implements ActionListener {
    private String playerName;
    private HashMap<String,Player> playerMap= new HashMap<>();
    private ArrayList<String> playerNameList = new ArrayList<>();
    private Player selectedPlayer;
    private int playerYear;
    private String stat;
    private double line;
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
        JLabel lineLabel = new JLabel("Line");

        JTextField nameField = new JTextField("Player Name", 15);
        JTextField yearField = new JTextField("2023", 15);
        JTextField lineField = new JTextField("0.5", 15);

        ArrayList<String> keys = new ArrayList<>(Utils.STAT_TO_ID.keySet());
        keys.remove(keys.indexOf("age"));
        keys.remove(keys.indexOf("date"));
        keys.remove(keys.indexOf("location"));
        String[] statChoices = new String[keys.size()];
        for ( int i = 0; i < keys.size(); i++) {
            statChoices[i] = keys.get(i);
        }
        Arrays.sort(statChoices);
        final JComboBox<String> statDropdown = new JComboBox<String>(statChoices);

        String[] chartToSee = {"Game Log", "Predictor"};
        final JComboBox<String> chartDropdown = new JComboBox<String>(chartToSee);

        JButton execute = new JButton("GO");
        execute.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerName = nameField.getText();
                playerYear = Integer.valueOf(yearField.getText());
                stat = statDropdown.getSelectedItem().toString();
                line = Double.valueOf(lineField.getText());
                if ( playerNameList.contains(playerName) ) selectedPlayer = playerMap.get(playerName);
                else {
                    try {
                        selectedPlayer = new Player(playerName);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                try {
                    if ( chartDropdown.getSelectedItem().equals("Game Log") ) {
                        DataVisualization dataVisualization = new DataVisualization(selectedPlayer, playerYear, stat, line);
                        dataVisualization.start();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
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
        lineField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if ( ! nameTextEntered ) {
                    lineField.setText("");
                    nameTextEntered = true;
                }
            }

            public void focusLost(FocusEvent e) {
                //nothing
            }
        });
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(5,2));
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(yearLabel, BorderLayout.WEST);
        panel.add(yearField, BorderLayout.CENTER);
        panel.add(statLabel, BorderLayout.WEST);
        panel.add(statDropdown, BorderLayout.CENTER);
        panel.add(lineLabel, BorderLayout.CENTER);
        panel.add(lineField, BorderLayout.WEST);

        panel.add(chartDropdown, BorderLayout.WEST);
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
