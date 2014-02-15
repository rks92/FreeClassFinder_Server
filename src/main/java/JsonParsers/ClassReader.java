package JsonParsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import models.ClassModel;

import com.google.gson.stream.JsonReader;

public class ClassReader {
	static Vector<ClassModel> ListOfClasses = new Vector<ClassModel>();
	
	
	public static Vector<ClassModel> getClassDetails(BufferedReader givenReader) throws IOException {
		ListOfClasses.clear();
		
		JsonReader reader = new JsonReader(givenReader);

		reader.beginObject();

		while (reader.hasNext()) {
			String tempTitle1 = reader.nextName();
			if (tempTitle1.equals("response")) {
				reader.beginObject();
				while (reader.hasNext()) {
					String tempTitle2 = reader.nextName();
					if (tempTitle2.equals("data")) {
						reader.beginObject();
						while (reader.hasNext()) {
							String tempTitle3 = reader.nextName();
							if (tempTitle3.equals("result")) {
								reader.beginArray();

								while (reader.hasNext()) {
									getClassInfo(reader);
								}

								reader.endArray();
							} else {
								reader.skipValue();
							}
						}
						reader.endObject();
					} else {
						reader.skipValue();
					}
				}
				reader.endObject();
			} else {
				reader.skipValue();
			}
		}

		reader.endObject();
		reader.close();
		
		return new Vector<ClassModel>(ListOfClasses);
	}

	private static void getClassInfo(JsonReader reader) throws IOException {
		ClassModel tempModel = new ClassModel();
		reader.beginObject();
		while (reader.hasNext()) {
			String title = reader.nextName();

			if (title.equals("Building")) {
				tempModel.setBuilding(reader.nextString());
			} else if (title.equals("Room")) {
				tempModel.setRoom(reader.nextString());
				System.out.println(tempModel.getRoom());
			} else if (title.equals("StartTime")) {
				tempModel.setStartTime(reader.nextString());
			} else if (title.equals("EndTime")) {
				tempModel.setEndTime(reader.nextString());
			} else if (title.equals("Days")) {
				tempModel.setDays(reader.nextString());
			} else {
				reader.skipValue();
			}
		}
		
		ListOfClasses.add(tempModel);
		reader.endObject();
	}
}
