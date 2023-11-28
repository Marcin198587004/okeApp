package pl.kaminski.okeapp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Contact {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
@NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String pesel;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String emailAdress;
    @NotEmpty
private String qualification;
    @NotEmpty
private String partOfQualification;

    private UUID uuid;
    private Date localDate;
    private Time localTime;
    @NotEmpty
private int randomNumber;

    public static List<Contact> getForm() {
        return null;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getLocaldate() {
        return localDate;
    }

    public void setLocaldate(Date localdate) {
        this.localDate = localdate;
    }

    public Time getLocalTime() {
        return localTime;
    }

    public void setLocalTime(Time localTime) {
        this.localTime = localTime;
    }



    public Contact() {
    }

    public Contact(String firstName, String lastName, String pesel, String phoneNumber, String emailAdress, String qualification, String partOfQualification,
                   UUID uuid, Date localdate, Time localTime, int randomNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.emailAdress = emailAdress;
        this.qualification = qualification;
        this.partOfQualification = partOfQualification;
        this.uuid =uuid;
        this.localDate=localdate;
        this.localTime=localTime;
        this.randomNumber= randomNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastname(String LastName) {
        this.lastName = LastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPartOfQualification() {
        return partOfQualification;
    }

    public void setPartOfQualification(String partOfQualification) {
        this.partOfQualification = partOfQualification;
    }

    @Override
    public String toString() {
        return "Form{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel=" + pesel +
                ", phoneNumber=" + phoneNumber +
                ", emailAdress='" + emailAdress + '\'' +
                ", qualification='" + qualification + '\'' +
                ", partOfQualification='" + partOfQualification + '\'' +
                ", uuid='" + uuid + '\'' +
                ", localDate='" + localDate + '\'' +
                ", localTime='" + localTime + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                '}';
    }
    public void localDate() {
        LocalDate localDate = LocalDate.now();


    }

}
