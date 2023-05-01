package exam07;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ParseJsonExample {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\user\\Desktop\\수업 과제\\2학년\\전공\\네트워크\\Writer0424.txt"));

        String json = br.readLine();
        br.close();

        //파싱
        JSONObject root = new JSONObject(json);

        //속성 읽어오기
        System.out.println("id: " + root.getString("id"));
        System.out.println("name: " + root.getString("name"));
        System.out.println("age: " + root.getInt("age"));
        System.out.println("student: " + root.getBoolean("student"));

        JSONObject tel = root.getJSONObject("tel");
        System.out.println("tel home: " + tel.getString("home"));
        System.out.println("tel mobile: " + tel.getString("mobile"));

        JSONArray skill = root.getJSONArray("skill");
        System.out.print("skill: ");
        for(int i = 0; i < skill.length(); i++) {
            System.out.print(skill.get(i) + ", ");
        }
    }
}
