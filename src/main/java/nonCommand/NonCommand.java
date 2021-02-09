package nonCommand;

import models.Tlguser;

import java.time.LocalDate;
import java.util.regex.*;

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
        //проверка корректности текст на "Дата описание"

        Pattern regexp = Pattern.compile("\\d+");
        Matcher m = regexp.matcher(text);

        int len = 3;
        int[] d = new int[len];
        int end = 0;
        LocalDate localDate;

        try {
            for (int i = 0; i < len && m.find(); i++) {
                d[i] = Integer.parseInt(m.group());
                end = m.end();
            }

            for (int e : d){
                if (e == 0) {
                    return false;
                }
            }

            localDate = LocalDate.of(d[2], d[1], d[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        String textDiscr = text.substring(end).trim();

        return !textDiscr.equals("");

    }
}