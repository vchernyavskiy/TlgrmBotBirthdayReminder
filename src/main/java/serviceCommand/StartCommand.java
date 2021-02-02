package serviceCommand;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Команда "Старт"
 */
public class StartCommand extends ServiceCommand {

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        //формируем имя пользователя - поскольку userName может быть не заполнено, для этого случая используем имя и фамилию пользователя
//        String userName = (user.getUserName() != null) ? user.getUserName() :
//                String.format("%s %s", user.getLastName(), user.getFirstName());

        //обращаемся к методу суперкласса для отправки пользователю ответа
        sendAnswer(absSender, chat.getId(), "Давайте начнём! Если Вам нужна помощь, нажмите /help");
    }
}