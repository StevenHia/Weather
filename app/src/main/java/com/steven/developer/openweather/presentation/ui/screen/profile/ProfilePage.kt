package com.steven.developer.openweather.presentation.ui.screen.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.steven.developer.openweather.R
import com.steven.developer.openweather.data.local.profile.entity.UserEntity
import com.steven.developer.openweather.presentation.viewmodel.ProfileViewModel
import com.steven.developer.openweather.utils.Resource

@Composable
fun ProfilePage(userId: Int, profileViewModel: ProfileViewModel = hiltViewModel()){
    val profileState = profileViewModel.profileState.value

    LaunchedEffect(Unit) {
        Log.d("","userId: $userId")
        profileViewModel.loadProfile(userId)
    }
    Scaffold { innerPadding ->
        BackgroundImage()
        Box(Modifier.padding(innerPadding)){
            when(profileState){
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if(profileState.data != null) {
                        Content(userEntity = profileState.data.copy(), profileViewModel)
                    }
                }
                is Resource.Error -> {}
                is Resource.StandBy -> {}
            }

        }
    }
}

@Composable
private fun Content(userEntity: UserEntity, profileViewModel: ProfileViewModel) {
    var nameUser by remember { mutableStateOf(userEntity.userName) }
    var passwordUser by remember { mutableStateOf(userEntity.userPassword) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = nameUser, onValueChange = { nameUser = it }, label = { Text("Nama") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = passwordUser,
            onValueChange = { passwordUser = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            profileViewModel.updateProfile(userEntity.copy(userName = nameUser, userPassword = passwordUser))
        }) {
            Text("Simpan")
        }
    }
}

@Composable
private fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
