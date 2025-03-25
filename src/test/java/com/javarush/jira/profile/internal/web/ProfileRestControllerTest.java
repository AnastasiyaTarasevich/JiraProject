package com.javarush.jira.profile.internal.web;

import com.javarush.jira.AbstractControllerTest;
import com.javarush.jira.bugtracking.sprint.Sprint;
import com.javarush.jira.bugtracking.task.TaskController;

import com.javarush.jira.common.util.JsonUtil;
import com.javarush.jira.login.internal.web.UserController;
import com.javarush.jira.profile.ProfileTo;
import com.javarush.jira.profile.internal.ProfileMapper;
import com.javarush.jira.profile.internal.ProfileRepository;
import com.javarush.jira.profile.internal.model.Profile;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.javarush.jira.common.util.JsonUtil.writeValue;
import static com.javarush.jira.login.internal.web.UserTestData.*;

import static com.javarush.jira.profile.internal.web.ProfileRestController.REST_URL;
import static com.javarush.jira.profile.internal.web.ProfileTestData.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProfileRestControllerTest extends AbstractControllerTest {


    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    ProfileMapper mapper;

    @Test
    void getUnAuth() throws Exception {
        perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Test
//    @WithUserDetails(value = "user@gmail.com")
//    void getUsers() throws Exception {
//        // Ваш тестовый код
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
//    }



//    @Test
//    void testListProfileForeignKeys() {
//        String sql = """
//            SELECT FK.CONSTRAINT_NAME,
//                   FK.TABLE_NAME,
//                   FK.COLUMN_NAME,
//                   PK.TABLE_NAME AS REFERENCED_TABLE_NAME,
//                   PK.COLUMN_NAME AS REFERENCED_COLUMN_NAME
//            FROM INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS RC
//            JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE FK
//                ON FK.CONSTRAINT_NAME = RC.CONSTRAINT_NAME
//            JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE PK
//                ON PK.CONSTRAINT_NAME = RC.UNIQUE_CONSTRAINT_NAME
//            WHERE FK.TABLE_NAME = 'PROFILE'
//        """;
//
//        List<Map<String, Object>> keys = jdbcTemplate.queryForList(sql);
//
//        keys.forEach(System.out::println);
//    }
//@Test
//public void testUserProfiles() {
//    String sql = "SELECT u.ID, u.DISPLAY_NAME, p.LAST_LOGIN " +
//            "FROM USERS u " +
//            "INNER JOIN PROFILE p ON u.ID = p.ID";
//
//    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
//
//    // Печатаем результаты
//    rows.forEach(row -> {
//        System.out.println("User ID: " + row.get("ID"));
//        System.out.println("Display Name: " + row.get("DISPLAY_NAME"));
//        System.out.println("Last Login: " + row.get("LAST_LOGIN"));
//    });
//
//    // Пример проверки данных, например, для первого пользователя
//    assertFalse(rows.isEmpty(), "No users found in the database");
//    assertNotNull(rows.get(0).get("ID"), "User ID should not be null");
//    assertNotNull(rows.get(0).get("DISPLAY_NAME"), "Display name should not be null");
//}

//    @Test
//    @WithUserDetails(value = GUEST_MAIL)
//    public void getEmptyProfileReturnedForGuest() throws Exception {
//        perform(MockMvcRequestBuilders.get(REST_URL))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(PROFILE_TO_MATCHER.contentJson(GUEST_PROFILE_EMPTY_TO));
//    }
//
//    @Test
//    @WithUserDetails(value = USER_MAIL)
//    public void getNewProfileFromDTO() throws Exception {
//        ProfileTo profileTo = ProfileTestData.getNewTo();
//        ResultActions action = perform(MockMvcRequestBuilders.put(REST_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(writeValue(profileTo)))
//                .andDo(print())
//                .andExpect(status().isCreated());
//        Profile created = PROFILE_MATCHER.readFromJson(action);
//        Profile newProfile = getNew(created.id());
//        PROFILE_MATCHER.assertMatch(created, newProfile);
//        PROFILE_MATCHER.assertMatch(profileRepository.getExisted(created.id()), newProfile);
//    }

//FIXME проблема с ответом от репозитория (не находит профили для пользователей) java.lang.AssertionError: Content type not set




}