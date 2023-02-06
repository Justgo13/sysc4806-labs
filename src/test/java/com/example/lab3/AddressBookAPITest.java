package com.example.lab3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookAPITest {
    @Autowired
    AddressBookRepository repository;

    @Autowired
    private MockMvc mockMvc;

    AddressBook a;
    BuddyInfo b;
    BuddyInfo b2;

    @BeforeEach
    public void setUp() throws Exception {
        a = new AddressBook();
        b = new BuddyInfo("Jason", "222-222-2222");
        b2 = new BuddyInfo("John", "222-222-2222");

        this.mockMvc.perform(post("/addressBook")
                .content(asJsonString(new AddressBook()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(post("/buddy")
                .content(asJsonString(new BuddyInfo("Jason", "222-222-2222")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(post("/buddy")
                .content(asJsonString(new BuddyInfo("Jason2", "222-222-2222")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void test_add() throws Exception {
        this.mockMvc.perform(put("/addressBook/1/buddyInfo")
                .content("http://localhost:8080/buddy/1")
                .contentType("text/uri-list")
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/addressBook/1/buddyInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\n" +
                        "  \"_embedded\" : {\n" +
                        "    \"buddyInfoes\" : [ {\n" +
                        "      \"name\" : \"Jason\",\n" +
                        "      \"telephone\" : \"222-222-2222\",\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/buddy/1\"\n" +
                        "        },\n" +
                        "        \"buddyInfo\" : {\n" +
                        "          \"href\" : \"http://localhost/buddy/1\"\n" +
                        "        },\n" +
                        "        \"addressBook\" : {\n" +
                        "          \"href\" : \"http://localhost/buddy/1/addressBook\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"_links\" : {\n" +
                        "    \"self\" : {\n" +
                        "      \"href\" : \"http://localhost/addressBook/1/buddyInfo\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"));
    }

    @Test
    public void test_delete() throws Exception {
        this.mockMvc.perform(put("/addressBook/1/buddyInfo")
                .content("http://localhost:8080/buddy/1")
                .contentType("text/uri-list")
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(put("/addressBook/1/buddyInfo")
                .content("http://localhost:8080/buddy/2")
                .contentType("text/uri-list")
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(delete("/buddy/1/addressBook")
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(get("/addressBook/1/buddyInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(content().json("{\n" +
                        "  \"_embedded\" : {\n" +
                        "    \"buddyInfoes\" : [ {\n" +
                        "      \"name\" : \"Jason2\",\n" +
                        "      \"telephone\" : \"222-222-2222\",\n" +
                        "      \"_links\" : {\n" +
                        "        \"self\" : {\n" +
                        "          \"href\" : \"http://localhost/buddy/2\"\n" +
                        "        },\n" +
                        "        \"buddyInfo\" : {\n" +
                        "          \"href\" : \"http://localhost/buddy/2\"\n" +
                        "        },\n" +
                        "        \"addressBook\" : {\n" +
                        "          \"href\" : \"http://localhost/buddy/2/addressBook\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    } ]\n" +
                        "  },\n" +
                        "  \"_links\" : {\n" +
                        "    \"self\" : {\n" +
                        "      \"href\" : \"http://localhost/addressBook/1/buddyInfo\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void tearDown() {
        a = null;
        b = null;
        b2 = null;
    }
}
