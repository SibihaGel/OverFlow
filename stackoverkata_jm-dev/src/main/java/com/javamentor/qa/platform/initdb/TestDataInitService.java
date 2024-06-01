package com.javamentor.qa.platform.initdb;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.user.Role;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestDataInitService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TagService tagService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ReputationService reputationService;

    public TestDataInitService(PasswordEncoder passwordEncoder, UserService userService,
                               TagService tagService, QuestionService questionService,
                               AnswerService answerService, ReputationService reputationService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.tagService = tagService;
        this.questionService = questionService;
        this.answerService = answerService;
        this.reputationService = reputationService;
    }

    @Transactional
    public void createEntity() {
        createRoles();
        createUsers();
        createTags();
        createQuestions();
        createAnswers();
        createReputations();

        userService.persistAll(users);
        tagService.persistAll(tags);
        questionService.persistAll(questions);
        answerService.persistAll(answers);
        reputationService.persistAll(reputations);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    List<User> users = new ArrayList<>();

    private void createUsers() {
        User user1 = new User();
        user1.setEmail("sib@mail.list");
        user1.setPassword(passwordEncoder.encode("Casella"));
        user1.setFullName("User 1");
        user1.setPersistDateTime(LocalDateTime.now());
        user1.setCity("City 1");
        user1.setLinkSite("https://example.com/user1");
        user1.setLinkGitHub("https://github.com/user1");
        user1.setLinkVk("https://vk.com/user1");
        user1.setAbout("About user 1");
        user1.setImageLink("https://example.com/user1.jpg");
        user1.setLastUpdateDateTime(LocalDateTime.now());
        user1.setNickname("user1");
        user1.setRole(roles.get(0));
        users.add(user1);

        User user2 = new User();
        user2.setEmail("user2@example.com");
        user2.setPassword(passwordEncoder.encode("password2"));
        user2.setFullName("User 2");
        user2.setPersistDateTime(LocalDateTime.now());
        user2.setCity("City 2");
        user2.setLinkSite("https://example.com/user2");
        user2.setLinkGitHub("https://github.com/user2");
        user2.setLinkVk("https://vk.com/user2");
        user2.setAbout("About user 2");
        user2.setImageLink("https://example.com/user2.jpg");
        user2.setLastUpdateDateTime(LocalDateTime.now());
        user2.setNickname("user2");
        user2.setRole(roles.get(1));
        users.add(user2);
    }

    List<Role> roles = new ArrayList<>();

    private void createRoles() {

        Role role1 = new Role();
        role1.setName("ROLE_USER");
        roles.add(role1);

        Role role2 = new Role();
        role2.setName("ROLE_ADMIN");
        roles.add(role2);
    }

    List<Tag> tags = new ArrayList<>();

    private void createTags() {
        Tag tag1 = new Tag();
        tag1.setName("Java");
        tag1.setDescription("Java programming language");
        tags.add(tag1);

        Tag tag2 = new Tag();
        tag2.setName("Spring");
        tag2.setDescription("Spring Framework for Java");
        tags.add(tag2);
    }

    List<Question> questions = new ArrayList<>();

    private void createQuestions() {

        Question question1 = new Question();
        question1.setTitle("Вопрос1");
        question1.setDescription("Как дела?");
        question1.setUser(users.get(0));
        question1.setTags(tags);
        questions.add(question1);

        Question question2 = new Question();
        question2.setTitle("Вопрос2");
        question2.setDescription("Как настроение?");
        question2.setUser(users.get(1));
        question2.setTags(tags);
        questions.add(question2);
    }

    List<Answer> answers = new ArrayList<>();

    private void createAnswers() {
        Answer answer1 = new Answer();
        answer1.setHtmlBody("Ответ 1");
        answer1.setUser(users.get(1));
        answer1.setQuestion(questions.get(0));
        answer1.setIsDeleted(false);
        answer1.setIsHelpful(false);
        answer1.setIsDeletedByModerator(false);
        answers.add(answer1);

        Answer answer2 = new Answer();
        answer2.setHtmlBody("Ответ 2");
        answer2.setUser(users.get(0));
        answer2.setQuestion(questions.get(1));
        answer2.setIsDeleted(false);
        answer2.setIsHelpful(true);
        answer2.setIsDeletedByModerator(false);
        answers.add(answer2);
    }

    List<Reputation> reputations = new ArrayList<>();

    private void createReputations() {
        Reputation reputation1 = new Reputation();
        reputation1.setAuthor(users.get(0));
        reputation1.setCount(100);
        reputation1.setType(ReputationType.Answer);
        reputation1.setAnswer(answers.get(0));
        reputations.add(reputation1);

        Reputation reputation2 = new Reputation();
        reputation2.setAuthor(users.get(1));
        reputation2.setCount(200);
        reputation2.setType(ReputationType.Question);
        reputation2.setQuestion(questions.get(1));
        reputations.add(reputation2);
    }
}
