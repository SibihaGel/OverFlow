package com.javamentor.qa.platform.controllers;

import com.github.database.rider.core.api.dataset.DataSet;
import com.javamentor.qa.platform.TestFoundation;
import org.junit.jupiter.api.Test;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class ResourceAnswerControllerTest extends TestFoundation {
    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {"dataset.ResourceAnswerController.addCommentToAnswer_isOKdataSet/users.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isOKdataSet/roles.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isOKdataSet/answers.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isOKdataSet/questions.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isOKdataSet/reputations.yml"})
    public void addCommentToAnswer_isOK() throws Exception {

        String username = "test2@mail.com";
        String password = "123";
        String comment = "New Comment123";
        Long questionId = 100L;
        Long answerId = 100L;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("http://localhost:8080/api/user/question/{questionId}/answer/{answerId}/comment", questionId, answerId)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .content(comment))

                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastRedactionDate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.persistDate").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.text").value("New Comment123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageLink").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.reputation").value(0));


    }

    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {"dataset.ResourceAnswerController.addCommentToAnswer_isBadRequestdataSet/users.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isBadRequestdataSet/roles.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isBadRequestdataSet/answers.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isBadRequestdataSet/questions.yml",
                    "dataset.ResourceAnswerController.addCommentToAnswer_isBadRequestdataSet/reputations.yml"})
    public void addCommentToAnswer_isBadRequest() throws Exception {

        String username = "test@mail.com";
        String password = "123";
        Long questionId = 100L;
        Long answerId = 100L;
        String error = "  ";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("http://localhost:8080/api/user/question/{questionId}/answer/{answerId}/comment", questionId, answerId)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .content(error))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.status().is(400));
    }


    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/users.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/questions.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/answers.yml",
            })
    void markAnswerToDelete_IsOk_200() throws Exception {

        final String username = "test@mail.com";
        final String password = "123";

        final Long questionId = 100L;
        final Long answerId = 100L;

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/user/question/{questionId}/answer/{answerId}",
                                questionId, answerId)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("Ответ помечен на удаление"));
    }

    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/users.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/questions.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/answers.yml",
            })
    void markAnswerToDelete_NonExistentAnswer_400() throws Exception {

        final String username = "test@mail.com";
        final String password = "123";

        final Long questionId = 100L;
        final Long answerId = 100L;

        final String errorAnswerId = "text";

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/user/question/{questionId}/answer/{answerId}",
                                questionId, errorAnswerId)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/users.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/questions.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/answers.yml",
            })
    void markAnswerToDelete_NonUnauthorized_403() throws Exception {

        final String username = "test@mail.com";
        final String password = "123";

        final Long questionId = 100L;
        final Long answerId = 100L;

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/user/question/{questionId}/answer/{answerId}",
                                questionId, answerId)
                        .header(HttpHeaders.AUTHORIZATION, "invalidToken")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    @Test
    @DataSet(useSequenceFiltering = false, disableConstraints = true,
            value = {
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/users.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/questions.yml",
                    "dataset/ResourceAnswerController-GetAnswerIdDatasets/answers.yml",
            })
    void markAnswerToDelete_NotFound_404() throws Exception {

        final String username = "test@mail.com";
        final String password = "123";

        final Long questionId = 100L;
        final Long answerId = 100L;

        final Long errorQuestionId = 222L;

        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/api/user/question/{questionId}/answer/{answerId}",
                                errorQuestionId, answerId)
                        .header(HttpHeaders.AUTHORIZATION, getToken(username, password))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(404));
    }

}
