package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


import java.util.*;


@Component
//@PropertySource("classpath:values.properties")
public class ConferenceBot extends LongPollBot {

    @Value( "${test.ts}" )
    public String gsg;
    @Value( "${app.userId}" )
    private int userId;

    @Value( "${app.token}" )
    private String token;

    public Generator gen;
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

        if (message.getText().toUpperCase().contains("БОТ")) {
            tryAnswer(() -> {
                        String answerText = gen.getGreenMessage(message.getText());
                        vk.sendPeerMessage(peerId, answerText);
                    }
            );
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

