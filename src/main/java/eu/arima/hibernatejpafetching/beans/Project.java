package eu.arima.hibernatejpafetching.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @ManyToOne
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    @Id
    private String name;

    public Project(String name) {
        this.name = name;
    }

}
