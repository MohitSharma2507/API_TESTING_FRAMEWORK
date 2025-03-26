package com.testingacademy.tests.Practice;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJ {

    public static void main(String[] args) {

        String response_name = "MOHIT";
        assertThat(response_name).isNotBlank().isNotNull().isEqualTo("MOHIT");

       List<String> names = Arrays.asList("Mohit","Ankit","Jai");
       assertThat(names).hasSize(3).contains("Mohit").doesNotContain("Rohit");

       Person person = new Person("Mohit", 22);
// ADVANTAGE -   2 Test case we are verifying side by side
       assertThat(person).hasFieldOrProperty("age")
                         .hasFieldOrPropertyWithValue("name", "Mohit");

        Map<String, Integer> ages = new HashMap<>();
        ages.put("Mohit", 22);
        ages.put("Jai", 21);
        ages.put("Ankit", 22);
        ages.put("Rohit", 24);

        assertThat(ages).hasSize(4).doesNotContainKey("Mohit").containsValue(22);
    }

    static class Person{

       private String name;
       private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
