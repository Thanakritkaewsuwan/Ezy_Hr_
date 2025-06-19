package com.example.ezy_hr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ezy_hr.ui.theme.Ezy_hrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ezy_hrTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "App version 1.0",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
        //Earth
    )

    val myOptions = listOf(
        MyOption(1, "แมว"),
        MyOption(2, "หมา"),
        MyOption(3, "นก")
    )

    Material3DropdownMenu(
        options = myOptions,
        onOptionSelected = { selected ->
            Log.d("Dropdown", "เลือก: ${selected.label} (id: ${selected.id})")
        }
    )

}

data class MyOption(
    val id: Int,
    val label: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Material3DropdownMenu(
    options: List<MyOption>,
    onOptionSelected: (MyOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options.first()) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedOption.label,
            onValueChange = {},
            readOnly = true,
            label = { Text("เลือกตัวเลือก") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.label) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                        onOptionSelected(option)
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ezy_hrTheme {
        Greeting("Android")
    }
}