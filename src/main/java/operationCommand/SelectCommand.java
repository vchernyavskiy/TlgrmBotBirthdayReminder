package operationCommand;

import models.Event;
import models.Tlguser;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import services.EventService;
import services.TlguserService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class SelectCommand extends OperationCommand{
    public SelectCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String answerOut = "";
        Long chatId = chat.getId();

        Tlguser tlguser = TlguserService.findTlguserByChatId(chatId);

        if (tlguser == null) {
            //log "create user with select";

            tlguser = new Tlguser(chatId, "select", user.getUserName(), user.getFirstName(), user.getLastName());
            TlguserService.saveTlguser(tlguser);
        } else {
            //log "update user with select";

            tlguser.setUsername(user.getUserName());
            tlguser.setFirstname(user.getFirstName());
            tlguser.setLastname(user.getLastName());
            tlguser.setState("select");
            TlguserService.updateTlguser(tlguser);
        }

        List<Event> events = EventService.selectEvents(tlguser);

        if (events.size() == 0){
            answerOut = "Вы еще не добавили ни одного напоминания.\nПомощь /help";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            for (int i = 1; i <= events.size(); i++) {
                Event event = events.get(i - 1);
                answerOut = answerOut + i + ". " + event.getDate().format(formatter) + ": " + event.getDescription() + "\n";
            }
        }

        sendAnswer(absSender, chat.getId(), answerOut);
    }
}
