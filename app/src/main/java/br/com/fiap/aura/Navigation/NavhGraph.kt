package br.com.fiap.aura.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.aura.Screens.*

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object BemEstar : Screen("bemestar")
    object VisualizacaoDados : Screen("visualizacao_dados")
    object Lembretes : Screen("lembretes")
    object RecursosApoio : Screen("recursos_apoio")
    object AvaliacaoRiscos : Screen("avaliacao_riscos")
}


@Composable
fun AppNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Welcome.route) {

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Screen.BemEstar.route) }
            )
        }

        composable(Screen.BemEstar.route) {
            BemEstarScreen(
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToLembretes = { navController.navigate(Screen.Lembretes.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        composable(Screen.VisualizacaoDados.route) {
            VisualizacaoDadosScreen(
                onNavigateToBemEstar = { navController.navigate(Screen.BemEstar.route) },
                onNavigateToLembretes = { navController.navigate(Screen.Lembretes.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        composable(Screen.Lembretes.route) {
            LembretesScreen(
                onNavigateToBemEstar = { navController.navigate(Screen.BemEstar.route) },
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        composable(Screen.RecursosApoio.route) {
            RecursosApoioScreen(
                onNavigateToBemEstar = { navController.navigate(Screen.BemEstar.route) },
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToLembretes = { navController.navigate(Screen.Lembretes.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        composable(Screen.AvaliacaoRiscos.route) {
            AvaliacaoRiscosPsicossociaisScreen(
                onNavigateToBemEstar = { navController.navigate(Screen.BemEstar.route) },
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToLembretes = { navController.navigate(Screen.Lembretes.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) }
            )
        }
    }
}

