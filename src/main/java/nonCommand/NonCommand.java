package nonCommand;

import models.Event;
import models.Tlguser;
import services.EventService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

/**
 * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
 */
public class NonCommand {

    public String nonCommandExecute(Tlguser tlguser, String textIn) {
        String answerOut;

        if (tlguser.getState().equals("add")) {
            if (textIsCorrectBD(textIn)) {
                //Сохраняем ДР в базе

                Map<String, Object> map = new HashMap<>();
                map.put("date", null);
                map.put("description", null);

                parsTextIn(map, textIn);

                LocalDate localDate = (LocalDate) map.get("date");
                String description = String.valueOf(map.get("description"));

                Event event = new Event(description, localDate, tlguser);
                EventService.saveEvent(event);

                answerOut = "Добавлено: " + textIn;
            } else {
                answerOut = "Введенный текст не соответствует формату";
            }
        } else {
            answerOut = textIn;
        }

        return answerOut;
    }

    private void parsTextIn(Map<String, Object> map, String textIn) {
        Pattern regexp = Pattern.compile("\\d+");
        Matcher m = regexp.matcher(textIn);

        int len = 3;
        int[] d = new int[len];
        int end = 0;

        for (int i = 0; i < len && m.find(); i++) {
            d[i] = Integer.parseInt(m.group());
            end = m.end();
        }

        map.put("date", LocalDate.of(d[2], d[1], d[0]));
        map.put("description", textIn.substring(end).trim());
    }

    private boolean textIsCorrectBD(String text) {
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

            for (int e : d) {
                if (e == 0) {
                    return false;
                }
            }

            localDate = LocalDate.of(d[2], d[1], d[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        String textDescr = text.substring(end).trim();

        return !textDescr.equals("");

    }
}