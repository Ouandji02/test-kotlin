package com.example.test.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.R
import com.example.test.ui.theme.TestTheme
import kotlinx.coroutines.flow.collect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = viewModel<MainViewModel>()
                    val context = LocalContext.current
                    LaunchedEffect(key1 = context) {
                        viewModel.validateEvents.collect { event ->
                            when (event) {
                                is MainViewModel.ValidationEvent.Success -> Toast.makeText(
                                    context,
                                    "Registration success",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }
                    }
                    Column() {
                        Greeting(viewModel = viewModel)
                        /*LazyColumn() {
                            items(users) { user ->
                                DisplayUser(user = user)
                            }
                        }*/
                    }
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Greeting(viewModel: MainViewModel) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(elevation = 2.dp, modifier = Modifier.padding(20.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Formulaire", style = MaterialTheme.typography.h4)
                    Spacer(modifier = Modifier.fillMaxHeight(.05f))
                    OutlinedTextField(
                        value = viewModel.state.value.email,
                        onValueChange = { viewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
                        label = { Text(text = "Entrer le nom") },
                        isError = state.emailError != null
                    )
                    if (state.emailError != null) Text(
                        text = state.emailError!!,
                        color = MaterialTheme.colors.error
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(.05f))
                    OutlinedTextField(
                        value = viewModel.state.value.password,
                        onValueChange = { viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it)) },
                        label = { Text(text = "Entrer le password") },
                        isError = state.passwordError != null,
                        visualTransformation = PasswordVisualTransformation(),
//                        trailingIcon = {
//                            IconButton(onClick = { viewModel.changeVisibility() }) {
//                                Icon(
//                                    painter = painterResource(id = if (!viewModel.visibility) R.drawable.visibility_off else R.drawable.visibility),
//                                    contentDescription = ""
//                                )
//                            }
//                        }
                    )
                    if (state.passwordError != null) Text(
                        text = state.passwordError!!,
                        color = MaterialTheme.colors.error
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(.05f))
                    OutlinedTextField(
                        value = viewModel.state.value.repeatPassword,
                        onValueChange = {
                            viewModel.onEvent(
                                RegistrationFormEvent.RepeatPasswordChanged(
                                    it
                                )
                            )
                        },
                        label = { Text(text = "Entrer le confirmPassword") },
                        isError = state.repeatPasswordError != null,
                        visualTransformation = PasswordVisualTransformation(),
//                        trailingIcon = {
//                            IconButton(onClick = { viewModel.changeVisibility() }) {
//                                Icon(
//                                    painter = painterResource(id = if (!viewModel.visibility) R.drawable.visibility_off else R.drawable.visibility),
//                                    contentDescription = ""
//                                )
//                            }
//                        }
                    )
                    if (state.repeatPasswordError != null) Text(
                        text = state.repeatPasswordError!!,
                        color = MaterialTheme.colors.error
                    )
                    Spacer(modifier = Modifier.fillMaxHeight(.05f))
                    Button(onClick = { viewModel.onEvent(RegistrationFormEvent.Submit) }) {
                        Text(text = "Authentifier")
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayUser(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = user.name)
        Text(text = user.password)
    }
}

data class User(var name: String = "", var password: String = "") {
    fun toEncode(): User {
        password = password.hashCode().toString()
        return User(name = name, password = password)
    }
}