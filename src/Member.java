public class Member {
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

    // Getters og Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public int getMemberId() {
        return memberId;
    }

    // Rediger alle oplysninger
    public void updateMember(String name, int age, String contactInfo, boolean isActive,
                             MembershipType membershipType, ActivityType activityType) {
        this.name = name;
        this.age = age;
        this.phoneNumber = contactInfo;
        this.isActive = isActive;
        this.membershipType = membershipType;
        this.activityType = activityType;
    }

    @Override
    public String toString() {
        return "Navn: " + name + "\nAlder: " + age + "\nTlf: " + phoneNumber +
                "\nStatus: " + (isActive ? "Aktiv" : "Passiv") +
                "\nMedlemstype: " + membershipType +
                "\nAktivitetsform: " + activityType + "\nMedlemsID: " + memberId;
    }
}
