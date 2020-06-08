package io.github.yidasanqian.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FastJsonMapper extends AbstractJsonMapper {

    @Override
    public Map toMap(String json) {
        Map result = null;
        try {
            result = objectMapper.readValue(json, LinkedHashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List toList(String json) {
        return JSON.parseObject(json, LinkedList.class);
    }

    @Override
    public <T> List<T> toList(String json, Type type) {
        TypeReference<T> typeReference1 = new TypeReference<T>() {
            @Override
            public Type getType() {
                return type;
            }
        };
        return JSON.parseObject(json, typeReference1.getType());
    }

    @Override
    public String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    @Override
    public String toJsonWithDateFormat(Object object, String dateFormatPattern) {
        return JSON.toJSONStringWithDateFormat(object, dateFormatPattern, SerializerFeature.WriteDateUseDateFormat);
    }

    @Override
    public <T> T toPojo(String json, Class<T> valueType) {
        return JSON.parseObject(json, valueType);
    }

    @Override
    public Map convertToMap(Object fromValue) {
        String json = JSON.toJSONString(fromValue);
        return JSON.parseObject(json, LinkedHashMap.class);
    }

    @Override
    public <T> T convertFromMap(Map fromMap, Class<T> toValueType) {
        String json = JSON.toJSONString(fromMap);
        return JSON.parseObject(json, toValueType);
    }
}
