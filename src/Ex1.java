/**
 * My name is: Rei Shaul
 *My ID is: 325390086
 */
import java.util.Scanner;

/**
 * Introduction to Computer Science, Ariel University, Ex1 (manual Example + a Template for your solution)
 * See: https://docs.google.com/document/d/1C1BZmi_Qv6oRrL4T5oN9N2bBMFOHPzSI/edit?usp=sharing&ouid=113711744349547563645&rtpof=true&sd=true
 * <p>
 * Ex1 Bulls & Cows - Automatic solution.
 * **** Add a general readme text here ****
 * Add your explanation here:
 * this program solve the "Bulls & Cows" game automaticlly.
 * the program need to guess the correct number with few guesses as possible.
 * and return the correct number, the time it takes to get the correct number, and the number of guesses
 * <p>
 * <p>
 * **** General Solution (algorithm) ****
 *
 * Add your explanation here:
 * The algorithm of the program goes like this-
 * The automatic methode whose main purpose is to find the correct number and return it at the end by using the feedback
 * from the getStatus methode and the return of the defined parameter that will count the number of guesses it took to reach
 * the correct number. In the automatic function, we will run a loop that will run as long as the game is running and we have
 * not reached the correct guess. there we activate functions that will bring us closer to the correct number from guess to guess
 * In the loop, the removeOptions methode is called, which calls the checkIfTrue function, within which the InfoFromArrays
 * methode is called, whose job it is to check if any guess it received contains stamps or vulnerabilities and it returns
 * an array of ints of size two cells, where cell zero contains the number of bulls  and cell one contains the number of cows
 * and This array return to the checkIfTrue methode which checks if the original number of bulls is equal to the number of
 * bulls in the array and if the original number of cows is equal to the number of cows in the array if so then it will return
 * true which says that it is a good guess and if not it will return false which says that the guess is not good and that we
 * will not use it again. The answer will be return to the methode that called it, the removeOptions function which checks
 * if a false was received from checkIfTrue and if a false was received it will put "false" in the cell which will mark the
 * cell as impossible to guess again. There is also another methode that is called at each run of the loop whose job is to
 * give possible guesses to a certain variable call(g), changing its value to a more informed guess at each repetition and
 * so the loop ran again and again until the game ends when we reach the correct code and win.
 *
 * <p>
 * <p>
 * **** Results ****
 * Make sure to state the average required guesses
 * for 2,3,4,5,6 digit code:
 * <p>
 * Average required guesses 2: 7.12
 * Average required guesses 3: 8.22
 * Average required guesses 4: 8.97
 * Average required guesses 5: 9.07
 * Average required guesses 6: 9.58
 */
public class Ex1 {
    public static final String Title = "Ex1 demo: manual Bulls & Cows game";

    public static void main(String[] args) {
        BP_Server game = new BP_Server();   // Starting the "game-server"
        long myID = 325390086;             // Your ID should be written here
        int numOfDigits = 6;                // Number of digits [2,6]
        game.startGame(myID, numOfDigits);  // Starting a game
        System.out.println(Title + " with code of " + numOfDigits + " digits");
        // manualEx1Game(game);
        autoEx1Game(game); // you should implement this function )and any additional required functions).
    }

    public static void manualEx1Game(BP_Server game) {
        Scanner sc = new Scanner(System.in);
        int ind = 1;      // Index of the guess
        int numOfDigits = game.getNumOfDigits();
        double max = Math.pow(10, numOfDigits);
        while (game.isRunning()) {           // While the game is running (the code has not been found yet
            System.out.println(ind + ") enter a guess: ");
            int g = sc.nextInt();
            if (g >= 0 && g < max) {
                int[] guess = toArray(g, numOfDigits); // int to digit array
                int[] res = game.play(guess); // Playing a round and getting the B,C
                if (game.isRunning()) {     // While the game is running
                    System.out.println(ind + ") B: " + res[0] + ",  C: " + res[1]); // Prints the Bulls [0], and the Cows [1]
                    ind += 1;               // Increasing the index
                }
            } else {
                System.out.println("ERR: wrong input, try again");
            }
        }
        System.out.println(game.getStatus());
    }


    /**
     * Simple parsing function that gets an int and returns an array of digits
     *
     * @param a    - a natural number (as a guess)
     * @param size - number of digits (to handle the 00 case).
     * @return an array of digits
     */
    private static int[] toArray(int a, int size) {
        int[] c = new int[size];
        for (int j = 0; j < c.length; j += 1) {
            c[j] = a % 10;
            a = a / 10;
        }
        return c;
    }
////////////////////////////////////////////////////

    /**
     * This function solves the Bulls & Cows game automatically.
     * in the methode autoEx1Game before the game start I am start with define numOfdigits, max and array. then, I insert
     * through the array true to mark that there are all options to be the correct guess.
     * I also defined parameter count for counting the number of guesses.
     * when the loop(while) strat I defined the parameter g, that will get a optional guess by calling the methode GetGuess
     * which its purpose is to return option that can be a possible guess. Then I defined few  more parameters and invite
     * the methode removeOptions which its purpose is s to drop all the guesses that can no longer be possible. in the methode
     * removeOptions I invite the methode CheckIfTrue which its purpose is to return true if "i" is a possible guess according
     * to "Guess" and the bulls and
     * cows we get. in the methode CheckIfTrue, I invite the methode InfoFromArrays which its purpose is to compare each
     * element and index in guess array to each element in the correct number array respectively. more over, the methode
     * insure that there are no repeated comparisons to make sure that count will return the exactly value of the bull and
     * the cows and not mix them. after one round, the loop is run more times each time with better guess and get colser to
     * the correct number untill the number of the bulls is equals to the numOfDigit and the loop stop and print the game status.
     */
    public static int autoEx1Game(BP_Server game) {
        int numOfDigits = game.getNumOfDigits();//Initialize numOfDigits with the code size we defined above
        int max = (int) Math.pow(10, numOfDigits);//Initialize Max to be equal to Num to the power of ten - which is all the options
        boolean[] array = new boolean[max];//Define a boolean array of max size

        for (int i = 0; i < array.length; i++) {//A loop that will go through all the members of the array and insert a true into them
            array[i] = true;
        }

        int count = 0;//parameter which count the number of guesses

        while (game.isRunning()) {//loop that run when the game is running

            int g = getGuess(array);//g get a optional guess from getGuess
            int[] guess = toArray(g, numOfDigits);//insert g to array which its size is numOfDigits
            int[] res = game.play(guess);//get a number from the game and insert it to an array
            count = count+1;// add 1 to count hence we guess some number
            int b = res[0];//parameter that contains the bulls
            int c = res[1];//parameter that contains the cows
            array[g]=false;//We will define the cell we guessed as false after we have used it so as not to repeat it


            removeOptions(g, b, c, array, numOfDigits);//The function is called and these variables are entered into it
        }
            System.out.println(game.getStatus());
        return count;//return the number of guesses


    }

    /**
     * The methode's purpose is to drop all the guesses that can no longer be possible
     * @param guess-the guess of the program
     * @param bulls- A variable which equal to the number of bulls
     * @param cows- A variable which equal to the number of cows
     * @param arr- a boolean that marks the cells that could be a possible guess
     * @param numOfDigits- a variable which equal to the number of the digits in this round
     */
    public static void removeOptions(int guess, int bulls, int cows, boolean[]arr, int numOfDigits) {
        for (int i = 0; i < arr.length; i++) {//loop that run from 0 to the length of array-1
            if (arr[i] && !CheckIfTrue(guess, bulls, cows, i, numOfDigits)) {//check if cell i and the CheckIfTrue return false

                arr[i] = false;// insert false to the cell to mark that the cell isn't possible to be a guess

            }

        }
    }

    /**
     * the methode return true if "i" is a possible guess according to "Guess" and the bulls and cows we get.
     * @param guess- the guess of the program
     * @param bull- a variable which equal to the number of bulls
     * @param cows- a variable which equal to the number of cows
     * @param i- the correct guess/number
     * @param numOfDigits-a variable which equal to the number of the digits in this round
     * @return true or false it depends if the term is correct
     */
     public static boolean CheckIfTrue(int guess, int bull, int cows, int i, int numOfDigits) {//a boolean methode which
         // return true if the guess have bulls and cows and false if the guess isn't have

       int [] count= InfoFromArrays(toArray(guess, numOfDigits), toArray(i,numOfDigits), numOfDigits);//the value that we get
         // from InfoFromArrays (the number of bulls and cows) go into the array count

       if (count[0]==bull && count[1]==cows){// check if the bulls and cows we get from count is equals to the original

           return true;//return true which mean the guess is good
       }

       return false;//return false which mean the guess is not have good elements
    }

    /**
     *The methode's purpose is to return option that can be a possible guess
     * @param arr- this boolean parameter contain true or false and false is mean that we already use this guess
     * @return guess that we already use/or -1 (Shouldn't happen)
     */
    public static int getGuess(boolean[] arr){//boolean methode
        for (int i = 0; i < arr.length; i++) {//loop which run from 0 to the length of the array-1
            if (arr[i]) {//term that actually always apply
                return i;//return the index of the cell that will use for a guess
            }
        }
        return -1;//shouldn't happen hence we supposed to find the correct code before we over all the options

    }

    /**
     *this methode compare each element and index in guess array to each element in the correct number array respectively.
     * more over, the methode insure that there are no repeated comparisons to make sure that count will return the
     * exactly value of the bull and the cows and not mix them.
     * @param guess-array that contain the guess we check
     * @param correct-array that contain the correct code/numbers
     * @param numOfDigits-parameter that equals to the number of digits
     * @return an array (count) which contain how much bulls and cows there is in the guess
     */

    public static int[] InfoFromArrays(int[] guess, int[] correct, int numOfDigits){//define two arrays- one for the guess
        // and second for the correct number
        boolean[] gussCheck = new boolean[numOfDigits];//boolean array which marks cells to avoid repetition of elements in "guess"
        boolean[] correctCheck = new boolean[numOfDigits];//boolean array which marks cells to avoid repetition of elements in "correct"
        int[] count = new int[2];//array which contain the number of cows and bulls for the guess
        for (int i = 0; i <numOfDigits ; i++) {
            if (guess[i]==correct[i]  && !gussCheck[i] && !correctCheck[i]) {//check if cell i in guess equals to cell i in correct including index
                // and also if there is false in the cells of the boolean arrays for this index
                gussCheck[i]=true;//insert true to avoid repetition and to not check again this cell
                correctCheck[i]=true;

                count[0]++;//adding 1 to the cell in index 0 which symbolizes the bulls
            }

        }
        for (int i = 0; i <numOfDigits; i++) {
            for (int j = 0; j <numOfDigits ; j++) {//in this comparison we just need to find equals with out index,
                // for this reason we need to compare each element to to other also is its in a differnt index

                 if(guess[i]==correct[j] && !gussCheck[i] && !correctCheck[j]) {
                    gussCheck[i]=true;
                    correctCheck[j]=true;

                    count[1]++;//adding 1 to cell in index 1 which symbolizes the cows
                }

            }
        }
        return count;//return the count with the bulls and cows we get
    }

    }





