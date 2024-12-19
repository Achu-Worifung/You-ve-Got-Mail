package application;

public class EmailMessage {
	
	public String header, subject, sent_date;
	
	   public EmailMessage(String header, String subject, String sentDate) {
	        this.header = header;
	        this.subject = subject;
	        this.sent_date = sentDate;
	    }

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSent_date() {
		return sent_date;
	}

	public void setSent_date(String sent_date) {
		this.sent_date = sent_date;
	}

}
