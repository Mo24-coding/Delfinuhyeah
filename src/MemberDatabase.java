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
                    newInfo.getContactInfo(),
                    newInfo.isActive(),
                    newInfo.getMembershipType(),
                    newInfo.getActivityType()
            );
        }
    }

    public ArrayList<Member> getAllMembers() {
        return members;
    }
}
