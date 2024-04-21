package com.lulugyda.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.micronaut.context.annotation.Value;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import static com.lulugyda.exceptions.ExceptionManager.handleException;

@Singleton
public class TwilioService {

    private final String accountSid;
    private final String authToken;


    public TwilioService(@Value("${twilio.account-sid}") String accountSid,
                         @Value("${twilio.auth-token}") String authToken) {
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    @PostConstruct
    void initTwilio() {
        Twilio.init(accountSid, authToken);
    }

    public void sendOtpMessage(String to, String otp) {
        try {
        Message.creator(
                new PhoneNumber("whatsapp:"+to),
                new PhoneNumber("whatsapp:+14155238886"),
               "Your OTP is: " + otp)
                .create();
        } catch (Exception exception) {
            handleException(exception);
        }
    }
}
