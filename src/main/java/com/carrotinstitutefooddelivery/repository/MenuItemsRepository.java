package com.carrotinstitutefooddelivery.repository;

import com.carrotinstitutefooddelivery.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MenuItemsRepository extends JpaRepository<MenuItem, UUID> {
    List<MenuItem>findMenuItemByMenuIdIn(List<UUID>menuItemIds);
}
