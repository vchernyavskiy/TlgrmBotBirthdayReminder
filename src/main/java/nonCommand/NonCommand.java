package nonCommand;

/**
 * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
 */
public class NonCommand {

    public String nonCommandExecute(Long chatId, String text) {
        String answer;
        answer = text;
//        try {
//            //добавляем настройки в мапу, чтобы потом их использовать для этого пользователя при генерации файла
//            answer = "Настройки обновлены. Вы всегда можете их посмотреть с помощью /settings";
//            //логируем событие, используя userName
//        } catch (IllegalSettingsException e) {
//            answer = e.getMessage() +
//                    "\n\n Настройки не были изменены. Вы всегда можете их посмотреть с помощью /settings";
//            //логируем событие, используя userName
//        } catch (Exception e) {
//            answer = "Простите, я не понимаю Вас. Возможно, Вам поможет /help";
//            //логируем событие, используя userName
//        }
        return answer;
    }
}