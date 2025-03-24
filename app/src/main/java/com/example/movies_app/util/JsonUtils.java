package com.example.movies_app.util;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

import android.content.Context;
import android.util.Log;

import com.example.movies_app.R;
import com.example.movies_app.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class JsonUtils {
    // Tag for logging (helps identify logs related to this class in Logcat)
    private static final String TAG = "JsonUtil";
    /**
     * Loads movie data from a JSON file stored in res/raw.
     * @param context The context of the activity or application.
     * @return A list of Movie objects parsed from the JSON file.
     */
    public static List<Movie> loadMoviesFromJson(Context context) {
        List<Movie> movies = new ArrayList<>();
        try {
            // Reading JSON file contents from res/raw/movies.json
            String jsonContent = readJsonFile(context, R.raw.movies);
            JSONArray jsonArray = new JSONArray(jsonContent);
            // Loop through the JSON array to extract movie data
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject movieJson = jsonArray.getJSONObject(i);
                    // Extract movie data with fallback default values
                    String title = "Unknown";
                    if (movieJson.has("title")) {
                    title = movieJson.optString("title");
                    }
                    int year = 0;
                    if (movieJson.has("year")) {
                        year = movieJson.optInt("year", 0);

                    }

                    String genre = "Unknown";
                    if (movieJson.has("genre")) {
                        genre = movieJson.getString("genre");
                    }

                    String posterResource = "default_poster";
                    if (movieJson.has("poster")) {
                        posterResource = movieJson.getString("poster");
                    }
                    // Create a new Movie object and add it to the list
                    movies.add(new Movie(title, year, genre, posterResource));
                } catch (JSONException e) {
                    // Log an error if a specific movie entry cannot be parsed
                    Log.e(TAG, "Error parsing movie at index " + i, e);
                }
            }
// Handle and log file reading, JSON parsing errors
        } catch (IOException e) {
            handleJsonException(e);

        } catch (JSONException e) {
            handleJsonException(e);
        }
        return movies;
    }
    /**
     * logs error messages
     * @param e The exception to be logged.
     */
    public static void handleJsonException(Exception e) {
        Log.e(TAG, "JSON Parsing Error: " + e.getMessage(), e);
    }

    // Helper method to read JSON file from resources
    /**
     * Reads a JSON file and returns its content as a String.
     * @param context The application or activity context.
     * @param resourceId The resource ID of the JSON file (e.g., R.raw.movies).
     * @return The content of the JSON file as a String.
     * @throws IOException If an error occurs while reading the file.
     */
    private static String readJsonFile(Context context, int resourceId) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        //open file using resourceid
        try (InputStream inputStream = context.getResources().openRawResource(resourceId);
             //Read the file
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = reader.readLine()) != null) {
                //append each line to stringBuilder
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error reading JSON file", e);
            throw e;

        }
        return stringBuilder.toString();
    }
}

