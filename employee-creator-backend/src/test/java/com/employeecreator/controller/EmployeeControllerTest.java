
package com.employeecreator.controller;

import com.employeecreator.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void createEmployee_shouldReturn422_whenValidationFails()
            throws Exception {

        String invalidEmployee = """
                {
                    "firstName": "",
                    "middleName": "",
                    "lastName": "",
                    "email": "invalid-email",
                    "mobile": "1234567890",
                    "residentialAddress": "",
                    "contractType": null,
                    "startDate": null,
                    "finishedDate": null,
                    "ongoing": false,
                    "employmentType": null,
                    "hoursPerWeek": 0
                }
                """;

        mockMvc.perform(
                post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidEmployee))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.message").value("Validation Failed"))
                .andExpect(jsonPath("$.path").value("/api/employees"))
                .andExpect(jsonPath("$.details.firstName").exists())
                .andExpect(jsonPath("$.details.lastName").exists())
                .andExpect(jsonPath("$.details.email").exists())
                .andExpect(jsonPath("$.details.mobile").exists())
                .andExpect(jsonPath("$.details.residentialAddress").exists())
                .andExpect(jsonPath("$.details.contractType").exists())
                .andExpect(jsonPath("$.details.startDate").exists())
                .andExpect(jsonPath("$.details.employmentType").exists())
                .andExpect(jsonPath("$.details.hoursPerWeek").exists());
    }
}
