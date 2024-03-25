package com.discord.backend.profileService.controller;

import com.discord.backend.profileService.model.Profile;
import com.discord.backend.profileService.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        return profiles.size()==0 ? ResponseEntity.notFound().build() : ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable String id) {
        Optional<Profile> fetchedProfile = profileService.getProfile(id);
        return fetchedProfile
                .map(profile -> ResponseEntity.ok(profile))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody Profile profile) {
        Profile createdProfile = profileService.saveOrUpdateProfile(profile);
        if (createdProfile == null) {
            // Return an error message in the response body
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Failed to create or update the profile.");
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/updateUniversity")
    public ResponseEntity<?> updateUniversity(@PathVariable String id, @RequestBody String university) {
        Profile updatedProfile = profileService.updateUniversity(id, university);
        if (updatedProfile == null) {
            // Return an error message in the response body
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Failed to update the university field in profile.");
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedProfile, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/updateBuddy")
    public ResponseEntity<?> updateBuddy(@PathVariable String id, @RequestBody String buddy) {
        Profile updatedProfile = profileService.updateBuddy(id, buddy);
        if (updatedProfile == null) {
            // Return an error message in the response body
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Failed to update the buddy field in profile.");
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/fetchBuddy/{buddy}/{index}")
    public ResponseEntity<?> fetchBuddy (@PathVariable String id, @PathVariable String buddy, @PathVariable int index){
        Profile fetchedBuddy = profileService.fetchBuddy(id, buddy, index);
        if (fetchedBuddy == null) {
            // Return an error message in the response body
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", "Failed to fetch buddy with this index or id in profile.");
            return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(fetchedBuddy, HttpStatus.OK);
    }
}
