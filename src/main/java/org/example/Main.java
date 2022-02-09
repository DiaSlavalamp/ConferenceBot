package org.example;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

            while (true) {
                try {
                    ClassPathXmlApplicationContext apcon = new ClassPathXmlApplicationContext("applicationContext.xml");

                    System.out.println("test");

                    ConferenceBot myBot = apcon.getBean(ConferenceBot.class);
                    //System.out.println(cb.gsg);

                    //ConferenceBot myBot = new ConferenceBot();
                    Generator gen = new Generator();
                    myBot.gen = gen;
                    Vkontakte vk = new Vkontakte();
                    vk.bot = myBot;
                    myBot.vk = vk;

                    try {
                        gen.ge = new String(Files.readAllBytes(Paths.get("C:\\Users\\defce\\IdeaProjects\\ConferenceBot\\src\\main\\resources\\Sorokin_Vladimir_-_Serdca_chetyrex.txt")));
                        gen.bv = new String(Files.readAllBytes(Paths.get("C:\\Users\\defce\\IdeaProjects\\ConferenceBot\\src\\main\\resources\\киш.txt")));
                        gen.kh =  new String(Files.readAllBytes(Paths.get("C:\\Users\\defce\\IdeaProjects\\ConferenceBot\\src\\main\\resources\\зс.txt")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String[] f = gen.ge.split("(?<=\\.)(.*)(?=[А-Я])");//между точкой и большой буквой выделяет пробел и по нему режет


                    String sd = gen.genBookAnswer("хуй");

                    System.out.println(gen.ge);

                    try {
                        new BotsLongPoll(myBot).run();
                    } catch (BotsLongPollHttpException e) {
                        e.printStackTrace();
                    } catch (BotsLongPollException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(58);
                }
                System.out.println("бот упал");
            }


    }

}
