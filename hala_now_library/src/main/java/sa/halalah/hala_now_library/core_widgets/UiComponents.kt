import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sa.halalah.hala_now_library.R


@Composable
fun GradientProgressBar(
    parentModifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    progress: Float = 0.3f,
) {

    val firstColor by animateColorAsState(
        targetValue = if (progress == 1f) colorResource(id = R.color.primary_100) else colorResource(
            id = R.color.secondary_100
        ), label = ""
    )

    val SecColor by animateColorAsState(
        targetValue = if (progress <= 0.3f) colorResource(id = R.color.secondary_100) else colorResource(
            id = R.color.primary_100
        ), label = ""
    )

    val animProgress by animateFloatAsState(
        targetValue = progress, label = ""
    )

    Box(
        modifier = parentModifier
            .background(
                color = colorResource(id = R.color.surface),
                shape = RoundedCornerShape(20.dp)
            )
            .fillMaxWidth()
            .height(7.dp)
    ) {
        Box(
            modifier = childModifier
                .fillMaxWidth(animProgress)
                .fillMaxHeight()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            firstColor,
                            SecColor,
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )


        )
    }
}


@Composable
fun ProgressBarWithPercent(
    parentModifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    progress: Float = 0.3f,
) {

    val _progress = remember { mutableFloatStateOf(0f) }
    val animProgress by animateFloatAsState(
        targetValue = _progress.floatValue, label = "",
        animationSpec = tween(
            durationMillis = 2000
        )
    )


    LaunchedEffect(key1 = progress) {
        _progress.floatValue = progress
    }

    Box(
        modifier = parentModifier
            .fillMaxWidth()
            .height(7.dp)
    ) {

        Box(
            modifier = childModifier
                .fillMaxWidth(animProgress)
                .fillMaxHeight(),


            ) {
            if (animProgress > 0.12) {
                Text(
                    text = (animProgress * 100).toInt().toString() + "%",
                    style = MaterialTheme.typography.subtitle2,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.primary_100),
                    modifier = Modifier
                        .padding(5.dp, 0.dp)
                        .align(Alignment.CenterEnd),

                    )
            }

        }

        if (animProgress <= 0.12) {
            Text(
                text = (animProgress * 100).toInt().toString() + "%",
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.primary_100),
                modifier = Modifier
                    .padding(5.dp, 0.dp)
                    .align(Alignment.CenterStart),

                )
        }
    }
}

@Composable
fun Border() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                color = colorResource(
                    id = R.color.overlay_3
                )
            ),
    ) {}
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GradientProgressIndicator() {
    ProgressBarWithPercent(
        parentModifier = Modifier
            .fillMaxWidth(0.9f)
            .background(colorResource(id = R.color.error_100))
            .height(25.dp),
        childModifier = Modifier
            .fillMaxWidth(0.9f)
            .background(colorResource(id = R.color.primary_100)),

        )
}


@Composable
fun HorizontalDashedBorder(
    color: Color = Color.Black,
    strokeWidth: Dp = 1.dp,
    dashWidth: Dp = 8.dp,
    dashGap: Dp = 4.dp,
    height: Dp = 1.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .drawWithContent {
                drawContent()
                val path = Path()
                val y = size.height / 2
                var x = 0f
                while (x < size.width) {
                    path.moveTo(x, y)
                    x += dashWidth.toPx()
                    path.lineTo(x.coerceAtMost(size.width), y)
                    x += dashGap.toPx()
                }
                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Butt
                    )
                )
            }
    )
}


@Composable
fun VerticalDashedBorder(
    color: Color = Color.Black,
    strokeWidth: Dp = 1.dp,
    dashWidth: Dp = 8.dp,
    dashGap: Dp = 4.dp,
    width: Dp = 1.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(width)
            .fillMaxHeight()
            .drawWithContent {
                drawContent()
                val startX = size.width / 2
                val path = Path()
                var y = 0f
                while (y < size.height) {
                    path.moveTo(startX, y)
                    y += dashWidth.toPx()
                    path.lineTo(startX, y.coerceAtMost(size.height))
                    y += dashGap.toPx()
                }
                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap = StrokeCap.Butt
                    )
                )
            }
    )
}