package mas.mziolek.mp1.model;

import java.io.*;
import java.util.List;

public class ExtentManager {
    private final static String EXTENT_FILE_PATH = "save_data/guild_member.ser";

    public static void saveExtent() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(EXTENT_FILE_PATH))) {
            output.writeObject(GuildMember.getGuildMemberExtent());
            output.writeObject(Guild.getGuildExtent());
            output.writeObject(ApplicationForm.getApplicationFormExtent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadExtent() {
        try (ObjectInputStream output = new ObjectInputStream(new FileInputStream(EXTENT_FILE_PATH))) {
            GuildMember.setGuildMembersExtent((List<GuildMember>) output.readObject());
            Guild.setGuildExtent((List<Guild>) output.readObject());
            ApplicationForm.setApplicationFormExtent((List<ApplicationForm>) output.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
