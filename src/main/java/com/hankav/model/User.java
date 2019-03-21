package com.hankav.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	private String ruuid;
	private String password;
	private String forgotlink;
	private boolean verified = false;
	@Embedded
	private Preferences user_preferences;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Tipster> tipster_profiles = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Item> cartitems = new HashSet<>();

	public User() {
		this.user_preferences = new Preferences("DECIMAL", "GMT+00:00", "USD");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Preferences getUser_preferences() {
		return this.user_preferences;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUser_preferences(Preferences user_preferences) {
		this.user_preferences = user_preferences;
	}

	public Set<Tipster> getTipster_profiles() {
		return tipster_profiles;
	}

	public void setTipster_profiles(Set<Tipster> tipster_profiles) {
		this.tipster_profiles = tipster_profiles;
	}

	public Set<Item> getCartitems() {
		return cartitems;
	}

	public void setCartitems(Set<Item> cartitems) {
		this.cartitems = cartitems;
	}

	public String getRuuid() {
		return ruuid;
	}

	public void setRuuid(String ruuid) {
		this.ruuid = ruuid;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public String getForgotlink() {
		return forgotlink;
	}

	public void setForgotlink(String forgotlink) {
		this.forgotlink = forgotlink;
	}

}