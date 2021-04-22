import mas.mziolek.mp1.model.ExtentManager;
import mas.mziolek.mp1.model.GuildMember;
import mas.mziolek.mp1.model.PlayerLocation;
import mas.mziolek.mp1.model.enums.MemberStatus;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        List<GuildMember> qATesters = createTestMembers();

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        testEqualsAndHashCode(qATesters.get(0), qATesters.get(1), qATesters.get(2));

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        testClassMethods();

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        extensionLoadingSavingTest();

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        getterSetterTest(qATesters.get(0));

        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    private static List<GuildMember> createTestMembers() {
        List<GuildMember> testMembers = new ArrayList<>();

        GuildMember gM1 = new GuildMember(1, 16, "Robert", "Student", new PlayerLocation("Dire Maul", 64, -34), LocalDate.of(2020, 2, 2), MemberStatus.ONLINE);
        gM1.setMessageOfTheDay(null);

        GuildMember gM2 = new GuildMember(1, 16, "Robert", "Student", new PlayerLocation("Dire Maul", 64, -34), LocalDate.of(2020, 2, 2), MemberStatus.ONLINE);

        GuildMember gM3 = new GuildMember(2, 32, "Arnold", "Monk", new PlayerLocation("Schoolmance", -415, -9), LocalDate.of(2020, 3, 3), MemberStatus.AFK);
        gM3.addReputationPoints(750);
        gM3.setMessageOfTheDay("WTB Magic Super Sword");

        GuildMember gM4 = new GuildMember(3, 99, "Artas", "Priest", new PlayerLocation("Ragefire Chasm", 144, -59), LocalDate.of(2020, 4, 4), MemberStatus.OFFLINE);
        gM4.addReputationPoints(240);
        gM4.setMessageOfTheDay("hi! how you guys doin?");

        testMembers.add(gM1);
        testMembers.add(gM2);
        testMembers.add(gM3);
        testMembers.add(gM4);

        System.out.println("[CREATED " + GuildMember.getGuildMemberExtent().size() + " MEMBERS]");
        GuildMember.getGuildMemberExtent().forEach(System.out::println);

        return testMembers;
    }

    private static void testClassMethods() {
        System.out.println("---ClassMethodTEST---");

        System.out.println("Ranking by Level:");
        GuildMember.getRankingByLevel().forEach(System.out::println);

        System.out.println("Online members:");
        GuildMember.getOnlineMembers().forEach(System.out::println);

        System.out.println("Ranking by reputation:");
        GuildMember.getRankingByRepPoints().forEach(System.out::println);
    }

    private static void testEqualsAndHashCode(GuildMember gM1, GuildMember gM2, GuildMember gM3) {

        System.out.println("[---Equals&HashCodeTEST---]\n");

        GuildMember gmTester = gM1;

        System.out.println("(gmTester = gM1)");
        System.out.println("-gmTester & gM1-");
        System.out.println("==  \t\t" + (gmTester == gM1));
        System.out.println("hash\t\t" + (gmTester.hashCode() == gM1.hashCode()));
        System.out.println("equals\t\t" + (gmTester.equals(gM1)));

        System.out.println("\n(other obj with same values)");
        System.out.println("-gmTester & gM2-");
        System.out.println("==  \t\t" + (gmTester == gM2));
        System.out.println("hash\t\t" + (gmTester.hashCode() == gM2.hashCode()));
        System.out.println("equals\t\t" + (gmTester.equals(gM2)));

        System.out.println("\n(other obj with different values)");
        System.out.println("-gmTester & gM3-");
        System.out.println("==  \t\t" + (gmTester == gM3));
        System.out.println("hash\t\t" + (gmTester.hashCode() == gM3.hashCode()));
        System.out.println("equals\t\t" + (gmTester.equals(gM3)));

    }

    private static void extensionLoadingSavingTest() {
        File myObj = new File("save_data/guild_member.ser");
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("---Extent save&load TEST---");
        GuildMember.getGuildMemberExtent().forEach(System.out::println);

        ExtentManager.saveExtent();
        System.out.println("Saved " + GuildMember.getGuildMemberExtent().size() + " members.");

        GuildMember.clearExtension();
        System.out.println("Cleared, now we have " + GuildMember.getGuildMemberExtent().size() + " members.");
        System.out.println(GuildMember.getGuildMemberExtent());

        ExtentManager.loadExtent();
        System.out.println("Loaded " + GuildMember.getGuildMemberExtent().size() + " members.");

        GuildMember.getGuildMemberExtent().forEach(System.out::println);
    }

    private static void getterSetterTest(GuildMember qualityAssurance) {
        System.out.println("---OtherTESTS---");

        GuildMember qA = qualityAssurance;

        qA.setNickname("adam małysz");
        qA.setDateOfAccession(LocalDate.now());
        qA.setLevel(15);
        qA.setId(9);
        qA.setStatus(MemberStatus.AFK);
        qA.setPlayerLocation(new PlayerLocation("Sweet Home", new Point(12, 34)));
        qA.setMessageOfTheDay("hello there");
        qA.addPlayerClass("Student");

        /*
        qA.setNickname("");
        qA.setNickname(null);
        qA.setDateOfAccession(null);
        qA.setLevel(0);
        qA.setId(-9);
        qA.setStatus(null);
        qA.setPlayerLocation(null);
        qA.setMessageOfTheDay("");
        */

        System.out.println(qA.getDaysOfService());
        System.out.println(qA.getDateOfAccession());
        System.out.println(qA.getStatus());
        System.out.println(qA.getDaysOfService());
        System.out.println(qA.getMessageOfTheDay().orElse("No Message"));
        System.out.println(qA.getPlayerLocation());
        System.out.println(qA.getNickname());
        System.out.println(qA.getLevel());
        System.out.println(qA.getId());
        System.out.println(qA.getPlayerClasses());
    }

}
