package com.example.demo200000.server;

import com.example.demo200000.enity.Coaches;
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

public class ServerConnectionTrainer {

    public static List<Coaches> getCoachesData() {
        List<Coaches> coaches = new ArrayList<>();

        try {
            String urlString = "http://localhost:8081/coaches/";
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
                    JSONObject jsonCoaches = jsonArray.getJSONObject(i);
                    int id = jsonCoaches.getInt("coachesid");
                    String name = jsonCoaches.getString("fullname");
                    String contactInformation = jsonCoaches.get("contactinfo").toString();
                    String login = jsonCoaches.getString("login");
                    String information = jsonCoaches.getString("information");
                    // Создание объекта Student и добавление его в список
                    Coaches coache = new Coaches(id, name, contactInformation, login, information);
                    coaches.add(coache);
                }


                reader.close();
            } else {
                System.out.println("GET Request - Response Code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return coaches;
    }

    public static void updateCoachesOnServer(Coaches coaches) {
        try {

            URL url = new URL("http://localhost:8081/coaches/add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Преобразование объекта Student в формат JSON
            String jsonData = "{ \"fullname\":\"" + coaches.getFull_name() + "\", \"contactinfo\":\"" + coaches.getContact_information() + "\", \"login\":\"" + coaches.getLogin() + "\", \"information\":\"" + coaches.getInformation()+ "\"}";

            // Отправка данных на сервер
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println(jsonData);
            if (responseCode == 200) {
                System.out.println("Данные успешно добавлены");
            } else {
                System.out.println("Ошибка при добавления данных: " + connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCoachesFromServer(int coachesId) {
        try {
            String urlString = "http://localhost:8081/coaches/" + coachesId;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Успешно удалено");
            } else {
                // Обработка ошибки
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
