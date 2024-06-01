package com.javamentor.qa.platform.controllers;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.TestFoundation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class ResourceUserControllerTest extends TestFoundation {

    private String username;
    private String password;

    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {"dataset/ResourceUserController/getUserDtoByIdDatasets/users.yml",
                    "dataset/ResourceUserController/getUserDtoByIdDatasets/roles.yml",
                    "dataset/ResourceUserController/getUserDtoByIdDatasets/answers.yml",
                    "dataset/ResourceUserController/getUserDtoByIdDatasets/questions.yml",
                    "dataset/ResourceUserController/getUserDtoByIdDatasets/reputations.yml"})
    public void getUserDtoById() throws Exception {

        username = "test@mail.com";
        password = "123";

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/user/{id}", 100)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName", Matchers.is("qwerty qwerty")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reputation", Matchers.is(800)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.votes", Matchers.is(2)));

//
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/user/{id}", 102)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }


}
