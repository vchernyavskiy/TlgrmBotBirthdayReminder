package dao;

import models.*;

//import java.util.List;

public interface TlguserDAO {

    Tlguser findByChatId(Long chatId);

    void save(Tlguser tlguser);

    void update(Tlguser tlguser);

    void delete(Tlguser tlguser);

    //    List<Tlguser> findAll();
}