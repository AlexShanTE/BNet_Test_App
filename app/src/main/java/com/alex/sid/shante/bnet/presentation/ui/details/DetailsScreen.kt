package com.alex.sid.shante.bnet.presentation.ui.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alex.sid.shante.bnet.BuildConfig
import com.alex.sid.shante.bnet.R
import com.alex.sid.shante.bnet.presentation.ui.home.SearchAppBar
import com.alex.sid.shante.bnet.presentation.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavController,
    drugsId: Int?
) {
    Scaffold(
        topBar = {
            SearchAppBar(
                text = "",
                isSearchingFieldEnabled = false,
                onBackPressed = { navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(navController.graph.id) {inclusive = true}
                } },
                onTextChange = {},
                onSearchClicked = { }
            )
        },
    ) {
        DetailsCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 40.dp),
            drugsId = drugsId
        )
    }
}

@Composable
fun DetailsCard(
    drugsId: Int?,
    modifier: Modifier = Modifier,
) {

    val viewModel: DetailsViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    if (drugsId !== null) viewModel.getDetails(drugsId)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    modifier = Modifier.size(28.dp),
                    model = BuildConfig.BASE_URL + state.drugs.categories.icon,
                    contentDescription = stringResource(R.string.category_image)
                )
                Spacer(modifier = Modifier.width(56.dp))
                AsyncImage(
                    modifier = Modifier
                        .height(183.dp)
                        .width(120.dp),
                    model = BuildConfig.BASE_URL + state.drugs.image,
                    contentDescription = stringResource(id = R.string.drugs_image)
                )
                Spacer(modifier = Modifier.width(56.dp))
                Image(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = stringResource(R.string.star)
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = state.drugs.name,
            style = MaterialTheme.typography.h2
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = state.drugs.description,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.makeToast(context,"Button clicked!!!") }
        ) {
            Image(painter = painterResource(id = R.drawable.ic_place), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.where_to_buy),
                style = MaterialTheme.typography.h1
            )
        }
    }
}