package eu.epitech.serverandroid.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Smartgroom
 * @param <T> Template for object to serialize and deserialize
 */
public class ParserJson<T> {

    private T value;

    public ParserJson() {
    }

    public ParserJson(final T val) {
        value = val;
    }

    /**
     *
     * @return Object in json String
     * @throws JsonProcessingException Exception catch by Jackson
     */
    public final String writeJSON() throws JsonProcessingException {
        ObjectMapper mapper;

        mapper = new ObjectMapper();

        return (mapper.writeValueAsString(value));
    }

    /*
    public T writeObject(String str) throws Exception {
        ObjectMapper mapper;

        mapper = new ObjectMapper();

        return (mapper.readValue(str, new TypeReference<ParamsResponse<T>>() {}));
    }
     */

    /**
     *
     * @param str String json
     * @param classe Class to create with json String
     * @return Class T
     * @throws Exception Exception catch by Jackson
     */

    public final T writeObject(final String str, final Class<T> classe) throws Exception {
        ObjectMapper mapper;

        mapper = new ObjectMapper();

        return (mapper.readValue(str, classe));
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }
}
