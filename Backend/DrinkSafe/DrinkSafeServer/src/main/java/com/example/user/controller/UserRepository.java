package com.example.user.controller;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import lombok.Data;

public interface UserRepository extends JpaRepository<User, Long> {


}