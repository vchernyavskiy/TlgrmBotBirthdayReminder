package services;

import dao.TlguserDAOImpl;
import models.Tlguser;

public class TlguserService {

    private static TlguserDAOImpl tlguserDAOImpl = new TlguserDAOImpl();

    public static Tlguser findTlguserByChatId(Long chatId) {
        return tlguserDAOImpl.findByChatId(chatId);
    }

    public static void saveTlguser(Tlguser tlguser) {
        tlguserDAOImpl.save(tlguser);
    }

    public static void updateTlguser(Tlguser tlguser) {
        tlguserDAOImpl.update(tlguser);
    }

    public static void deleteTlguser(Tlguser tlguser) {
        tlguserDAOImpl.delete(tlguser);
    }

}
