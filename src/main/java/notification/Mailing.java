package notification;

import bot.Bot;
import models.Event;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Mailing implements Runnable {

    private Bot bot;

    public Mailing(Bot bot) {
        this.bot = bot;
    }

    public Bot getBot() {
        return bot;
    }

    public void sendNotifications(List<Event> events) {
        for(Event event : events){
            String textOut = "Поздравьте с Днем Рождения " + event.getDescription();
            Long chatID = event.getOwner().getChatid();
            bot.sendMsg(chatID.toString(), textOut);
        }
    }

    @Override
    public void run() {
        while (true){
            LocalDate dateNow = LocalDate.now();
            List<Event> events = Check.getEvents(dateNow);

            sendNotifications(events);

            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
