

## YouGotMail - Email Deletion Application

**YouGotMail** is a JavaFX application that allows users to delete emails from their inbox within a specific date range. The application connects to a Gmail account using IMAP and provides an interface for selecting the range of emails to be deleted based on the date. The user can enter their credentials and the range for email deletion.

## Features

- **User Authentication**: Users can log in with their Gmail credentials (email and password).
- **Email Deletion**: Delete emails from your inbox within a specific date range.
- **Date Selection**: Use a graphical date picker to select the "From" and "To" dates for email deletion.
- **Warning System**: Before proceeding with email deletion, a warning is shown that the process cannot be undone.

## Technical Details

### Binary Search Optimization

The application uses a modified binary search algorithm to optimize the email deletion process:

- **Smart Starting Point**: Uses binary search to quickly locate either:
  - The exact starting date in the range, or
  - The closest date within the specified range
- **Efficient Processing**: Once the starting point is found, the application:
  - Deletes emails sequentially from that point
  - Continues until reaching emails beyond the target end date
  - This approach avoids unnecessary scanning of emails outside the target range
- **Performance Benefits**: 
  - Eliminates the need to scan through older emails
  - Particularly efficient when deleting recent emails from large inboxes
  - Reduces initial search time from O(n) to O(log n)

## Requirements

- **Java 11 or higher** (as JavaFX is no longer bundled with JDK).
- **JavaFX SDK** (download and configure separately if using JDK 11 or later).
- **Internet connection** (to connect to the Gmail account via IMAP).

## Setup Instructions

### Prerequisites

Before running the application, ensure that you have the following installed:

1. **Java 11+ SDK**:
   - Download and install from [OpenJDK](https://jdk.java.net/) or [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
   - Set the `JAVA_HOME` environment variable to the installation path.

2. **JavaFX SDK**:
   - Download the [JavaFX SDK](https://openjfx.io/) for your platform.
   - Set up JavaFX in your IDE (IntelliJ IDEA or Eclipse) or with your build tool (e.g., manually via classpath).

3. **Required JAR Dependencies**:
   - Download and include the following JAR files in your project classpath:
     - [activation-1.1.1.jar](https://mvnrepository.com/artifact/jakarta.activation/jakarta.activation-api/2.1.3)
     - [javax.mail-1.6.2.jar](https://mvnrepository.com/artifact/javax.mail/javax.mail-api)

## Clone the Project

Clone this repository to your local machine:

```bash
git clone https://github.com/yourusername/YouGotMail.git](https://github.com/Achu-Worifung/You-ve-Got-Mail.git)
```

## Adding JAR Dependencies

You need to include the necessary JAR files for JavaMail and Jakarta Activation:

1. Download `javax.mail-1.6.2.jar` and `activation-1.1.1.jar` from the provided links.
2. Add both JAR files to your classpath.

### Configuration

The application connects to Gmail using IMAP. Make sure your Gmail account allows access to less secure apps or that you use an **App Password** if you have two-factor authentication enabled.

1. Enter your **Gmail Email** and **Password** when prompted.
2. Select the date range (From and To) for the emails you wish to delete.

## Troubleshooting

- **`java.lang.ClassNotFoundException: javafx.application.Application`**: This indicates that JavaFX is not configured correctly. Ensure that you have added the JavaFX SDK to your project and set it up properly in your IDE or via the classpath.
- **Gmail IMAP Issues**: Ensure your Gmail account settings allow IMAP access and that you have the correct permissions to access your email programmatically.
- **App Passwords**: If you use two-factor authentication, ensure that you're using an App Password rather than your regular Gmail password.

## Screenshots
![deletemyMail](https://github.com/user-attachments/assets/9022b982-3582-44d2-b24c-8e6479b1fdd4)
![Screenshot 2024-12-19 010842](https://github.com/user-attachments/assets/e4dbb8ed-0cb8-4274-abf2-ab363e9df685)
![Screenshot 2024-12-19 010628](https://github.com/user-attachments/assets/21f4194b-e585-4398-87d4-4ca0da13b730)
![deletemyMailmenu](https://github.com/user-attachments/assets/2ca887c0-ca0b-4e9a-9f2c-cc03f7685bcb)
![deletemymailInstructions](https://github.com/user-attachments/assets/4e1ec7dd-cc37-4491-b14f-d18817505315)

