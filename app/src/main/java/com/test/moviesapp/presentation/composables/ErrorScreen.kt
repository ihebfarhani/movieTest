package com.test.moviesapp.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.moviesapp.R

@Composable
fun CustomErrorScreenSomethingHappens(
    modifier: Modifier = Modifier,
) {
    CustomEmptyStateScreen(
        modifier = modifier,
        title = stringResource(id = R.string.empty_screen_title_error_something_went_wrong),
        description = stringResource(id = R.string.empty_screen_description_error_something_went_wrong),
    )
}

@Preview
@Composable
fun ErrorScreen() {
    CustomErrorScreenSomethingHappens()
}


