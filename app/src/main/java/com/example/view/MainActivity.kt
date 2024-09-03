package com.example.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.view.ui.theme.ViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewTheme {
                App()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun App(viewModel: DiceRollViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val paddingBetweenElements = 10.dp

        val uiState = viewModel.uiState.collectAsStateWithLifecycle()

        Row {
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .padding(end = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "With ViewModel",
                    modifier = Modifier.padding(paddingBetweenElements),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "firstValue: " + uiState.value.firstValue.toString(),
                    modifier = Modifier
                        .padding(paddingBetweenElements)
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "secondValue: " + uiState.value.secondValue.toString(),
                    modifier = Modifier
                        .padding(paddingBetweenElements)
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "count: " + uiState.value.numberOfRolls.toString(),
                    modifier = Modifier
                        .padding(paddingBetweenElements)
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.primary
                )

                Button(
                    onClick = { viewModel.rollDice() },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary,
                        disabledContentColor = MaterialTheme.colorScheme.onTertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Text("Roll the dice", color = MaterialTheme.colorScheme.onTertiary)
                }

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val firstValue = remember { mutableIntStateOf(0) }
                val secondValue = remember { mutableIntStateOf(0) }
                val numberOfRolls = remember { mutableIntStateOf(0) }

                fun rollDice() {
                    firstValue.intValue = (1..6).random()
                    secondValue.intValue = (1..6).random()
                    numberOfRolls.intValue++
                }

                Text(
                    "Without ViewModel",
                    modifier = Modifier.padding(paddingBetweenElements),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "firstValue: " + firstValue.intValue.toString(),
                    modifier = Modifier
                        .padding(paddingBetweenElements)
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "secondValue: " + secondValue.intValue.toString(),
                    modifier = Modifier
                        .padding(paddingBetweenElements)
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    "count: " + numberOfRolls.intValue.toString(),
                    modifier = Modifier
                        .padding(paddingBetweenElements)
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.primary
                )

                Button(
                    onClick = { rollDice() },
                    colors = ButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary,
                        disabledContentColor = MaterialTheme.colorScheme.onTertiary,
                        disabledContainerColor = MaterialTheme.colorScheme.onTertiary
                    )
                ) {
                    Text("Roll the dice", color = MaterialTheme.colorScheme.onTertiary)
                }
            }
        }
    }
}