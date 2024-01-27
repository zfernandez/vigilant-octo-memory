package com.vom;

import com.vom.util.DiceRoller;

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

        DiceRoller diceRoller = new DiceRoller();
        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new FlowLayout());
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d4"));
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d6"));
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d8"));
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d10"));
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d12"));
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d20"));

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

    private static JButton getDiceButton(JTextArea logTextArea, DiceRoller diceRoller, String diceText) {
        JButton button = new JButton(diceText);
        button.addActionListener(e -> logTextArea.append("Rolled: " + diceRoller.diceRoll(button.getText()) + "\n"));
        return button;
    }
}
