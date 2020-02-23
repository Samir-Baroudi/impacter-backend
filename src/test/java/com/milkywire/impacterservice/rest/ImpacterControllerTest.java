package com.milkywire.impacterservice.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.milkywire.impacterservice.dto.ImpacterDto;
import com.milkywire.impacterservice.service.ImpacterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImpacterControllerTest {

    @InjectMocks
    ImpacterController cut;

    @Mock
    ImpacterService impacterService;

    @Test
    public void testToRetrieveBasiqScoresForCandidateInProject() {
        final String expectedMessage = "Hello Samir";

        when(impacterService.getProperMessage()).thenReturn(expectedMessage);

        ImpacterDto dto = cut.getMessage();
        String actualMessage = dto.getMessage();

        verify(impacterService).getProperMessage();

        assertEquals("Testing return value from controller", expectedMessage, actualMessage);
    }
}
