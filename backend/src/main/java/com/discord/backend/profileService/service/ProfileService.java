package com.discord.backend.profileService.service;

import com.discord.backend.profileService.model.Profile;
import com.discord.backend.profileService.repository.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }



    public static class Person {
        String id;
        String searchingBuddy;
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }
    public Optional<Profile> getProfile(String id) {
        return profileRepository.findById(id);
    }
    public Profile saveOrUpdateProfile(Profile profile) {
        Optional<Profile> existingProfile = profileRepository.findById(profile.getId());
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


    public Profile updateUniversity(String id, String university) {
        Assert.notNull(id, "The given id must not be null");
        Optional<Profile> existingProfile = profileRepository.findById(id);
        if (!existingProfile.isPresent()) {
            throw new EntityNotFoundException("Profile not found for this id :: " + id);
        }
        Profile updatedProfile = existingProfile.get();
        updatedProfile.setUniversity(university);
        return profileRepository.save(updatedProfile);
    }

    public Profile updateBuddy(String id, String buddy) {
        Assert.notNull(id, "The given id must not be null");
        Optional<Profile> existingProfile = profileRepository.findById(id);
        if (!existingProfile.isPresent()) {
            throw new EntityNotFoundException("Profile not found for this id :: " + id);
        }
        Profile updatedProfile = existingProfile.get();
        updatedProfile.setBuddy(buddy);
        return profileRepository.save(updatedProfile);
    }
//
}
