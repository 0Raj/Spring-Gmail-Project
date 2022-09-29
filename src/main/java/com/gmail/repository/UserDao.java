package com.gmail.repository;

import com.gmail.module.Mail;
import com.gmail.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    Optional<User> findByEmail(String s);

    List<Mail> findByDraft(User user);
    
    List<User> findByTrashMails(User user);
}
