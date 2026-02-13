package com.example.recursivegrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recursivegrid.logic.GridCell
import com.example.recursivegrid.logic.GridInteractor
import com.example.recursivegrid.ui.theme.RecursiveGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecursiveGridTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GridScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}



@Composable
fun GridScreen(modifier: Modifier = Modifier) {
    val interactor = remember { GridInteractor() }
    val gridState = interactor.gridState

    androidx.compose.foundation.layout.Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ) {
        Text(
            text = "Recursive Grid",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(16.dp), 
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            content = {
                itemsIndexed(gridState) { index, cell ->
                    GridBox(
                        cell = cell,
                        onClick = { interactor.onBoxClicked(index) }
                    )
                }
            }
        )

        androidx.compose.material3.Button(
            onClick = { interactor.resetGrid() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Reset")
        }
    }
}

@Composable
fun GridBox(cell: GridCell, onClick: () -> Unit) {
    val count = cell.value
    val isLocked = cell.isLocked
    val isEven = count % 2 == 0
    

    val backgroundColor = when {
        isLocked -> Color.Red
        isEven -> Color(0xFFE0E0E0)
        else -> Color(0xFF1A237E)
    }
    
    val textColor = when {
        isLocked -> Color.White
        isEven -> Color.Black
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(1f)
            .drawBehind {

                drawRoundRect(
                    color = Color.Black,
                    topLeft = Offset(2.dp.toPx(), 2.dp.toPx()),
                    size = size,
                    cornerRadius = CornerRadius(4.dp.toPx())
                )
            }
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .clickable(enabled = !isLocked) { onClick() }
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = count.toString(),
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridScreenPreview() {
    RecursiveGridTheme {
        GridScreen()
    }
}