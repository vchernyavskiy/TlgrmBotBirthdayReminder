package operationCommand;

import models.Tlguser;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import services.TlguserService;

public class AddCommand extends OperationCommand{
    public AddCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {

        String msg;

        Tlguser tlguser = TlguserService.findTlguserByChatId(chat.getId());

        if (tlguser == null) {
            msg = "create";
            //TlguserService.saveTlguser(tlguser);
        } else {
            msg = "update";
        }

        sendAnswer(absSender, chat.getId(), msg);
    }
}