package utilities;
import java.io.*;
import java.util.Properties;
import javax.mail.*;

public class ReadingEmail {

	public static void receiveMail(String userName, String password) {
		
			try {
				Properties prop = new Properties();
				prop.setProperty("mail.store.protocol", "imaps");
				Session emailSession = Session.getDefaultInstance(prop);
				Store emailStore = emailSession.getStore("imaps");
				emailStore.connect("imap.gmail.com", userName, password);
				//getting Inbox folder
				Folder emailFolder = emailStore.getFolder("INBOX");
				emailFolder.open(Folder.READ_WRITE);
				
				Message emailMessage = emailFolder.getMessage(14390);
				System.out.println("Email From:" + emailMessage.getFrom());
				System.out.println("Email Subject:" + emailMessage.getSubject());
				System.out.println("Email Description:" + emailMessage.getDescription());
				System.out.println("Email Sent Date:" + emailMessage.getSentDate());
				emailMessage.setFlag(Flags.Flag.SEEN, true);
				//Message msg[] = emailFolder.getMessages();
				
//				for(int i=msg.length-3; i<msg.length; i++) {
//					Message message = msg[i];
//					System.out.println("Email Number: " + (i+1));
//					System.out.println("subject: " + message.getSubject());
//					System.out.println("Frome: " + message.getFrom()[0]);
//					System.out.println("Sent date: " + message.getSentDate());
//				}
				//closing emailFolder and emailStore
				emailFolder.close(true);
				emailStore.close();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public static void main(String...args) {
		receiveMail("amiteshshukla999@gmail.com","9919930969");
	}
}
