package com.discord.backend.profileService.controller;

import com.discord.backend.profileService.model.Profile;
import com.discord.backend.profileService.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable String id) {
        Optional<Profile> updatedProfile = profileService.getProfile(id);
        return updatedProfile
                .map(profile -> ResponseEntity.ok(profile))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        Profile createdProfile = profileService.saveOrUpdateProfile(profile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/updateUniversity")
    public ResponseEntity<Profile> updateUniversity(@PathVariable String id, @RequestBody String university) {
        Profile updatedProfile = profileService.updateUniversity(id, university);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @PutMapping("/{id}/updateBuddy")
    public ResponseEntity<Profile> updateBuddy(@PathVariable String id, @RequestBody String buddy) {
        Profile updatedProfile = profileService.updateBuddy(id, buddy);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @GetMapping("/{id}/fetchBuddy/{buddy}/{index}")
    public ResponseEntity<Profile> fetchBuddy (@PathVariable String id, @PathVariable String buddy, @PathVariable int index){
        Profile fetchedBuddy = profileService.fetchBuddy(id, buddy, index);
        return new ResponseEntity<>(fetchedBuddy, HttpStatus.OK);
    }
}
