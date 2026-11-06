package com.adpp.mobile.feature.competition.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CompetitionItem(
    val id: String,
    val title: String,
    val format: String,
    val prizePool: String,
    val dateRange: String,
    val registeredTeams: Int,
    val maxTeams: Int,
    val status: String,
    val description: String
)

data class LeaderboardUser(
    val rank: Int,
    val name: String,
    val institution: String,
    val elo: Int,
    val winRate: Int,
    val matchesPlayed: Int
)

data class BracketMatch(
    val matchId: String,
    val roundName: String,
    val team1: String,
    val team1Score: Int,
    val team2: String,
    val team2Score: Int,
    val winner: String
)

data class CompetitionUiState(
    val competitions: List<CompetitionItem> = listOf(
        CompetitionItem(
            id = "comp-1",
            title = "Giải Vô địch Tranh biện Sinh viên Toàn quốc 2026",
            format = "Nghị viện Anh BP (2v2)",
            prizePool = "50.000.000 đ",
            dateRange = "15/11 - 20/11/2026",
            registeredTeams = 64,
            maxTeams = 64,
            status = "Đang diễn ra",
            description = "Giải đấu tranh biện học thuật danh giá quy mô toàn quốc theo luật thi đấu Nghị viện Anh tiêu chuẩn, ứng dụng hệ thống AI phân tích phản biện trực tiếp."
        ),
        CompetitionItem(
            id = "comp-2",
            title = "Giải Mở rộng Tranh biện Đạo đức AI & Công nghệ",
            format = "Tranh biện Oxford (1v1)",
            prizePool = "25.000.000 đ",
            dateRange = "01/12 - 05/12/2026",
            registeredTeams = 28,
            maxTeams = 32,
            status = "Đang mở đăng ký",
            description = "Sân chơi chuyên đề tranh luận về giới hạn đạo đức của mô hình ngôn ngữ lớn, bản quyền dữ liệu đào tạo và khuôn khổ pháp lý AI toàn cầu."
        ),
        CompetitionItem(
            id = "comp-3",
            title = "Cúp Tranh biện Mùa Xuân Tân sinh viên 2027",
            format = "Lincoln-Douglas",
            prizePool = "15.000.000 đ",
            dateRange = "10/01 - 15/01/2027",
            registeredTeams = 16,
            maxTeams = 32,
            status = "Sắp diễn ra",
            description = "Giải đấu học thuật nhập môn rèn luyện tư duy logic, cấu trúc lập luận chặt chẽ và khả năng hùng biện thuyết phục trước đám đông."
        )
    ),
    val topDebaters: List<LeaderboardUser> = listOf(
        LeaderboardUser(1, "Hoàng Minh Trí", "ĐH Quốc gia Hà Nội", 2540, 88, 142),
        LeaderboardUser(2, "Trần Mai Phương", "ĐH Ngoại thương", 2495, 84, 130),
        LeaderboardUser(3, "Vũ Đức Long", "ĐH Bách Khoa", 2420, 81, 118),
        LeaderboardUser(4, "Nguyễn Minh Anh (Bạn)", "CLB Tranh biện ADPP", 2140, 76, 84),
        LeaderboardUser(5, "Đỗ Khánh Linh", "ĐH Kinh tế Quốc dân", 2110, 75, 96),
        LeaderboardUser(6, "Bùi Gia Huy", "ĐH FPT", 2075, 72, 88),
        LeaderboardUser(7, "Lê Bảo Châu", "ĐH Sư phạm Hà Nội", 2040, 71, 92)
    ),
    val bracketMatches: List<BracketMatch> = listOf(
        BracketMatch("m1", "Tứ kết 1", "ĐH Quốc gia HN", 3, "ĐH Ngoại thương", 1, "ĐH Quốc gia HN"),
        BracketMatch("m2", "Tứ kết 2", "ĐH Bách Khoa", 3, "ĐH FPT", 2, "ĐH Bách Khoa"),
        BracketMatch("m3", "Tứ kết 3", "ĐH Kinh tế QD", 3, "ĐH Luật Hà Nội", 0, "ĐH Kinh tế QD"),
        BracketMatch("m4", "Tứ kết 4", "CLB ADPP", 3, "ĐH Sư phạm HN", 2, "CLB ADPP"),
        BracketMatch("m5", "Bán kết 1", "ĐH Quốc gia HN", 3, "ĐH Bách Khoa", 2, "ĐH Quốc gia HN"),
        BracketMatch("m6", "Bán kết 2", "ĐH Kinh tế QD", 3, "CLB ADPP", 1, "ĐH Kinh tế QD"),
        BracketMatch("m7", "Chung kết Tổng", "ĐH Quốc gia HN", 3, "ĐH Kinh tế QD", 2, "ĐH Quốc gia HN")
    )
)

class CompetitionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CompetitionUiState())
    val uiState: StateFlow<CompetitionUiState> = _uiState.asStateFlow()
}
