package uk.co.heliosx;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import uk.co.heliosx.domain.ConsultationService;

@WebMvcTest
class ConsultationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ConsultationController consultationController;

    @MockBean
    ConsultationService consultationService;

    @Test
    void getQuestions() throws Exception {
        mockMvc.perform(get("/consultation/questions")
                        .queryParam("condition-id", "allergy-genovian-pear"))
                .andExpect(status().isOk());

        verify(consultationService).getQuestions("allergy-genovian-pear");
    }

    // Tests for submitAnswers:
    // 1. Test that parameters are passed correctly in consultationService.submitAnswers
    // 2. Tests for authentication (unauthorized, authorized with different customer id)
}