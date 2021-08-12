package com.example.vinib.msrapi.domain.repository;

import com.example.vinib.msrapi.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository  extends JpaRepository<Entrega, Long> {
}
