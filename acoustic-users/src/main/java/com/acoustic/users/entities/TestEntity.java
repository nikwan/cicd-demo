package com.acoustic.users.entities;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "test")
public class TestEntity
{
    @Column(name = "name")
    String name;
    @Id
    int id;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(final int id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "TestEntity [name=" + name + ", id=" + id + "]";
	}
    
    
}