package com.example.monk_prj.utility;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonMapper {
    private static ObjectMapper objectMapper = null;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        objectMapper.configure(StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION.mappedFeature(), true);
    }

    public static <T> T convertObjectToClass(Object from, Class<T> toType) {
        return objectMapper.convertValue(from, toType);
    }

    public static String mapObjectToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T mapJsonToObject(String json, Class<T> toType) throws JsonProcessingException {
        return objectMapper.readValue(json, toType);
    }

    public static <T> T mapJsonToObjectFromFile(File file, Class<T> toType) throws IOException {
        return objectMapper.readValue(file, toType);
    }

    public static JsonNode mapJsonToJsonNode(Object object) {
        return objectMapper.valueToTree(object);
    }

    public static <T> T mapJsonNodeToObject(JsonNode jsonNode, Class<T> toType) throws JsonProcessingException {
        return objectMapper.treeToValue(jsonNode, toType);
    }
}
