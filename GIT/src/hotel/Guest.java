package hotel;
import hotel.Room;


public class Guest {
	private String name;
	private Room room;
	public Guest(String name){
		this.name = name;	
	}
	
	public String getName(){
		return name;
	}

	public Room getRoom(String name){
		return room;
	}


	public boolean checkIn(Room r){
		if (r.getGuest() == null && room == null){
			room = r;
			r.setGuest(this); 
			return true;
		} else {
			return false;
		}
	}
	public boolean checkOut(){
		if (room != null){
			room.setGuest(null);
			room = null;
			return true;
		} else { 
			return false; }
	}
	public String toString(){
		return "Guest: " + name + "Room: " + room;
	}

}
