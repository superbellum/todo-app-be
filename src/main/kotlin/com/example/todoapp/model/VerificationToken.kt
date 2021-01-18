package com.example.todoapp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "verification_tokens")
data class VerificationToken(
    @Id
    var id: UUID? = null,

    var token: String? = null,

    @OneToOne
    @MapsId
    @JsonIgnore
    var user: User? = null,

    var expiresAt: Instant? = null
) {
    fun expired() = expiresAt!!.isBefore(Instant.now())
}