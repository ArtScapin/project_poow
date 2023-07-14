package br.com.ufsm.todolist.repositories;

import br.com.ufsm.todolist.model.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
    java.util.List<List> findAllByWorkspaceId(Long workspaceId);
    Long countByWorkspace_User_Id(Long userId);
}
