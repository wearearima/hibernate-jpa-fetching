package eu.arima.hibernatejpafetching.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eu.arima.hibernatejpafetching.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select distinct u from User u join fetch u.departments")
    public List<User> findUsers();
    
}
