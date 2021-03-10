
import bot.Bot;
import notification.Mailing;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.sql.SQLException;

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

        Mailing mailing = new Mailing(bot);

        Thread thread = new Thread(mailing);
        thread.start();
    }
}
