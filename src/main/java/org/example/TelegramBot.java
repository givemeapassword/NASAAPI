package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class TelegramBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String Nasa_URL
            = "https://api.nasa.gov/planetary/" +
            "apod?api_key=0EQ5jMdJas2pdA2jSc6oTM87wIF99gfh1WjfqRQL";

    public TelegramBot(String botName, String botToken) throws TelegramApiException {
        BOT_NAME = botName;
        BOT_TOKEN = botToken;

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            String[] separatedText = text.split(" ");

            switch (separatedText[0]){
                case "/help":
                case "/помощь":
                    sendMessage("Я бот NASA, введите /image или /картинка", chatId);
                    break;
                case "/image":
                case "/картинка":
                    String image = Utils.getURL(Nasa_URL);
                    sendMessage(image, chatId);
                    break;
                case "/date":
                case "/дата":
                    image = Utils.getURL(Nasa_URL + "&date=" + separatedText[1]);
                    sendMessage(image,chatId);
                    break;
                default:
                    sendMessage("Что-то ты сделал не так",chatId);

            }

        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }
    @Override
    public String getBotToken(){
        return BOT_TOKEN;
    }

    void sendMessage(String msgText,long chatId){
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(msgText);
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
