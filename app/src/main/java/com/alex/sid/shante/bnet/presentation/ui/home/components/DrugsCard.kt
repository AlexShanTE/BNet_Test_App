package com.alex.sid.shante.bnet.presentation.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alex.sid.shante.bnet.BuildConfig
import com.alex.sid.shante.bnet.R
import com.alex.sid.shante.bnet.domain.models.Drugs

@Composable
fun DrugsCard(
    modifier: Modifier = Modifier,
    drugs: Drugs,
    onCardClick: (Int) -> Unit
) {
    Card(
        modifier = modifier
            .height(300.dp)
            .width(150.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onCardClick(drugs.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            AsyncImage(
                modifier = modifier
                    .height(132.dp)
                    .width(78.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally),
                model = BuildConfig.BASE_URL + drugs.image,
                contentDescription = stringResource(R.string.drugs_image),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = drugs.name,
                style = MaterialTheme.typography.h1,

                )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = drugs.description,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}