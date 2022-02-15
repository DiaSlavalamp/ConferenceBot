package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vkontakte {

    @Autowired
    public LongPollBot bot;

    public boolean sendPeerMessage(int peerId, String text) {
        if (text.length() <= 4096) {
            try {
                new MessagesSend(bot)
                        .setPeerId(peerId)
                        .setMessage(text)
                        .execute();
            } catch (BotsLongPollHttpException | BotsLongPollException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }else {
            System.out.println("Ты ахуел, пес? Это слишком длинное сообщение!");
            return false;
        }
    }

}
