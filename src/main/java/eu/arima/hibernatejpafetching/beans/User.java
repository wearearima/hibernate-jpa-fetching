package eu.arima.hibernatejpafetching.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"nickname"}))
@Getter
@Setter
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "hibernate_sequence", initialValue = 100, allocationSize = 50)
    private long id;

    private String nickname;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Department> departments;

    public User(String nickname, List<Department> departments) {
        this.nickname = nickname;
        this.departments = departments;
    }

    public String toString() {
        String user = "User " + this.id + " :: Nickname: " + this.nickname + " || \n";
        for (Department department : this.departments) {
            user += "   Department: " + department.toString() + "\n";
        }
        return user;
    }
}
