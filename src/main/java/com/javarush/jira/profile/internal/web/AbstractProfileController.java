package com.javarush.jira.profile.internal.web;

import com.javarush.jira.common.util.validation.ValidationUtil;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.ProfileMapper;
import com.javarush.jira.profile.internal.ProfileRepository;
import com.javarush.jira.profile.internal.ProfileUtil;
import com.javarush.jira.profile.internal.model.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractProfileController {
    @Autowired
    protected ProfileMapper profileMapper;
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileTo get(long id) {
        log.info("get {}", id);

        Profile profile = profileRepository.getOrCreate(id);

        // Логирование перед маппингом
        if (profile == null) {
            log.error("Profile not found for user ID: {}", id);
        } else {
            log.info("Loaded profile from repository: {}", profile);
        }

        // Маппинг
        ProfileTo profileTo = profileMapper.toTo(profile);

        // Логирование результата маппинга
        log.info("Mapped profileTo: {}", profileTo);

        return profileTo;
    }

    public void update(ProfileTo profileTo, long id) {
        log.info("update {}, user {}", profileTo, id);
        ValidationUtil.assureIdConsistent(profileTo, id);
        ValidationUtil.assureIdConsistent(profileTo.getContacts(), id);
        ProfileUtil.checkContactsExist(profileTo.getContacts());
        Profile profile = profileMapper.updateFromTo(profileRepository.getOrCreate(profileTo.id()), profileTo);
        profileRepository.save(profile);
    }
}
