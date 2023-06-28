package com.example.fcmilan.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fcmilan.entities.Fixture
import com.example.fcmilan.entities.Player
import com.example.fcmilan.repositories.FixtureRepository
import com.example.fcmilan.repositories.PlayerRepository
import kotlinx.coroutines.launch

class MainViewModel(private val playerRepository: PlayerRepository,
                    private val fixtureRepository: FixtureRepository): ViewModel() {
    private val _isHomeScreen = MutableLiveData<Boolean>()
    val isHomeCurrent: LiveData<Boolean> = _isHomeScreen
    val allPlayers: LiveData<List<Player>> = playerRepository.allPlayers.asLiveData()
    val allFixtures: LiveData<List<Fixture>> = fixtureRepository.allFixtures.asLiveData()

    fun insertPlayers(players:List<Player>)  = viewModelScope.launch{
        playerRepository.insertAll(players)
    }
    fun insertFixtures(fixtures:List<Fixture>) = viewModelScope.launch {
        fixtureRepository.insertAll(fixtures)
    }
    fun deletePlayers() = viewModelScope.launch {
        playerRepository.deleteAll()
    }
    fun deleteFixtures() = viewModelScope.launch {
        fixtureRepository.deleteAll()
    }
    fun setIsHomeScreen(isHome:Boolean) {
        _isHomeScreen.value = isHome
    }
}
class MainViewModelFactory(private val playerRepository: PlayerRepository,
                           private val fixtureRepository: FixtureRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(playerRepository, fixtureRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}