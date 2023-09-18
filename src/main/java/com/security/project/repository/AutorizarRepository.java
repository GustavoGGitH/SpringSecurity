package com.security.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security.project.Model.Autorizar;

@Repository
public interface AutorizarRepository extends JpaRepository<Autorizar, Integer>{

}
