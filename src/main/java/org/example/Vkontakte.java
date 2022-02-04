package org.example;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.BotsLongPollException;
import api.longpoll.bots.exceptions.BotsLongPollHttpException;
import api.longpoll.bots.methods.messages.MessagesSend;

public class Vkontakte {

    public LongPollBot bot;

    public boolean sendPeerMessage(int peerId, String text){
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
    }

}
