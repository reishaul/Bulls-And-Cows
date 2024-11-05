/**
 * my name: Rei Shaul
 * my id: 325390086
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Ex1Test {

    /**
     * this test cheacking the CheckIfTrue function by entering some guess, bulls, cows, i(the correct number) and number
     * of digits and check if the correct answear was received.
     */

    @Test
    public void testCheckIfTrue() {

        int guess = 0051;
        int bulls = 3;
        int cows = 0;
        int i = 0041;
        int numOfDigits = 4;

        boolean b = Ex1.CheckIfTrue(guess, bulls, cows, i, numOfDigits);
        assertFalse(b);

    }

    /**
     * this test checking the Getguess function by entering a boolean array and checking if the expected answear was received
     */

    @Test
    public void testGetguess() {

        boolean[] arr = new boolean[]{
                false, true, false, false
        };
        assertEquals
                (1, Ex1.getGuess(arr));
        System.out.println(arr[0] + " " + "correct the answaer should be 1");
    }

    /**
     * This test checks the infoFromArrays function by entering two four-digit numbers and checking
     * the number of expected bulls and cows.
     */

    @Test

    public void testInfoFromArrays() {
        int[] g = new int[]{
                0, 0, 0, 9
        };
        int[] r = new int[]{
                1, 0, 0, 0

        };
        int numOfdigits = 4;

        int[] s = Ex1.InfoFromArrays(g, r, numOfdigits);

        assertTrue(s[0] == 2 && s[1] == 1, " the answaer should be 2,1");

        System.out.println(s[0] + "," + s[1]);
    }

    /**
     *This test calculate the average of 100 rounds for each number of digits from 2 to 6 and also calculate
     * the average of all of them together
     */


    @Test
    public void testGuess() {
        int[] sum = new int[7];
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 325390086;
        for (int j = 2; j < 7; j++) {  //j [2,6]
            // Your ID should be written here
            int numOfDigits = j;// Number of digits [2,6]
            int sum1 = 0;
            for (int i = 0; i < 100; i++) {
                game.startGame(myID, numOfDigits);
                int curr = Ex1.autoEx1Game(game);
                sum[j] = sum[j] + curr;
            }
        }
        double all = 0;
        for (int j = 2; j < 7; j++) {
            double ave = (sum[j] / 100.0);
            all += sum[j];
            System.out.println(j + ") " + sum[j] + " ave of all  " + ave);
        }
        double ave = all/500.0;
        System.out.println("ave all = "+ave);
    }


}

