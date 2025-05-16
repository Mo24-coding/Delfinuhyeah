package data;
import model.Member;
import java.util.ArrayList;

public class MemberDatabase {
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        members.remove(member);
    }

    public Member findMemberByName(String name) {
        for (Member m : members) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public void editMember(String name, Member newInfo) {
        Member member = findMemberByName(name);
        if (member != null) {
            member.updateMember(
                    newInfo.getName(),
                    newInfo.getAge(),
                    newInfo.getPhoneNumber(),
                    newInfo.isActive(),
                    newInfo.getMembershipType(),
                    newInfo.getActivityType()
            );
        }
    }
    public double calculateTotalExpectedIncome() {
        double total = 0;
        for (Member m : members) {
            total += m.calculateFee();
        }
        return total;
    }


    public void listMembers() {
        System.out.println("\n--- Alle medlemmer ---");
        for (Member m : getAllMembers()) {
            System.out.println(m);
            System.out.println("-----------------------");
        }
    }

    public ArrayList<Member> getAllMembers() {
        return members;
    }
}
