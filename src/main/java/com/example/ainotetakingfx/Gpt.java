package com.example.ainotetakingfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Gpt {
    public static void main(String[] args){
        System.out.println(chatGPT("what are you"));
    }
    public static String chatGPT(String prompt) {
        String apiKey = System.getenv("API_KEY");
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";

        try {
            //HTTP post request
            URL obj = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            //request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            //get response
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return (response.toString().split("\"content\":\"")[1].split("\"")[0]).substring(4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

