package com.adpp.mobile.core.navigation

sealed class Screen(val route: String) {
    // Auth & Onboarding
    data object Splash : Screen("splash")
    data object Onboarding : Screen("onboarding")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object ForgotPassword : Screen("forgot_password")

    // Main bottom tabs
    data object Home : Screen("home_screen")
    data object Explore : Screen("explore_screen")
    data object DebateModeSelection : Screen("debate_mode_selection_screen")
    data object CompetitionList : Screen("competition_list_screen")
    data object Profile : Screen("profile_screen")

    // AI Debate Flow
    data object AIDebateSetup : Screen("ai_debate_setup_screen")
    data object AIDebateSession : Screen("ai_debate_session_screen")
    data object AIEvaluationResult : Screen("ai_evaluation_result_screen")

    // Multiplayer Flow
    data object DebateRoomBrowser : Screen("debate_room_browser_screen")
    data object CreateDebateRoom : Screen("create_debate_room_screen")
    data object DebateLobby : Screen("debate_lobby_screen")
    data object LiveDebate : Screen("live_debate_screen")

    // Competition Flow
    data object CompetitionDetails : Screen("competition_details_screen")
    data object TournamentBracket : Screen("tournament_bracket_screen")
    data object Leaderboard : Screen("leaderboard_screen")

    // Profile Flow
    data object EditProfile : Screen("edit_profile_screen")
    data object DebateHistory : Screen("debate_history_screen")
    data object DebateHistoryDetails : Screen("debate_history_details_screen")
    data object Settings : Screen("settings_screen")
}

