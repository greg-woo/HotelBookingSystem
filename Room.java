
/*
Created by: GREG WOO
Program: Simulate a hotel booking system
*/

public class Room {

  // Attributes

  private String type;
  private double price;
  private boolean availability;

//////////////////////////////////////////////////////////
  // Public Methods
  //
  // Constructor

  public Room(String type) {

    // Only types supported are "double" "queen" and "king" with a specific price for each

    if (type.equals("double")) {

      this.type = type;
      this.availability = true;
      this.price = 90;

    } else if (type.equals("queen")) {
      this.type = type;
      this.availability = true;
      this.price = 110;

    } else if (type.equals("king")) {
      this.type = type;
      this.availability = true;
      this.price = 150;

      // Constructor throws an exception if the input is not one of the supported types
    } else {
      throw new IllegalArgumentException ("No room of such type can be created.");
    }
  }

  // Get Methods

  public String getType(){
    return this.type;

  }

  public double getPrice(){
    return this.price;

  }

  public boolean getAvailability(){
    return this.availability;

  }

  // Other public methods
  //
  // This method changes the availability of a room

  public void changeAvailability(){

    boolean reverse = !(this.availability);
    this.availability = reverse;

  }

  // This method finds if there exists an available room of a certain type in a room array

  public static Room findAvailableRoom(Room[] roomArray, String type) {

    for (int i = 0; i < roomArray.length; i++) {

      // This if statement checks if a room of such type exists in the array
      if (roomArray[i].getType().equals(type)) {
        // This if statement then checks if the room is available
        if(roomArray[i].getAvailability() == true){
          return roomArray[i];
        }
      }
    }

    return null;

  }


}
