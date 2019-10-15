package com.javagda25.restapi;

import com.javagda25.restapi.model.Student;
import com.javagda25.restapi.model.dto.CreateStudentRequest;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class RestapiApplicationTests {

    /**
     * Klasa odpowiada za wywoływanie zapytań REST.
     * <p>
     * Adresuje zawsze adres naszego serwera (localhost) i jeśli zdefiniowany jest poprawnie port
     * w konfiguracji (np. DEFINED_PORT) to adresuje go na wybranych przez nas mappingach,
     * <p>
     * Przykład:
     * <p>
     * testRestTemplate.exchange("/student", HttpMethod.GET...) adresuje "localhost:8080/student"
     */

    @Autowired
    private TestRestTemplate testRestTemplate;
    private final String STUDENT_NAME = "Pablo";
    private final String STUDENT_SURNAME = "Picasso";
    private final boolean STUDENT_ALIVE = true;
    private final LocalDate STUDENT_DATE = LocalDate.of(2000, 1, 10);

    @Test
    public void test_1_add_student() {
        CreateStudentRequest request = new CreateStudentRequest(null,
                STUDENT_NAME,
                STUDENT_SURNAME,
                STUDENT_ALIVE,
                STUDENT_DATE);

        HttpEntity<CreateStudentRequest> httpEntity = new HttpEntity<>(request);

        ResponseEntity<Long> response =
                testRestTemplate.exchange("/student", HttpMethod.PUT, httpEntity,
                        new ParameterizedTypeReference<Long>() {
                        });

        Long createdId = response.getBody();
        Assert.assertNotNull(createdId);
    }

    @Test
    public void test_2_check_added_student() {
        ResponseEntity<List<Student>> response =
                testRestTemplate.exchange("/student", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Student>>() {
                        });

        List<Student> studentList = response.getBody();
        Assert.assertEquals(1, studentList.size());
        Assert.assertFalse(studentList.isEmpty());

        Student retrieved = studentList.get(0);

        Assert.assertEquals(STUDENT_NAME, retrieved.getName());
        Assert.assertEquals(STUDENT_SURNAME, retrieved.getSurname());
        Assert.assertEquals(STUDENT_ALIVE, retrieved.isAlive());
        Assert.assertEquals(STUDENT_DATE, retrieved.getDateOfBirth());
        Integer ageSinceBirthYear = LocalDate.now().getYear() - STUDENT_DATE.getYear();

        Assert.assertEquals(ageSinceBirthYear, retrieved.getAge());
    }

//    @Test
//    public void test_3_delete_student() {
//        ResponseEntity<List<Student>> response =
//                testRestTemplate.exchange("/student", HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<Student>>() {
//                        });
//
//        List<Student> studentList = response.getBody();
//        Assert.assertEquals(1, studentList.size());
//        Assert.assertFalse(studentList.isEmpty());
//
//        Student retrieved = studentList.get(0);
//
//        Assert.assertEquals(STUDENT_NAME, retrieved.getName());
//        Assert.assertEquals(STUDENT_SURNAME, retrieved.getSurname());
//        Assert.assertEquals(STUDENT_ALIVE, retrieved.isAlive());
//        Assert.assertEquals(STUDENT_DATE, retrieved.getDateOfBirth());
//        Integer ageSinceBirthYear = LocalDate.now().getYear() - STUDENT_DATE.getYear();
//
//        Assert.assertEquals(ageSinceBirthYear, retrieved.getAge());
//    }


}
