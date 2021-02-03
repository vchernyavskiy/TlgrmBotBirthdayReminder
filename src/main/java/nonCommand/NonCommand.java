package nonCommand;

import models.Tlguser;

/**
 * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
 */
public class NonCommand {

    public String nonCommandExecute(Tlguser tlguser, String textIn) {
        String answerOut;

        if (tlguser.getState().equals("add")) {
            if (textIsCorrectBD(textIn)) {
                //добавляем ДР в список
                answerOut = "Добавлено: " + textIn;
            } else {
                answerOut = "Введенный текст не соответствует формату";
            }
        } else {
            answerOut = textIn;
        }

        return answerOut;
    }

    private boolean textIsCorrectBD(String text){
        //проверка корректности текст на "ДР - описание"
        return true;
    }
}