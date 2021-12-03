import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import themes.Words;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Words words = new Words();
        ArrayList<Character> usedLetters = new ArrayList<>();

        boolean wannaContinue = true;
        int score = 0;

        do {
            int chances = 5;

            String[] round = words.getWord();
            String theme = round[0];
            char[] answer = round[1].toUpperCase().toCharArray();
            char[] word = blankWord(answer);

            clearScreen();
            cyan("Welcome to the Hangman Game! Hope you have fun! :D\n");

            while (chances > 0) {
                String consoleData;
                char letter = ' ';

                do {
                    System.out.println("Theme: " + theme + "\n");
                    System.out.println(wordToString(word) + "\n");
                    yellow("Score: " + score);
                    cyan("Chances: " + chances);
                    cyan("Used Letters: " + usedLetters.toString() + "\n");
                    System.out.print("Please enter a letter: ");
                    consoleData = sc.next();

                    if (consoleData.length() > 1) {
                        clearScreen();
                        red("Please, enter only ONE letter at time! >:(\n");
                    } else {
                        letter = Character.toUpperCase(consoleData.charAt(0));

                        if (usedLetters.contains(letter)) {
                            clearScreen();
                            yellow("You already used this letter! Try another one...\n");
                        }
                    }
                } while (consoleData.length() > 1 || usedLetters.contains(letter));

                usedLetters.add(letter);

                if (contains(letter, answer)) {
                    clearScreen();
                    green("Nice! You got this!\n");
                    updateWord(answer, word, letter);
                } else {
                    clearScreen();
                    red("Oh no! You missed :(\n");
                    chances--;
                }

                if (wordIsComplete(word)) {
                    score++;
                    green("YOU WON! :D\n");
                    System.out.println(wordToString(word) + "\n");
                    yellow("Score: " + score);
                    cyan("Chances: " + chances);
                    cyan("Used Letters: " + usedLetters.toString() + "\n");
                    chances = 0;
                    yellow("Do you want to continue? [y/n]");
                    wannaContinue = Character.toUpperCase(sc.next().charAt(0)) == 'Y';
                    if (wannaContinue)
                        resetGame(usedLetters);
                } else if (chances == 0) {
                    red("GAME OVER!\n");
                    System.out.println("The answer was: " + wordToString(answer) + "\n");
                    yellow("Score: " + score);
                    cyan("Chances: " + chances);
                    cyan("Used Letters: " + usedLetters.toString() + "\n");
                    yellow("Do you want to continue? [y/n]");
                    wannaContinue = Character.toUpperCase(sc.next().charAt(0)) == 'Y';
                    if (wannaContinue)
                        resetGame(usedLetters);
                }
            }
        } while (wannaContinue);

        credits();

        sc.close();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        menu();
    }

    public static char[] blankWord(char[] word) {
        char[] blank = new char[word.length];
        for (int i = 0; i < word.length; i++) {
            if (word[i] != ' ') {
                blank[i] = '_';
            } else {
                blank[i] = ' ';
            }
        }
        return blank;
    }

    public static String wordToString(char[] word) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < word.length; i++) {
            s.append(word[i] + " ");
        }
        s.deleteCharAt(s.length() - 1);
        return s.toString();
    }

    public static boolean contains(char letter, char[] answer) {
        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == letter) {
                return true;
            }
        }
        return false;
    }

    public static char[] updateWord(char[] answer, char[] word, char letter) {
        for (int i = 0; i < word.length; i++) {
            if (answer[i] == letter) {
                word[i] = letter;
            }
        }
        return word;
    }

    public static boolean wordIsComplete(char[] word) {
        if (!Arrays.toString(word).contains("_")) {
            return true;
        }
        return false;
    }

    public static void resetGame(ArrayList<Character> usedLetters) {
        usedLetters.clear();
    }

    // Screens

    public static void menu() {
        System.out.println("\u001B[35m");
        System.out.println("▒█░▒█ █▀▀█ █▀▀▄ █▀▀▀ █▀▄▀█ █▀▀█ █▀▀▄");
        System.out.println("▒█▀▀█ █▄▄█ █░░█ █░▀█ █░▀░█ █▄▄█ █░░█ ");
        System.out.println("▒█░▒█ ▀░░▀ ▀░░▀ ▀▀▀▀ ▀░░░▀ ▀░░▀ ▀░░▀");
        System.out.println("\u001B[0m");
    }

    public static void credits() {
        yellow("\nThanks for playing! Developed by Daniel Antunes | @dannesx 🚀");
    }

    public static void red(String s) {
        System.out.println("\u001B[31m" + s + "\u001B[0m");
    }

    public static void green(String s) {
        System.out.println("\u001B[32m" + s + "\u001B[0m");
    }

    public static void yellow(String s) {
        System.out.println("\u001B[33m" + s + "\u001B[0m");
    }

    public static void cyan(String s) {
        System.out.println("\u001B[36m" + s + "\u001B[0m");
    }
}