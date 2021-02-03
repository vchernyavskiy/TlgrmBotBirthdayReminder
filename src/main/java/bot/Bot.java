package bot;

import models.Tlguser;
import nonCommand.NonCommand;
import operationCommand.AddCommand;
import serviceCommand.HelpCommand;
import serviceCommand.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import services.TlguserService;

public class Bot extends TelegramLongPollingCommandBot {

    private final String botName;
    private final String botToken;
    private final NonCommand nonCommand;

    public Bot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;

        this.nonCommand = new NonCommand();

        register(new StartCommand("start", "Старт"));
        register(new HelpCommand("help","Помощь"));
        register(new AddCommand("add","Добавить напоминание"));
    }

    /**
     * Ответ на запрос, не являющийся командой
     */
    @Override
    public void processNonCommandUpdate(Update update) {
        Message msgIn = update.getMessage();
        Long chatId = msgIn.getChatId();
        Tlguser tlguser = TlguserService.findTlguserByChatId(chatId);

        String answerOut;
        if (tlguser == null) {
            answerOut = "Введите команду";
        } else {
            answerOut = nonCommand.nonCommandExecute(tlguser, msgIn.getText());
        }

        sendMsg(chatId.toString(), answerOut);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

}