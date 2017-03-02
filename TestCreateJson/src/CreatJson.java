import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CreatJson {
	public static void main(String[] args) {
		JsonObject object = new JsonObject();//创建JSON对象
		object.addProperty("cat", "it");//向JSON对象中添加JSON属性
		JsonArray array = new JsonArray();//创建JSON数组对象
		
		JsonObject lan1 = new JsonObject();
		lan1.addProperty("id",1);
		lan1.addProperty("ide", "eclipse");
		lan1.addProperty("name", "java");
		array.add(lan1);//向JSON数组对象中添加JSON属性
		JsonObject lan2 = new JsonObject();
		lan2.addProperty("id",2);
		lan2.addProperty("ide", "Visual Studio");
		lan2.addProperty("name", "C#");
		array.add(lan2);
		JsonObject lan3 = new JsonObject();
		lan3.addProperty("id",3);
		lan3.addProperty("ide", "Xcode");
		lan3.addProperty("name", "Swift");
		array.add(lan3);
		
		object.add("languages", array);//向JSON对象中添加JSON数组对象
		object.addProperty("pop", true);
		System.out.println(object.toString());//直接调用toString()方法输出创建好的json字符串
	}
}
