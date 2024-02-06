package com.example.demo200000.server;

import com.example.demo200000.enity.Groups;

import com.example.demo200000.enity.Student;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ServerConnectionGroups {

    public static List<Groups> getGroupsData() {
        List<Groups> students = new ArrayList<>();

        try {
            String urlString = "http://localhost:8081/groups/";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseData = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseData.append(line);
                }

                JSONArray jsonArray = new JSONArray(responseData.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonStudent = jsonArray.getJSONObject(i);
                    int id = jsonStudent.getInt("groupid");
                    String groupname = jsonStudent.getString("groupname");
                    String sporttype =jsonStudent.getString("sporttype");
                    String aage = jsonStudent.getString("aage");
                    int maxstudents = jsonStudent.getInt("maxstudents");
                    int coachesid = jsonStudent.getInt("coachesid");

                    // Создание объекта Student и добавление его в список
                    Groups groups = new Groups(id, groupname, sporttype, aage, maxstudents, coachesid);
                    students.add(groups);
                }


                reader.close();
            } else {
                System.out.println("GET Request - Response Code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
    public static void updateGroupsOnServer(Groups groups) {
        try {


            URL url = new URL("http://localhost:8081/groups/" + groups.getGroupid());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Преобразование объекта Student в формат JSON

            String jsonData = "{\"groupid\":" + groups.getGroupid() + ", \"groupname\":\"" + groups.getGroupname() + "\", \"sporttype\":\"" + groups.getSporttype() + "\", \"aage\":\"" + groups.getAage() + "\", \"maxstudents\":" + groups.getMaxstudents() + ", \"coachesid\":" + groups.getGroupid() + "}";

            System.out.println(jsonData);
            // Отправка данных на сервер
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Данные успешно обновлены");
            } else {
                System.out.println("Ошибка при обновлении данных: " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
