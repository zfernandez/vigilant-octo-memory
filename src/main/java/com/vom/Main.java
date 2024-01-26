package com.vom;

import com.vom.util.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("VOM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea logTextArea = new JTextArea(10, 20);
        JScrollPane logScrollPane = new JScrollPane(
            logTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );


        Dice dFourDice = new Dice(4);
        JButton dFourButton = new JButton("d4");
        dFourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append("Rolled: " + dFourDice.roll() + "\n");
            }
        });

        Dice dSixDice = new Dice(6);
        JButton dSixButton = new JButton("d6");
        dSixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append("Rolled: " + dSixDice.roll() + "\n");
            }
        });

        Dice dEightDice = new Dice(8);
        JButton dEightButton = new JButton("d8");
        dEightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append("Rolled: " + dEightDice.roll() + "\n");
            }
        });

        Dice dTenDice = new Dice(10);
        JButton dTenButton = new JButton("d10");
        dTenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append("Rolled: " + dTenDice.roll() + "\n");
            }
        });

        Dice dTwelveDice = new Dice(12);
        JButton dTwelveButton = new JButton("d12");
        dTwelveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append("Rolled: " + dTwelveDice.roll() + "\n");
            }
        });

        Dice dTwentyDice = new Dice(20);
        JButton dTwentyButton = new JButton("d20");
        dTwentyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append("Rolled: " + dTwentyDice.roll() + "\n");
            }
        });

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        dicePanel.add(dFourButton);
        dicePanel.add(dSixButton);
        dicePanel.add(dEightButton);
        dicePanel.add(dTenButton);
        dicePanel.add(dTwelveButton);
        dicePanel.add(dTwentyButton);

        JTextField inputTextField = new JTextField(20);
        inputTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logTextArea.append(inputTextField.getText() + "\n");
                inputTextField.setText("");
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(dicePanel, BorderLayout.CENTER);
        inputPanel.add(inputTextField, BorderLayout.SOUTH);

        frame.getContentPane().add(logScrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);

        frame.pack();

        frame.setVisible(true);
    }
}
