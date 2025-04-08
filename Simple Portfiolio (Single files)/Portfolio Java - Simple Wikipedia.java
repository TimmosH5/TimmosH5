import java.util.Scanner;

public class Javapedia {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n**********Javapedia**********");
        System.out.println("How many historical figures will you register?");

        int amountHistorians = scan.nextInt();
        
        String[][] dataBase = new String [amountHistorians][3];
        scan.nextLine(); 

        for (int i = 0; i < amountHistorians; i++) {
            System.out.println("\t Figure " + (i + 1));
            // for (int j = 0; j < dataBase[i].length; j++) {
                System.out.print("Name: ");
                dataBase[i][0] = scan.nextLine();

                System.out.print("Date of birth: ");
                dataBase[i][1] = scan.nextLine();
                
                System.out.print("Occupation: ");
                dataBase[i][2] = scan.nextLine();
                // break;

        }
         
        System.out.println("\nThese are the values you stored:"); 
        print2DArray(dataBase);

        System.out.print("\nWho do you want information on? ");  
        String searchQuery = scan.nextLine();

        for (int i = 0; i < dataBase.length; i++) {
            if (searchQuery.equals(dataBase[i][0])) {
                System.out.println("\t - Name: " + dataBase[i][0]);
                System.out.println("\t - Date of birth: " + dataBase[i][1]);
                System.out.println("\t - Occupation: " + dataBase[i][2]);
            }
            }

    scan.close();
            
        }

    public static void print2DArray(String database [][]) {
        for (int i = 0; i <database.length; i++) {
                System.out.println("\nFigure " + (i + 1));
                System.out.println("\t - Name: " + database[i][0]);
                System.out.println("\t - Date of birth: " + database[i][1]);
                System.out.println("\t - Occupation: " + database[i][2]);
            }

        } 
    }
