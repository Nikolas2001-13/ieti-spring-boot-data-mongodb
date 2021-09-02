package org.ada.school.repository;

import org.ada.school.dto.UserDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class UserDocument
{
    @Id
    String id;

    String name;

    @Indexed( unique = true )
    String email;

    String lastName;

    Date createdAt;

    public UserDocument(String id, String name, String email, String lastName, Date createdAt){
        setId(id);
        setName(name);
        setEmail(email);
        setLastName(lastName);
        setCreatedAt(createdAt);
    }

    public UserDocument()
    {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    public void update( UserDto userDto )
    {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
    }
}


