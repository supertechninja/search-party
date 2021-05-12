package com.mcwilliams.searchparty.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mcwilliams.searchparty.theme.QuoDbTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: QuoDbViewModel by viewModels()

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuoDbTheme {
                QuoDbScaffold(viewModel)
            }
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun QuoDbScaffold(viewModel: QuoDbViewModel) {
    val movies by viewModel.movies.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchTerm by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchTerm,
            onValueChange = { searchTerm = it },
            label = {
                Text(
                    text = "Search Movie Quote"
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                viewModel.search(searchTerm.text)
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        movies?.let {
            LazyColumn() {
                items(it.docs) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = "${it.title} (${it.year})",
                                        style = MaterialTheme.typography.h6
                                    )
                                    Text(
                                        text = "\"${it.phrase}\"",
                                        style = MaterialTheme.typography.subtitle1
                                    )

                                    val hours = (it.time / (1000 * 60 * 60)) % 24
                                    val minutes = (it.time / (1000 * 60)) % 60
                                    // long seconds = (milliseconds / 1000);
                                    val seconds = (it.time / (1000)) % 60

                                    Text(
                                        text = "${hours.formatToString()}:${minutes.formatToString()}:${seconds.formatToString()}",
                                        style = MaterialTheme.typography.subtitle2
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    Log.d("TAG", "WeatherBottomSheetScaffold: ${movies?.docs?.get(0)}")
}

fun Long.formatToString(): String {
    return String.format("%02d", this)
}