package com.gmail.derynem.finalcontrolwork;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.derynem.finalcontrolwork.service.model.DiscountDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ApiDiscountIntegrationTest {
    private final static String SUPER_USER_NAME = "ROOT";
    private final static String CUSTOMER_USER = "CUSTOMER";
    private ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails(value = SUPER_USER_NAME)
    public void shouldAddDiscountForUser() throws Exception {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.getUser().setId(2L);
        discountDTO.setName("test");
        discountDTO.setDate("2022-06-05T19:43:55.146");
        discountDTO.setPercent("10");
        mvc.perform(post("/api/v1/root/discount")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(discountDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails(value = CUSTOMER_USER)
    public void shouldGetListOfDiscounts() throws Exception {
        mvc.perform(get("/api/v1/discounts"))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails(value = SUPER_USER_NAME)
    public void shouldReturn404IfNotFound() throws Exception {
        mvc.perform(delete("/api/v1/root/discount/99999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = SUPER_USER_NAME)
    public void shouldReturnBadRequestIfDiscountNotValid() throws Exception {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.getUser().setId(2L);
        discountDTO.setName("test");
        discountDTO.setDate("2019-06-05T19:43:55.146");
        discountDTO.setPercent("10");
        mvc.perform(post("/api/v1/root/discount")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(discountDTO)))
                .andExpect(status().isBadRequest());
    }
}