package com.adpp.mobile.feature.debate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SpeechTurn(
    val speaker: String,
    val role: String, // Phe Ủng hộ / Phe Phản đối
    val isUser: Boolean,
    val round: Int,
    val roundTitle: String,
    val argumentText: String,
    val timestamp: String
)

data class AIDebateSetupState(
    val motion: String = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
    val selectedSide: String = "Phe Ủng hộ", // Phe Ủng hộ, Phe Phản đối, Ngẫu nhiên
    val aiDifficulty: String = "Trung cấp", // Cơ bản, Trung cấp, Nâng cao, Thích ứng
    val format: String = "Tranh biện 3 lượt ngắn",
    val speechTimeSeconds: Int = 120,
    val roundsCount: Int = 3
)

data class AIDebateSessionState(
    val motion: String = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
    val currentRound: Int = 2,
    val maxRounds: Int = 3,
    val currentTurnRole: String = "Phe Ủng hộ",
    val isUserTurn: Boolean = true,
    val remainingSeconds: Int = 84,
    val totalSeconds: Int = 120,
    val userInputText: String = "",
    val isAILoading: Boolean = false,
    val speeches: List<SpeechTurn> = listOf(
        SpeechTurn(
            speaker = "Bạn (Minh Anh)",
            role = "Phe Ủng hộ",
            isUser = true,
            round = 1,
            roundTitle = "Lượt 1: Trình bày mở đầu",
            argumentText = "Kính thưa quý vị, quyền tác giả là nền tảng bảo vệ công sức sáng tạo của con người. Khi các mô hình AI sử dụng hàng triệu tác phẩm của nghệ sĩ mà không có sự đồng thuận hay đền bù thỏa đáng, điều đó làm suy giảm nghiêm trọng động lực sáng tạo nghệ thuật đích thực trong xã hội.",
            timestamp = "10:14"
        ),
        SpeechTurn(
            speaker = "Trợ lý AI Nova",
            role = "Phe Phản đối",
            isUser = false,
            round = 1,
            roundTitle = "Lượt 1: Phản bác mở đầu",
            argumentText = "Phe Ủng hộ đang nhầm lẫn giữa việc học hỏi phong cách nghệ thuật với hành vi sao chép trực tiếp. Con người cũng học hỏi từ tiền nhân để tạo ra tác phẩm mới. Việc giới hạn AI sáng tạo sẽ cản trở quyền tiếp cận các công cụ hỗ trợ sáng tác đột phá cho cộng đồng.",
            timestamp = "10:16"
        ),
        SpeechTurn(
            speaker = "Bạn (Minh Anh)",
            role = "Phe Ủng hộ",
            isUser = true,
            round = 2,
            roundTitle = "Lượt 2: Phản biện lập luận",
            argumentText = "Sự khác biệt cốt lõi nằm ở quy mô thương mại hóa tự động. Con người tiếp thu và lọc qua tư duy cá nhân, trong khi thuật toán trích xuất dữ liệu cơ học với tốc độ hàng triệu lần để cạnh tranh trực tiếp với chính người tạo ra dữ liệu đó.",
            timestamp = "10:18"
        )
    )
)

data class AIDebateEvaluationState(
    val motion: String = "Trí tuệ nhân tạo (AI) nên bị giới hạn trong việc sáng tạo các tác phẩm nghệ thuật có bản quyền.",
    val winnerSide: String = "Phe Ủng hộ (Minh Anh)",
    val overallScore: Int = 92,
    val adjudicatorVerdict: String = "Phe Ủng hộ đã thiết lập thành công gánh nặng chứng minh về việc bảo vệ quyền tác giả trước tác động của tự động hóa quy mô lớn. Phe Phản đối đưa ra quan điểm mở về công nghệ nhưng chưa giải quyết triệt để vấn đề thiệt hại kinh tế của người sáng tác gốc.",
    val logicScore: Float = 94f,
    val logicFeedback: String = "Cấu trúc lập luận chặt chẽ, phân biệt rõ ràng giữa học hỏi tự nhiên và khai thác dữ liệu thuật toán.",
    val refutationScore: Float = 89f,
    val refutationFeedback: String = "Phản bác nhanh và đánh trúng điểm yếu so sánh của đối phương; nên bổ sung thêm giải pháp cấp phép bản quyền.",
    val argumentQualityScore: Float = 93f,
    val argumentQualityFeedback: String = "Luận điểm rõ ràng, từ ngữ mạch lạc, đúng chuẩn phong cách tranh luận học thuật.",
    val evidenceScore: Float = 91f,
    val evidenceFeedback: String = "Dẫn chứng thực tế xác đáng về tác động thị trường đối với giới họa sĩ và nhà văn độc lập.",
    val keyStrengths: List<String> = listOf(
        "Định nghĩa phạm vi vấn đề rõ ràng ngay từ phút đầu",
        "Phản bác thuyết phục về bản chất trích xuất dữ liệu thương mại",
        "Giữ vững thái độ tranh luận lịch sự, tôn trọng đối thủ"
    ),
    val recommendations: List<String> = listOf(
        "Nên bổ sung số liệu thống kê cụ thể về thị trường nghệ thuật số",
        "Có thể đề xuất mô hình cơ chế chia sẻ doanh thu bản quyền hợp lý hơn"
    )
)

class DebateViewModel : ViewModel() {
    private val _setupState = MutableStateFlow(AIDebateSetupState())
    val setupState: StateFlow<AIDebateSetupState> = _setupState.asStateFlow()

    private val _sessionState = MutableStateFlow(AIDebateSessionState())
    val sessionState: StateFlow<AIDebateSessionState> = _sessionState.asStateFlow()

    private val _evaluationState = MutableStateFlow(AIDebateEvaluationState())
    val evaluationState: StateFlow<AIDebateEvaluationState> = _evaluationState.asStateFlow()

    fun updateSetupMotion(motion: String) {
        _setupState.update { it.copy(motion = motion) }
    }

    fun updateSetupSide(side: String) {
        _setupState.update { it.copy(selectedSide = side) }
    }

    fun updateSetupDifficulty(difficulty: String) {
        _setupState.update { it.copy(aiDifficulty = difficulty) }
    }

    fun updateSetupTime(seconds: Int) {
        _setupState.update { it.copy(speechTimeSeconds = seconds) }
    }

    fun updateUserInputText(text: String) {
        _sessionState.update { it.copy(userInputText = text) }
    }

    fun submitUserArgument() {
        val currentText = _sessionState.value.userInputText
        if (currentText.isBlank()) return

        val newTurn = SpeechTurn(
            speaker = "Bạn (Minh Anh)",
            role = "Phe Ủng hộ",
            isUser = true,
            round = _sessionState.value.currentRound,
            roundTitle = "Lượt ${_sessionState.value.currentRound}: Mở rộng lập luận",
            argumentText = currentText,
            timestamp = "Vừa xong"
        )

        _sessionState.update {
            it.copy(
                speeches = it.speeches + newTurn,
                userInputText = "",
                isAILoading = true,
                isUserTurn = false
            )
        }

        viewModelScope.launch {
            delay(1200)
            val aiTurn = SpeechTurn(
                speaker = "Trợ lý AI Nova",
                role = "Phe Phản đối",
                isUser = false,
                round = _sessionState.value.currentRound,
                roundTitle = "Lượt phản biện phản đối",
                argumentText = "Tuy nhiên, nghệ thuật luôn mang tính cộng hưởng xã hội. Nếu áp đặt lệnh cấm ngặt nghèo, chúng ta sẽ bỏ lỡ cơ hội phổ cập hóa công nghệ sáng tạo cho hàng triệu người không có điều kiện học vẽ chuyên nghiệp.",
                timestamp = "Vừa xong"
            )
            _sessionState.update {
                it.copy(
                    speeches = it.speeches + aiTurn,
                    isAILoading = false,
                    isUserTurn = true
                )
            }
        }
    }
}
