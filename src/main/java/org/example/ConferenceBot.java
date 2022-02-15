package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.util.*;


@Component
//@PropertySource("classpath:values.properties")
public class ConferenceBot extends LongPollBot {

    @Value("${test.ts}")
    public String gsg;
    @Value("${app.userId}")
    private int userId;

    @Value("${app.token}")
    private String token;

    @Autowired
    public Generator gen;

    @Autowired
    public Vkontakte vk;

    public boolean tryAnswer(BotAnswerer botAnswerer) {
        try {
            botAnswerer.answer();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {
        //   try {

        System.out.print(".");

        Message message = messageNewEvent.getMessage();
        Date date = new Date();
        Integer peerId = message.getPeerId();

        if (message.getText().toUpperCase().substring(0, 3).contains("БОТ")) {
            tryAnswer(() -> {
                        //String answerText = gen.getGreenMessage(message.getText());

                        String words = message.getText();
                        String[] sad = words.split(" ");
                        String word = sad[sad.length - 1];
                        //word = new String(word.getBytes(), StandardCharsets.US_ASCII);
                        System.out.println("\nword=" + word);
                        String answerText = gen.genBookAnswer(word);
                        if (!answerText.equals("Ничиво не нашел(")) {
                            if (!answerText.replace(" ", "").isEmpty()) {
                                vk.sendPeerMessage(peerId, answerText);
                            }
                        } else {
                            System.out.println("нинашел(");
                        }

                    }
            );
//            tryAnswer(() -> {
//                        //String answerText = gen.getGreenMessage(message.getText());
//                        String answerText = gen.genBookAnswer(message.getText().replace("бот+ ", ""));
//                        vk.sendPeerMessage(peerId, answerText);
//                    }
            //           );
        }

        //todo вместо даты в айдишник пихать сумму цифр в айди или хз или тупа рандом на 666 и типа делится ли он на 6 без остатка

        int id = message.getConversationMessageId();
        System.out.println("id=" + id + "/");
//        id = id.replace("0", "");
//        id = id.replace("1", "");
//        id = id.replace("2", "");
//        id = id.replace("3", "");
//        id = id.replace("4", "");
//        id = id.replace("5", "");
//        id = id.replace("7", "");
//        id = id.replace("8", "");
//        id = id.replace("9", "");

        int avesatanas = id * 666 * 3 % 13;
        System.out.println("?" + id * 666 + "-" + avesatanas + "?");
        //avesatanas = 0;////////////////
        if (avesatanas == 0) {
            System.out.println("Ave satanas!");
            tryAnswer(() -> {
                        //String answerText = gen.getGreenMessage(message.getText());

                        String words = message.getText();
                        String[] sad = words.split(" ");
                        String word = sad[sad.length - 1];
                        //word = new String(word.getBytes(), StandardCharsets.UTF_8);
                        System.out.println("\nword=" + word);
                        String answerText = gen.genBookAnswer(word);
                        if (!answerText.equals("Ничиво не нашел(")) {
                            if (!answerText.replace(" ", "").isEmpty()) {
                                vk.sendPeerMessage(peerId, answerText);
                            }
                        } else {
                            System.out.println("нинашел(");
                        }

                    }
            );
            return;
        } else if (0 == 0) {
            return;
        }


        if (message.getText().toUpperCase().equals("ВРЕМЯ")) {
            tryAnswer(() -> {
                vk.sendPeerMessage(peerId, "Время на ебаном сервере: " + date.toString());
            });
        }
//        if (message.getText().toUpperCase().contains("РАБОТ")) {
//            try {
//                new MessagesSend(this)
//                        .setPeerId(message.getPeerId())
//                        .setMessage(">"+message.getText()+ "\n" + "Ненавижу блядь свою работу.")
//                        .execute();
//            } catch ( BotsLongPollHttpException | BotsLongPollException e) {
//                e.printStackTrace();
//            }
//        }
        else if (message.getText().contains("где я")) {
            try {
                Integer unix = message.getDate();
                Date date2 = new Date(unix * 1000L);
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date2);
                //String timeZone = calendar.getTimeZone().getID();
                String timeZone = calendar.getTimeZone().getDisplayName(Locale.forLanguageTag("ru-RU"));

                String date3 = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss Z").format(date2);

                //TimeZone tz = TimeZone.getTimeZone()
                new MessagesSend(this)
                        .setPeerId(message.getPeerId())
                        .setMessage("Пиздеж из сообщения: \n" + "Время отправки говна в UNIX формате: " + message.getDate().toString() + "\n"
                                + "Время отправки говна в человековом формате: " + date3 + "\n"
                                + "Ебаная нерабочая таймзона: " + timeZone + "(Она не работает)\n"
                                + "ID отправителя: " +
                                message.getFromId().toString() + "\n" + "Сообщений в анусе: " + message.getConversationMessageId().toString())
                        .execute();
            } catch (BotsLongPollHttpException | BotsLongPollException e) {
                e.printStackTrace();
            }
        } else if (message.hasGeo()) {
            try {
                new MessagesSend(this)
                        .setPeerId(message.getPeerId())
                        .setMessage("Выебут тут: " + message.getGeo().getCoordinates().getLatitude().toString() + " " + message.getGeo().getCoordinates().getLongitude().toString())
                        .execute();
            } catch (BotsLongPollHttpException | BotsLongPollException e) {
                e.printStackTrace();
            }
        }
//        else if (message.hasAttachments()) {
//            try {
//                new MessagesSend(this)
//                        .setPeerId(message.getPeerId())
//                        .setMessage("лол.")
//                        .execute();
//            } catch ( BotsLongPollHttpException | BotsLongPollException e) {
//                e.printStackTrace();
//            }
//        }
//        else if (message.getText().toUpperCase().equals("")) {
//            try {
//                new MessagesSend(this)
//                        .setPeerId(message.getPeerId())
//                        .setMessage("Вот тебе мемас!")
//                        .attachPhoto(new File("F:\\бот в беседе\\фреско.jpg"))
//                        .execute();
//            } catch ( BotsLongPollHttpException | BotsLongPollException e) {
//                e.printStackTrace();
//            }
//        }

//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public String getAccessToken() {
        return token;
    }

    @Override
    public int getGroupId() {
        return 201223334;
    }

}

