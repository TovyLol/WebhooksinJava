package org.example;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        String webhookURL = "your url";
        String message = "message";
        String author = "Webhook mf";
        String title = "Tovy @ before christ existed";
        String color = "#FF5743";

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