package com.nayson.minhaLoja.repository;

import com.nayson.minhaLoja.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT * FROM clients WHERE email = :email", nativeQuery = true)
    Client getClientByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM clients WHERE id = :id", nativeQuery = true)
    Client getClientById(@Param("id") long id);
}
