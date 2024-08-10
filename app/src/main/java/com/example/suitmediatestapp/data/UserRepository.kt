package com.example.suitmediatestapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmediatestapp.Result
import com.google.gson.Gson
import retrofit2.HttpException

class UserRepository (
    private val apiService: ApiService,
){

    fun getuser(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService)
            }
        ).liveData
    }

    fun getUsers(): LiveData<Result<List<DataItem>>> = liveData{
        emit(Result.Loading)
        try{
            val response = apiService.getusers()
            emit(Result.Success(response.listUser))
        }catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
            emit(Result.Error(errorResponse.message))
        }catch (e: Exception){
            emit(Result.Error(e.message ?: "Error"))
        }
    }


    companion object {
        private var INSTANCE: UserRepository? = null
        fun clearInstance(){
            INSTANCE = null
        }
        fun getInstance(
            apiService: ApiService,
        ): UserRepository =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: UserRepository(apiService)
        }.also { INSTANCE = it }
    }
}