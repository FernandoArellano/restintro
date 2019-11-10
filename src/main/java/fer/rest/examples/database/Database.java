package fer.rest.examples.database;

import fer.rest.examples.model.Message;
import fer.rest.examples.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static{
        loadMessages();
        loadProfiles();
    }

    public static Map<Long, Message> getMessages(){
        return messages;
    }

    public static Map<String, Profile> getProfiles(){
        return  profiles;
    }

    public static void loadMessages(){
        messages.put(1L, new Message(1L, "Hello world", "Koushik"));
        messages.put(2L, new Message(2L, "Hello jersey world", "Fer"));
    }

    public static void loadProfiles(){
        profiles.put("fer10", new Profile(1L,"fer10", "fernando", "arellano"));
        profiles.put("fer11", new Profile(2L,"fer11", "fernando2", "arellano2"));
    }
}
