package operationCommand;

import models.Event;
import models.Tlguser;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import services.EventService;
import services.TlguserService;

import java.time.format.DateTimeFormatter;
import java.util.*;
//import java.io.*;

public class DeleteCommand extends OperationCommand {
    public DeleteCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String answerOut = "Введите номер для удаления:";
        Long chatId = chat.getId();

        Tlguser tlguser = TlguserService.findTlguserByChatId(chatId);

        if (tlguser == null) {
            tlguser = new Tlguser(chatId, "delete", user.getUserName(), user.getFirstName(), user.getLastName());
            TlguserService.saveTlguser(tlguser);
        } else {
            //log "update user with add";

            tlguser.setUsername(user.getUserName());
            tlguser.setFirstname(user.getFirstName());
            tlguser.setLastname(user.getLastName());
            tlguser.setState("delete");
            TlguserService.updateTlguser(tlguser);
        }

        List<Event> events = EventService.selectEventsByUser(tlguser);
        Collections.sort(events);
        if (events.size() == 0) {
            answerOut = "Вы еще не добавили ни одного напоминания.\nПомощь /help";
        } else {
            int i = 1;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            for(Event e : events){
                answerOut += "\n" + i + ". " + e.getDescription() + ", " + e.getDate().format(formatter);
                i++;
            }
        }

        sendAnswer(absSender, chat.getId(), answerOut);
    }
}