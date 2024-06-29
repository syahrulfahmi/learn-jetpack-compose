package com.sf.tempconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.sf.tempconverter.ui.theme.LearnJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        StatefulTemperatureInput()
                        ConverterApp()
                        TwoWayConverterApp()
                    }
                }
            }
        }
    }
}

@Composable
fun StatefulTemperatureInput(
    modifier: Modifier = Modifier,
) {
    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.stateful_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newInput ->
                input = newInput
                output = convertToFahrenheit(newInput)
            },
        )
        Text(stringResource(R.string.temperature_fahrenheit, output))
    }

}


@Composable
fun ConverterApp() {
    var input by remember {
        mutableStateOf("")
    }
    var output by remember {
        mutableStateOf("")
    }
    StatelessTemperatureInput(input = input, output = output, onValueChange = {
        input = it
        output = convertToFahrenheit(input)
    })
}

@Composable
fun StatelessTemperatureInput(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.stateless_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
        Text(stringResource(R.string.temperature_fahrenheit, output))
    }
}

@Composable
fun TwoWayConverterApp() {
    Column {
        var celsius by remember { mutableStateOf("") }
        var fahrenheit by remember { mutableStateOf("") }
        GeneralTempInput(scale = Scale.CELSIUS, input = celsius, onValueChange = {
            celsius = it
            fahrenheit = convertToCelsius(celsius)

        })
        GeneralTempInput(scale = Scale.FAHRENHEIT, input = fahrenheit, onValueChange = {
            fahrenheit = it
            celsius = convertToFahrenheit(fahrenheit)
        })
    }
}

@Composable
fun GeneralTempInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
        OutlinedTextField(
            value = input,
            label = { Text(stringResource(R.string.enter_celsius, scale.scaleName)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
    }
}

private fun convertToFahrenheit(celsius: String) =
    celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()

private fun convertToCelsius(fahrenheit: String) =
    fahrenheit.toDoubleOrNull()?.let {
        (it - 32) * 5 / 9
    }.toString()

enum class Scale(val scaleName: String) {
    CELSIUS("celsius"),
    FAHRENHEIT("fahrenheit");
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnJetpackComposeTheme {
        Column {
            StatefulTemperatureInput()
            ConverterApp()
            TwoWayConverterApp()
        }
    }
}