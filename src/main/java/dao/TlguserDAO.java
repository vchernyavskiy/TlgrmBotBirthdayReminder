package dao;

import models.*;

//import java.util.List;

public interface TlguserDAO {
    void save(Tlguser tlguser);
    Tlguser findByChatId(Long chatId);
    void update(Tlguser tlguser);
    void delete(Tlguser tlguser);
}