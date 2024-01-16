package org.myCompany.hector;

public class Game {
    private int[] rawScores
            = new int[21];
    private int tryCount = 0;

    public void roll(int pinsKnocked) {
            if (tryCount > 20) {
                throw new IllegalArgumentException("Only Ten frames allowed");
            }
            if (pinsKnocked < 0 || pinsKnocked >10) {
                throw new IllegalArgumentException("Invalid argument");
            }
            rawScores[tryCount] = pinsKnocked;
            if (tryCount % 2 != 0) {
                if (rawScores[tryCount-1] + pinsKnocked > 10) {
                    throw new IllegalArgumentException("A frame cannot have more than 10 knocked down pins");
                }
            }
            if (tryCount % 2 == 0 && pinsKnocked ==10) {
                rawScores[tryCount + 1] = 0;
                tryCount = tryCount + 2;
            } else {
                tryCount++;
            }
    }

    public int score() {
        int score = 0;
        int bonus = 0;
        for (int i = 0; i < rawScores.length-1; i++) {
            if (i % 2 == 0) {
               if ((rawScores[i] == 10 ) ) {
                   if(i == 18){
                       bonus = rawScores[i+2] ;
                   }else{
                       bonus = rawScores[i+2] + rawScores[i+3];
                   }
                   score += rawScores[i] + bonus ;
               } else {
                   score += rawScores[i];
               }
            }
            else if (i % 2 != 0) {
                if ((rawScores[i] + rawScores[i-1] == 10) && rawScores[i] !=0) {
                    bonus = rawScores[i+1];
                    score += rawScores[i] + bonus;
                } else {
                    score += rawScores[i];
                }
            }
            bonus = 0;
        }
        return score;
    }
}

