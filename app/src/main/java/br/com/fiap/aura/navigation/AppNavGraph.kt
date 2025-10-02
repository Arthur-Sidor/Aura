package br.com.fiap.aura.navigation

import CheckInScreen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.aura.screens.*
import br.com.fiap.aura.viewmodel.AuthViewModel


sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Entry : Screen("entry_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("signup_screen")
    object CheckIn : Screen("checkin_screen")
    object Comunicacao : Screen("visualizacao_dados_screen")
    object Carga : Screen("carga_screen")
    object Lideranca : Screen("lideranca_screen")
    object Alertas : Screen("alertas_screen")
    object Relacionamentos : Screen("relacionamentos_screen")
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val loggedIn = authViewModel.loginResult.collectAsState().value?.startsWith("ey") ?: false

    NavHost(
        navController = navController,
        startDestination = if (loggedIn) Screen.CheckIn.route else Screen.Welcome.route
    ) {
        // --- Welcome ---
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Screen.Entry.route) }
            )
        }

        // --- Entry ---
        composable(Screen.Entry.route) {
            EntryScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToRegister = { navController.navigate(Screen.SignUp.route) }
            )
        }

        // --- Login ---
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = { token ->
                    Toast.makeText(context, "Login realizado!", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.CheckIn.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavigateToRegister = { navController.navigate(Screen.SignUp.route) },

            )
        }

        // --- SignUp ---
        composable(Screen.SignUp.route) {
            SignUpScreen(
                viewModel = authViewModel,
                onRegisterSuccess = { token ->
                    Toast.makeText(context, "Cadastro realizado!", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.CheckIn.route) {
                        popUpTo(Screen.Welcome.route) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // --- CheckIn ---
        composable(Screen.CheckIn.route) {
            CheckInScreen(
                onNavigateToComunicacao = { navController.navigate(Screen.Comunicacao.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToAlertas = { navController.navigate(Screen.Alertas.route) },
                onNavigateToRelacionamentos = { navController.navigate(Screen.Relacionamentos.route) },
                onNavigateToLideranca = { navController.navigate(Screen.Lideranca.route) }
            )
        }

        // --- Comunicacao ---
        composable(Screen.Comunicacao.route) {
            ComunicacaoScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToAlertas = { navController.navigate(Screen.Alertas.route) },
                onNavigateToRelacionamentos = { navController.navigate(Screen.Relacionamentos.route) },
                onNavigateToLideranca = { navController.navigate(Screen.Lideranca.route) }
            )
        }

        // --- Carga ---
        composable(Screen.Carga.route) {
            CargaScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToComunicacao = { navController.navigate(Screen.Comunicacao.route) },
                onNavigateToAlertas = { navController.navigate(Screen.Alertas.route) },
                onNavigateToRelacionamentos = { navController.navigate(Screen.Relacionamentos.route) },
                onNavigateToLideranca = { navController.navigate(Screen.Lideranca.route) }
            )
        }

        // --- Alertas ---
        composable(Screen.Alertas.route) {
            AlertasScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToComunicacao = { navController.navigate(Screen.Comunicacao.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToRelacionamentos = { navController.navigate(Screen.Relacionamentos.route) },
                onNavigateToLideranca = { navController.navigate(Screen.Lideranca.route) }
            )
        }

        // --- Relacionamentos ---
        composable(Screen.Relacionamentos.route) {
            RelacionamentoScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToComunicacao = { navController.navigate(Screen.Comunicacao.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToAlertas = { navController.navigate(Screen.Alertas.route) },
                onNavigateToLideranca = { navController.navigate(Screen.Lideranca.route) }
            )
        }

        // --- Liderança ---
        composable(Screen.Lideranca.route) {
            LiderancaScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToComunicacao = { navController.navigate(Screen.Comunicacao.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToAlertas = { navController.navigate(Screen.Alertas.route) },
                onNavigateToRelacionamentos = { navController.navigate(Screen.Relacionamentos.route) },
                onNavigateToLideranca = { navController.navigate(Screen.Lideranca.route) }
            )
        }

    }

}