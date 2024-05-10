package com.theplanners.pkiclassroomrescheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.theplanners.pkiclassroomrescheduler.system.Controllers.ClearResultsController;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Schedule;
import com.theplanners.pkiclassroomrescheduler.system.Entities.Section;

@SpringBootTest
public class ClearResultsTest {
    @Test
    public void testClearResults() {
        Schedule mockSchedule = mock(Schedule.class);
        ClearResultsController controller = new ClearResultsController(mockSchedule, null);

        ArrayList<Section> sections = new ArrayList<>();
        Section section1 = mock(Section.class);
        Section section2 = mock(Section.class);
        sections.add(section1);
        sections.add(section2);

        when(mockSchedule.returnCSSchedule()).thenReturn(sections);

        ArrayList<Section> result = controller.clearResults();

        verify(section1, times(1)).resetRoom();
        verify(section2, times(1)).resetRoom();

        assertEquals(sections, result);
    }
}
