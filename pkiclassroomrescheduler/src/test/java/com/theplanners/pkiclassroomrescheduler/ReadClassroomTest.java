package com.theplanners.pkiclassroomrescheduler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.theplanners.pkiclassroomrescheduler.system.Entities.ClassroomList;
import com.theplanners.pkiclassroomrescheduler.system.Utilites.ReadClassroom;

@SpringBootTest
public class ReadClassroomTest {

    @Test
    public void testReadClassroomCSV() {

        ClassroomList classrooms = new ClassroomList();
        ReadClassroom.readClassroomCSV(classrooms);
        assertEquals(classrooms.returnClassrooms().size(), 31);
    }
}
