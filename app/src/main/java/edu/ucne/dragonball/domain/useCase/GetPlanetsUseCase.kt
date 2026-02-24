package edu.ucne.dragonball.domain.useCase

import edu.ucne.dragonball.domain.repository.planetRepository
import javax.inject.Inject

class GetPlanetsUseCase @Inject constructor(
    private val repository: planetRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        limit: Int = 10,
        name: String? = null
    ) = repository.getPlanets(page, limit, name)
}