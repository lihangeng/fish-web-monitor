package com.yihuacomputer.common.filter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.IPUtils;

public class JsonUtilsTest {
    @Test
    public void testFromJson() {
        String json = "{'id':1,'name':'java'}";
        Book book = JsonUtils.fromJson(json, Book.class);
        assertEquals(1, book.getId());
        assertEquals("java", book.getName());

        String json2 = "{'id':2,'name':'javassss'}";
        String jsons = "[" + json + "," + json2 + "]";
        try {
            List<Book> books = JsonUtils.om.readValue(jsons, new TypeReference<List<Book>>() {
            });
            assertEquals(2, books.size());
        }
        catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

       for(ITypeIP ip : IPUtils.listLocalIp()){
           System.out.println(ip.toString());
       }
    }
}

class Book {
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
