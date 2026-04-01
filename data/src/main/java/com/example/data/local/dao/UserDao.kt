package com.example.data.local.dao

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun putUserData(user: UserEntity)

    @Update
    fun updateUserData(user: UserEntity)

    @Query("select * from users where login = :login limit 1")
    suspend fun getUserByLogin(login: String?): UserEntity?

//    @Transaction
//    @Query("select * from users")
//    fun getUserDonations(): List<UserDonations>
}