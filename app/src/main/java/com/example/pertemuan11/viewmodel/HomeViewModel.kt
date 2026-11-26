package com.example.pertemuan11.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pertemuan11.modeldata.DataSiswa
import com.example.pertemuan11.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StatusUiSiswa {
    data class Success(val siswa: List<DataSiswa> = listOf()) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}
class HomeViewModel(private val repositoryDataSiswa: RepositoryDataSiswa): ViewModel() {
    var listSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init{
        loadSiswa()
    }

    fun loadSiswa(){
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading
            listSiswa = try {
                StatusUiSiswa.Success(repositoryDataSiswa.getDataSiswa())
            } catch (e:IOException){
                Log.e("HomeViewModel", "Gagal memuat data siswa (IOException)", e)
                StatusUiSiswa.Error
            } catch (e:HttpException){
                Log.e("HomeViewModel", "Gagal memuat data siswa (HTTP ${e.code()})", e)
                StatusUiSiswa.Error
            }  catch (e: Exception) {
                Log.e("HomeViewModel", "Gagal memuat data siswa", e)
                StatusUiSiswa.Error
            }
        }
    }

}
