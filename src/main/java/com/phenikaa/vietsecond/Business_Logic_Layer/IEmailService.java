package com.phenikaa.vietsecond.Business_Logic_Layer;

import com.phenikaa.vietsecond.Entity.User;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface IEmailService {
 void sendEmailAccountVerification(User user, String token) throws MessagingException, UnsupportedEncodingException;
 void sendEmailResetPassword(User user,String token) throws MessagingException, UnsupportedEncodingException;
 void sendEmailNotification(User user,String title,String content);
}
