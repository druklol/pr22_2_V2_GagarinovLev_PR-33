package com.example.pr22_2_v2_gagarinovlev_pr33.registration.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val username: String,
    val password: String
)