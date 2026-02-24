package edu.ucne.dragonball.presentation.tareas.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.dragonball.data.remote.Resource
import edu.ucne.dragonball.domain.useCase.GetPlanetDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val getPlanetDetailUseCase: GetPlanetDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailPlanetUiState())
    val state = _state.asStateFlow()

    init {
        // Asumimos que el ID del planeta se pasa a través de la navegación
        val planetId = savedStateHandle.get<Int>("id") ?: -1
        if (planetId != -1) {
            loadPlanetDetail(planetId)
        } else {
            _state.update { it.copy(error = "ID de planeta no válido") }
        }
    }

    private fun loadPlanetDetail(id: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = getPlanetDetailUseCase(id)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            planet = result.data
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}