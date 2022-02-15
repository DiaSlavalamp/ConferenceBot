package org.example;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class App {

    public static String rootPath;
    public static String contentPath;
    public static ClassPathXmlApplicationContext appcon;
    public static List<File> content;

    @Autowired
    public static ConferenceBot conferenceBot;

    public static void initConst() {
        rootPath = System.getProperty("user.dir");
        contentPath = rootPath + "\\content";
        System.out.println(System.getProperty("user.dir"));
    }

    public static void initAppcon() {
        appcon = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("test");
    }

    public static void initBot() {
          conferenceBot = appcon.getBean(ConferenceBot.class);
    }

    public static void addContent() {
        try {
            content = Arrays.asList(new File(contentPath).listFiles());
            conferenceBot.gen.setContent(content );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run() {

        initConst();
        initAppcon();
        initBot();
        addContent();

        //run
        try {
            new BotsLongPoll(conferenceBot).run();
        } catch (BotsLongPollHttpException e) {
            e.printStackTrace();
        } catch (BotsLongPollException e) {
            e.printStackTrace();
        }

    }

}
