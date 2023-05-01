package exam07;

import org.json.JSONObject;
import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CreateJsonExample {
    public static void main(String[] args) throws IOException {
        //JSON 생성
        JSONObject root = new JSONObject();

        //속성 추가
        root.put("id", "qaqa");
        root.put("name", "현호");
        root.put("age", 18);
        root.put("student", true);

        //객체 속성 추가
        JSONObject tel = new JSONObject();
        tel.put("home", "02-123-1234");
        tel.put("mobile", "010-1234-1234");
        root.put("tel", tel);

        //배열 속성 추가
        JSONArray skill = new JSONArray();
        skill.put("JAVA");
        skill.put("C");
        skill.put("C++");

        root.put("skill", skill);

        //JSON 출력
        String json = root.toString();
        System.out.println(json);

        Writer writer = new FileWriter("C:\\Users\\user\\Desktop\\수업 과제\\2학년\\전공\\네트워크\\Writer0424.txt", StandardCharsets.UTF_8);
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
