package net.minecraft.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SkinFixes {

    private static String getUrl(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static String getSkinUrl(String username)
    {
        try{
            String uuidJson = getUrl("https://api.mojang.com/users/profiles/minecraft/"+username);

            int idStart = uuidJson.indexOf("{\"id\":\"")+7;
            int idEnd = uuidJson.substring(idStart).indexOf("\",\"")+idStart;

            String uuid = uuidJson.substring(idStart,idEnd);
            String profileJson = getUrl("https://sessionserver.mojang.com/session/minecraft/profile/"+uuid);

            int profileStart = profileJson.indexOf("\"name\":\"textures\",\"value\":\"")+27;
            int profileEnd = profileJson.substring(profileStart).indexOf("\"}]}")+profileStart;
            String b64Json = profileJson.substring(profileStart,profileEnd);

            String skinJson = new String(Base64.decode(b64Json));
            int skinStart = skinJson.indexOf("{\"SKIN\":{\"url\":\"")+16;
            int skinEnd = skinJson.substring(skinStart).indexOf("\"")+skinStart;

            String skinUrl = skinJson.substring(skinStart,skinEnd);
            return skinUrl;
        }catch (Exception e){};
        return "";
    }

}
