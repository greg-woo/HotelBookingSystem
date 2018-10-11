
/*
Created by: GREG WOO
Program: Simulate a hotel booking system
*/

public class Reservation {

  // Attributes

  private String name;
  private Room roomReserved;

////////////////////////////////////////////////////////////
  // Public Methods
  //
  // Constructor

  public Reservation(Room r, String name) {
    this.name = name;

    Room newRoom = new Room("double");
    newRoom = r;
    this.roomReserved = newRoom;

  }

  // Get methods

  public String getName() {
    return this.name;

  }

  public Room getRoom() {
    return this.roomReserved;

  }

}
