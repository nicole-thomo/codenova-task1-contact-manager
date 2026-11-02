/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codenova.taskOne;

import java.time.LocalDate;

/**
 *
 * @author Nicole
 */
public class ContactDetails {
    private int contactId;
    private String contactName;
    private String emailAddress;
    private String phoneNumber;
    private String category;
    private boolean active;
    private LocalDate dateAdded;

    public ContactDetails(int contactId, String contactName, String emailAddress, String phoneNumber, String category, boolean active, LocalDate dateAdded) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.active = active;
        this.dateAdded = dateAdded;
    }

    public ContactDetails(String contactName, String emailAddress, String phoneNumber, String category, boolean active, LocalDate dateAdded) {
        this.contactName = contactName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.active = active;
        this.dateAdded = dateAdded;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}