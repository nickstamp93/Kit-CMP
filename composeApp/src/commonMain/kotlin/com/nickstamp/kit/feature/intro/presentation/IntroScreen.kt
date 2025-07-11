package com.nickstamp.kit.feature.intro.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.HtmlText
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.nickstamp.kit.feature.intro.domain.model.IntroPage
import com.nickstamp.kit.feature.intro.domain.model.IntroPageType
import com.nickstamp.kit.feature.intro.presentation.IntroContract.Event
import com.nickstamp.kit.feature.intro.presentation.IntroContract.State
import com.nickstamp.kit.feature.intro.presentation.components.AcceptTermsButton
import com.nickstamp.kit.feature.intro.presentation.components.IntroNavButton
import com.nickstamp.kit.feature.intro.presentation.components.PagerIndicator
import com.nickstamp.kit.ui.components.HtmlWebView
import com.nickstamp.kit.ui.theme.AppTheme.colors
import com.nickstamp.kit.ui.theme.AppTheme.shapes
import com.nickstamp.kit.ui.theme.AppTheme.spacing
import com.nickstamp.kit.ui.theme.AppTheme.typography
import com.nickstamp.kit.ui.theme.highestEmphasis
import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.ic_check
import kit_cmp.composeapp.generated.resources.ic_next
import kit_cmp.composeapp.generated.resources.ic_previous
import kit_cmp.composeapp.generated.resources.terms_and_conditions
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import kotlin.math.absoluteValue

@Composable
fun IntroScreen(
    state: State,
    onEvent: (Event) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = state.currentPagePos,
        pageCount = { state.totalPages }
    )

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collectLatest { page ->
            onEvent(Event.OnPageChanged(page))
        }
    }

    LaunchedEffect(state.currentPagePos) {
        if (state.currentPagePos != pagerState.currentPage) {
            pagerState.animateScrollToPage(state.currentPagePos)
        }
    }

    Box(
        modifier = Modifier
            .background(colors.primary)
            .safeContentPadding()
            .fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            when (IntroPage.get(page).type) {
                IntroPageType.INFO -> {
                    IntroPageContent(
                        page = IntroPage.get(page),
                        modifier = Modifier
                            .introPageChangeEffect(pagerState, page)
                    )
                }

                IntroPageType.TERMS -> {
                    TermsPageContent(
                        page = IntroPage.get(page),
                        htmlText = state.htmlText,
                        modifier = Modifier
                            .introPageChangeEffect(pagerState, page)
                    )
                }
            }

        }

        IntroBottomNavigationControls(
            state = state,
            onEvent = onEvent,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        )

    }
}


@Composable
private fun IntroBottomNavigationControls(
    state: State,
    onEvent: (Event) -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = state.currentPage.type,
        label = "bottomControls",
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(horizontal = spacing.default)
    ) { type ->
        Box(modifier = Modifier.fillMaxWidth()) {
            when (type) {
                IntroPageType.INFO -> {

                    AnimatedVisibility(
                        visible = state.canGoToPreviousPage,
                        enter = scaleIn(),
                        exit = scaleOut(),
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        if (state.canGoToPreviousPage) {
                            IntroNavButton(
                                iconRes = Res.drawable.ic_previous,
                                onClick = {
                                    onEvent(Event.OnPreviousPageButtonClick)
                                }
                            )
                        }
                    }

                    val nextButtonIconRes = if (!state.isLastInfoScreen) {
                        Res.drawable.ic_next
                    } else {
                        Res.drawable.ic_check
                    }
                    IntroNavButton(
                        iconRes = nextButtonIconRes,
                        onClick = {
                            onEvent(Event.OnNextPageButtonClick)
                        },
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )

                    PagerIndicator(
                        pageCount = state.totalPages - 1,
                        currentPage = state.currentPagePos,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                IntroPageType.TERMS -> {
                    AcceptTermsButton(
                        onClick = {
                            onEvent(Event.OnAcceptTermsClick)
                        },
                        modifier = Modifier
                            .height(56.dp)
                            .padding(horizontal = spacing.default)
                            .align(Alignment.Center)
                    )

                }
            }
        }
    }
}

@Composable
private fun IntroPageContent(
    page: IntroPage,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(colors.primary)
            .padding(
                bottom = 80.dp,
                start = spacing.default,
                end = spacing.default
            )
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.widthIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(page.title),
                style = typography.bold20.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(page.icon),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(spacing.default))

        Text(
            text = stringResource(page.text),
            style = typography.regular16.copy(color = Color.White),
            textAlign = TextAlign.Center,
            maxLines = 3,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun TermsPageContent(
    page: IntroPage,
    htmlText: String,
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .background(colors.primary)
            .padding(
                bottom = 80.dp,
                start = spacing.large,
                end = spacing.large
            )
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.widthIn(min = 48.dp)
        ) {
            Text(
                text = stringResource(Res.string.terms_and_conditions),
                style = typography.bold20.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color.White.highestEmphasis(),
                    shape = shapes.large
                )
        ) {
            HtmlWebView(
                html = htmlText,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.xlarge)
                    .verticalScroll(rememberScrollState())
            )
        }
    }
}

@Composable
private fun Modifier.introPageChangeEffect(pagerState: PagerState, page: Int): Modifier =
    this then graphicsLayer {
        val pageOffset = pagerState.calculateCurrentOffsetForPage(page)

        // get a scale value between 1 and 1.25f, 1.25 will be when its resting,
        // 1f is the smallest it'll be when not the focused page
        val scale = lerp(1.2f, 1f, 1 - pageOffset.absoluteValue)
//        // apply the scale equally to both X and Y, to not distort the image
        scaleX = scale
        scaleY = scale

        translationX = pageOffset * size.width
        alpha = 1 - pageOffset.absoluteValue
    }

// extension method for current page offset
private fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction
}

private class IntroPreviewStateProvider : PreviewParameterProvider<State> {
    override val values = (0..3).map {
        State(4, it, "HTML TEXT")
    }.asSequence()
}

@Preview
@Composable
private fun IntroScreenPreview(
    @PreviewParameter(IntroPreviewStateProvider::class) state: State
) {
    IntroScreen(
        state = state,
        onEvent = {}
    )
}
