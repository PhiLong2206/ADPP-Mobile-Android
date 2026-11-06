package com.adpp.mobile.feature.explore.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DebateTopicItem(
    val id: String,
    val title: String,
    val summary: String,
    val category: String,
    val difficulty: String,
    val participantCount: Int
)

data class ExploreUiState(
    val searchQuery: String = "",
    val selectedCategory: String = "Tất cả",
    val selectedDifficulty: String = "Tất cả",
    val categories: List<String> = listOf("Tất cả", "Công nghệ", "Giáo dục", "Kinh tế", "Xã hội", "Môi trường", "Đạo đức"),
    val difficulties: List<String> = listOf("Tất cả", "Cơ bản", "Trung cấp", "Nâng cao"),
    val allTopics: List<DebateTopicItem> = listOf(
        DebateTopicItem(
            id = "topic-1",
            title = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
            summary = "Phân tích tác động của Generative AI đến quyền sở hữu trí tuệ, đạo đức dữ liệu đào tạo và sinh kế của giới họa sĩ, nhà văn.",
            category = "Công nghệ",
            difficulty = "Nâng cao",
            participantCount = 1420
        ),
        DebateTopicItem(
            id = "topic-2",
            title = "Tiền kỹ thuật số của Ngân hàng Trung ương (CBDC) nên thay thế hoàn toàn tiền mặt truyền thống.",
            summary = "Tranh luận về hiệu quả thanh toán quốc gia, giám sát tài chính, quyền riêng tư người dùng và an ninh tiền tệ.",
            category = "Kinh tế",
            difficulty = "Trung cấp",
            participantCount = 980
        ),
        DebateTopicItem(
            id = "topic-3",
            title = "Các trường đại học nên thay thế bài thi tự luận bằng đánh giá năng lực tư duy phản biện trực tiếp.",
            summary = "Đánh giá sự thoái trào của kiểm tra truyền thống trong kỷ nguyên trợ lý AI và sự cần thiết của kỹ năng tranh luận thực chiến.",
            category = "Giáo dục",
            difficulty = "Cơ bản",
            participantCount = 1650
        ),
        DebateTopicItem(
            id = "topic-4",
            title = "Các quốc gia đang phát triển nên ưu tiên điện hạt nhân mô-đun nhỏ hơn năng lượng tái tạo phân tán.",
            summary = "So sánh tính ổn định của tải nền điện hạt nhân hiện đại với chi phí vốn và tác động môi trường của pin lưu trữ.",
            category = "Môi trường",
            difficulty = "Nâng cao",
            participantCount = 890
        ),
        DebateTopicItem(
            id = "topic-5",
            title = "Nên xử phạt hành chính các thuật toán mạng xã hội tối ưu hóa lượt tương tác thay vì độ chính xác thông tin.",
            summary = "Xem xét ranh giới tự do ngôn luận, sự phân cực cộng đồng và trách nhiệm pháp lý của các nền tảng công nghệ lớn.",
            category = "Xã hội",
            difficulty = "Cơ bản",
            participantCount = 2310
        ),
        DebateTopicItem(
            id = "topic-6",
            title = "Chỉnh sửa gen phôi người nhằm mục đích nâng cao năng lực thể chất và trí tuệ phải bị cấm trên toàn cầu.",
            summary = "Tranh biện về bình đẳng xã hội, thuyết ưu sinh thế hệ mới và rủi ro biến đổi di truyền không thể đảo ngược.",
            category = "Đạo đức",
            difficulty = "Nâng cao",
            participantCount = 1120
        ),
        DebateTopicItem(
            id = "topic-7",
            title = "Áp dụng chính sách Thu nhập Cơ bản Phổ quát (UBI) là giải pháp bắt buộc trước làn sóng tự động hóa lao động.",
            summary = "Đánh giá tính khả thi ngân sách nhà nước, động lực lao động xã hội và xóa đói giảm nghèo bền vững.",
            category = "Kinh tế",
            difficulty = "Trung cấp",
            participantCount = 1840
        )
    )
)

class ExploreViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun selectDifficulty(difficulty: String) {
        _uiState.update { it.copy(selectedDifficulty = difficulty) }
    }

    fun getFilteredTopics(): List<DebateTopicItem> {
        val state = _uiState.value
        return state.allTopics.filter { topic ->
            val matchesQuery = state.searchQuery.isBlank() ||
                    topic.title.contains(state.searchQuery, ignoreCase = true) ||
                    topic.summary.contains(state.searchQuery, ignoreCase = true)
            val matchesCategory = state.selectedCategory == "Tất cả" || topic.category.equals(state.selectedCategory, ignoreCase = true)
            val matchesDifficulty = state.selectedDifficulty == "Tất cả" || topic.difficulty.equals(state.selectedDifficulty, ignoreCase = true)
            matchesQuery && matchesCategory && matchesDifficulty
        }
    }
}
