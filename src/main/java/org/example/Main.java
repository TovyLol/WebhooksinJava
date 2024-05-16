package org.example;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        String webhookURL = "your discord webhook link";
        String message = "Webhook Creation succesfull";
        String author = "Webhook school tester smh";
        String title = "Tovy @ 2024";
        String color = "#FF5733";

        sendWebhook(webhookURL, message, author, title, color);
    }

    public static void sendWebhook(String webhookURL, String message, String author, String title, String color) {
        try {
            URL url = new URL(webhookURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);


            String colorWithoutHash = color.substring(1);


            String payload = "{\"username\":\"" + author + "\",\"embeds\":[{\"title\":\"" + title + "\",\"description\":\"" + message + "\",\"color\":" + Integer.parseInt(colorWithoutHash, 16) + "}]}";

            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(payload);
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Webhook sent successfully!");
            } else {
                System.out.println("Failed to send webhook. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}