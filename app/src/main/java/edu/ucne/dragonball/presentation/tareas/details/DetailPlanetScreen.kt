package edu.ucne.dragonball.presentation.tareas.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.dragonball.domain.model.planet
import edu.ucne.dragonball.ui.theme.DragonBallTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPlaetScreen(
    viewModel: PlanetDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PlanetDetailBodyScreen(
        state = state,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailBodyScreen(
    state: DetailPlanetUiState,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle del Planeta") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.planet?.let { planet ->
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = planet.name,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (planet.isDestroyed) "Estado: Destruido" else "Estado: Intacto",
                        color = if (planet.isDestroyed) Color.Red else Color.Green,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Descripción:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = planet.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlanetDetailBodyScreenPreview() {
    val samplePlanet = planet(
        id = 1,
        name = "Namek",
        isDestroyed = true,
        description = "Planeta natal de Piccolo y Kami. Fue destruido por Freezer durante su batalla contra Goku.",
    )

    val state = DetailPlanetUiState(
        planet = samplePlanet
    )

    DragonBallTheme {
        Surface {
            PlanetDetailBodyScreen(
                state = state,
                onBack = {}
            )
        }
    }
}