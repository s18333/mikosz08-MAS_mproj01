import mas.mziolek.mp1.model.GuildMember;
import mas.mziolek.mp1.model.PlayerLocation;
import mas.mziolek.mp1.model.enums.MemberStatus;

import java.time.LocalDate;


public class Main {

    public static void main(String[] args) {

        GuildMember gm = new GuildMember(1, 15, "mikosz08", "Warlock",
                new PlayerLocation("Ragefire Chasm", 15, -9), LocalDate.of(2020, 1, 1), MemberStatus.ONLINE);

        GuildMember gm2 = new GuildMember(2, 16, "Gutek", "Warrior",
                new PlayerLocation("Ragefire Chasm", 64, -34), LocalDate.of(2020, 2, 2), MemberStatus.ONLINE);

        GuildMember gm3 = new GuildMember(3, 32, "Arnold", "Monk",
                new PlayerLocation("Ragefire Chasm", -415, -9), LocalDate.of(2020, 3, 3), MemberStatus.ONLINE);

        GuildMember gm4 = new GuildMember(4, 99, "Artas", "Priest",
                new PlayerLocation("Ragefire Chasm", 144, -59), LocalDate.of(2020, 4, 4), MemberStatus.ONLINE);

        gm.setMessageOfTheDay("hello");
        gm2.setMessageOfTheDay("lfr 1 person for dungeon xyz");
        gm3.setMessageOfTheDay("wtb 50x materials for this super magic sword");
        gm4.setMessageOfTheDay("ostrze mrozu jest głodne");

        System.out.println(gm);

        gm.setNickname("mikosz09");

        gm.setLevel(56);

        System.out.println(gm.getPlayerClasses());
        gm.addPlayerClass("Rune Walker");
        gm.removePlayerClass("Warlock");

        System.out.println("Message: " + gm.getMessageOfTheDay().orElse("message of the day"));
        gm.setMessageOfTheDay(null);
        System.out.println("Message: " + gm.getMessageOfTheDay().orElse("message of the day"));

        System.out.println(gm.getReputationAwarded());
        gm.setReputationAwarded(500);
        gm.setReputationAwarded(500);
        System.out.println(gm.getReputationAwarded());

        System.out.println(gm.getDateOfAccession());

        System.out.println(gm.getStatus());
        gm.setStatus(MemberStatus.OFFLINE);
        System.out.println(gm.getStatus());

        System.out.println(gm.getDaysOfService());

        System.out.println(gm.getPlayerLocation());
        gm.setPlayerLocation(new PlayerLocation("Stormwind", 123, 321));
        System.out.println(gm.getPlayerLocation());

        gm.setReputationAwarded(123);
        gm2.setReputationAwarded(1323);
        gm3.setReputationAwarded(5);
        gm4.setReputationAwarded(14323);

        System.out.println(gm);
        System.out.println('\n');
        GuildMember.getRankingByLevel().forEach(System.out::println);
        System.out.println('\n');
        GuildMember.getRankingByRepPoints().forEach(System.out::println);
        System.out.println('\n');
        GuildMember.getOnlineMembers().forEach(System.out::println);
    }

}
