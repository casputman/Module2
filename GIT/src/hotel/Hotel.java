package hotel;

public class Hotel {
	public Room room;
	public Guest guest;
	public String hotelName;
	public Safe safe;

	public Hotel(String hotelName){
		this.hotelName = hotelName;
		hotelName = new String("Prominente Pikken Hotel");
		room = new Room(1);
	}
	//@ pure
	public String getHotelName(){
		return hotelName;
	}
	
	//@ requires getFreeRoom() || requires getPassword().testWord(password) || guest.getName() != null
	//@ ensures guest.getRoom() != null
	
	public Room checkIn(String password, String name){
		guest = new Guest(name);
		if (guest.getName() == name){
			return null;
		} else {
			if (getPassword().testWord(password) == false){
				return null;
			} else {
				if (getFreeRoom() == null){
					return null;
				} else {
					guest.checkIn(getFreeRoom());
					return room;
				}
			}
		}
	}
	//@ requires guest.getRoom(name) != null
	//@ ensures guest.getRoom().getGuest() == null
	public void checkOut(String name){
		if (guest.getRoom(name) != null) {
		guest.getRoom(name).getSafe().deactivate();
		guest.getRoom(name).getGuest().checkOut();
		}
	}
	//@ pure
	public Password getPassword(){
		return room.getSafe().getPassword();

	}
	//@ requires room.getGuest() == null
	public Room getFreeRoom(){
		if (room.getGuest() == null){
			return room; }
		else {
			return null;
		}
	}
	public String toString(){
		return "Rooms: " + room + "Guests: " + guest;
	}
	
	//@ pure
	public String getName(){
		return room.getGuest().getName();
	}
}
