package com.discord.backend.profileService.service;

import com.discord.backend.profileService.model.Profile;
import com.discord.backend.profileService.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public boolean isValidId(String id) {
        if(id.length() != 18){
            return false;
        }
        char[] charArr = id.toCharArray();
        for(int i = 0; i<charArr.length; i++){
            if(!Character.isDigit(charArr[i])){
                return false;
            }
        }
        return true;
    }

        @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
    public Optional<Profile> getProfile(String id) {
        return profileRepository.findById(id);
    }
    public Profile saveOrUpdateProfile(Profile profile) {
        String profileId = profile.getId();
        if(isValidId(profileId)) {
            Optional<Profile> existingProfile = profileRepository.findById(profileId);
            if (((Optional<?>) existingProfile).isPresent()) {
                Profile updatedProfile = existingProfile.get();
                updatedProfile.setName(profile.getName());
                updatedProfile.setAge(profile.getAge());
                updatedProfile.setDegree(profile.getDegree());
                updatedProfile.setAboutMe(profile.getAboutMe());
                return profileRepository.save(updatedProfile); // Update
            } else {
                return profileRepository.save(profile); // Insert
            }
        }
        System.out.println("Failed to save this id. ID doesn't have discord format");
        return null;
      }

    public Profile updateUniversity(String id, String university) {
        if (isValidId(id)) {
            Assert.notNull(id, "The given id must not be null");
            Optional<Profile> existingProfile = profileRepository.findById(id);
            if (!existingProfile.isPresent()) {
                // Profile not found for this id, create a new one
                Profile newProfile = new Profile();
                newProfile.setId(id);
                newProfile.setUniversity(university);
                // Set default values for other fields
                newProfile.setName("Unknown");
                newProfile.setAge("");
                newProfile.setBuddy("");
                newProfile.setDegree("");
                newProfile.setAboutMe("");
                // Save the new profile to the repository
                return profileRepository.save(newProfile);
            }
            // Profile found, update the university field
            Profile updatedProfile = existingProfile.get();
            updatedProfile.setUniversity(university);
            return profileRepository.save(updatedProfile);
        }
        else {
            System.out.println("Failed to save this id. ID doesn't have discord format");
            return null;
        }
    }

    public Profile updateBuddy(String id, String buddy) {
        if (isValidId(id)){
            Assert.notNull(id, "The given id must not be null");
            Optional<Profile> existingProfile = profileRepository.findById(id);
            if (!existingProfile.isPresent()) {
                // Profile not found for this id, create a new one
                Profile newProfile = new Profile();
                newProfile.setId(id);
                newProfile.setBuddy(buddy);
                // Set default values for other fields
                newProfile.setName("Unknown");
                newProfile.setAge("");
                newProfile.setUniversity("");
                newProfile.setDegree("");
                newProfile.setAboutMe("");
                // Save the new profile to the repository
                return profileRepository.save(newProfile);
            }
            // Profile found, update the buddy field
            Profile updatedProfile  = existingProfile.get();
            updatedProfile.setBuddy(buddy);
            return profileRepository.save(updatedProfile);
        }
        else {
            System.out.println("Failed to save this id. ID doesn't have discord format");
            return null;
        }
    }

    public Profile fetchBuddy(String id, String buddy, int index) {
        if(isValidId(id)){
            List<Profile> buddies = profileRepository.findByIdNot(id).stream()
                    .filter(profile -> buddy.equals(profile.getBuddy()))
                    .collect(Collectors.toList());
            if (index >= 0 && index < buddies.size()) {
                return buddies.get(index);
            }
            return null;
        }
        return null;
    }
}
