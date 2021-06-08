package rn5.djs.stravalib.common.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import lombok.Getter;
import lombok.Setter;

import static rn5.djs.stravalib.common.model.Constants.getObjectFromJsonString;

@Getter
@Setter
public class Token {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long expirationDate;
    private String username;
    private String firstName;
    private String lastName;
    private transient File path;

    public Token withAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
    public Token withTokenType(String type) {
        this.tokenType = type;
        return this;
    }
    public Token withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
    public Token withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    public Token withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public Token withUsername(String username) {
        this.username = username;
        return this;
    }

    public Token withPath(File path) {
        this.path = path;
        return this;
    }

    public Token expiresAt(Long expiresAt) {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
        now.setTimeInMillis(expiresAt*1000L);
        this.expirationDate = now.getTimeInMillis();
        return this;
    }

    public static Token fromFile(File path) {
        File fileToken = new File(path,"token.json");
        StringBuilder sb = new StringBuilder();
        String ln;

        try {
            if (fileToken.exists()) {
                FileReader fr = new FileReader(fileToken);
                BufferedReader br = new BufferedReader(fr);
                while ((ln = br.readLine()) != null) sb.append(ln);
                br.close();
                fr.close();

                Token token = getObjectFromJsonString(sb.toString(), Token.class);
                token.setPath(path);
                return token;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.e("Token",e.getMessage());
        }
        return null;
    }

    public Token() {}

    public Token(String value) {
        String tmpVal = value;
        if (value.startsWith("Bearer"))
            tmpVal = value.substring(7);
        this.accessToken = tmpVal;
    }

    @NonNull
    @Override
    public String toString() {
        return tokenType + " " + accessToken;
    }

    public void setExpirationDate(Long expiresAt) {
        Calendar now = Calendar.getInstance(TimeZone.getTimeZone("gmt"));
        now.setTimeInMillis(expiresAt*1000L);
        this.expirationDate = now.getTimeInMillis();
    }

    public void delete() {
        if (path != null) {
            File fileToken = new File(path, "token.json");
            if (fileToken.exists())
                //noinspection ResultOfMethodCallIgnored
                fileToken.delete();
        }
    }

    public void save() {
        if (path != null) {
            File fileToken = new File(path, "token.json");

            try {
                JSONObject jsonToken = new JSONObject();
                jsonToken.put("tokenType", tokenType);
                jsonToken.put("accessToken", accessToken);
                jsonToken.put("refreshToken", refreshToken);
                jsonToken.put("username", username);
                jsonToken.put("expirationDate", expirationDate);
                jsonToken.put("firstName", firstName);
                jsonToken.put("lastName", lastName);
                FileWriter fw = new FileWriter(fileToken);
                fw.write(jsonToken.toString());
                fw.close();
            } catch (Exception e) {
                Log.e("Token", e.getMessage());
            }
        }
    }
}
