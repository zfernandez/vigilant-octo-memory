package com.vom.ui;

import com.vom.util.DiceRoller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Window implements Draggable {
    public Window() {

    }

    public void init() {
        DiceRoller diceRoller = new DiceRoller();

        JFrame frame = new JFrame("VOM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea scratchArea = new JTextArea(10, 20);
        JScrollPane scratchScrollPane = new JScrollPane(
            scratchArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        JTextArea logTextArea = new JTextArea(5, 20);
        logTextArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(
            logTextArea,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        JTextField inputTextField = new JTextField(20);
        inputTextField.addActionListener(e -> {
            logTextArea.append("Rolled: " + diceRoller.diceRoll(inputTextField.getText()) + "\n");
            inputTextField.setText("");
        });

        JPanel dicePanel = new JPanel();
        dicePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d4"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d6"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d8"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d10"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d12"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        dicePanel.add(getDiceButton(logTextArea, diceRoller, "d20"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        dicePanel.add(inputTextField, gbc);

        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("A menu with menu items");
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("New", KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Creates a new file");
        menuItem.addActionListener(e -> System.out.println("New file created!"));
        menu.add(menuItem);

        menuItem = new JMenuItem("Open...", KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Opens a file");
        menuItem.addActionListener(e -> System.out.println("Opening file!"));
        menu.add(menuItem);

        menuItem = new JMenuItem("Save", KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Saves a file");
        menuItem.addActionListener(e -> System.out.println("Saving file!"));
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Exits");
        menuItem.addActionListener(e -> frame.dispose());
        menu.add(menuItem);

        JPanel inputPanel = new JPanel();
        inputPanel.add(dicePanel, BorderLayout.WEST);
        inputPanel.add(logScrollPane, BorderLayout.CENTER);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(scratchScrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);

        frame.pack();

        frame.setVisible(true);
    }

    private static JButton getDiceButton(JTextArea logTextArea, DiceRoller diceRoller, String diceText) {
        JButton button = new JButton(diceText);
        button.addActionListener(e -> logTextArea.append("Rolled: " + diceRoller.diceRoll(button.getText()) + "\n"));
        return button;
    }

    @Override
    public void drag(int dx, int dy) {

    }
}
