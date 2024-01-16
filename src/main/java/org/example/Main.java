package org.example;

import org.myCompany.hector.Game;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        playGame(game);
        int score = game.score();
        System.out.println("Score  = " + score);
    }

    private static void playGame(Game game) {
        int randomKnockedPinsCount = 0;
        int randomKnockedPinsCountPrevious = 0;
        int roll = 1;
        boolean isRoll21 = false;
        while (roll < 21) {
            if (roll % 2 != 0) {
                randomKnockedPinsCount = getRandomKnockedPinsCount(11);
                game.roll(randomKnockedPinsCount);
                roll++;
                randomKnockedPinsCountPrevious = randomKnockedPinsCount;
            } else {
                randomKnockedPinsCount = getRandomKnockedPinsCount(11 - randomKnockedPinsCount);
                if (randomKnockedPinsCountPrevious == 10 && roll <20) {
                    roll++;
                    continue;
                }
                else if (randomKnockedPinsCountPrevious == 10 && roll == 20) {
                    randomKnockedPinsCount = getRandomKnockedPinsCount(11);
                    game.roll(randomKnockedPinsCount);
                    roll++;
                    isRoll21 = true;
                }
                else if ((randomKnockedPinsCountPrevious + randomKnockedPinsCount)  == 10 && roll == 20) {
                    game.roll(randomKnockedPinsCount);
                    roll++;
                    isRoll21 = true;
                } else {
                    game.roll(randomKnockedPinsCount);
                    roll++;
                }

            }

        }
        if (isRoll21) {
            randomKnockedPinsCount = getRandomKnockedPinsCount(11);
            game.roll(randomKnockedPinsCount);
        }
    }

    //Random number generation from 0 to maxLimit-1
    private static int getRandomKnockedPinsCount(int maxLimit) {
        Random rand = new Random();
        int randomKnockedPinsCountpinsCount = rand.nextInt(maxLimit);
        return randomKnockedPinsCountpinsCount;
    }
}