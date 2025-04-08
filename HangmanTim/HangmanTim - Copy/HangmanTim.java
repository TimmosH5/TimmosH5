import java.util.Scanner;
import java.util.Arrays;

public class HangmanTim {

    // Filling up the array with playable words
    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    // Preset info on how the gallows are shown in output
    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    // MAIN PSEUDO (FIRST IDEAS) (KLADBLOK)
    // print board every turn, and every turn the guess is not broken in the for loop, it prints the next phase, next [i]
    // word: placeholders _ need to be length of word _ 
    // guess = scan.nextline needs to store the last guess. if last guess = correct = no new gallows fase + placeholder removal in correct index 
    //      if guess incorrect -> new gallows + letter goes to new misses line 
    // if win then break FIRST 
    // if lose then break = lose is all the turns fulfilled BREAK SECOND 

    // functionalities
    // 1 random word needs to be chosen/not shown. make a random math function math.random * something = location array = word chosen 
    // word chosen needs to feed the rest of the functionalities (placeholders)

// DESIGN TIPS (KLADBLOK)
//     Convert a String to an array of characters.
// WHY - the word that is chosen randomly, needs to make an array of characters to play with
//     - this array has an index length to placeholder and place correct guessess 
// HOW String str define / char[] charArray = str.toCharArray();

//     Get the index of a character in a String.
// String str = "Hello";
// int index = str.indexOf('e');  // returns the index of the character 'e'
// System.out.println(index);  // Output: 1

//     Loop through characters in a String.
// Using an enhanced for loop (this requires converting the string to a char array first):
// //String str = "Hello";
// for (char c : str.toCharArray()) {
//     System.out.println(c);
// }

//     check if two arrays are equal to each other.
// if (wordToGuess.equals(playword)) = win? 
// To check if two arrays are equal in terms of their content (i.e., they have the same elements in the same order), you should use Arrays.equals() from the java.util.Arrays class.
//Arrays.equals(array1, array2) checks if both arrays have the same length and the same elements in the same order.
// if equal, winning = done? 

//64 words 63 index 

    public static void main(String[] args) {

        // MAIN GAME CODE

        // defining scanner en initializing variables outside a main game while loop
        // incorrectguesses is needed to change the level of the gallows for output (more on that later)
        // misses should be predefined, empty, to be filled with characters that are not correct
        // the boolean correctguess is made for logic, to decide what happens if true = path and if false = other path. 
        Scanner scanner = new Scanner(System.in);
        int incorrectGuesses = 0;
        String misses = " ";
        boolean correctGuess = true;

        // calling the random word picker function and storing it
        // the function is defined at the bottom of the code
        // essentially generates a random number, and links the number to a word from the array (words), which
        // is then linked to wordcurrentlyinplay.
        String wordCurrentlyInPlay;
        wordCurrentlyInPlay = wordPicker(words);

        // storing the above defined randomword (wordcurrentlyinplay) into a workable array
        char[] arrayWordInPlay = wordCurrentlyInPlay.toCharArray();

        // defining the answer in a direct variable to print at game's end for easy printing
        String wordAnswer = new String(wordCurrentlyInPlay);

        // defining a similar (mock) array length to the wordCurrentlyInPlay
        char[] placeHolderArray = new char [arrayWordInPlay.length];

        // converting the mock array to placeholders with _ _ _ 
        // these placeholders will function dynamically throughout the game
            for (int i = 0; i < placeHolderArray.length; i++) {
            placeHolderArray[i] = '_';
            }

        // print the gallows functionality once - starting board
        System.out.println(gallows[0]);

                // MAIN GAME LOOP IN WHILE FUNCTION
                // This runs until the incorrectguesses are equal 
                // to the gallows max index position
                // then it breaks to a 'you lost' path
    while (incorrectGuesses < gallows.length - 1) {

        // this helps debugging quickly 
        System.out.println("\n THIS IS TO HELP DEBUG: " + wordAnswer);

        // formatting the top part of terminal output with the "word: "" line
        System.out.print("\nWord: ");

        // printing the placeholder either with _ or correct guessInput
        // at the beginning its all converted to _ but below in the code it is organized
        // in such that this placeholderarray will be updated with correct guessed characters
            for (char c : placeHolderArray) {
                System.out.print(c + " ");
            }

            // this checks after every guess consistently if the placeholder updated word is 
            // filled with correct characters at that point. It checks if all the characters are
            // equal to the arrayWordInPlay, essentially a wincheck.
            // if it IS equal, it means the player won, and the wile loop/game will be ended 
            // with a system.exit command; and with a print of the winning word.
                if (Arrays.equals(arrayWordInPlay, placeHolderArray)) {
                    System.out.print("\n");
                    System.out.println("\nYou win! The word was: " + wordAnswer);
                    System.exit(0);
                }
        
        // formatting the middle part of the terminal output with the word: misses + count of misses
        System.out.println("\n");
        System.out.print("Misses: " + misses);

        // resetting the correctguess in the while loop - NOT in for loop - so it enables if!correctguess functionality below
        // resetting it here makes sure that before a new guess is made, the default is false, so it will not trigger
        // the wrong logic automatically 
        correctGuess = false; 

        // formatting the bottom part of terminal output with the word: guess, scanner etc.
        String guessInput = "";
        System.out.print("\n");
        System.out.print("\nGuess: ");
        guessInput = scanner.nextLine().toLowerCase();

        // handling if someone gives input that is already part of the misses variable 
        // or if someone enters a space. 
        while (misses.contains(guessInput) || guessInput.equals(" ")) {
            System.out.println("Invalid entry, try again");
            guessInput = scanner.nextLine().toLowerCase();
        }

        // handling if input is a character that is already correctly guessed
        String placeHolderString = new String(placeHolderArray);
        while (placeHolderString.contains(guessInput)) {
            System.out.println("This is already guessed correctly, try another");
            guessInput = scanner.nextLine().toLowerCase();
        }

        // for loop that iterates through the guess, the placeolder array, and updates the placeholder array if correct.
        // if the guess is incorrect it does not update and procedes to the code below (IN main while loop)
            for (int i = 0; i < arrayWordInPlay.length; i++){

                // checks the above scanner input (guessInput - L193) with a functionality that only checks the first 
                // character of the input since the game is only one letter per guess input based, it works fine. 
                // it checks this letter for every position in arrayWordInPlay and if its equal, and thereby guessed correctly
                // it will update the placeholderarray at the EXACT position the right character should be (found with arraywordinplay)
                if (guessInput.charAt(0) == arrayWordInPlay[i]) {
                placeHolderArray[i] = arrayWordInPlay[i];
                printGallows(gallows, incorrectGuesses);
                correctGuess = true; 
            } 
        }

        // This is happening IN the while loop OUTSIDE the for loop, after the guess was checked for correctness. 
        // IF incorrect, its confirmed again and it updates the misses variable with 
        // the incorrect guess input character. It also
        // updates the gallows level by incorrectguesses variable + 1, which is used for level input of printGallows.
        // in the printGallows function the arguments are (gallows, stage), but incorrectguesses represents the 'stage' here.
        if (!correctGuess) {
            misses = misses + guessInput.charAt(0);
            incorrectGuesses = incorrectGuesses + 1;
            printGallows(gallows, incorrectGuesses);
        
        }   
    } 4

  // assuming that the player did not win, this last part of the code will be triggered.
  // this is ONLY after the while loop and ONLY if the while loop is not broken prematurely due to a break; by win.
  // ONLY then this code runs, which informs the player that the game was lost with the answer they were looking for.   
    System.out.println("You lost! The word was: " + wordAnswer);

    scanner.close();
}

    // defining a function that uses the gallows as input for the print info grounding, and the stage.
    // in the main game loop the argument stage is replaced with incorrectguesses, which informs the function
    // which indexnumber of the gallows (level) it should print. Because with correct guess, the level does not change. 
    // only with incorrectguess increments, the gallows change. 
    public static void printGallows(String[] gallows, int Stage) {
            System.out.println(gallows[Stage]);
      }

    // defining a function that generates a random number till 63, which is then connected to an index position
    // of the playable words array. Now, a word is chosen and stored in wordPicker, which is equalised to further variables
    // for further input of the game logic. 
    // 64 words 63 index
    public static String wordPicker(String[] words) {
        int randomNumber = (int) (Math.random() * 64);
        String wordInPlay = words[randomNumber];
        return wordInPlay;
    }

}

