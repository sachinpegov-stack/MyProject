package com.example.test.listeners;

import io.qameta.allure.Allure;

public class AllureManager {

    public static void log(String message) {
        Allure.step(message);
    }

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, content);
    }

    public static void attachHtml(String name, String html) {
        Allure.addAttachment(name, "text/html", html, ".html");
    }
}
