import bot.Bot;
import models.Event;
import notification.Check;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        String bName = System.getenv("tlg.bdrem.botName");
        String bToken = System.getenv("tlg.bdrem.botToken");

        Bot bot = new Bot(bName, bToken);

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        LocalDate dateNow = LocalDate.now();
        List<Event> events = Check.getEvents(dateNow);




//        Thread thread = new Thread();
//        thread.start();
    }
}
