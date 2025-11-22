package cs1603;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import swiftbot.*; // SwiftBotAPI, Button, Underlight, ImageSize, etc.

public class SimonGame {

    // ==== SwiftBot ====
    private static SwiftBotAPI swiftBot;

    // ==== Game state ====
    private static final Random RNG = new Random();
    private static final String[] COLOURS = {"RED", "GREEN", "BLUE", "YELLOW"};
    private static volatile Character lastPress = null; // updated in button callbacks

    public static void main(String[] args) throws Exception {

        // 1) Connect to SwiftBot (same pattern as DoesMySwiftBotWork)
        try {
            swiftBot = SwiftBotAPI.INSTANCE;
        } catch (Exception e) {
            System.out.println("\nI2C disabled or SwiftBot not detected!");
            System.exit(5);
        }

        printIntro();

        int score = 0;
        int round = 1;
        List<String> sequence = new ArrayList<>();

        // main game loop
        while (true) {
            System.out.println("\n===== ROUND " + round + " =====");
            System.out.println("Current Score: " + score);

            // grow sequence by one random colour
            sequence.add(randomColour());

            // show sequence using LEDs
            showSequence(sequence);

            // read player inputs via buttons
            boolean ok = readAndCheckInputs(sequence);

            if (!ok) {
                // ---- GAME OVER FOR THIS RUN ----
                System.out.println("\nGame Over!");
                System.out.println("Final Score: " + score);

                // tidy up bot state
                swiftBot.disableAllButtons();
                swiftBot.disableButtonLights();
                swiftBot.disableUnderlights();

                // ask if they want to restart
                System.out.println("Play again? (A = Yes, B = No)");

                lastPress = null;
                // only need A and B to decide
                armButtonsForChoice();

                while (lastPress == null) {
                    Thread.sleep(5);
                }

                if (lastPress == 'B') {
                    System.out.println("Thanks for playing!");
                    return; // exit program
                }

                // restart game
                System.out.println("Restarting game from Round 1...");
                score = 0;
                round = 1;
                sequence.clear();
                continue; // back to top of while(true)
            }

            // success
            score++;
            System.out.println("Correct! Score = " + score);

            if (score >= 5) {
                celebrationDive(score);
            }

            round++;
        }
    }

    // ===== Helper methods =====

    private static void printIntro() {
        System.out.println("Welcome to SIMON on SwiftBot!");
        System.out.println("Watch the bot play a colour sequence, then repeat it using buttons:");
        System.out.println("  A -> RED   |  B -> GREEN   |  X -> BLUE   |  Y -> YELLOW");
        System.out.println();
        System.out.println("Lights used:");
        System.out.println("  - Button-lights for the current colour's button");
        System.out.println("  - Under-lights filled with the current colour");
    }

    private static String randomColour() {
        return COLOURS[RNG.nextInt(COLOURS.length)];
    }

    private static void showSequence(List<String> seq) throws InterruptedException {
        Thread.sleep(500);
        for (String colour : seq) {
            showColour(colour, true);
            Thread.sleep(750);
            showColour(colour, false);
            Thread.sleep(250);
        }
    }

    private static void showColour(String colour, boolean on) {
        // under-lights
        if (on) {
            swiftBot.fillUnderlights(rgb(colour));
        } else {
            swiftBot.disableUnderlights();
        }

        // button light
        Button b = buttonFor(colour);
        if (b != null) {
            swiftBot.setButtonLight(b, on);
        }
        if (!on) {
            swiftBot.disableButtonLights();
        }
    }

    private static int[] rgb(String colour) {
        switch (colour) {
            case "RED":
                return new int[]{255, 0, 0};
            case "GREEN":
                return new int[]{0, 255, 0};
            case "BLUE":
                return new int[]{0, 0, 255};
            case "YELLOW":
                return new int[]{255, 255, 0};
            default:
                return new int[]{255, 255, 255};
        }
    }

    private static Button buttonFor(String colour) {
        switch (colour) {
            case "RED":
                return Button.A;
            case "GREEN":
                return Button.B;
            case "BLUE":
                return Button.X;
            case "YELLOW":
                return Button.Y;
            default:
                return null;
        }
    }

    private static boolean readAndCheckInputs(List<String> seq) throws InterruptedException {
        for (int i = 0; i < seq.size(); i++) {
            String expected = seq.get(i);

            // arm for a single captured press
            armButtonsForSingleCapture();
            lastPress = null;

            while (lastPress == null) {
                Thread.sleep(5);
            }

            String pressedColour;
            switch (lastPress) {
                case 'A':
                    pressedColour = "RED";
                    break;
                case 'B':
                    pressedColour = "GREEN";
                    break;
                case 'X':
                    pressedColour = "BLUE";
                    break;
                case 'Y':
                    pressedColour = "YELLOW";
                    break;
                default:
                    pressedColour = "UNKNOWN";
            }

            // small feedback flash on pressed colour
            showColour(pressedColour, true);
            Thread.sleep(150);
            showColour(pressedColour, false);

            if (!pressedColour.equals(expected)) {
                System.out.println("Wrong step (" + (i + 1) + "). Expected "
                        + expected + " but got " + pressedColour + ".");
                return false;
            }
        }
        return true;
    }

    private static void armButtonsForSingleCapture() {
        swiftBot.disableAllButtons();

        swiftBot.enableButton(Button.A, () -> {
            if (lastPress == null) {
                lastPress = 'A';
                swiftBot.disableAllButtons();
            }
        });

        swiftBot.enableButton(Button.B, () -> {
            if (lastPress == null) {
                lastPress = 'B';
                swiftBot.disableAllButtons();
            }
        });

        swiftBot.enableButton(Button.X, () -> {
            if (lastPress == null) {
                lastPress = 'X';
                swiftBot.disableAllButtons();
            }
        });

        swiftBot.enableButton(Button.Y, () -> {
            if (lastPress == null) {
                lastPress = 'Y';
                swiftBot.disableAllButtons();
            }
        });
    }

    private static void armButtonsForChoice() {
        swiftBot.disableAllButtons();

        swiftBot.enableButton(Button.A, () -> {
            if (lastPress == null) {
                lastPress = 'A';
                swiftBot.disableAllButtons();
            }
        });

        swiftBot.enableButton(Button.B, () -> {
            if (lastPress == null) {
                lastPress = 'B';
                swiftBot.disableAllButtons();
            }
        });
    }

    private static void celebrationDive(int score) throws InterruptedException {
        System.out.println("\n=== CELEBRATION DIVE! ===");

        // quick random underlight flashes
        for (int i = 0; i < 10; i++) {
            swiftBot.fillUnderlights(rgb(randomColour()));
            Thread.sleep(120);
        }
        swiftBot.disableUnderlights();

        int speed;
        if (score < 5) {
            speed = 40;
        } else if (score >= 10) {
            speed = 100;
        } else {
            speed = score * 10;
        }

        // simple "V" move
        swiftBot.move(speed, speed, 600);
        Thread.sleep(100);
        swiftBot.move(speed, 0, 350);
        Thread.sleep(100);
        swiftBot.move(speed, speed, 600);

        // final flashes
        for (int i = 0; i < 8; i++) {
            swiftBot.fillUnderlights(rgb(randomColour()));
            Thread.sleep(100);
        }
        swiftBot.disableUnderlights();
        swiftBot.disableButtonLights();

        System.out.println("=== CELEBRATION END ===");
    }
}
