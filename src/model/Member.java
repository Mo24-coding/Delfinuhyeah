package model;

import java.io.Serializable;

public class Member implements Serializable {
    private String name;
    private int age;
    private String phoneNumber;
    private boolean isActive; // true = aktiv, false = passiv
    private MembershipType membershipType; // JUNIOR/SENIOR
    private ActivityType activityType;     // MOTIONIST/KONKURRENCE
    private static int idCounter = 1;
    private int memberId;

    public enum MembershipType {
        JUNIOR,
        SENIOR
    }

    public enum ActivityType {
        MOTIONIST,
        KONKURRENCE
    }
    public double calculateFee() {
        Subscription.MembershipType subType = isActive() ? Subscription.MembershipType.AKTIV : Subscription.MembershipType.PASSIV;
        Subscription subscription = new Subscription(subType, age);
        return subscription.calculateFee();
    }


    // Constructor
    public Member(String name, int age, String phoneNumber, boolean isActive,
                  MembershipType membershipType, ActivityType activityType) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.membershipType = membershipType;
        this.activityType = activityType;
        this.memberId = idCounter++;
    }

    // Beregn årligt kontingent
    public double calculateFee() {
        Subscription.MembershipType subType = isActive ?
                Subscription.MembershipType.AKTIV :
                Subscription.MembershipType.PASSIV;
        Subscription subscription = new Subscription(subType, age);
        return subscription.calculateFee();
    }

    // Getters og Setters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPhoneNumber() { return phoneNumber; }
    public boolean isActive() { return isActive; }
    public MembershipType getMembershipType() { return membershipType; }
    public ActivityType getActivityType() { return activityType; }
    public int getMemberId() { return memberId; }

    // Opdater alle medlemsoplysninger
    public void updateMember(String name, int age, String phoneNumber,
                             boolean isActive, MembershipType membershipType,
                             ActivityType activityType) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.membershipType = membershipType;
        this.activityType = activityType;
    }

    @Override
    public String toString() {
        return "Navn: " + name +
                "\nAlder: " + age +
                "\nTlf: " + phoneNumber +
                "\nStatus: " + (isActive ? "Aktiv" : "Passiv") +
                "\nMedlemstype: " + membershipType +
                "\nAktivitetsform: " + activityType +
                "\nMedlemsID: " + memberId;
    }
}
