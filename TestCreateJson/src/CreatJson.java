import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CreatJson {
	public static void main(String[] args) {
		JsonObject object = new JsonObject();//����JSON����
		object.addProperty("cat", "it");//��JSON���������JSON����
		JsonArray array = new JsonArray();//����JSON�������
		
		JsonObject lan1 = new JsonObject();
		lan1.addProperty("id",1);
		lan1.addProperty("ide", "eclipse");
		lan1.addProperty("name", "java");
		array.add(lan1);//��JSON������������JSON����
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
		
		object.add("languages", array);//��JSON���������JSON�������
		object.addProperty("pop", true);
		System.out.println(object.toString());//ֱ�ӵ���toString()������������õ�json�ַ���
	}
}
