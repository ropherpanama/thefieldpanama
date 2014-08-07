package com.thefieldpanama.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String login;
    private String password;
   
    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="roles",
        joinColumns = {@JoinColumn(name="id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="id", referencedColumnName="id")}
    )
    private Roles role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }  
}
