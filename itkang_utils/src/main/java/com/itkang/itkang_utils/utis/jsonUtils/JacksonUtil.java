
package com.itkang.itkang_utils.utis.jsonUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JacksonUtil {
    private static Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JacksonUtil() {
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    public static String writeValueAsString(Object obj) {
        try {
            return getInstance().writeValueAsString(obj);
        } catch (JsonGenerationException var2) {
            logger.error(var2.getMessage(), var2);
        } catch (JsonMappingException var3) {
            logger.error(var3.getMessage(), var3);
        } catch (IOException var4) {
            logger.error(var4.getMessage(), var4);
        }

        return null;
    }

    public static <T> T readValue(String jsonStr, Class<T> clazz) {
        try {
            return getInstance().readValue(jsonStr, clazz);
        } catch (JsonParseException var3) {
            logger.error(var3.getMessage(), var3);
        } catch (JsonMappingException var4) {
            logger.error(var4.getMessage(), var4);
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static <T> T readValueRefer(String jsonStr, Class<T> clazz) {
        try {
            return getInstance().readValue(jsonStr, new TypeReference<T>() {
            });
        } catch (JsonParseException var3) {
            logger.error(var3.getMessage(), var3);
        } catch (JsonMappingException var4) {
            logger.error(var4.getMessage(), var4);
        } catch (IOException var5) {
            logger.error(var5.getMessage(), var5);
        }

        return null;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> map = new HashMap();
            map.put("aaa", "111");
            map.put("bbb", "222");
            String json = writeValueAsString(map);
            System.out.println(json);
            System.out.println(readValue(json, Map.class));
        } catch (Exception var3) {
            logger.error(var3.getMessage(), var3);
        }

    }
}
