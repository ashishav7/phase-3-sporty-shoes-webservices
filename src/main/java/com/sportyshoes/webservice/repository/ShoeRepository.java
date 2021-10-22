package com.sportyshoes.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.webservice.entity.Shoe;

public interface ShoeRepository extends JpaRepository<Shoe, Long>{

}
