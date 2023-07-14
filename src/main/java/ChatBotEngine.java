import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @author Nabil Jarrai
 * @since 14/07/2023
 */
public class ChatBotEngine {

    private static final String API_URL = "https://api.openai.com/v1/engines/davinci-codex/completions";
    private static final String API_KEY = "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";

    private final OkHttpClient client;

    public ChatBotEngine() {
        this.client = new OkHttpClient();
    }

    public String getChatBotResponse(String userMessage) {
        MediaType mediaType = MediaType.parse("application/json");

        String json = "{\"prompt\": \"" + userMessage + "\", \"max_tokens\": 50}";
        RequestBody requestBody = RequestBody.create(json, mediaType);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("OpenAI-Organization","org-VBXcOZJwmevXNdGmsReX9Kys")
                .post(requestBody)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String responseData = response.body().string();
                return parseResponseData(responseData);
            } else {
                System.out.println("Error: " + response.code() + " " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "I'm sorry, I couldn't generate a response.";
    }

    private String parseResponseData(String responseData) {
        // Parse the response data to extract the chatbot's reply
        // You'll need to adapt this code based on the structure of the response you receive
        // In this example, we assume the response contains the chatbot's reply in a 'choices' array
        // with the first choice being the most likely one
        // Modify this code to fit the response format of the LLM API version you're using
        // Here's a simplified example assuming a JSON response structure
        // {"choices": [{"text": "Chatbot reply"}]}
        String chatbotReply = "";
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            JSONArray choicesArray = jsonObject.getJSONArray("choices");
            if (choicesArray.length() > 0) {
                JSONObject firstChoice = choicesArray.getJSONObject(0);
                chatbotReply = firstChoice.getString("text");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chatbotReply;
    }
}

