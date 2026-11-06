package com.adpp.mobile.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adpp.mobile.core.designsystem.components.ADPPBottomBar
import com.adpp.mobile.core.designsystem.theme.AppBackground
import com.adpp.mobile.feature.auth.presentation.screen.ForgotPasswordScreen
import com.adpp.mobile.feature.auth.presentation.screen.LoginScreen
import com.adpp.mobile.feature.auth.presentation.screen.RegisterScreen
import com.adpp.mobile.feature.auth.presentation.viewmodel.AuthViewModel
import com.adpp.mobile.feature.competition.presentation.screen.CompetitionDetailsScreen
import com.adpp.mobile.feature.competition.presentation.screen.CompetitionListScreen
import com.adpp.mobile.feature.competition.presentation.screen.TournamentBracketScreen
import com.adpp.mobile.feature.competition.presentation.viewmodel.CompetitionViewModel
import com.adpp.mobile.feature.debate.presentation.screen.AIDebateSessionScreen
import com.adpp.mobile.feature.debate.presentation.screen.AIDebateSetupScreen
import com.adpp.mobile.feature.debate.presentation.screen.AIEvaluationResultScreen
import com.adpp.mobile.feature.debate.presentation.screen.CreateDebateRoomScreen
import com.adpp.mobile.feature.debate.presentation.screen.DebateLobbyScreen
import com.adpp.mobile.feature.debate.presentation.screen.DebateModeSelectionScreen
import com.adpp.mobile.feature.debate.presentation.screen.DebateRoomBrowserScreen
import com.adpp.mobile.feature.debate.presentation.screen.LiveDebateScreen
import com.adpp.mobile.feature.debate.presentation.viewmodel.DebateViewModel
import com.adpp.mobile.feature.explore.presentation.screen.ExploreScreen
import com.adpp.mobile.feature.explore.presentation.viewmodel.ExploreViewModel
import com.adpp.mobile.feature.home.presentation.screen.HomeScreen
import com.adpp.mobile.feature.home.presentation.viewmodel.HomeViewModel
import com.adpp.mobile.feature.leaderboard.presentation.screen.LeaderboardScreen
import com.adpp.mobile.feature.onboarding.presentation.screen.OnboardingScreen
import com.adpp.mobile.feature.profile.presentation.screen.DebateHistoryDetailsScreen
import com.adpp.mobile.feature.profile.presentation.screen.DebateHistoryScreen
import com.adpp.mobile.feature.profile.presentation.screen.EditProfileScreen
import com.adpp.mobile.feature.profile.presentation.screen.ProfileScreen
import com.adpp.mobile.feature.profile.presentation.screen.SettingsScreen
import com.adpp.mobile.feature.profile.presentation.viewmodel.ProfileViewModel
import com.adpp.mobile.feature.splash.presentation.screen.SplashScreen

@Composable
fun ADPPNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = setOf(
        Screen.Home.route,
        Screen.DebateModeSelection.route,
        Screen.DebateHistory.route,
        Screen.Profile.route
    )

    // Shared ViewModels for state retention
    val authViewModel: AuthViewModel = viewModel()
    val homeViewModel: HomeViewModel = viewModel()
    val exploreViewModel: ExploreViewModel = viewModel()
    val debateViewModel: DebateViewModel = viewModel()
    val competitionViewModel: CompetitionViewModel = viewModel()
    val profileViewModel: ProfileViewModel = viewModel()

    Scaffold(
        containerColor = AppBackground,
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                ADPPBottomBar(
                    currentRoute = currentRoute,
                    onNavigateToRoute = { route ->
                        if (currentRoute != route) {
                            navController.navigate(route) {
                                popUpTo(Screen.Home.route) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(220)) },
            exitTransition = { fadeOut(animationSpec = tween(220)) }
        ) {
            // 1. Splash Screen
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(Screen.Splash.route) { inclusive = true }
                        }
                    }
                )
            }

            // 2. Onboarding Screen
            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }

            // 3. Login Screen
            composable(Screen.Login.route) {
                LoginScreen(
                    viewModel = authViewModel,
                    onNavigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Screen.Register.route)
                    },
                    onNavigateToForgotPassword = {
                        navController.navigate(Screen.ForgotPassword.route)
                    }
                )
            }

            // 4. Register Screen
            composable(Screen.Register.route) {
                RegisterScreen(
                    viewModel = authViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Register.route) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.popBackStack()
                    }
                )
            }

            // 5. Forgot Password Screen
            composable(Screen.ForgotPassword.route) {
                ForgotPasswordScreen(
                    viewModel = authViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.ForgotPassword.route) { inclusive = true }
                        }
                    }
                )
            }

            // 6. Home Dashboard Screen
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = homeViewModel,
                    onNavigateToDebateSetup = {
                        navController.navigate(Screen.AIDebateSetup.route)
                    },
                    onNavigateToExplore = {
                        navController.navigate(Screen.Explore.route)
                    },
                    onNavigateToProfile = {
                        navController.navigate(Screen.Profile.route)
                    },
                    onNavigateToHistory = {
                        navController.navigate(Screen.DebateHistory.route)
                    },
                    onNavigateTo1vs1 = {
                        navController.navigate(Screen.DebateRoomBrowser.route)
                    }
                )
            }

            // 7. Explore Debate Topics Screen
            composable(Screen.Explore.route) {
                ExploreScreen(
                    viewModel = exploreViewModel,
                    onTopicSelected = { topicId ->
                        navController.navigate(Screen.AIDebateSetup.route)
                    }
                )
            }

            // 8. Debate Mode Selection Screen
            composable(Screen.DebateModeSelection.route) {
                DebateModeSelectionScreen(
                    onNavigateToAISetup = {
                        navController.navigate(Screen.AIDebateSetup.route)
                    },
                    onNavigateTo1vs1 = {
                        navController.navigate(Screen.DebateRoomBrowser.route)
                    }
                )
            }

            // 9. AI Debate Setup Screen
            composable(Screen.AIDebateSetup.route) {
                AIDebateSetupScreen(
                    viewModel = debateViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onStartDebate = {
                        navController.navigate(Screen.AIDebateSession.route)
                    }
                )
            }

            // 10. AI Debate Session Screen
            composable(Screen.AIDebateSession.route) {
                AIDebateSessionScreen(
                    viewModel = debateViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onEndDebateToEvaluation = {
                        navController.navigate(Screen.AIEvaluationResult.route)
                    }
                )
            }

            // 11. AI Evaluation Result Screen
            composable(Screen.AIEvaluationResult.route) {
                AIEvaluationResultScreen(
                    viewModel = debateViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onDebateAgain = {
                        navController.navigate(Screen.AIDebateSetup.route) {
                            popUpTo(Screen.Home.route)
                        }
                    },
                    onReturnToHome = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }

            // 12. Debate Room Browser Screen
            composable(Screen.DebateRoomBrowser.route) {
                DebateRoomBrowserScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onCreateRoom = {
                        navController.navigate(Screen.CreateDebateRoom.route)
                    },
                    onJoinRoom = { roomId ->
                        navController.navigate(Screen.DebateLobby.route)
                    }
                )
            }

            // 13. Create Debate Room Screen
            composable(Screen.CreateDebateRoom.route) {
                CreateDebateRoomScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onRoomCreated = { roomId ->
                        navController.navigate(Screen.DebateLobby.route)
                    }
                )
            }

            // 14. Debate Lobby Screen
            composable(Screen.DebateLobby.route) {
                DebateLobbyScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onStartLiveMatch = {
                        navController.navigate(Screen.LiveDebate.route)
                    }
                )
            }

            // 15. Live Debate 1v1 / 2v2 Screen
            composable(Screen.LiveDebate.route) {
                LiveDebateScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onFinishDebateToEvaluation = {
                        navController.navigate(Screen.AIEvaluationResult.route)
                    }
                )
            }

            // 16. Competition List Screen
            composable(Screen.CompetitionList.route) {
                CompetitionListScreen(
                    viewModel = competitionViewModel,
                    onCompetitionSelected = { compId ->
                        navController.navigate(Screen.CompetitionDetails.route)
                    },
                    onNavigateToLeaderboard = {
                        navController.navigate(Screen.Leaderboard.route)
                    }
                )
            }

            // 17. Competition Details Screen
            composable(Screen.CompetitionDetails.route) {
                CompetitionDetailsScreen(
                    competitionId = "comp-1",
                    onNavigateBack = { navController.popBackStack() },
                    onViewBracket = {
                        navController.navigate(Screen.TournamentBracket.route)
                    }
                )
            }

            // 18. Tournament Bracket Screen
            composable(Screen.TournamentBracket.route) {
                TournamentBracketScreen(
                    viewModel = competitionViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // 19. Leaderboard Screen
            composable(Screen.Leaderboard.route) {
                LeaderboardScreen(
                    viewModel = competitionViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // 20. User Profile Screen
            composable(Screen.Profile.route) {
                ProfileScreen(
                    viewModel = profileViewModel,
                    onNavigateToEditProfile = {
                        navController.navigate(Screen.EditProfile.route)
                    },
                    onNavigateToHistory = {
                        navController.navigate(Screen.DebateHistory.route)
                    },
                    onNavigateToSettings = {
                        navController.navigate(Screen.Settings.route)
                    },
                    onSignOutToLogin = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // 21. Edit Profile Screen
            composable(Screen.EditProfile.route) {
                EditProfileScreen(
                    viewModel = profileViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // 22. Debate History Screen
            composable(Screen.DebateHistory.route) {
                DebateHistoryScreen(
                    viewModel = profileViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onHistoryItemClick = { matchId ->
                        navController.navigate(Screen.DebateHistoryDetails.route)
                    }
                )
            }

            // 23. Debate History Details Screen
            composable(Screen.DebateHistoryDetails.route) {
                DebateHistoryDetailsScreen(
                    matchId = "hist-1",
                    onNavigateBack = { navController.popBackStack() },
                    onReSparTopic = {
                        navController.navigate(Screen.AIDebateSetup.route)
                    }
                )
            }

            // 24. Settings Screen
            composable(Screen.Settings.route) {
                SettingsScreen(
                    viewModel = profileViewModel,
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}

