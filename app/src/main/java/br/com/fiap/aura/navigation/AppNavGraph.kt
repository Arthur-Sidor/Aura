
package br.com.fiap.aura.navigation

import CheckInScreen
import android.widget.Toast
import androidx.compose.runtime.Composable

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.aura.Screens.*

// --- Simulação de Autenticação (coloque isso em um ViewModel/Repository em um app real) ---
object FakeAuthManager {
    private val registeredUsers = mutableMapOf<String, String>() // email to password
    var loggedInUserEmail: String? = null
        private set

    fun isUserRegistered(email: String): Boolean = registeredUsers.containsKey(email)

    fun registerUser(email: String, pass: String): Boolean {
        if (isUserRegistered(email)) {
            return false // Usuário já existe
        }
        registeredUsers[email] = pass
        return true
    }

    fun loginUser(email: String, pass: String): Boolean {
        val isLoginSuccessful = registeredUsers[email] == pass
        if (isLoginSuccessful) {
            loggedInUserEmail = email
        }
        return isLoginSuccessful
    }

    fun logoutUser() {
        loggedInUserEmail = null
    }
}
// --- Fim da Simulação de Autenticação ---

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome_screen")
    object Entry : Screen("entry_screen")
    object Login : Screen("login_screen")
    object SignUp : Screen("signup_screen")
    object CheckIn : Screen("checkin_screen")
    object VisualizacaoDados : Screen("visualizacao_dados_screen")
    object Carga : Screen("carga_screen")
    object RecursosApoio : Screen("recursos_apoio_screen")
    object AvaliacaoRiscos : Screen("avaliacao_riscos_screen")
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current // Para Toasts

    NavHost(
        navController = navController,
        startDestination = if (FakeAuthManager.loggedInUserEmail != null) Screen.CheckIn.route else Screen.Welcome.route
        // Se já estiver logado (ex: app reiniciado), vai direto para CheckIn
    ) {
        composable(Screen.Welcome.route) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Screen.Entry.route) }
            )
        }

        composable(Screen.Entry.route) {
            EntryScreen(
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onNavigateToRegister = { navController.navigate(Screen.SignUp.route) }
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { email, senha ->
                    if (FakeAuthManager.loginUser(email, senha)) {
                        // Login bem-sucedido
                        navController.navigate(Screen.CheckIn.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true } // Limpa toda a pilha até Welcome
                            launchSingleTop = true
                        }
                    } else {
                        // Login falhou
                        Toast.makeText(context, "Email ou senha inválidos.", Toast.LENGTH_SHORT).show()
                        // Não redireciona, permite nova tentativa ou ir para cadastro
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.SignUp.route)
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                onRegisterClick = { nome, email, cpf, senha ->
                    if (email.isBlank() || senha.isBlank() || nome.isBlank() || cpf.isBlank()) {
                        Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
                        return@SignUpScreen
                    }
                    if (FakeAuthManager.isUserRegistered(email)) {
                        Toast.makeText(context, "Este email já está cadastrado.", Toast.LENGTH_SHORT).show()
                        return@SignUpScreen
                    }

                    if (FakeAuthManager.registerUser(email, senha)) {
                        // Cadastro e login automático simulado
                        FakeAuthManager.loginUser(email, senha) // Loga o usuário recém-cadastrado
                        Toast.makeText(context, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.CheckIn.route) {
                            popUpTo(Screen.Welcome.route) { inclusive = true } // Limpa toda a pilha até Welcome
                            launchSingleTop = true
                        }
                    } else {
                        // Isso não deveria acontecer se a checagem isUserRegistered foi feita
                        Toast.makeText(context, "Falha no cadastro.", Toast.LENGTH_SHORT).show()
                    }
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.CheckIn.route) {
            // Adicionar um "logout" simulado para testar
            val onLogout = {
                FakeAuthManager.logoutUser()
                navController.navigate(Screen.Entry.route) {
                    popUpTo(Screen.CheckIn.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
            CheckInScreen(
                //onLogoutAction = onLogout, // Você precisaria adicionar este parâmetro à CheckInScreen
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        // ... Outras rotas (VisualizacaoDados, Carga, etc.) permanecem as mesmas
        composable(Screen.VisualizacaoDados.route) {
            VisualizacaoDadosScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        composable(Screen.Carga.route) {
            CargaScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }


        composable(Screen.RecursosApoio.route) {
            RecursosApoioScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToAvaliacaoRiscos = { navController.navigate(Screen.AvaliacaoRiscos.route) }
            )
        }

        composable(Screen.AvaliacaoRiscos.route) {
            AvaliacaoRiscosPsicossociaisScreen(
                onNavigateToCheckIn = { navController.navigate(Screen.CheckIn.route) },
                onNavigateToVisualizacaoDados = { navController.navigate(Screen.VisualizacaoDados.route) },
                onNavigateToCarga = { navController.navigate(Screen.Carga.route) },
                onNavigateToRecursosApoio = { navController.navigate(Screen.RecursosApoio.route) }
            )
        }
    }
}

