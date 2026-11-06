package com.adpp.mobile.feature.debate.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adpp.mobile.core.designsystem.components.ADPPCard
import com.adpp.mobile.core.designsystem.components.ADPPSearchBar
import com.adpp.mobile.core.designsystem.components.ADPPStatusBadge
import com.adpp.mobile.core.designsystem.components.BadgeVariant
import com.adpp.mobile.core.designsystem.theme.AccentAmber
import com.adpp.mobile.core.designsystem.theme.AccentSky
import com.adpp.mobile.core.designsystem.theme.ChipShape
import com.adpp.mobile.core.designsystem.theme.LightBackground
import com.adpp.mobile.core.designsystem.theme.LightBorder
import com.adpp.mobile.core.designsystem.theme.LightSurface
import com.adpp.mobile.core.designsystem.theme.PrimaryEmerald
import com.adpp.mobile.core.designsystem.theme.TextBody
import com.adpp.mobile.core.designsystem.theme.TextHeading
import com.adpp.mobile.core.designsystem.theme.TextMuted
import com.adpp.mobile.core.designsystem.theme.TextSecondary

data class DebateRoomItem(
    val id: String,
    val roomCode: String,
    val hostName: String,
    val hostLevel: String,
    val motion: String,
    val format: String,
    val currentParticipants: Int,
    val maxParticipants: Int = 2,
    val status: String,
    val isPrivate: Boolean = false
)

@Composable
fun DebateRoomBrowserScreen(
    onNavigateBack: () -> Unit,
    onCreateRoom: () -> Unit,
    onJoinRoom: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Tất cả") }

    val sampleRooms = listOf(
        DebateRoomItem(
            id = "room-101",
            roomCode = "#OXF-402",
            hostName = "Trần Bảo Long",
            hostLevel = "Trình độ Nâng cao",
            motion = "Nhà nước nên áp dụng thuế carbon bắt buộc đối với các ngành công nghiệp thâm dụng năng lượng.",
            format = "1v1 Oxford",
            currentParticipants = 1,
            maxParticipants = 2,
            status = "Đang chờ đối thủ"
        ),
        DebateRoomItem(
            id = "room-102",
            roomCode = "#OXF-108",
            hostName = "Lê Thu Hằng",
            hostLevel = "Kỳ cựu (95 trận)",
            motion = "Các trường đại học nên thay thế hoàn toàn điểm trung bình (GPA) bằng portfolio dự án thực tế.",
            format = "1v1 Cổ điển",
            currentParticipants = 1,
            maxParticipants = 2,
            status = "Đang chờ đối thủ"
        ),
        DebateRoomItem(
            id = "room-103",
            roomCode = "#OXF-774",
            hostName = "Đặng Hoàng Nam",
            hostLevel = "Trung cấp (42 trận)",
            motion = "Nên cấm sử dụng các thuật toán định giá cá nhân hóa trong thương mại điện tử.",
            format = "1v1 Oxford",
            currentParticipants = 2,
            maxParticipants = 2,
            status = "Đang thi đấu (Trực tiếp)"
        ),
        DebateRoomItem(
            id = "room-104",
            roomCode = "#OXF-319",
            hostName = "Phạm Quỳnh Chi",
            hostLevel = "Trình độ Nâng cao",
            motion = "Liên minh Châu Âu nên áp đặt hạn ngạch năng lực tính toán AI độc quyền đối với các tập đoàn công nghệ ngoại quốc.",
            format = "1v1 Tự do",
            currentParticipants = 1,
            maxParticipants = 2,
            status = "Phòng kín",
            isPrivate = true
        )
    )

    val filteredRooms = sampleRooms.filter { room ->
        val matchesQuery = searchQuery.isBlank() ||
                room.roomCode.contains(searchQuery, ignoreCase = true) ||
                room.motion.contains(searchQuery, ignoreCase = true) ||
                room.hostName.contains(searchQuery, ignoreCase = true)
        val matchesFilter = when (selectedFilter) {
            "Đang chờ đối thủ" -> room.currentParticipants < room.maxParticipants && !room.isPrivate
            "Đang thi đấu" -> room.status.contains("Trực tiếp")
            "Phòng kín" -> room.isPrivate
            else -> true
        }
        matchesQuery && matchesFilter
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Quay lại",
                        tint = TextHeading
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Phòng tranh biện 1vs1",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                    Text(
                        text = "Tham gia các phòng đấu 1v1 trực tiếp hoặc tự khởi tạo phòng mới",
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            ADPPSearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                placeholder = "Tìm mã phòng, chủ đề, tên chủ phòng..."
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Filter Chips
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Tất cả", "Đang chờ đối thủ", "Đang thi đấu", "Phòng kín").forEach { filterName ->
                    val isSelected = selectedFilter == filterName
                    Box(
                        modifier = Modifier
                            .clip(ChipShape)
                            .background(if (isSelected) PrimaryEmerald else LightSurface)
                            .border(
                                width = 1.dp,
                                color = if (isSelected) PrimaryEmerald else LightBorder,
                                shape = ChipShape
                            )
                            .clickable { selectedFilter = filterName }
                            .padding(horizontal = 14.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = filterName,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) LightSurface else TextBody
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Room List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredRooms) { room ->
                    ADPPCard(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onJoinRoom(room.id) }
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = room.roomCode,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = AccentSky
                                    )
                                    if (room.isPrivate) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Icon(
                                            imageVector = Icons.Filled.Lock,
                                            contentDescription = "Phòng kín",
                                            tint = AccentAmber,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }

                                ADPPStatusBadge(
                                    text = room.status,
                                    variant = when {
                                        room.status.contains("Trực tiếp") -> BadgeVariant.WARNING
                                        room.currentParticipants < room.maxParticipants -> BadgeVariant.PRIMARY
                                        else -> BadgeVariant.NEUTRAL
                                    },
                                    showDot = true
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = room.motion,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextHeading,
                                lineHeight = 20.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Chủ phòng: ${room.hostName}",
                                        fontSize = 12.sp,
                                        color = TextSecondary
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "• ${room.hostLevel}",
                                        fontSize = 11.sp,
                                        color = PrimaryEmerald,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Filled.Person,
                                        contentDescription = null,
                                        tint = TextMuted,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${room.currentParticipants}/${room.maxParticipants} người",
                                        fontSize = 12.sp,
                                        color = TextSecondary
                                    )
                                }
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = onCreateRoom,
            containerColor = PrimaryEmerald,
            contentColor = LightSurface,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Tạo phòng 1v1 mới",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
