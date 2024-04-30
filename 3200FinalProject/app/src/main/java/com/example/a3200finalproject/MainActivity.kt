package com.example.a3200finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.background(brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Red,
                    Color.Magenta,
                    Color.Blue,
                    Color.Cyan,
                    Color.Green,
                    Color.Yellow,
                    Color.Red
                )
            ))) {
                val artObject = remember { mutableStateOf<ArtObject?>(null) }
                val question = remember { mutableStateOf<Question?>(null) }
                val score = remember { mutableStateOf(0) }
                val scope = rememberCoroutineScope()
                val numQuestionsInput = remember { mutableStateOf("") }
                val numQuestions = remember { mutableStateOf<Int?>(null) }
                val currentQuestion = remember { mutableStateOf(0) }
                val quizEnded = remember { mutableStateOf(false) }

                if (!quizEnded.value) {
                    if (numQuestions.value == null) {
                        // Input for number of questions
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            TextField(
                                value = numQuestionsInput.value,
                                onValueChange = { numQuestionsInput.value = it },
                                label = { Text("Number of Questions", color = Color.Black, style = TextStyle(fontSize = 20.sp)) },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                numQuestions.value = numQuestionsInput.value.toIntOrNull()
                                if (numQuestions.value != null) {
                                    loadNextQuestion(scope, artObject, question)
                                }
                            }) {
                                Text("Submit", style = TextStyle(fontSize = 20.sp))
                            }
                        }
                    } else {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            MainActivityArtObjectView(artObject.value)

                            if (question.value != null) {
                                BasicText(question.value!!.text, style = TextStyle(fontSize = 20.sp))

                                Button(onClick = {
                                    if (question.value!!.correctAnswer) score.value++
                                    currentQuestion.value++
                                    if (currentQuestion.value < numQuestions.value!!) {
                                        loadNextQuestion(scope, artObject, question)
                                    } else {
                                        quizEnded.value = true
                                    }
                                }) {
                                    Text("True", style = TextStyle(fontSize = 20.sp))
                                }

                                Button(onClick = {
                                    if (!question.value!!.correctAnswer) score.value++
                                    currentQuestion.value++
                                    if (currentQuestion.value < numQuestions.value!!) {
                                        loadNextQuestion(scope, artObject, question)
                                    } else {
                                        quizEnded.value = true
                                    }
                                }) {
                                    Text("False", style = TextStyle(fontSize = 20.sp))
                                }

                                Button(onClick = {
                                    currentQuestion.value++
                                    if (currentQuestion.value < numQuestions.value!!) {
                                        loadNextQuestion(scope, artObject, question)
                                    } else {
                                        quizEnded.value = true
                                    }
                                }) {
                                    Text("Image did not render", style = TextStyle(fontSize = 20.sp))
                                }

                                BasicText("Score: ${score.value} / ${numQuestions.value}", style = TextStyle(fontSize = 20.sp))
                            } else {
                                BasicText("Loading...", style = TextStyle(fontSize = 20.sp))
                            }
                        }
                    }
                } else {
                    // Score screen
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        BasicText("Quiz Ended", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                        BasicText("Your score: ${score.value} / ${numQuestions.value}", style = TextStyle(fontSize = 20.sp))
                    }
                }
            }
        }
    }

    private fun loadNextQuestion(
        scope: CoroutineScope,
        artObject: MutableState<ArtObject?>,
        question: MutableState<Question?>
    ) {
        scope.launch {
            var success = false
            while (!success) {
                try {//
                    val randomId1 = Random.nextInt(40, 1000)
                    val randomId2 = Random.nextInt(40, 1000)
                    val artObject1 = RetrofitInstance.metApiService.getArtObject(randomId1)
                    val artObject2 = RetrofitInstance.metApiService.getArtObject(randomId2)
                    artObject.value = artObject1
                    question.value = generateQuestion(artObject1, artObject2)
                    success = true
                } catch (e: HttpException) {
                    if (e.code() != 404) {
                        throw e
                    }
                }
            }
        }
    }

    private fun generateQuestion(artObject1: ArtObject?, artObject2: ArtObject?): Question? {
        if (artObject1 == null || artObject2 == null) return null

        val correctAnswer = Random.nextBoolean()
        val text = if (correctAnswer) {
            "This art object is titled '${artObject1.title}'."
        } else {
            "This art object is titled '${artObject2.title}'."
        }

        return Question(text, correctAnswer)
    }
}

@Composable
fun MainActivityArtObjectView(artObject: ArtObject?) {
    if (artObject != null) {
        val painter = rememberImagePainter(
            data = artObject.primaryImage,
            builder = {
                crossfade(true)
//                error(R.drawable.ic_error)
            }
        )
        Image(
            painter = painter,
            contentDescription = artObject.title,
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        )
    } else {
        BasicText("Loading...")
    }
}