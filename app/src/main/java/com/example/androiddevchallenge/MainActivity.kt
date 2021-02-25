/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.lang.reflect.Modifier

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = Repo()
        setContent {
            petList(pets = repo.getListOfPets())
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Ready... Set... GO!")
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

@Composable
fun petList(pets:List<Pet>){
    MaterialTheme{
        val typography = MaterialTheme.typography
        val listState = rememberLazyListState()
        LazyColumn(content = {
            items(pets) { pets ->
                PetListItem(pet = pets,typography = typography)
            }
        }, contentPadding = PaddingValues(bottom = 80.dp),
            state = listState
        )
    }
}

@Composable
fun PetListItem(pet: Pet,typography: Typography) {
    Column {
        pet.image?.let {
            Image(
                painter = painterResource(it),
                contentDescription = null,
                modifier = androidx.compose.ui.Modifier
                    .height(180.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Text(pet.name)
        Text(text = pet.age,style = typography.h6)
        Text(text = pet.aboutMe,style = typography.body2)
        Text(text = pet.breed,style = typography.body2)
    }
}

data class Pet(val name:String,
                val breed:String,
                val image:Int?=null,
                val age:String,
                val aboutMe:String,
                val sex:String)

class Repo(){
    fun getListOfPets(): ArrayList<Pet> {
        var pet = Pet("bruno","retriever",age = "12",aboutMe = "i am very friendly",sex = "Female",image = R.drawable.ic_launcher_background)
        var pets = arrayListOf<Pet>()
        pets.add(pet)
        return pets
    }
}





