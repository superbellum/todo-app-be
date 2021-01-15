package com.example.todoapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

data class UserDetailsImpl(
    private val id: UUID?,
    private val username: String?,
    private val email: String?,
    private val enabled: Boolean = false,
    @JsonIgnore private val password: String?,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    companion object {
        const val serialVersionUID = 1L

        fun build(user: User) =
            UserDetailsImpl(
                id = user.id,
                email = user.email,
                enabled = user.enabled,
                password = user.password,
                username = user.username,
                authorities = user.roles.map { SimpleGrantedAuthority(it.name.name) }
            )
    }

    override fun getAuthorities() = authorities

    override fun getPassword() = password

    override fun getUsername() = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = enabled
}