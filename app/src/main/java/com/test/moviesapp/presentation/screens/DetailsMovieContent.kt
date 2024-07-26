package com.test.moviesapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.test.moviesapp.R
import com.test.moviesapp.domain.GenreDomain

@Composable
fun DetailsMovieContent(
    onClickBack: () -> Unit,
    title: String,
    description: String,
    imageBackdrop: String,
    genres: List<GenreDomain>,
    releaseDate: String,
    runtime: String,
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically

        ) {

            Icon(
                modifier = Modifier.clickable {
                    onClickBack()
                },
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null,
                tint = Color.Black,
            )
            Text(
                text = stringResource(R.string.Détails),
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 24.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp),
            model = imageBackdrop,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        Spacer(modifier = Modifier.height(5.dp))


        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .height(48.dp),
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(5.dp))

        HorizontalThreeOptions(
            yearRelease = releaseDate,
            duration = runtime,
            genre = if (genres.firstOrNull() == null) "" else genres.firstOrNull()?.name.toString()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = "Description",
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = description,
            textAlign = TextAlign.Justify,
            fontSize = 14.sp,
            fontWeight = FontWeight(400),
        )

        Spacer(modifier = Modifier.height(24.dp))

        val listGenres = genres.map { it.name }.joinToString(separator = " * ")

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            text = listGenres,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight(600),
        )

    }
}

@Composable
fun HorizontalThreeOptions(
    yearRelease: String,
    duration: String,
    genre: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center,
    ) {

        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Outlined.DateRange,
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = yearRelease,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Outlined.DateRange,
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = duration,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

        Spacer(modifier = Modifier.width(12.dp))

        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = Icons.Outlined.Warning,
            contentDescription = null,
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = genre,
            color = Color.Black,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
        )

    }

}


@Preview
@Composable
fun DetailsMovieContentPrev() {
    DetailsMovieContent(
        title = "TEST",
        description = "Test Description",
        imageBackdrop = "https://image.tmdb.org/t/p/w500/vViRXFnSyGJ2fzMbcc5sqTKswcd.jpg",
        genres = listOf(GenreDomain(name = "Action")),
        releaseDate = "2024-12-12",
        runtime = "118min",
        onClickBack = {}
    )
}