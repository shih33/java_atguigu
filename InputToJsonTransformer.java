package org.example;

import java.io.*;
import java.util.*;
import org.json.*;

public class InputToJsonTransformer {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.json";

        try {
            List<String> lines = readInputFile(inputFile);
            JSONObject jsonStructure = createJsonStructure(lines);
            writeJsonToFile(jsonStructure, outputFile);
            System.out.println(jsonStructure.toString(4)); // Print formatted JSON
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readInputFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static JSONObject createJsonStructure(List<String> lines) throws JSONException {
        JSONObject root = new JSONObject();
        JSONArray dataArray = new JSONArray();
        root.put("data", dataArray);

        Map<String, JSONObject> level1Map = new LinkedHashMap<>();

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            if (parts.length == 3) {
                String level1 = parts[0];
                String level2 = parts[1];
                String level3 = parts[2];

                JSONObject level1Obj = level1Map.computeIfAbsent(level1, k -> {
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("title", k);
                        obj.put("menu", new JSONArray());
                        dataArray.put(obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return obj;
                });

                JSONArray level1Menu = level1Obj.getJSONArray("menu");
                JSONObject level2Obj = findOrCreateLevel2(level1Menu, level2);
                JSONArray level2Menu = level2Obj.getJSONArray("menu");

                JSONObject level3Obj = new JSONObject();
                level3Obj.put("title", level3);
                level3Obj.put("menu", new JSONArray());
                level2Menu.put(level3Obj);
            }
        }

        return root;
    }

    private static JSONObject findOrCreateLevel2(JSONArray level1Menu, String level2Title) throws JSONException {
        for (int i = 0; i < level1Menu.length(); i++) {
            JSONObject obj = level1Menu.getJSONObject(i);
            if (obj.getString("title").equals(level2Title)) {
                return obj;
            }
        }
        JSONObject newObj = new JSONObject();
        newObj.put("title", level2Title);
        newObj.put("menu", new JSONArray());
        level1Menu.put(newObj);
        return newObj;
    }

    private static void writeJsonToFile(JSONObject jsonObject, String fileName) throws IOException {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(jsonObject.toString(4));
        }
    }
}