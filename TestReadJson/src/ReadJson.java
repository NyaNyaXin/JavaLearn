import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ReadJson {
	public static void main(String[] args) {
		
		try {
			JsonParser parser = new JsonParser();
			//创建JSON解析器对象
			JsonObject object = (JsonObject)parser.parse(new FileReader("test.json"));
			//把解析器获取到的JSONElement转换成JSONObject
			System.out.println("cat="+object.get("cat").getAsString());
			System.out.println("pop="+object.get("pop").getAsBoolean());
			 
			JsonArray array = object.get("languages").getAsJsonArray();
			for(int i=0;i<array.size();i++){
				System.out.println("-----------");
				JsonObject subobject  = array.get(i).getAsJsonObject();
				System.out.println("id="+subobject.get("id").getAsInt());
				System.out.println("ide="+subobject.get("ide").getAsString());
				System.out.println("name="+subobject.get("name").getAsString());
			}
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
