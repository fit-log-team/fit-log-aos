package com.example.presentation.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.presentation.ui.component.SegmentedButtonsDefaults.ITEM_ANIMATION_MILLIS
import com.example.presentation.ui.component.SegmentedButtonsDefaults.minimumHeight

/**
 * Segmented Button Component
 * @param modifier Modifier for the component
 * @param buttonShape Shape of the component | Default [RoundedCornerShape]
 * @param colors Colors of the component | Default [SegmentedButtonsDefaults.colors]
 * @param outlineThickness Thickness of the outline | Default [SegmentedButtonsDefaults.outlineThickness]
 * @param border Border of the component | Default [BorderStroke]
 * @param content Content of the component
 */
@Composable
fun SegmentedButton(
    modifier: Modifier = Modifier,
    buttonShape: Shape = RoundedCornerShape(percent = 50),
    colors: SegmentedButtonColors = SegmentedButtonsDefaults.colors(),
    outlineThickness: Dp = SegmentedButtonsDefaults.outlineThickness,
    border: BorderStroke = BorderStroke(outlineThickness, colors.outlineColor),
    content: @Composable () -> Unit
) {
    Surface(
        shape = buttonShape,
        border = border,
        modifier = modifier.defaultMinSize(minHeight = minimumHeight)
    ) {
        SubcomposeLayout(Modifier.fillMaxWidth()) { constraints ->
            val bottomRowWidth = constraints.maxWidth
            val buttonMeasurable = subcompose(ButtonSlots.Buttons, content)
            val buttonCount = buttonMeasurable.size
            val dividerCount = buttonMeasurable.size - 1

            val outlineThicknessPx = outlineThickness.roundToPx()

            var buttonWidth = 0
            if (buttonCount > 0) {
                buttonWidth = (bottomRowWidth / buttonCount)
            }
            val buttonRowHeight =
                buttonMeasurable.fold(initial = minimumHeight.roundToPx()) { max, curr ->
                    maxOf(curr.maxIntrinsicHeight(buttonWidth), max)
                }

            val buttonPlaceables = buttonMeasurable.map {
                it.measure(
                    constraints.copy(
                        minWidth = buttonWidth,
                        maxWidth = buttonWidth,
                        minHeight = buttonRowHeight,
                        maxHeight = buttonRowHeight,
                    )
                )
            }
            val dividers = @Composable {
                repeat(dividerCount) {
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                            .background(color = Color.Gray)
                    )
                }
            }
            val dividerPlaceables =
                subcompose(ButtonSlots.Divider, dividers).map {
                    it.measure(
                        constraints.copy(
                            minWidth = outlineThicknessPx,
                            maxWidth = outlineThicknessPx,
                            minHeight = buttonRowHeight - outlineThicknessPx * 2,
                            maxHeight = buttonRowHeight - outlineThicknessPx * 2,
                        )
                    )
                }

            layout(bottomRowWidth, buttonRowHeight) {
                buttonPlaceables.forEachIndexed { index, button ->
                    if (index < dividerPlaceables.size) {
                        dividerPlaceables[index].placeRelative(
                            index * buttonWidth + buttonWidth,
                            outlineThicknessPx,
                            1f
                        )
                    }
                    button.placeRelative(index * buttonWidth, 0, 0f)
                }
            }

        }
    }
}

@Composable
fun SegmentedButtonItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    icon: @Composable (() -> Unit)? = null,
    colors: SegmentedButtonColors = SegmentedButtonsDefaults.colors(),
    textStyle: TextStyle = MaterialTheme.typography.labelLarge
) {
    val styledLabel: @Composable (() -> Unit)? =
        label?.let {
            @Composable {
                val textColor by colors.textColor(selected = selected)
                CompositionLocalProvider(LocalContentColor provides textColor) {
                    ProvideTextStyle(textStyle, content = label)
                }
            }
        }

    val styledIcon: @Composable (() -> Unit)? =
        icon?.let {
            @Composable {
                val iconAlpha by iconVisible(selected = selected)

                val iconColor by colors.iconColor(selected = selected)
                val clearSemantics = label != null && selected
                Box(modifier = if (clearSemantics) Modifier.clearAndSetSemantics {} else Modifier.alpha(iconAlpha)) {
                    CompositionLocalProvider(LocalContentColor provides iconColor, content = icon)
                }
            }
        }

    val animationProgress: Color by animateColorAsState(
        targetValue = if(selected) colors.indicatorColor else Color.White,
        animationSpec = tween(ITEM_ANIMATION_MILLIS), label = "SegmentedButton"
    )

    Box(
        modifier
            .fillMaxSize()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.Tab,
            )
            .background(
                color = animationProgress
            )
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            styledIcon?.invoke()
            styledLabel?.invoke()
        }
    }
}

@Composable
fun iconVisible(selected: Boolean): State<Float> {
    val targetValue = when {
        selected -> 1f
        else -> 0f
    }
    return animateFloatAsState(
        targetValue = targetValue,
        animationSpec = tween(ITEM_ANIMATION_MILLIS),
        label = "SegmentedButtonsIconVisibility"
    )
}

object SegmentedButtonsDefaults {

    @Composable
    fun colors(
        selectedTextColor: Color = Color.Black,
        selectedIconColor: Color = Color.Black,
        unselectedTextColor: Color = Color.Black,
        unselectedIconColor: Color = MaterialTheme.colorScheme.onSurface,
        indicatorColor: Color = MaterialTheme.colorScheme.secondaryContainer,
        outlineColor: Color = MaterialTheme.colorScheme.outlineVariant
    ): SegmentedButtonColors = SegmentedButtonColors(
        selectedTextColor = selectedTextColor,
        selectedIconColor = selectedIconColor,
        indicatorColor = indicatorColor,
        unselectedTextColor = unselectedTextColor,
        unselectedIconColor = unselectedIconColor,
        outlineColor = outlineColor
    )

    internal val outlineThickness: Dp = 1.dp
    internal val minimumHeight: Dp = 48.dp
    internal const val ITEM_ANIMATION_MILLIS: Int = 100
}

@Stable
data class SegmentedButtonColors internal constructor(
    val selectedTextColor: Color,
    val selectedIconColor: Color,
    val unselectedTextColor: Color,
    val unselectedIconColor: Color,
    val indicatorColor: Color,
    val outlineColor: Color
) {
    @Composable
    internal fun textColor(selected: Boolean): State<Color> {
        val targetValue = when {
            selected -> selectedTextColor
            else -> unselectedTextColor
        }
        return animateColorAsState(
            targetValue = targetValue,
            animationSpec = tween(ITEM_ANIMATION_MILLIS),
            label = "SegmentedButtonsTextColor"
        )
    }

    @Composable
    fun iconColor(selected: Boolean): State<Color> {
        val targetValue = when {
            selected -> selectedIconColor
            else -> unselectedIconColor
        }
        return animateColorAsState(
            targetValue = targetValue,
            animationSpec = tween(ITEM_ANIMATION_MILLIS),
            label = "SegmentedButtonsIconColor"
        )
    }
}

private enum class ButtonSlots {
    Buttons,
    Divider,
}