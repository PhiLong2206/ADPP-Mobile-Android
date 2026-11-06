package com.adpp.mobile.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class QuickTopic(
    val id: String,
    val title: String,
    val category: String,
    val difficulty: String,
    val participantCount: Int
)

data class HomeUiState(
    val userName: String = "Nguyễn Minh Anh",
    val greetingSubtext: String = "Hôm nay bạn muốn luyện tập chủ đề gì?",
    val userRankTitle: String = "Trọng tài Thực tập",
    val currentStreakDays: Int = 7,
    val totalDebates: Int = 84,
    val winRatePercent: Int = 76,
    val averageScore: Float = 89.4f,
    val recommendedTopics: List<QuickTopic> = listOf(
        QuickTopic(
            id = "topic-1",
            title = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
            category = "Công nghệ",
            difficulty = "Nâng cao",
            participantCount = 1240
        ),
        QuickTopic(
            id = "topic-2",
            title = "Nên áp dụng hình thức đánh giá liên tục thay thế hoàn toàn các kỳ thi cuối kỳ tại bậc đại học.",
            category = "Giáo dục",
            difficulty = "Trung bình",
            participantCount = 890
        ),
        QuickTopic(
            id = "topic-3",
            title = "Nên áp dụng chính sách thu nhập cơ bản phổ quát (UBI) khi tự động hóa ngày càng mở rộng.",
            category = "Kinh tế",
            difficulty = "Trung bình",
            participantCount = 1530
        )
    ),
    val activeTournamentTitle: String = "Giải Vô địch Tranh biện Mùa Xuân 2026",
    val activeTournamentDate: String = "Khai mạc sau 3 ngày • 32 đội tham gia"
)

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
}
