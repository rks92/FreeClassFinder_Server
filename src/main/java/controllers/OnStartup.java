package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import models.ClassModel;
import models.RoomTimesModel;
import models.StartEndTimes;

import org.springframework.beans.factory.InitializingBean;

import sqlHandler.SQLMainWrapper.SQLMain;
import HelperFunctions.UrlCaller;
import JsonParsers.ClassReader;
import JsonParsers.DepartmentListReader;

public class OnStartup implements InitializingBean{
	
	String departmentListJsonURL = "http://api.uwaterloo.ca/public/v1/?key=3c804d5a86791dc629e7bc1b9a8f7c60&service=DepartmentsList&output=json";
	
	String courseListingUrlBefore = "http://api.uwaterloo.ca/public/v1/?key=3c804d5a86791dc629e7bc1b9a8f7c60&service=Schedule&q=";
	String courseListingUrlAfter = "&term=1141&output=json";
	Vector<BufferedReader> classListReaders = new Vector<BufferedReader>();
	Vector<RoomTimesModel> roomTimes = new Vector<RoomTimesModel>();

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			BufferedReader reader = UrlCaller.getUrl(departmentListJsonURL);
			Set<String> departmentList = DepartmentListReader.readJson(reader);
			
//			for (String dept : departmentList){
//				classListReaders.add(UrlCaller.getUrl(courseListingUrlBefore + dept+ courseListingUrlAfter));
//				System.out.println("URL Called : " + courseListingUrlBefore + dept + courseListingUrlAfter);
//			}
			
			classListReaders.add(UrlCaller.getUrl(courseListingUrlBefore + "CS" + courseListingUrlAfter));
			
			for (int i = 0; i < this.classListReaders.size(); i++){
				Vector<ClassModel> tempModel = ClassReader.getClassDetails(this.classListReaders.elementAt(i));
				AddToCurrentList(tempModel);
			}
			
			SQLMain temp123 = new SQLMain();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void AddToCurrentList(Vector<ClassModel> tempModel) {
		for (int i = 0; i < tempModel.size(); i++){
			if (roomTimes.contains(tempModel.elementAt(i).getBuilding()) && roomTimes.contains(tempModel.elementAt(i).getRoom())){
				for (int j = 0; j < roomTimes.size(); j++){
					if (roomTimes.elementAt(j).getRoomNumber().equals(tempModel.elementAt(i).getRoom()) && roomTimes.elementAt(j).equals(tempModel.elementAt(i).getBuilding())){
						roomTimes.elementAt(j).getTimes().add(new StartEndTimes(tempModel.elementAt(i).getStartTime(), tempModel.elementAt(i).getEndTime()));
					}
				}
			} else{
				if (!tempModel.elementAt(i).getBuilding().isEmpty() && !tempModel.elementAt(i).getRoom().isEmpty()){					
					roomTimes.add(new RoomTimesModel(tempModel.elementAt(i).getBuilding(), tempModel.elementAt(i).getRoom()));
				}
				
				for (int j = 0; j < roomTimes.size(); j++){
					if (roomTimes.elementAt(j).getRoomNumber().equals(tempModel.elementAt(i).getRoom()) && roomTimes.elementAt(j).getBuilding().equals(tempModel.elementAt(i).getBuilding())){
						roomTimes.elementAt(j).getTimes().add(new StartEndTimes(tempModel.elementAt(i).getStartTime(), tempModel.elementAt(i).getEndTime()));
					}
				}
			}
		}
	}
}
