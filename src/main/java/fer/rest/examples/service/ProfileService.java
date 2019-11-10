package fer.rest.examples.service;

import fer.rest.examples.database.Database;
import fer.rest.examples.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = Database.getProfiles();

    public List<Profile> getAllProfiles(){
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName){
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        return profiles.put(profile.getProfileName(), profile);
    }

    public Profile updateProfile(Profile profile){
        if(profile.getId()<=0){
            return null;
        }
        return profiles.put(profile.getProfileName(), profile);
    }

    public Profile removeProfile(String profileName){
        return profiles.remove(profileName);
    }
}
