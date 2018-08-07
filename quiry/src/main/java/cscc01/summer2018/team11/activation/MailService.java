package cscc01.summer2018.team11.activation;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cscc01.summer2018.team11.user.User;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMail;

    public void sendActivationEmail(User user) throws MessagingException{
        MimeMessage message = javaMail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String activationUrl="http://localhost:8080/user/activate?userId="+ user.getUserId() + "&verification=" + user.getVerification();
        String content="Hello "+ user.getFirstName() +",<br/><br/>Please use this link to activate your Quiry account:<br/>"
                + "<a href='"+ activationUrl +"'>"+ activationUrl +"</a><br/><br/>Team Eleven";

        helper.setTo(user.getEmail());
        helper.setSubject("Quiry Account Activation");
        helper.setText(content, true);
        
        javaMail.send(message);
    }

}
