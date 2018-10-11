
/*
Created by: GREG WOO
Program: Simulate a hotel booking system
*/

import java.util.NoSuchElementException;

public class Hotel {

  // Attributes

  private String name;
  private Room[] roomArray;
  private Reservation[] reservationArray;

////////////////////////////////////////////////////////////
  //
  // Constructor

  public Hotel(String name, Room[] roomArray) {
    this.name = name;

    this.roomArray = new Room[roomArray.length];
    for( int i = 0; i < roomArray.length; i++) {
      this.roomArray[i] = roomArray[i];

    }

    this.reservationArray = new Reservation[0];

  }

  ///////////////////////////////////////////////////////////
  //
  // add/remove Reservation methods

  private void addReservation(Reservation reser) {

    // Here we add 1 to the length of the reservationArray
    Reservation[] copyOfTheArray = this.reservationArray;
    this.reservationArray = new Reservation[copyOfTheArray.length + 1];

    // Then we copy each value to the reservationArray
    for( int i = 0; i < copyOfTheArray.length; i++) {
      this.reservationArray[i] = copyOfTheArray[i];

    }

    //Finally, we add the new reservation at the last index of the array
    this.reservationArray[copyOfTheArray.length] = reser;

  }

  private void removeReservation(String name, String type) {

    int lengthOfTheReservations = this.reservationArray.length;

    for( int i = 0; i < lengthOfTheReservations; i++) {

      // Here we check if a reservation matches the name and type of room
      if( this.reservationArray[i].getName().equals(name)){
        if( this.reservationArray[i].getRoom().getType().equals(type)) {

          // Now we make the room available again
          this.reservationArray[i].getRoom().changeAvailability();

          // Here we remove the reservation from the array of reservations of the hotel
          Reservation[] copyOfTheArray = this.reservationArray;
          this.reservationArray = new Reservation[copyOfTheArray.length -1];

          // Here we copy the array and we shift everything so that only one reservation is removed
          for( int j = 0; j < this.reservationArray.length; j++) {
            if( j < i) {
              this.reservationArray[j] = copyOfTheArray[j];
            } else {
              this.reservationArray[j] = copyOfTheArray[j+1];
            }
          }
          //lengthOfTheReservations = lengthOfTheReservations - 1;
          lengthOfTheReservations = 0;

          // Here we throw an exception if no reservation match the name and type of room
        } else {
          throw new NoSuchElementException ("No reservation has been made under the given name for the given type of room");
        }

        // Here we throw an exception if no reservation match the name and type of room
      } else if (i == lengthOfTheReservations - 1) {
        throw new NoSuchElementException ("No reservation has been made under the given name for the given type of room");
      }
    }
  }

  //////////////////////////////////////////////////////////////////////////////////////////////
  // create/cancel Reservation methods

  public void createReservation(String name, String type) {

    // We verify if there is an available room of the selected type
    if(Room.findAvailableRoom(this.roomArray, type) == null) {
      System.out.println("The hotel " + this.name + " has no available room of the requested type");

    } else {
      // If there is one, we create a reservation
      Reservation res = new Reservation(Room.findAvailableRoom(this.roomArray, type), name);
      // Then we change the availability of the room
      Room.findAvailableRoom(this.roomArray, type).changeAvailability();
      // And we add to the array of reservations
      addReservation(res);
      System.out.println("The reservation was successfully completed.");
      System.out.println("We look forward having you at the " + this.name);

    }
  }


  public void cancelReservation(String name, String type) {
    // Here we cancel the reservation
    try {
      removeReservation(name, type);

      // If no matching reservation is found, we let the user know
    } catch (NoSuchElementException e) {
      System.out.println("ERROR CAUGHT: No reservation under such name has been made for the given type of room");
      return;
    }

    // Otherwise, we let the user know that it was successful
    System.out.println("The reservation was successfully canceled");

  }

  /////////////////////////////////////////////////////////////////////////////////////////////
  // Method that tells the user how much the given person owes the hotel based on all the reservations made under his name

  public void printInvoice(String name) {

    double debtCounter = 0;

    // Here we find all the reservations under his name and count how much he owes the hotel
    for( int i = 0; i < this.reservationArray.length; i++) {

      if( this.reservationArray[i].getName().equals(name)) {
        debtCounter += this.reservationArray[i].getRoom().getPrice();

      }
    }

    System.out.println( name + " owes " + debtCounter + " to the hotel " + this.name);

  }


  // toString method

  public String toString() {



    int counterDouble = findNumberAvailableRoom(this.roomArray, "double");
    int counterQueen = findNumberAvailableRoom(this.roomArray, "queen");
    int counterKing = findNumberAvailableRoom(this.roomArray, "king");



    String s1 = "Hotel name: " + this.name + "\n";
    String s2 = "Here is the number of rooms available: " + "\n";


    String s3 = " - Double: " + counterDouble + "\n";
    String s4 = " - Queen: " + counterQueen + "\n";
    String s5 = " - King: " + counterKing + "\n";

    String finalString = s1 + s2 + s3 + s4 + s5;
    return finalString;
  }

  // Helper Method

    private static int findNumberAvailableRoom(Room[] roomArray, String type) {
    int counter = 0;

    for( int i = 0; i < roomArray.length; i++) {

      if( roomArray[i].getType().equals(type)) {
        if( roomArray[i].getAvailability() == true) {
          counter++;
        }
      }
    }
    return counter;
  }



}
