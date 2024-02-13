package org.example.events.npmg.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.events.npmg.models.Tag;

import java.io.IOException;

public class CategorySerializer extends JsonSerializer<Tag> {
    @Override
    public void serialize(Tag value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(value.getId());
        }
    }
}