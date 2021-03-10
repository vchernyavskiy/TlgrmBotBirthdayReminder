package nonCommand;

import models.Event;
import models.Tlguser;
import services.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

/**
 * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
 */
public class NonCommand {

    public String nonCommandExecute(Tlguser tlguser, String textIn) {
        String answerOut = "Введенный текст не соответствует формату";

        if (tlguser.getState().equals("add") && textIsCorrectBD(textIn)) {
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
        }

        if (tlguser.getState().equals("delete")) {
            //проверить, что есть события
            List<Event> events = EventService.selectEventsByUser(tlguser);
            Collections.sort(events);
            if (events.size() == 0) {
                answerOut = "Вы еще не добавили ни одного напоминания.\nПомощь /help";
                return answerOut;
            }

            //проверить, что введен int
            int num = 0;
            try {
                num = Integer.parseInt(textIn);
            } catch (NumberFormatException ex) {
                int i = 1;
                answerOut = "Введите число с номером строки:";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                for(Event e : events){
                    answerOut += "\n" + i + ". " + e.getDescription() + ", " + e.getDate().format(formatter);
                    i++;
                }
                return answerOut;
            }

            //проверить что введенный номер не больше количества событий
            if ((events.size() < num) || (num < 1)) {
                int i = 1;
                answerOut = "Введен несуществующий номер.\nВведите номер для удаления:";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                for(Event e : events){
                    answerOut += "\n" + i + ". " + e.getDescription() + ", " + e.getDate().format(formatter);
                    i++;
                }
                return answerOut;
            }

            //удалить
            Event event = events.get(num - 1);
            EventService.deleteEvent(event);
            answerOut = "";

            //вывести список после удаления
            events = EventService.selectEventsByUser(tlguser);
            Collections.sort(events);
            if (events.size() == 0) {
                answerOut = "Все напоминания удалены";
            } else {
                int i = 1;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                for(Event e : events){
                    answerOut += "\n" + i + ". " + e.getDescription() + ", " + e.getDate().format(formatter);
                    i++;
                }
            }
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
        //проверка корректности текста на "Дата описание"

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