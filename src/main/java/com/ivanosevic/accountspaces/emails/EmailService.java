package com.ivanosevic.accountspaces.emails;

import com.ivanosevic.accountspaces.emails.common.EmailTemplate;
import com.ivanosevic.accountspaces.emails.exceptions.EmailNotSendException;
import com.ivanosevic.accountspaces.emails.exceptions.EmailTemplateNotFoundException;
import com.ivanosevic.accountspaces.emails.templates.Email;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final ContextProperties contextProperties;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine, ContextProperties contextProperties) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.contextProperties = contextProperties;
    }

    private String getTemplate(Email email) {
        var emailTemplate = email.getClass().getAnnotation(EmailTemplate.class);
        if (emailTemplate == null) {
            throw new EmailTemplateNotFoundException("The email template for email: " + email.getClass().getSimpleName() + " was not found.");
        }
        return emailTemplate.path();
    }

    private Context createEmailContext(Email email) throws IllegalAccessException {
        var ctx = new Context();
        Map<String, Object> emailVariables = new HashMap<>();
        List<Field> fields = new ArrayList<>();

        // We iterate over each superclass recursively
        // until we hit the Object.class.
        // During loop fetch all fields of that current class
        Class<?> currentClass = email.getClass();
        while (currentClass.getSuperclass() != null) {
            List<Field> subFields = List.of(currentClass.getDeclaredFields());
            fields.addAll(subFields);
            currentClass = currentClass.getSuperclass();
        }

        for (Field f : fields) {
            f.setAccessible(true); // makes private fields accessible
            String name = f.getName();
            Object value = f.get(email);
            emailVariables.put(name, value);
        }

        ctx.setVariables(emailVariables);
        return ctx;
    }

    @Async
    public CompletableFuture<Void> send(Email email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(email.getTitle());
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setFrom(contextProperties.getNoReplyEmailAddress());
            helper.setTo(email.getTo());
            if (email.hasCarbonCopy()) {
                String[] cc = email.getCarbonCopy().toArray(String[]::new);
                helper.setCc(cc);
            }
            Context ctx = createEmailContext(email);
            String emailTemplate = getTemplate(email);
            String messageContent = templateEngine.process(emailTemplate, ctx);
            helper.setText(messageContent, true);
            mailSender.send(message);
        } catch (Exception ex) {
            throw new EmailNotSendException(email.getClass().getName(), ex.getMessage());
        }

        return CompletableFuture.completedFuture(null);
    }
}
