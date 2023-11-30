package com.example.ainotetakingfx;

//Import all necessary classes from Java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Gpt {
    public static void main(String[] args){
        //Example use of chatGPT method
        System.out.println(chatGPT("what are you"));
    }

    //Method to interact with ChatGPT 3.5 Turbo API 
    public static String chatGPT(String prompt) {

        //Names API key, API endpoint, and model name
        String apiKey = "";
        String apiUrl = "https://api.openai.com/v1/chat/completions";
        String model = "gpt-3.5-turbo";

        try {
            //HTTP post request
            URL obj = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            //request body containing model and user message
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + "Answer the following prompt as briefly as you can: '" + prompt + "'" + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            //grab response from API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Extract and reuturn content from API
            return extractContentFromResponse(response.toString());
            //return (response.toString().split("\"content\":\"")[1].split("\"")[0]).substring(4);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11; // Marker for where the content starts.
        int endMarker = response.indexOf("\"", startMarker); // Marker for where the content ends.
        return response.substring(startMarker, endMarker); // Returns the substring containing only the response.
    }
}