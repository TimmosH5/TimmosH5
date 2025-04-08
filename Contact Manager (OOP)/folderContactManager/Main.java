package folderContactManager;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        int choice = 0;

    // here we are creating a new contactmanager object with passing in the contactData function
    // so that the initialized array of the new ContactManager gets filled with elements
    ContactManager contactManager = new ContactManager(contactData());
    displayContacts(contactManager);

    // displaying the contacts by calling the function and passing in the object 
    // from which the info gets displayed

    System.out.println("if you want to edit them enter continue");
    String request = scan.nextLine();

    while (request.equals("continue")) {
        System.out.println("\nChoose an index from 0 to 7");
        choice = scan.nextInt();
        scan.nextLine(); // throwaway line 

        contactManager.setContact(editedContact(), choice);

        System.out.println("\n\nUPDATED CONTACTS\n\n");
        displayContacts(contactManager);
        System.out.print("Enter 'continue' to make more changes: ");
        request = scan.nextLine();
}

        // System.out.println("if you want more the enter continue");
        // request = scan.nextLine();

    scan.close();

    }
    // METHODS

    public static Contact[] contactData() {
        return new Contact[] {
                new Contact("John Doe", "555-123-4567", "1985-01-01"),
                new Contact("Jane Smith", "555-987-6543", "1990-02-14"),
                new Contact("Alice Johnson", "555-345-6789", "1975-03-30"),
                new Contact("Bob Brown", "555-567-8910", "2000-12-25"),
                new Contact("Charlie Davis", "555-111-2222", "1983-07-04"),
                new Contact("Diana White", "555-333-4444", "1997-11-18"),
                new Contact("Ethan Green", "555-555-6666", "1988-05-22"),
                new Contact("Fiona Black", "555-777-8888", "2002-10-31")
        };
    }

    public static Contact editedContact() {

        System.out.println("Please enter name for new contact here: ");
        String editedContactName = scan.nextLine();

        System.out.println("Please enter phonenumber for new contact here: ");
        String editedContactPhoneNumber = scan.nextLine();

        System.out.println("Please enter birthdate for new contact here: ");
        String editedContactBirthDate = scan.nextLine();

        Contact newContact = new Contact (editedContactName, editedContactPhoneNumber, editedContactBirthDate);
        return newContact;
    }

    // method with passing in a contactmanager object
    // from which the for loop iterates through the contacts array that is in the contactmanager object
    // every contact gets copied with the get.contact(i) functionality and gets stored in the local contact object
    // the local contact object gets printed with the toString method within Contact java file
    // for every new contact the stored Contact contact line gets initialized with a new contact.
    public static void displayContacts(ContactManager contactManager) {
        for (int i = 0; i < 8; i++) {
            Contact contact = contactManager.getContact(i);
            System.out.println(contact);
            System.out.println("\n");

        }
    }
}


