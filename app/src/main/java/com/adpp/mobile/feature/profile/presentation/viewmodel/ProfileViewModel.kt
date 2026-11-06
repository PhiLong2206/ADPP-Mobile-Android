package com.adpp.mobile.feature.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HistoryMatchItem(
    val id: String,
    val motion: String,
    val mode: String,
    val opponent: String,
    val userRole: String,
    val isVictory: Boolean,
    val score: Int,
    val date: String,
    val summaryFeedback: String
)

data class UserProfileData(
    val name: String = "Nguyễn Minh Anh",
    val email: String = "nguyen.anh@adpp.edu.vn",
    val title: String = "Tranh biện viên Cao cấp",
    val institution: String = "CLB Tranh biện ADPP",
    val bio: String = "Tranh biện viên chuyên về đạo đức công nghệ, kinh tế số và kỹ thuật phản bác lập luận học thuật.",
    val academicLevel: String = "Hạng Vàng (Bậc 5)",
    val winRate: Int = 76,
    val totalMatches: Int = 84,
    val preferredFormat: String = "1v1 Oxford",
    val notificationsEnabled: Boolean = true,
    val soundEffectsEnabled: Boolean = true,
    val voiceSpeechEnabled: Boolean = true
) {
    // Keep backward-compatible property for any existing call
    val elo: Int get() = 2140
}

class ProfileViewModel : ViewModel() {
    private val _profileState = MutableStateFlow(UserProfileData())
    val profileState: StateFlow<UserProfileData> = _profileState.asStateFlow()

    private val _historyMatches = MutableStateFlow(
        listOf(
            HistoryMatchItem(
                id = "hist-1",
                motion = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
                mode = "Luyện tập với AI",
                opponent = "Trợ lý AI Nova",
                userRole = "Phe Ủng hộ",
                isVictory = true,
                score = 92,
                date = "Hôm nay, 10:18",
                summaryFeedback = "Xác lập thành công gánh nặng chứng minh và bảo vệ quyền lợi kinh tế của tác giả gốc rất thuyết phục."
            ),
            HistoryMatchItem(
                id = "hist-2",
                motion = "Tiền kỹ thuật số của Ngân hàng Trung ương (CBDC) nên thay thế hoàn toàn tiền mặt truyền thống.",
                mode = "Tranh biện 1v1",
                opponent = "Trần Bảo Long (Kỳ cựu)",
                userRole = "Phe Phản đối",
                isVictory = true,
                score = 88,
                date = "Hôm qua, 15:30",
                summaryFeedback = "Lập luận sâu sắc về quyền riêng tư tài chính và rủi ro gián đoạn kỹ thuật số toàn diện."
            ),
            HistoryMatchItem(
                id = "hist-3",
                motion = "Các quốc gia đang phát triển nên ưu tiên điện hạt nhân mô-đun nhỏ hơn năng lượng tái tạo.",
                mode = "Tranh biện 1v1",
                opponent = "Lê Thu Hằng (Nâng cao)",
                userRole = "Phe Ủng hộ",
                isVictory = false,
                score = 79,
                date = "3 ngày trước",
                summaryFeedback = "Phân tích tải nền rất tốt nhưng đối thủ khai thác hiệu quả thời gian trễ thu hồi vốn đầu tư công."
            ),
            HistoryMatchItem(
                id = "hist-4",
                motion = "Chỉnh sửa gen phôi người nhằm nâng cao năng lực thể chất và trí tuệ phải bị cấm trên toàn cầu.",
                mode = "Luyện tập với AI",
                opponent = "Trợ lý AI Nova (Nâng cao)",
                userRole = "Phe Phản đối",
                isVictory = true,
                score = 95,
                date = "5 ngày trước",
                summaryFeedback = "Phân biệt xuất sắc giữa trị liệu phục hồi và cải biên chọn lọc; phản bác toàn diện lệnh cấm đại trà."
            )
        )
    )
    val historyMatches: StateFlow<List<HistoryMatchItem>> = _historyMatches.asStateFlow()

    fun updateName(name: String) = _profileState.update { it.copy(name = name) }
    fun updateBio(bio: String) = _profileState.update { it.copy(bio = bio) }
    fun updateInstitution(inst: String) = _profileState.update { it.copy(institution = inst) }
    fun updatePreferredFormat(format: String) = _profileState.update { it.copy(preferredFormat = format) }
    fun toggleNotifications() = _profileState.update { it.copy(notificationsEnabled = !it.notificationsEnabled) }
    fun toggleSoundEffects() = _profileState.update { it.copy(soundEffectsEnabled = !it.soundEffectsEnabled) }
    fun toggleVoiceSpeech() = _profileState.update { it.copy(voiceSpeechEnabled = !it.voiceSpeechEnabled) }
}
