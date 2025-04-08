import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int cardInput;

        System.out.println("Welcome to Java Casino - BlackJack");
        System.out.println("Ready? Press anything to start");
        scanner.nextLine();

        System.out.println("\nYou get card 1: ");
        cardInput = randomCard();
        System.out.println(cardVisual(cardInput));
        int cardValue1 = Math.min(10, cardInput);
        scanner.nextLine();

        System.out.println("\nYou get card 2: ");
        cardInput = randomCard();
        System.out.println(cardVisual(cardInput));
        int cardValue2 = Math.min(10, cardInput);
        scanner.nextLine();

        System.out.println("Your handvalue is: " + (cardValue1 + cardValue2));
        scanner.nextLine();

        System.out.println("The dealer has one card shown: ");
        cardInput = randomCard();
        System.out.println(cardVisual(cardInput));
        int dealerCardValue1 = Math.min(10, cardInput);
        scanner.nextLine();

        System.out.println("The dealer has one facing down: ");
        scanner.nextLine();
        System.out.println( "   _____\n"+
                            "  |     |\n"+ 
                            "  |  J  |\n"+
                            "  | JJJ |\n"+
                            "  |  J  |\n"+
                            "  |_____|\n");

        System.out.println("Now it's your turn");

        // int hitsValue; 
        int handValue = cardValue1 + cardValue2;

        System.out.println("Press 1 for hit or 2 for stay");
        int choicePlay = scanner.nextInt();

        while (choicePlay != 1 && choicePlay != 2) {
            System.out.println("invalid choice try again"); 
            choicePlay = scanner.nextInt();
        }

        while (handValue < 21 && choicePlay == 1) {

            if (choicePlay == 1) {
            System.out.println("You wanted to hit another, here it is: ");
            cardInput = randomCard();
            System.out.println(cardVisual(cardInput));
            handValue = handValue + Math.min(10, cardInput);
            System.out.println("This is your new value: " + handValue);
                if (handValue > 21) {
                    System.out.println("Lost. busted!");
                    System.exit(0);
                }
            System.out.println("Another round or stay?");
            choicePlay = scanner.nextInt();
            }
        }

        if (choicePlay == 2) {
            System.out.println("Youve chosen to stay. The dealer will reveal the hidden card now"); 
            scanner.nextLine();

        }

        System.out.println("The dealer flips: ");
        cardInput = randomCard();
        System.out.println(cardVisual(cardInput));
        int dealerCardValue2 = Math.min(10, cardInput);
        int dealerHand = dealerCardValue1 + dealerCardValue2;

        System.out.println("The dealers hand is now: " + dealerHand);
        scanner.nextLine();

        while (dealerHand <17) {
            System.out.println("The dealer draws:");
            cardInput = randomCard();
            System.out.println(cardVisual(cardInput));
            dealerHand = dealerHand + Math.min(10, cardInput);
            System.out.println("The dealers hand is now: " + dealerHand);
            scanner.nextLine();
        }

        if (dealerHand > 21) {
            System.out.println("The dealer busted! You won!");
            System.exit(0);
        }

        if (dealerHand > handValue) {
            System.out.println("The dealer won! Better luck next time.");
        } else if (dealerHand == handValue) {
            System.out.println("Its a draw - good game!");
        } else {
            System.out.println("You won! Nice. Till next time!");
        }




        // while (choicePlay !== 2 || choicePlay !== 1) {
        //     System.out.println("invalid entry, try again");
        //     choicePlay = scanner.nextInt(); 
        // 

        // make while loop 

        // if (choicePlay == 1) {
        //     System.out.println("You wanted to hit another, here it is: ");
        //     cardInput = randomCard();
        //     System.out.println(cardVisual(cardInput));
        //     int cardValue3 = Math.min(10, cardInput);

        // if ((cardValue1 + cardValue2 + cardValue3) > 21) {
        //     System.out.println("You busted! Lost.");
        // }

        scanner.close();
    }


    public static int randomCard() {
        int randomInt = (int) (Math.random() * 13) + 1;
        return randomInt;
    }

    public static String cardVisual (int cardInput) {
        String emptyString = "fuck this";
        switch (cardInput) {
            case 1: return
            "   _____\n"+
            "  |A _  |\n"+ 
            "  | ( ) |\n"+
            "  |(_'_)|\n"+
            "  |  |  |\n"+
            "  |____V|\n";
            // handValue ++; 
            case 2: return
            "   _____\n"+              
            "  |2    |\n"+ 
            "  |  o  |\n"+
            "  |     |\n"+
            "  |  o  |\n"+
            "  |____Z|\n"; 
            // handValue = handValue +2;
            case 3: return
          "   _____\n" +
          "  |3    |\n"+
          "  | o o |\n"+
          "  |     |\n"+
          "  |  o  |\n"+
          "  |____E|\n"; 
        //   handValue = handValue +3;
          case 4: return
           "   _____\n" +
           "  |4    |\n"+
           "  | o o |\n"+
           "  |     |\n"+
           "  | o o |\n"+
           "  |____h|\n"; 
        //    handValue = handValue +4;
           case 5: return
            "   _____ \n" +
            "  |5    |\n" +
            "  | o o |\n" +
            "  |  o  |\n" +
            "  | o o |\n" +
            "  |____S|\n"; 
            // handValue = handValue +5;
            case 6: return
            "   _____ \n" +
            "  |6    |\n" +
            "  | o o |\n" +
            "  | o o |\n" +
            "  | o o |\n" +
            "  |____6|\n"; 
            // handValue = handValue +6;
            case 7: return
            "   _____ \n" +
            "  |7    |\n" +
            "  | o o |\n" +
            "  |o o o|\n" +
            "  | o o |\n" +
            "  |____7|\n"; 
            // handValue = handValue +7;
            case 8: return
            "   _____ \n" +
            "  |8    |\n" +
            "  |o o o|\n" +
            "  | o o |\n" +
            "  |o o o|\n" +
            "  |____8|\n"; 
            // handValue = handValue +8;
            case 9: return
            "   _____ \n" +
            "  |9    |\n" +
            "  |o o o|\n" +
            "  |o o o|\n" +
            "  |o o o|\n" +
            "  |____9|\n"; 
            // handValue = handValue +9;
            case 10: return
            "   _____ \n" +
            "  |10  o|\n" +
            "  |o o o|\n" +
            "  |o o o|\n" +
            "  |o o o|\n" +
            "  |___10|\n"; 
            // handValue = handValue +10;
            case 11: return
            "   _____\n" +
            "  |J  ww|\n"+ 
            "  | o {)|\n"+ 
            "  |o o% |\n"+ 
            "  | | % |\n"+ 
            "  |__%%[|\n"; 
            // handValue = handValue +10;
            case 12: return
            "   _____\n" +
            "  |Q  ww|\n"+ 
            "  | o {(|\n"+ 
            "  |o o%%|\n"+ 
            "  | |%%%|\n"+ 
            "  |_%%%O|\n"; 
            // handValue = handValue +10;
            case 13: return
            "   _____\n" +
            "  |K  WW|\n"+ 
            "  | o {)|\n"+ 
            "  |o o%%|\n"+ 
            "  | |%%%|\n"+ 
            "  |_%%%>|\n"; 
            // handValue = handValue +10;
        
            default: return emptyString;
        } 
    } 
}