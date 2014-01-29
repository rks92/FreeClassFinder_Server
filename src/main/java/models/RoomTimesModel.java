package models;

import java.util.Vector;

public class RoomTimesModel {
	String roomNumber;
	String building;
	Vector<StartEndTimes> times = new Vector<StartEndTimes>();
	
	public RoomTimesModel(String building, String roomNumber){
		this.roomNumber = roomNumber;
		this.building = building;
	}

	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Vector<StartEndTimes> getTimes() {
		return times;
	}
	public void setTimes(Vector<StartEndTimes> times) {
		this.times = times;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
}
