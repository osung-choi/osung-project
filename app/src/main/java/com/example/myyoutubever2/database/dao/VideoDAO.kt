package com.example.myyoutubever2.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.myyoutubever2.database.entity.VideoDB

@Dao
interface VideoDAO: BaseDAO<VideoDB> {
    @Query("SELECT * FROM VideoDB")
    fun selectVideoAll(): LiveData<List<VideoDB>>

    @Query("SELECT * FROM VideoDB WHERE userSeq IN (:seqList)")
    fun getUserVideoList(seqList: List<Int>) : LiveData<List<VideoDB>>

    @Query("SELECT video.* FROM SubscribeDB AS subscribe LEFT JOIN VideoDB AS video ON subscribe.userSubscribeSeq = video.userSeq WHERE subscribe.userSeq = :seq")
    fun getSubscribeUserVideoList(seq: Int): LiveData<List<VideoDB>>
}