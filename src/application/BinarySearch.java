package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;

public class BinarySearch
{
	int binarySearch(LocalDate from,LocalDate to, Message[] msg) throws MessagingException
	{

		
		int low = 0, high = msg.length-1, result = -1, save = -1;
		
		while (low <= high)
		{
			int mid = low + (high - low) /2;
			
			//getting the date the msg was sent
			LocalDate messageDate = LocalDateTime.ofInstant(msg[mid].getSentDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
			
			
			System.out.println("current message date "+ messageDate);
			System.out.println("passed message date "+ from);
			
			if(messageDate.equals(from))
			{
				//getting the 1st occurrence of the send date
				result = mid;
				System.out.println("found 1 occurance at " + result );
				high = mid - 1;
			}
			else if (messageDate.isBefore(from))
			{
				low = mid + 1;
			}
			else if (messageDate. isAfter(from))
			{
				if (messageDate.isBefore(to)) save = mid;
				high = mid - 1;
			}
		}
		return result == -1? save:result;
	}
}