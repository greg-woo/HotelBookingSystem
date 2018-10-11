
/*
Created by: GREG WOO
Program: Simulate a hotel booking system
*/

import java.util.Scanner;
import java.util.Random;

public class BookingSystem {

  private static String[] typeOfRooms = {"double","queen","king"};
  private static Random r = new Random(123);

  //returns a random String from the above array.
  private static String getRandomType(){
    int index = r.nextInt(typeOfRooms.length);
    return typeOfRooms[index];
  }
  //returns a random number of rooms between 5 and 50.
  private static int getRandomNumberOfRooms(){
    return r.nextInt(50)+1;
  }
  //End of provided code.

  public static void main(String[] args){

    String intro1 = "Welcome to the COMP 202 booking system";
    String intro2 = "Please enter the name of the hotel you'd like to book";
    System.out.println(intro1 + "\n" + intro2);

    // Here we create a scanner to get the hotel's name
    Scanner sc = new Scanner(System.in);
    String nameHotel = sc.nextLine();

    // Here we create an array with a random number of rooms
    int randomNumber = BookingSystem.getRandomNumberOfRooms();

    Room[] roomArray = new Room[randomNumber];

    for( int i = 0; i < randomNumber; i++) {
      Room newRoom = new Room(BookingSystem.getRandomType());
      roomArray[i] = newRoom;
    }

    // Now we can create a hotel
    Hotel firstHotel = new Hotel(nameHotel, roomArray);

    // Here are all the menu options
    String s0 = "\n"+ "***************************************************" + "\n";
    String hotelIntro = "Welcome to " + nameHotel + ". Choose one of the following options:" + "\n";
    String s1 = "1. Make a reservation" + "\n";
    String s2 = "2. Cancel a reservation" + "\n";
    String s3 = "3. See an invoice" + "\n";
    String s4 = "4. See the hotel info" + "\n";
    String s5 = "5. Exit the booking system" + "\n";
    String s6 = "***************************************************" + "\n"+ "\n";

    String finalString = s0 + hotelIntro + s1 + s2 + s3 + s4 + s5 + s6;
    System.out.println(finalString);

    Scanner sc2 = new Scanner(System.in);

    int numberMenu = sc.nextInt();

    // Here we keep asking for a new int as long as the user doesn't press 5
    while(!(numberMenu == 5)) {

      // The user wants to make a reservation
      if( numberMenu == 1) {
        System.out.print("What is your name?");
        String nameUsedForReservation = sc2.nextLine();
        System.out.println();
        System.out.print("What kind of room?");
        String type = sc2.nextLine();
        firstHotel.createReservation(nameUsedForReservation, type);

        // The user wants to cancel a reservation
      } else if ( numberMenu == 2) {
        System.out.println("What is your name?");
        String nameForReservation = sc2.nextLine();
        System.out.println("What kind of room?");
        String typeRoomReservation = sc2.nextLine();
        firstHotel.cancelReservation(nameForReservation, typeRoomReservation);

        // The user wants to see his invoice
      } else if ( numberMenu == 3) {
        System.out.println("What is your name?");
        String nameForInvoice = sc2.nextLine();
        firstHotel.printInvoice(nameForInvoice);

        // The user wants to see the details of the hotel
      } else if (numberMenu == 4) {
        System.out.println(firstHotel);

      }

      // We display the menu once more
      System.out.println(finalString);
      numberMenu = sc.nextInt();


    }

    // If we reach this line, then the user must have pressed 5 to exit the booking system
    System.out.println("It was a pleasure doing business with you!");

    sc.close();
    sc2.close();
  }
}
