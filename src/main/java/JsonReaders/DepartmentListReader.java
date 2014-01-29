package JsonReaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.stream.JsonReader;

public class DepartmentListReader {

	private static Set<String> departmentList = new HashSet<String>();

	public static Set<String> readJson(BufferedReader reader)
			throws IOException {
		
		departmentList.clear();
		
		JsonReader gsonReader = new JsonReader(reader);

		gsonReader.beginObject();

		while (gsonReader.hasNext()) {
			String title = gsonReader.nextName();
			if (title.equals("response")) {
				gsonReader.beginObject();
				while (gsonReader.hasNext()) {
					String tempTitle2 = gsonReader.nextName();
					if (tempTitle2.equals("data")) {
						gsonReader.beginObject();
						while (gsonReader.hasNext()) {
							String tempTitle3 = gsonReader.nextName();
							if (tempTitle3.equals("result")) {
								readResultsArray(gsonReader);
							} else {
								gsonReader.skipValue();
							}
						}
						gsonReader.endObject();
					} else {
						gsonReader.skipValue();
					}
				}
				gsonReader.endObject();
			} else {
				gsonReader.skipValue();
			}
		}

		gsonReader.endObject();

		gsonReader.close();

		return departmentList;
	}

	private static void readResultsArray(JsonReader gsonReader)
			throws IOException {
		gsonReader.beginArray();
		while (gsonReader.hasNext()) {
			addDepartment(gsonReader);
		}
		gsonReader.endArray();
	}

	private static void addDepartment(JsonReader gsonReader) throws IOException {
		String tempString = "";

		gsonReader.beginObject();
		while (gsonReader.hasNext()) {
			String title = gsonReader.nextName();
			if (title.equals("Acronym")) {
				tempString = gsonReader.nextString();
				System.out.println(tempString);
				departmentList.add(tempString);
			} else {
				gsonReader.skipValue();
			}
		}
		gsonReader.endObject();

	}
}
