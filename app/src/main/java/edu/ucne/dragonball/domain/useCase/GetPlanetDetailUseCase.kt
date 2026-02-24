package edu.ucne.dragonball.domain.useCase

import edu.ucne.dragonball.domain.repository.planetRepository
import javax.inject.Inject

class GetPlanetDetailUseCase @Inject constructor(
    private val repository: planetRepository
) {
    suspend operator fun invoke(id: Int) = repository.getPlanetDetail(id)
}