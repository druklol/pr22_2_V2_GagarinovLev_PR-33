package com.example.pr22_2_v2_gagarinovlev_pr33.registration.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    fun getUserByUsernameAndPassword(username: String, password: String): User?

    @Query("SELECT * FROM User WHERE username = :username")
    fun getUserByUsername(username: String): User?


}