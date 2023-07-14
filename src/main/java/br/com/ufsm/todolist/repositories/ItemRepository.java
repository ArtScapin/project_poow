package br.com.ufsm.todolist.repositories;

import br.com.ufsm.todolist.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByListIdOrderById(Long listId);

    Long countByList_Workspace_User_Id(Long userId);
    Long countByList_Workspace_User_IdAndStatusTrue(Long userId);
    Long countByList_Workspace_User_IdAndStatusFalse(Long userId);
}
