package com.nickstamp.kit.feature.intro.domain.model

import kit_cmp.composeapp.generated.resources.Res
import kit_cmp.composeapp.generated.resources.empty
import kit_cmp.composeapp.generated.resources.ic_intro_1
import kit_cmp.composeapp.generated.resources.ic_intro_2
import kit_cmp.composeapp.generated.resources.ic_intro_3
import kit_cmp.composeapp.generated.resources.intro_page_content_1
import kit_cmp.composeapp.generated.resources.intro_page_content_2
import kit_cmp.composeapp.generated.resources.intro_page_title_1
import kit_cmp.composeapp.generated.resources.intro_page_title_2
import kit_cmp.composeapp.generated.resources.terms_and_conditions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class IntroPage(
    val pos: Int,
    val type: IntroPageType,
    val title: StringResource,
    val text: StringResource,
    val icon: DrawableResource
) {
    PAGE_1(
        pos = 0,
        type = IntroPageType.INFO,
        title = Res.string.intro_page_title_1,
        text = Res.string.intro_page_content_1,
        icon = Res.drawable.ic_intro_1
    ),
    PAGE_2(
        pos = 1,
        type = IntroPageType.INFO,
        title = Res.string.intro_page_title_2,
        text = Res.string.intro_page_content_2,
        icon = Res.drawable.ic_intro_2
    ),
    PAGE_3(
        pos = 2,
        type = IntroPageType.TERMS,
        title = Res.string.terms_and_conditions,
        text = Res.string.empty,
        icon = Res.drawable.ic_intro_3
    );

    companion object {
        private val map = entries.associateBy { it.pos }

        fun get(pos: Int) = map[pos] ?: PAGE_1

        fun pagesCount() = entries.size
    }
}

enum class IntroPageType {
    INFO,
    TERMS
}