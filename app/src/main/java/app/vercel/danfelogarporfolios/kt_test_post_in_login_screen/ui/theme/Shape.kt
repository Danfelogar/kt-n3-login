package app.vercel.danfelogarporfolios.kt_test_post_in_login_screen.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Shape definitions for MercApp following Material Design 3 guidelines
 */
val Shapes = Shapes(
    // Small components like chips, buttons, etc.
    small = RoundedCornerShape(8.dp),

    // Medium components like cards, dialogs
    medium = RoundedCornerShape(12.dp),

    // Large components like bottom sheets, expanded menus
    large = RoundedCornerShape(16.dp),

    // Extra small for subtle rounded elements
    extraSmall = RoundedCornerShape(4.dp)
)

// Additional shapes for specialized components
object CustomShapes {
    // For fully circular elements like FABs, profile pictures
    val Circle = CircleShape

    // For pill-shaped buttons
    val Pill = RoundedCornerShape(50.dp)

    // Card shapes with different corner treatments
    val CardRounded = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomStart = 8.dp,
        bottomEnd = 8.dp
    )

    // Top rounded corners for bottom sheets
    val BottomSheet = RoundedCornerShape(
        topStart = 18.dp,
        topEnd = 18.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )

    // Cut corner shape for special elements
    val CutCorner = CutCornerShape(12.dp)
}
