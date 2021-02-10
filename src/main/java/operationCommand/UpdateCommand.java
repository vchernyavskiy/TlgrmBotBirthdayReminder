package operationCommand;

import models.Tlguser;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import services.TlguserService;

public class UpdateCommand extends OperationCommand{
    public UpdateCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String answerOut;
        Long chatId = chat.getId();

        Tlguser tlguser = TlguserService.findTlguserByChatId(chatId);

        if (tlguser == null) {
            //log "create user with update";

            tlguser = new Tlguser(chatId, "update", user.getUserName(), user.getFirstName(), user.getLastName());
            TlguserService.saveTlguser(tlguser);
        } else {
            //log "update user with update";

            tlguser.setUsername(user.getUserName());
            tlguser.setFirstname(user.getFirstName());
            tlguser.setLastname(user.getLastName());
            tlguser.setState("update");
            TlguserService.updateTlguser(tlguser);
        }

        answerOut = "Для изменения напоминания сначала удалите его (команда /delete), затем добавьте заново (команда /add)";
        sendAnswer(absSender, chat.getId(), answerOut);
    }
}
