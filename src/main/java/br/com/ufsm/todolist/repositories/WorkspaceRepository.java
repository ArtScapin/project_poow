package br.com.ufsm.todolist.repositories;

import br.com.ufsm.todolist.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    List<Workspace> findAllByUserId(Long userId);
    long countByUser_Id(Long userId);
}
