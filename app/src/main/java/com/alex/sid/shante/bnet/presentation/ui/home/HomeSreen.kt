package com.alex.sid.shante.bnet.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alex.sid.shante.bnet.R
import com.alex.sid.shante.bnet.presentation.ui.home.components.DrugsList
import com.alex.sid.shante.bnet.ui.theme.roboto

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen() {

    val viewModel: HomeScreenViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            SearchAppBar(
                text = "",
                onTextChange = { request ->
                    viewModel.updateSearchField(request)
                },
                onSearchClicked = { request ->
                    viewModel.updateSearchField(request)
                }
            )
        },
    ) {
        DrugsList(viewModel)
    }

}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit
) {

    val bgColor = MaterialTheme.colors.primary
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var isSearchFieldShowed by remember { mutableStateOf(false) }
    val textFieldBgColor = if (isSearchFieldShowed) Color.White else bgColor

    var value by remember { mutableStateOf("") }

    TopAppBar(
        modifier = Modifier.height(70.dp),
        title = {  },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .focusRequester(focusRequester),
                value = if (!isSearchFieldShowed) stringResource(R.string.list) else value,
                onValueChange = {
                    value = it
                    onTextChange(it)
                },
                textStyle = TextStyle(
                    color = if (!isSearchFieldShowed) Color.White else Color.Black,
                    fontFamily = roboto,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchClicked(text) }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = textFieldBgColor,
                    cursorColor = Color.Black.copy(alpha = ContentAlpha.medium),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(4.dp)
            )
            IconButton(
                modifier = Modifier,
                onClick = {
                    isSearchFieldShowed = !isSearchFieldShowed
                    if (!isSearchFieldShowed) {
                        value = ""
                        onTextChange("")
                        focusManager.clearFocus()
                    } else focusRequester.requestFocus()
                }
            ) {
                if (isSearchFieldShowed) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = stringResource(R.string.cancel_button),
                        tint = Color.White
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = stringResource(R.string.search_button),
                        tint = Color.White
                    )
                }
            }
        }
    )
}


