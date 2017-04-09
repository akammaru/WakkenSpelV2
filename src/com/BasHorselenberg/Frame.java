package com.BasHorselenberg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Bas_Horselenberg on 5-4-2017.
 * main creation of all frames.
 */
public class Frame extends JFrame {
    //creating variables.

    //arrayList for storing the dices.
    public ArrayList<DiceLogic> diceRoll;

    //creating the main frame anddifferent pannels.
    private JFrame frame;
    private JPanel input;
    private JPanel result;
    private JPanel output;
    private JPanel tips;

    //Textfields for the in and output of the game.
    private JTextField wakValue;
    private JTextField ijsbeerValue;
    private JTextField pinguinValue;
    private JTextField setDice;
    private JTextField wakResult;
    private JTextField ijsbeerResult;
    private JTextField pinguinResult;

    //Textfields for the output of statistics.
    private JTextField winField;
    private JTextField losField;
    private JTextField gameField;

    //Textfield for tips
    private JTextField errorField;
    private JTextField tipField;

    //creating values for classwide use.
    private int wins;
    private int losses;
    private int totalGames;
    private int curLosses;

    public Frame() {
        //initialising and basic setup of variables and the main screen.
        frame = new JFrame();
        frame.setTitle("Wakken Spel");
        diceRoll = new ArrayList<DiceLogic>();

        //disabeling LayoutManager.
        frame.setLayout(null);

        //generating the GUI.
        generateUI();

        //fill output Textfields.
        updateTextFields();

        //Initialising counters.
        wins = 0;
        losses = 0;
        totalGames = 0;
        curLosses = 0;

        // changing background color to gray.
        frame.getContentPane().setBackground(Color.green);
        // setting width and hight of the screen.
        frame.setSize(1200, 800);
        // make sure the programs quits properly when closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // allow the pannel to show up.
        frame.setVisible(true);
    }

    /**
     * This method will add the spesified ammount of dice to the arrayList diceRoll (as objects), for later use.
     *
     * @param diceAmount an int of the ammount if dice that need to be added to the screen.
     */
    public void addDice(int diceAmount) {
        totalGames++;
        int number = diceAmount / 2;
        int row1 = number;
        if ((diceAmount % 2) > 0) {
            row1++;
        }
        for (int i = 0; i < diceAmount; i++) {
            diceRoll.add(new DiceLogic(i));
            int rolled = diceRoll.get(i).getDiceRoll();
            DicePrint printDice = new DicePrint(rolled);
            frame.add(printDice);
            if (i < row1) {
                printDice.setBounds(20 + (161 * i), 20, 151, 151);
            } else {
                printDice.setBounds(20 + (161 * (i - row1)), 180, 151, 151);
            }
        }

    }

    /**
     * removed the dices from the screen and empties the arrayList diceRoll.
     */
    public void removeDice() {
        Component[] comp = frame.getRootPane().getContentPane().getComponents();
        for (Component component : comp) {
            //System.out.println(comp[i].getClass().getName());
            if (component.getClass().getName().endsWith("DicePrint")) {
                //System.out.println("Found a dice");
                frame.getRootPane().getContentPane().remove(component);
                diceRoll.remove(0);
            }
        }
        frame.revalidate();
        frame.repaint();
    }

    /**
     * generates the complete GUI other than the dices.
     */
    public void generateUI() {
        // *********** Input Panel ****************
        //adding the input panel and placing it correctly, disabeling the content manager.
        input = new JPanel();
        frame.add(input);
        input.setBackground(Color.blue);
        input.setBounds(5, 370, 1006, 35);
        input.setLayout(null);

        //labels for the input panel.
        JLabel wakLabel = new JLabel("Wakken: ");
        JLabel ijsbeerLabel = new JLabel("IJsberen: ");
        JLabel pinguinLabel = new JLabel("Pinguins: ");
        JLabel setDiceLabel = new JLabel("number of Dice: ");

        //text fields for the input panel.
        wakValue = new JTextField(4);
        ijsbeerValue = new JTextField(4);
        pinguinValue = new JTextField(4);
        setDice = new JTextField(2);

        //button for the input panel.
        JButton commit = new JButton("Check");
        JButton dice = new JButton("Rol");

        //placing the labels.
        //wak Label.
        input.add(wakLabel);
        wakLabel.setForeground(Color.WHITE);
        Dimension wakLabelSize = wakLabel.getPreferredSize();
        wakLabel.setBounds(25, 5, wakLabelSize.width, wakLabelSize.height);

        //ijsbeer Label.
        input.add(ijsbeerLabel);
        ijsbeerLabel.setForeground(Color.WHITE);
        Dimension ijsbeerLabelSize = ijsbeerLabel.getPreferredSize();
        ijsbeerLabel.setBounds(140, 5, ijsbeerLabelSize.width, ijsbeerLabelSize.height);

        //pinguin Label.
        input.add(pinguinLabel);
        pinguinLabel.setForeground(Color.WHITE);
        Dimension pinguinLabelSize = pinguinLabel.getPreferredSize();
        pinguinLabel.setBounds(255, 5, pinguinLabelSize.width, pinguinLabelSize.height);

        //set Dice ammout Label.
        input.add(setDiceLabel);
        setDiceLabel.setForeground(Color.WHITE);
        Dimension setDiceLabelSize = setDiceLabel.getPreferredSize();
        setDiceLabel.setBounds(480, 5, setDiceLabelSize.width, setDiceLabelSize.height);

        //placing the textFields.
        //wak Text Field.
        input.add(wakValue);
        Dimension wakValueSize = wakValue.getPreferredSize();
        wakValue.setBounds(80, 5, wakValueSize.width, wakValueSize.height);

        //ijsbeer Text Field.
        input.add(ijsbeerValue);
        Dimension ijsbeerValueSize = ijsbeerValue.getPreferredSize();
        ijsbeerValue.setBounds(200, 5, ijsbeerValueSize.width, ijsbeerValueSize.height);

        //pinguin Text Field
        input.add(pinguinValue);
        Dimension pinguinValueSize = pinguinValue.getPreferredSize();
        pinguinValue.setBounds(320, 5, pinguinValueSize.width, pinguinValueSize.height);

        //set Dice Text Field.
        input.add(setDice);
        Dimension setDiceSize = setDice.getPreferredSize();
        setDice.setBounds(575, 5, setDiceSize.width, setDiceSize.height);

        //adding Button
        //Check Button
        input.add(commit);
        Dimension commitButtonSize = commit.getPreferredSize();
        commit.setBounds(400, 5, commitButtonSize.width, commitButtonSize.height);
        commit.setEnabled(false);
        //adding ActionListener check.
        commit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!wakValue.getText().isEmpty() && !ijsbeerValue.getText().isEmpty() && !pinguinValue.getText().isEmpty()) {
                    String regex = "\\d+";
                    if (wakValue.getText().matches(regex) && ijsbeerValue.getText().matches(regex) && pinguinValue.getText().matches(regex)) {
                        check();
                        updateTextFields();
                        giveTips();
                        dice.setEnabled(true);
                        commit.setEnabled(false);
                        errorHandeling(0);
                        setDice.setEnabled(true);
                    } else {
                        int errorCode = 10;
                        if (!wakValue.getText().matches(regex)) {
                            errorCode++;
                        }
                        if (!ijsbeerValue.getText().matches(regex)) {
                            errorCode = errorCode + 2;
                        }
                        if (!pinguinValue.getText().matches(regex)) {
                            errorCode = errorCode + 4;
                        }
                        errorHandeling(errorCode);
                    }
                } else {
                    int errorCode = 0;
                    if (wakValue.getText().isEmpty()) {
                        errorCode++;
                    }
                    if (ijsbeerValue.getText().isEmpty()) {
                        errorCode = errorCode + 2;
                    }
                    if (pinguinValue.getText().isEmpty()) {
                        errorCode = errorCode + 4;
                    }
                    errorHandeling(errorCode);
                }

            }
        });

        //Dice set Button
        input.add(dice);
        Dimension diceSize = dice.getPreferredSize();
        dice.setBounds(610, 5, diceSize.width, diceSize.height);
        //adding ActionListener dice.
        dice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String diceFetch = setDice.getText();
                if (!diceFetch.isEmpty()) {
                    //regular expresion to check if a number has been entered.
                    String regex = "\\d+";
                    if (diceFetch.matches(regex)) {
                        //fetch the amount of dice and place in a int.
                        int diceAmount = Integer.valueOf(diceFetch);
                        if (diceAmount >= 3 && diceAmount <= 12) {
                            removeDice();
                            addDice(diceAmount);
                            resetInput();


                            dice.setEnabled(false);
                            setDice.setEnabled(false);
                            commit.setEnabled(true);

                            errorHandeling(0);
                        } else {
                            if (diceAmount < 3) {
                                errorHandeling(10);
                            } else if (diceAmount > 12) {
                                errorHandeling(18);
                            } else {
                                errorHandeling(99);
                            }
                        }
                    } else {
                        errorHandeling(8);
                    }
                } else if (setDice.getText().isEmpty()) {
                    errorHandeling(9);
                }
            }
        });


        // *********** Result Panel ****************
        //adding result panel to the frame and setting default location and values.
        result = new JPanel();
        frame.add(result);
        result.setBackground(Color.magenta);
        result.setBounds(5, 410, 1006, 35);
        result.setLayout(null);

        //restult labels.
        JLabel wakResultLabel = new JLabel("Correct aantal Wakken: ");
        JLabel ijsbeerResultLabel = new JLabel("Correct aantal IJsberen: ");
        JLabel pinguinResultLabel = new JLabel("Correct aantal Pinguins: ");

        //result Text Fields
        wakResult = new JTextField(4);
        ijsbeerResult = new JTextField(4);
        pinguinResult = new JTextField(4);

        //placing wak Labels.
        //wak result Label.
        result.add(wakResultLabel);
        wakResultLabel.setForeground(Color.black);
        Dimension wakResultLabelSize = wakResultLabel.getPreferredSize();
        wakResultLabel.setBounds(25, 5, wakResultLabelSize.width, wakResultLabelSize.height);

        //ijsbeer result label.
        result.add(ijsbeerResultLabel);
        ijsbeerResultLabel.setForeground(Color.black);
        Dimension ijsbeerResultLabelSize = ijsbeerResultLabel.getPreferredSize();
        ijsbeerResultLabel.setBounds(225, 5, ijsbeerResultLabelSize.width, ijsbeerResultLabelSize.height);

        //pinguin result label.
        result.add(pinguinResultLabel);
        pinguinResultLabel.setForeground(Color.black);
        Dimension pinguinResultLabelSize = pinguinResultLabel.getPreferredSize();
        pinguinResultLabel.setBounds(430, 5, pinguinResultLabelSize.width, pinguinResultLabelSize.height);

        //placing Textfield.
        //wak result.
        result.add(wakResult);
        wakResult.setEnabled(false);
        wakResult.setDisabledTextColor(Color.BLACK);
        Dimension wakResultSize = wakResult.getPreferredSize();
        wakResult.setBounds(165, 5, wakResultSize.width, wakResultSize.height);

        //ijsbeer Textfield.
        result.add(ijsbeerResult);
        ijsbeerResult.setEnabled(false);
        ijsbeerResult.setDisabledTextColor(Color.BLACK);
        Dimension ijsbeerResultSize = ijsbeerResult.getPreferredSize();
        ijsbeerResult.setBounds(370, 5, ijsbeerResultSize.width, ijsbeerResultSize.height);

        //Pinguin Textfield
        result.add(pinguinResult);
        pinguinResult.setEnabled(false);
        pinguinResult.setDisabledTextColor(Color.BLACK);
        Dimension pinguinResultSize = pinguinResult.getPreferredSize();
        pinguinResult.setBounds(575, 5, pinguinResultSize.width, pinguinResultSize.height);

        // *********** Output Panel ****************
        //adding result panel to the frame and setting default location and values.
        output = new JPanel();
        frame.add(output);
        output.setBackground(Color.black);
        output.setBounds(508, 450, 503, 300);
        output.setLayout(null);

        //Creating labels.
        JLabel winLabel = new JLabel("Total Wins: ");
        JLabel losLabel = new JLabel("Total Losses: ");
        JLabel gameLabel = new JLabel("Total amount of Games: ");

        //Creating Buttons.
        JButton reset = new JButton("Reset");

        //initalising Text Fields.
        winField = new JTextField(3);
        losField = new JTextField(3);
        gameField = new JTextField(3);

        //adding labels to the panel and setting them in place.
        //winLabel.
        output.add(winLabel);
        winLabel.setForeground(Color.WHITE);
        Dimension winlabelSize = winLabel.getPreferredSize();
        winLabel.setBounds(5, 5, winlabelSize.width, winlabelSize.height);

        //losLabel
        output.add(losLabel);
        losLabel.setForeground(Color.WHITE);
        Dimension losLabelSize = losLabel.getPreferredSize();
        losLabel.setBounds(5, 25, losLabelSize.width, losLabelSize.height);

        //games Played Label.
        output.add(gameLabel);
        gameLabel.setForeground(Color.WHITE);
        Dimension gameLabelSize = gameLabel.getPreferredSize();
        gameLabel.setBounds(5, 45, gameLabelSize.width, gameLabelSize.height);

        //placing and adding Text Fields.
        //win Field.
        output.add(winField);
        winField.setEnabled(false);
        winField.setDisabledTextColor(Color.BLACK);
        Dimension winFieldSize = winField.getPreferredSize();
        winField.setBounds(150, 5, winFieldSize.width, winFieldSize.height);

        //los Field.
        output.add(losField);
        losField.setEnabled(false);
        losField.setDisabledTextColor(Color.BLACK);
        Dimension losFieldSize = losField.getPreferredSize();
        losField.setBounds(150, 25, losFieldSize.width, losFieldSize.height);

        //game Field.
        output.add(gameField);
        gameField.setEnabled(false);
        gameField.setDisabledTextColor(Color.BLACK);
        Dimension gameFieldSize = gameField.getPreferredSize();
        gameField.setBounds(150, 45, gameFieldSize.width, gameFieldSize.height);

        //adding reset button.
        output.add(reset);
        Dimension resetSize = reset.getPreferredSize();
        reset.setBounds(150, 65, resetSize.width, resetSize.height);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorHandeling(0);
                resetCounters();
                updateTextFields();
                resetInput();
                dice.setEnabled(true);
                commit.setEnabled(false);
                setDice.setEnabled(true);
            }
        });

        // *********** tips Panel ****************
        //adding tips panel to the frame and setting default location and values.
        tips = new JPanel();
        frame.add(tips);
        tips.setBounds(5, 450, 503, 300);
        tips.setLayout(null);

        //initialising textfields.
        errorField = new JTextField(44);
        tipField = new JTextField(44);

        //adding error field.
        tips.add(errorField);
        errorField.setEnabled(false);
        errorField.setDisabledTextColor(Color.BLACK);
        Dimension errorFieldSize = errorField.getPreferredSize();
        errorField.setBounds(5, 25, errorFieldSize.width, errorFieldSize.height);
        errorField.setVisible(false);

        //adding tips field.
        tips.add(tipField);
        tipField.setEnabled(false);
        tipField.setDisabledTextColor(Color.BLACK);
        Dimension tipFieldSize = tipField.getPreferredSize();
        tipField.setBounds(5, 150, tipFieldSize.width, tipFieldSize.height);
        tipField.setVisible(false);
    }

    /**
     * This method will check if the input is correct compared to the numbers found in de diceRoll arrayList.
     * this includes the coloring of the textfields for correct answeres green, for incorrect red.
     * last the counters for won or lost games and total games played are updates.
     */
    private void check() {
        wakValue.setEnabled(false);
        ijsbeerValue.setEnabled(false);
        pinguinValue.setEnabled(false);

        String wakFetch = wakValue.getText();
        String ijsFetch = ijsbeerValue.getText();
        String pinFetch = pinguinValue.getText();

        wakValue.setText(wakFetch);
        ijsbeerValue.setText(ijsFetch);
        pinguinValue.setText(pinFetch);

        int totalWak = 0;
        int totalIJs = 0;
        int totalPin = 0;

        for (int i = 0; i < diceRoll.size(); i++) {
            switch (diceRoll.get(i).getDiceRoll()) {
                case 1:
                    totalWak++;
                    totalPin = totalPin + 6;
                    break;
                case 3:
                    totalWak++;
                    totalIJs = totalIJs + 2;
                    totalPin = totalPin + 4;
                    break;
                case 5:
                    totalWak++;
                    totalIJs = totalIJs + 4;
                    totalPin = totalPin + 2;
                    break;
                default:
                    //nothing happens.
                    break;
            }
        }
//        System.out.println("Totaal gevonden Wakken: " + totalWak + ".\n" +
//                "Totaal gevonden Ijsberen: " + totalIJs + "\n" +
//                "Totaal gevonden Pinguins: " + totalPin + "\n");

        wakResult.setText("" + totalWak);
        ijsbeerResult.setText("" + totalIJs);
        pinguinResult.setText("" + totalPin);

        if (Integer.valueOf(wakFetch) == totalWak) {
            wakValue.setBackground(Color.GREEN);
            wakValue.setDisabledTextColor(Color.BLACK);
        } else {
            wakValue.setBackground(Color.RED);
            wakValue.setDisabledTextColor(Color.BLACK);
        }

        if (Integer.valueOf(ijsFetch) == totalIJs) {
            ijsbeerValue.setBackground(Color.GREEN);
            ijsbeerValue.setDisabledTextColor(Color.BLACK);
        } else {
            ijsbeerValue.setBackground(Color.RED);
            ijsbeerValue.setDisabledTextColor(Color.BLACK);
        }

        if (Integer.valueOf(pinFetch) == totalPin) {
            pinguinValue.setBackground(Color.GREEN);
            pinguinValue.setDisabledTextColor(Color.BLACK);
        } else {
            pinguinValue.setBackground(Color.RED);
            pinguinValue.setDisabledTextColor(Color.BLACK);
        }

        if (Integer.valueOf(wakFetch) == totalWak && Integer.valueOf(ijsFetch) == totalIJs && Integer.valueOf(pinFetch) == totalPin) {
            wins++;
            curLosses = 0;
        } else {
            losses++;
            curLosses++;
        }
    }

    /**
     * this method resets the counters: wins losses and totalGames to 0.
     */
    private void resetCounters() {
        wins = 0;
        losses = 0;
        totalGames = 0;
    }

    /**
     * This method updates the textfields with the new numbers for the textfields: winField, losField and gameField.
     */
    private void updateTextFields() {
        winField.setText("" + wins);
        losField.setText("" + losses);
        gameField.setText("" + totalGames);
    }

    /**
     * This method empties out all the values in the input fields.
     */
    private void resetInput() {
        //reset wak field.
        wakValue.setEnabled(true);
        wakValue.setBackground(Color.WHITE);
        wakValue.setText(null);

        //reset ijsbeer field.
        ijsbeerValue.setEnabled(true);
        ijsbeerValue.setBackground(Color.WHITE);
        ijsbeerValue.setText(null);

        //reset pinguin field.
        pinguinValue.setEnabled(true);
        pinguinValue.setBackground(Color.WHITE);
        pinguinValue.setText(null);

    }

    /**
     * this method sets the tipfield to visible after an even amount of losses has been detected.
     * once set visible a random tip will be show.
     */
    private void giveTips() {

        String tip = "Leeg";

        if ((curLosses % 2) == 0 && curLosses != 0) {
            int tipNr = (new Random().nextInt(6));
            switch (tipNr) {
                case 0:
                    tip = "Het aantal ogen op voor en achterkant zijn altijd 7.";
                    break;
                case 1:
                    tip = "Het aantal pinguins op de zuidpool is het tegevenovergestende van de ijsberen.";
                    break;
                case 2:
                    tip = "De middelste stip is een wak.";
                    break;
                case 3:
                    tip = "Op de zuidpool leven geen ijsberen";
                    break;
                case 4:
                    tip = "Op de Noordpool komen in dit spel geen pinguins";
                    break;
                case 5:
                    tip = "De zuidpool heeft geen wakken.";
            }
            System.out.println("tip: " + tip);
            tipField.setVisible(true);
            tipField.setText(tip);
        } else {
            tipField.setVisible(false);
        }


    }

    /**
     * this method will catch all errors generated by incorrect use of the program and give an error message.
     *
     * @param error int with the number of the error reported. This can come from different locations.
     *              each error code is unique and comes from a specific location.
     *              0 is an exception for that, 0 is the general return value returned when everything is ok.
     */
    private void errorHandeling(int error) {
        String errormsg = "Leeg";
        System.out.println("error number: " + error);
        if (error > 0) {
            switch (error) {
                case 1:
                    errormsg = "U bent de wakken vergeten.";
                    break;
                case 2:
                    errormsg = "U bent de ijsberen vergeten.";
                    break;
                case 3:
                    errormsg = "U bent de wakken en ijsberen vergeten.";
                    break;
                case 4:
                    errormsg = "U bent de pinguins vergeten.";
                    break;
                case 5:
                    errormsg = "U bent de wakken en pinguins vergeten.";
                    break;
                case 6:
                    errormsg = "U bent de ijsberen en pinguins vergeten.";
                    break;
                case 7:
                    errormsg = "Vul uw antwoord in.";
                    break;
                case 8:
                    errormsg = "Vul graag alleen cijfers in voor het aantal Dobbelstenen.";
                    break;
                case 9:
                    errormsg = "Vul graag eerst het aantal dobbelstenen in.";
                    break;
                case 10:
                    errormsg = "Het minimaal aantal dobelstenen is drie.";
                    break;
                case 11:
                    errormsg = "Vul graag een nummer in bij wakken.";
                    break;
                case 12:
                    errormsg = "Vul graag een nummer in bij ijsberen.";
                    break;
                case 13:
                    errormsg = "Vul graag een nummer in bij wakken en ijsberen.";
                    break;
                case 14:
                    errormsg = "Vul graag een nummer in bij pinguins.";
                    break;
                case 15:
                    errormsg = "Vul graag een nummer in bij wakken en pinguins.";
                    break;
                case 16:
                    errormsg = "Vul graag een nummer in bij ijsberen en pinguins";
                    break;
                case 17:
                    errormsg = "Vul graag een nummer in bij alle vakken.";
                    break;
                case 18:
                    errormsg = "Het maximaal aantal dobbelstenen is twaalf.";
                    break;
                default:
                    errormsg = "Er is iets mis gegaan, niet zeker waar.";
            }
            System.out.println("Error message: " + errormsg);
            errorField.setVisible(true);
            errorField.setText(errormsg);
        } else {
            errorField.setVisible(false);
            errorField.setText("Leeg");
        }

    }
}