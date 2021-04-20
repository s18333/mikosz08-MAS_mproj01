import mas.mziolek.mp1.model.GuildMember;
import mas.mziolek.mp1.model.PlayerLocation;
import mas.mziolek.mp1.model.enums.MemberStatus;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

public class Main {

    public static void main(String[] args) {

        GuildMember gm = new GuildMember(1, 15, "mikosz08", "Warlock",
                new PlayerLocation("Ragefire Chasm", 15, -9), LocalDate.of(2020, 1, 1), MemberStatus.ONLINE);

        System.out.println(gm);

        gm.setNickname("mikosz09");

        gm.setLevel(1);

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

        System.out.println(gm);
    }

}
