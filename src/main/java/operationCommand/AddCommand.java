package operationCommand;

import models.Tlguser;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import services.TlguserService;

public class AddCommand extends OperationCommand {
    public AddCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        String answerOut;
        Long chatId = chat.getId();

        Tlguser tlguser = TlguserService.findTlguserByChatId(chatId);

        if (tlguser == null) {
            //log "create user with add";

            tlguser = new Tlguser(chatId, "add", user.getUserName(), user.getFirstName(), user.getLastName());
            TlguserService.saveTlguser(tlguser);
        } else {
            //log "update user with add";

            tlguser.setUsername(user.getUserName());
            tlguser.setFirstname(user.getFirstName());
            tlguser.setLastname(user.getLastName());
            tlguser.setState("add");
            TlguserService.updateTlguser(tlguser);
        }

        answerOut = "Введите текст в формате \"дд.мм.гггг Описание\", например: \"12.12.2012 Зина Корзина\"";
        sendAnswer(absSender, chat.getId(), answerOut);
    }
}