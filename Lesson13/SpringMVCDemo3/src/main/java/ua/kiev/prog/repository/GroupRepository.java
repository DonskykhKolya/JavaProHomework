package ua.kiev.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.kiev.prog.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
