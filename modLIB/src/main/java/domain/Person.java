package domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {
    private static final List<Person> PERSON_LIST = new ArrayList<>();

    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String klasse;

    @NonNull
    private String eMail;

    public Person(String firstName, String lastName, String klasse, String eMail) {
        this(null, firstName, lastName, klasse, eMail);
    }

    public Person() {
        this(1, "Max", "Muster", "4BHIF", "email");
    }

    public static boolean addSchueler(Person person) {
        return PERSON_LIST.add(person);
    }

    public static boolean removeSchueler(Person person) {
        return PERSON_LIST.remove(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
