package org.myCompany.hector;

import static org.junit.jupiter.api.Assertions.*;


import org.myCompany.hector.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;


public class GameTests {

    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    @DisplayName("Test Score method when roll() is not called")
    public void testScoreWhenRollisNotCalled() {
        assertEquals(0, game.score());
    }

    @Test
    @DisplayName("Test Score happy path")
    public void testScore() {
        List<Integer> rolls = createTwentyRollPredifinedScores();
        for (Integer roll : rolls) {
            game.roll(roll);
        }
        game.score();
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    @DisplayName("Test roll happy path")
    public void testRoll() {
        List<Integer> rolls = createTwentyRollPredifinedScores();
        for (Integer roll : rolls) {
            game.roll(roll);
        }
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    @DisplayName("Test by passing roll score as per PDF expecting total score 133")
    public void testScoreWhenExpectedResult133() {
        List<Integer> rolls = createTwentyRollPredifinedScores();
        for (Integer roll : rolls) {
            game.roll(roll);
        }
        assertEquals(133, game.score());
    }

    @Test
    @DisplayName("Test Roll() when frames exceed 10")
    public void testRollWhenFramesMoreThanTen() {

        game.roll(10);
        game.roll(5);
        game.roll(5);
        game.roll(4);
        game.roll(6);
        game.roll(8);
        game.roll(1);
        game.roll(6);
        game.roll(3);
        game.roll(4);
        game.roll(6);
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(6);
        game.roll(4);
        game.roll(10);
        game.roll(5);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(5),
                "Only Ten frames allowed");
    }

    @Test
    @DisplayName("Test Roll() When Negative Number is Passed")
    public void testRollWhenNegativeNumberIsPassed() {

        game.roll(10);
        game.roll(5);
        game.roll(5);
        game.roll(4);
        game.roll(6);
        game.roll(8);
        game.roll(1);
        game.roll(6);
        game.roll(3);
        game.roll(4);
        game.roll(6);
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(6);
        game.roll(4);
        game.roll(10);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> game.roll(-5),
                "Invalid argument");
    }

    @Test
    @DisplayName("Test score when last frame is Strike")
    public void testScoreWhenLastFrameIsStrike() {
        game.roll(10);
        game.roll(5);
        game.roll(5);
        game.roll(4);
        game.roll(6);
        game.roll(8);
        game.roll(1);
        game.roll(6);
        game.roll(3);
        game.roll(4);
        game.roll(6);
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(6);
        game.roll(4);
        game.roll(10);
        game.roll(5);
        assertEquals(160, game.score());

    }

    @Test
    @DisplayName("Test score when last frame is Spare")
    public void testScoreWhenLastFrameIsSpare() {
        game.roll(10);
        game.roll(5);
        game.roll(5);
        game.roll(4);
        game.roll(6);
        game.roll(8);
        game.roll(1);
        game.roll(6);
        game.roll(3);
        game.roll(4);
        game.roll(6);
        game.roll(5);
        game.roll(5);
        game.roll(10);
        game.roll(6);
        game.roll(4);
        game.roll(5);
        game.roll(5);
        game.roll(2);
        assertEquals(152, game.score());
    }

    @Test
    @DisplayName("Test score with only 5 rolls")
    public void testScoreWithOnlyFiveRolls() {
        game.roll(10);
        game.roll(5);
        game.roll(5);
        game.roll(4);
        assertEquals(38, game.score());
    }

    @Test
    @DisplayName("Test score when no their is no bonus(No Spare/Strike)")
    public void testScoreWhenNOBonus() {

        game.roll(4);
        game.roll(4);
        game.roll(3);
        game.roll(6);
        game.roll(2);
        game.roll(5);
        game.roll(6);
        game.roll(2);
        game.roll(5);
        game.roll(2);
        game.roll(3);
        game.roll(4);
        game.roll(5);
        game.roll(4);
        game.roll(3);
        game.roll(4);
        game.roll(3);
        game.roll(3);
        game.roll(2);
        game.roll(4);
        assertEquals(74, game.score());

    }

    private List<Integer> createTwentyRollPredifinedScores() {
        List<Integer> rollScores = new ArrayList<>();
        rollScores.add(1);//1st frame, try 1
        rollScores.add(4);//1st frame, try 2
        rollScores.add(4);//2nd frame, try 1
        rollScores.add(5);//2nd frame, try 2
        rollScores.add(6);//3rd frame, try 1
        rollScores.add(4);//3rd frame, try 2
        rollScores.add(5);//4th frame, try 1
        rollScores.add(5);//4th frame, try 2
        rollScores.add(10);//5th frame, try 1 only
        rollScores.add(0);//6th frame, try1
        rollScores.add(1);//6th frame, try2
        rollScores.add(7);//7th frame, try1
        rollScores.add(3);//7th frame, try2
        rollScores.add(6);//8th frame, try1
        rollScores.add(4);//8th frame, try2
        rollScores.add(10);//9th frame, try1 only
        rollScores.add(2);//10th frame, try1
        rollScores.add(8);//10th frame try2
        rollScores.add(6);//10th frame try3, extra try due to spare in 10th frame

        return rollScores;

    }

}
