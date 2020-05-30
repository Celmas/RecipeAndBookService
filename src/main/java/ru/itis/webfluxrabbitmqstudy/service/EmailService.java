package ru.itis.webfluxrabbitmqstudy.service;

public interface EmailService {
    boolean sendEmail(String email, String body);
}
