# EmailService
Bpring boot application that allows you to send emails (Plaintext emails, html emails, emails with attachments)
Made using Java Mail API.
Enables using predefined mail templates (thymeleaf templates) and sending attachments.  

### Examples of calls:

- Method: POST
- Endpoint: http://localhost:8080/mail/send/templateMessage
-- Sends email with verification link
- Body(raw,JSON):  
``` 
  {
    "to": "new.user@gmail.com",
    "subject": "Verify your email",
    "templateName": "registration-link",
    "variablesMap":
    {
        "recipientName": "John Snow",
        "link": "https://bartosztanski.azurewebsites.net/auth?token=123456789",
        "senderName": "Bartosz"
    },
    "attachment": null
}
```
### Example of email sent
![Sent email](screenshots/screenshot-mail.PNG)

- Method: GET
- Endpoint: http://localhost:8080/templates
-- Returns Map of avaliable templates to use
