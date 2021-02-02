package serviceCommand;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

/**
 * Команда "Помощь"
 */
public class HelpCommand extends ServiceCommand {

    public HelpCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String msgHelp = "Это бот для напоминания о днях рождения.\nСписок команд:";

        msgHelp += "\n /help - помощь";

        sendAnswer(absSender, chat.getId(), msgHelp);
    }
}