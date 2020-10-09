package eu.arima.hibernatejpafetching.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.arima.hibernatejpafetching.beans.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
}
