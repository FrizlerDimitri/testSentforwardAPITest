package com.example.testsentforwardint;

import com.oth.sentforward.persistence.entities.Appointment;
import com.oth.sentforward.persistence.entities.Calendar;
import com.oth.sentforward.persistence.entities.EmailAccount;
import com.oth.sentforward.persistence.entities.SentEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TestService {

    @Autowired
    private RestTemplate restServiceClient;

    public void testRestService1()
    {
        EmailAccount emailAccount = new EmailAccount();
        emailAccount.setEmailAddress("test1@sentforward.de");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity <EmailAccount> entity = new HttpEntity<>(emailAccount, headers);

//        Calendar calendar=restServiceClient.postForObject("http://localhost:8080/sentforward-rest-api/calendars", entity, Calendar.class);

        ResponseEntity responseEntity = restServiceClient.postForEntity("http://localhost:8080/sentforward-rest-api/calendars",entity,Calendar.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK)
        {
           Calendar c = (Calendar) responseEntity.getBody();

            System.out.println("Calendar Name: "+c.getName());

            for(Appointment a: c.getAppointments())
            {
                System.out.println(a.getDate());
                System.out.println(a.getDescription());
                System.out.println(a.getName());
            }

        }

    }

    public void testRestService2()
    {

        EmailAccount from = new EmailAccount();
        from.setEmailAddress("test1@sentforward.de");

        EmailAccount to = new EmailAccount();
        to.setEmailAddress("test2@sentforward.de");

        SentEmail sentEmail = new SentEmail();

        sentEmail.setContent("With Rest API");
        sentEmail.setSubject("Rest2");

        sentEmail.setFrom(from);
        sentEmail.getTo().add(to);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity <SentEmail> entity = new HttpEntity<>(sentEmail, headers);
        ResponseEntity responseEntity=restServiceClient.postForEntity("http://localhost:8080/sentforward-rest-api/sentEmails", entity, SentEmail.class);

        if(responseEntity.getStatusCode() == HttpStatus.OK)
        {
            SentEmail s = (SentEmail) responseEntity.getBody();
            System.out.println(s.getContent());
        }

    }

}
