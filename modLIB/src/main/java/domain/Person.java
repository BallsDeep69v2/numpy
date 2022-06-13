package domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Person {
    public static final List<Person> PERSON_LIST = new ArrayList<>();

    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String schoolClass;

    private String eMail;

    public Person(String firstName, String lastName, String klasse, String eMail) {
        this(null, firstName, lastName, klasse, eMail);
    }

    public Person() {
        this(1, "Max", "Muster", "4BHIF", "email");
    }

    public static boolean addPerson(Person person) {
        return PERSON_LIST.add(person);
    }

    public static boolean removePerson(Person person) {
        return PERSON_LIST.remove(person);
    }

    public static void createPersonsFromCSVFile(Path pathToCSVFile) throws IOException {
        Files.readAllLines(pathToCSVFile.toAbsolutePath()).stream().filter(line -> {
            var splitLine = line.split(";");
            if (splitLine.length != 5 && !splitLine[0].isEmpty() && !splitLine[1].isEmpty() && !splitLine[4].isEmpty())
                return false;
            try {
                Integer.parseInt(splitLine[4].substring(0, 1));
            } catch (NumberFormatException exception) {
                return false;
            }
            return true;
        }).map(line -> {
            var splitLine = line.split(";");
            return new Person(splitLine[0], splitLine[1], splitLine[4], null);
        }).forEach(PERSON_LIST::add);
    }

    public static Set<String> getAllSchoolClasses() {
        return PERSON_LIST.stream().map(person -> person.schoolClass).collect(Collectors.toSet());
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

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public StringProperty firstNameProperty() {
        return new SimpleStringProperty(firstName);
    }

    public StringProperty lastNameProperty() {
        return new SimpleStringProperty(lastName);
    }

    public StringProperty schoolClassProperty() {
        return new SimpleStringProperty(schoolClass);
    }

    public StringProperty eMailProperty() {
        return new SimpleStringProperty(eMail);
    }
}
