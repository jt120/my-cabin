package com.studytrails.json.jackson;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
public class DataBinding1 {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {
        String url = "http://freemusicarchive.org/api/get/albums.json?api_key=60BLHNQCAOUFPIBZ&limit=2";
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Albums albums = mapper.readValue(new URL(url), Albums.class);
        System.out.println(albums);
        Dataset[] datasets = albums.getDataset();
        for (Dataset dataset : datasets) {
            System.out.println(dataset);
        }

        Map<String, Object> map = datasets[0].getOtherProperties();
        System.out.println("size:" + map.size());
        for(Map.Entry<String, Object> entry:map.entrySet()) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
 
}