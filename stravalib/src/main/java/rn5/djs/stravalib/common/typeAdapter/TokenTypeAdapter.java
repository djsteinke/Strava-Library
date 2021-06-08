package rn5.djs.stravalib.common.typeAdapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import rn5.djs.stravalib.common.model.Token;

import java.io.IOException;

public class TokenTypeAdapter extends TypeAdapter<Token> {

    @Override
    public void write(JsonWriter out, Token token) throws IOException {
        out.value(token.toString());
    }

    @Override
    public Token read(JsonReader in) throws IOException {
        if(!in.peek().equals(JsonToken.NULL)) {
            return new Token(in.nextString());
        } else {
            in.nextNull();
            return null;
        }
    }
}
