package eu.arima.hibernatejpafetching.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "hibernate_sequence", initialValue = 100, allocationSize = 50)
    private long id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Project> projects;

    public Department(String name) {
        this.name = name;
    }

    public String toString() {
        String department = "Department " + this.id + " :: Name: " + this.name + " || \n";
        for (Project project : this.projects) {
            department += "   Project: " + project.getName() + "\n";
        }
        return department;
    }

}
