package com.projects.myHRBackend.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.myHRBackend.modal.MyHRUser;

@Repository
public interface MyHRUserRepo extends JpaRepository<MyHRUser, Integer>{
	public Optional<MyHRUser> findByUsername(String username);
}
