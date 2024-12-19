package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DeleteMail {
    private int count;
    private LocalDate to, from;
    private String email, password;

    public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}

	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public DeleteMail(LocalDate to, LocalDate from, String email, String pass)
	{
		this.email = email;
		this.password = pass;
		this.to = to;
		this.from = from;
				
	}

	public DeleteMail() {
        System.out.println("Entering DeleteMail constructor");
        count = 0;
        System.out.println("Exiting DeleteMail constructor");
    }

    public void deleateMyMail() {
        Store store = null;
        Folder inbox = null;
        
        try {
            String userEmail = email;
            // Replace with your App Password from Google Account settings
            String googlePassword = password;
           
            count = 0;
            LocalDate now = LocalDate.now();
            if (from.isAfter(now) || to.isAfter(now)) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Invalid Date Range");
                alert.setHeaderText("Future dates selected");
                alert.setContentText("Please select dates not beyond today's date");
                alert.show();
                return;
            }

            Properties props = new Properties();
            props.put("mail.store.protocol", "imaps");
            props.put("mail.imaps.host", "imap.gmail.com");
            props.put("mail.imaps.port", "993");
            props.put("mail.imaps.ssl.enable", "true");
            props.put("mail.imaps.ssl.protocols", "TLSv1.2");
            props.put("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.imaps.socketFactory.fallback", "false");
            props.put("mail.imaps.socketFactory.port", "993");
            props.put("mail.debug", "true");  // For debugging

            Session session = Session.getInstance(props, null);
            store = session.getStore();
            store.connect("imap.gmail.com", userEmail, googlePassword);

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Date fromDate = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date toDate = Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());

            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                Date messageDate = message.getSentDate();
                
                if (messageDate != null) {
                    // Debug print to see what dates we're comparing
                    System.out.println("Message date: " + messageDate + 
                                       " | Is after " + fromDate + ": " + messageDate.after(fromDate) +
                                       " | Is before " + toDate + ": " + messageDate.before(toDate));
                    
                    // Check if the message is within the deletion range
                    if (messageDate.after(fromDate) && messageDate.before(toDate)) {
                        message.setFlag(Flags.Flag.DELETED, true);
                        count++;
                        System.out.println("Marked for deletion: " + message.getSubject() + 
                                           " | Date: " + messageDate);
                    }
                    // If we've passed the range and the message is after the 'from' date, stop further processing
                    else if (messageDate.after(toDate)) {
                        System.out.println("Passed the deletion range. Stopping deletion.");
                        break; // Stop processing further messages
                    }
                }
            }


            // Show success alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("E-Mail DELETED");
            alert.setHeaderText("YOUR EMAILS HAVE BEEN DELETED");
            alert.setContentText(count + " EMAIL(S) WERE DELETED FROM YOUR EMAIL ADDRESS");
            alert.show();

        } catch (MessagingException e) {
            // Show error alert for messaging errors
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to Delete Emails");
            alert.setContentText("Error: " + e.getMessage());
            alert.show();
            System.out.println("Messaging error: " + e.getMessage());
            e.printStackTrace();
            
        } catch (Exception e) {
            // Show error alert for other errors
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unexpected Error");
            alert.setContentText("An unexpected error occurred: " + e.getMessage());
            alert.show();
            System.out.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
            
        } finally {
            try {
                // Properly close resources
                if (inbox != null && inbox.isOpen()) {
                    inbox.close(true);  // true to expunge deleted messages
                }
                if (store != null && store.isConnected()) {
                    store.close();
                }
            } catch (MessagingException e) {
                System.out.println("Error closing resources: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}