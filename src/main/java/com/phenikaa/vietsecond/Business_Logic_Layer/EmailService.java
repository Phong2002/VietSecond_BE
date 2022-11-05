package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Data_Access_Layer.UserRepository;
import com.phenikaa.vietsecond.Entity.User;
import com.phenikaa.vietsecond.Form.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


@Service
@Transactional
public class EmailService implements IEmailService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    EmailTemplate emailTemplate;
    @Override
    public void sendEmailAccountVerification(User user, String token) throws MessagingException, UnsupportedEncodingException {
        String email = user.getEmail();
        String fullname = user.getFirstName()+" "+user.getLastName();
        String content = emailTemplate.verificationAccount(fullname,token);
        sendEmail(email, "Xác thực tài khoản!",content);
    }

    @Override
    public void sendEmailResetPassword(User user, String token) throws MessagingException, UnsupportedEncodingException {
        String email = user.getEmail();
        String fullname = user.getFirstName()+" "+user.getLastName();
        String content = emailTemplate.resetPassword(fullname,token);
        sendEmail(email, "Đặt lại mật khẩu!",content);
    }

    @Override
    public void sendEmailNotification(User user, String title, String content) {

    }

    private void sendEmail(String email,String subject,String content) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = content;
        helper.setText(htmlMsg, true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setFrom("vietsecond.ecommerce@gmail.com","VietSecond");
        mailSender.send(mimeMessage);
    }
}
