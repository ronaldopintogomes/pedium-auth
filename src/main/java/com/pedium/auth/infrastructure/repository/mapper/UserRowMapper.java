package com.pedium.auth.infrastructure.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.pedium.auth.core.domain.entity.Contact;
import com.pedium.auth.core.domain.entity.Role;
import com.pedium.auth.core.domain.entity.User;

public class UserRowMapper implements RowMapper<User>{
 @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUid(rs.getString("uid"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        user.setRole(Role.valueOf(rs.getString("role")));
        user.setRegistrationDate(rs.getObject("registration_date", OffsetDateTime.class));

        Contact contact = new Contact();
        contact.setEmail(rs.getString("email"));
        contact.setCellphone(rs.getString("cellphone"));
        user.setContact(contact);

        return user;
    }   
}