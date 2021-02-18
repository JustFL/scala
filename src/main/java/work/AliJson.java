package work;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;


class JsonTestObject {
    private int age;
    private String fullName;
    public JsonTestObject(int age, String fullName) {
        super();
        this.age = age;
        this.fullName= fullName;
    }

    public JsonTestObject() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "JsonTestObject{" +
                "age=" + age +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

public class AliJson {
    public static void main(String[] args) {
        JsonTestObject diaochan = new JsonTestObject(20, "貂蝉");
        JsonTestObject zhaojun = new JsonTestObject(22, "王昭君");
        ArrayList<JsonTestObject> list = new ArrayList<>();
        list.add(diaochan);
        list.add(zhaojun);
        String jsonString = JSON.toJSONString(diaochan);
        String jsonList = JSON.toJSONString(list);
        System.out.println(jsonString);
        System.out.println(jsonList);

        //反向解析必须有无参构造
        JsonTestObject parseObject = JSON.parseObject(jsonString, JsonTestObject.class);
        System.out.println(parseObject);

        ArrayList arrayList = JSON.parseObject(jsonList, ArrayList.class);
        arrayList.forEach(System.out::println);
    }
}
